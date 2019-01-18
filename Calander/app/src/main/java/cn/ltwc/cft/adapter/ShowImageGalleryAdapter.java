package cn.ltwc.cft.adapter;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.ltwc.bitmaputils.gallery.PhotoView;
import cn.ltwc.bitmaputils.glide.GlideListener;
import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.TiangouBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.DownLoadUtil;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.utils.ToastUtil;
import rx.functions.Action1;

/**
 * ImageViewGalleryAdapter
 * used this adapter to save the memory
 * Created by LZL on 2018/3/6.
 */

public class ShowImageGalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<TiangouBean> imgList;


    public ShowImageGalleryAdapter(Context context, List<TiangouBean> imgList) {
        super();
        this.context = context;
        this.imgList = imgList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new ImageHolder(LayoutInflater.from(context).inflate(R.layout.view_imagegallery, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((ImageHolder) viewHolder).setData(position);
    }


    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Action1<Boolean> {
        PhotoView photoView;
        View bar;
        View title, setWallpaper, downLoad, share;
        TextView dec;
        int position;

        private ImageHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            photoView = (PhotoView) itemView.findViewById(R.id.img);
            bar = itemView.findViewById(R.id.par);
            title = itemView.findViewById(R.id.title);
            setWallpaper = itemView.findViewById(R.id.set_wallpaper);
            downLoad = itemView.findViewById(R.id.down_load);
            share = itemView.findViewById(R.id.share);
            dec = (TextView) itemView.findViewById(R.id.dec);
        }

        public void setData(int position) {
            this.position = position;
            GlideUtil.loadImgWithListener(context, imgList.get(position).getImg(), GlideListener.NULL, bar, R.drawable.placeholder_black, R.drawable.loading_failed, photoView);
            dec.setText(Html.fromHtml(imgList.get(position).getTitle()));
            setWallpaper.setOnClickListener(this);
            downLoad.setOnClickListener(this);
            share.setOnClickListener(this);
            photoView.setSignalTapListener(this);
        }

        @Override
        public void onClick(View v) {
            title.setVisibility(View.GONE);
            switch (v.getId()) {
                case R.id.set_wallpaper:
                    setImgToWallpaper(position);
                    break;
                case R.id.down_load:
                    downLoadImg(position);
                    break;
                case R.id.share:
                    share(position);
                    break;
            }
        }

        /**
         * 将图片设置为壁纸
         */
        private void setImgToWallpaper(int position) {
            MyApplication.getInstance().showProgressDialog();
            DownLoadUtil.downLoad(imgList.get(position).getImg(), new Action1<String>() {
                @Override
                public void call(String s) {
                    if (!TextUtils.isEmpty(s)) {
                        WallpaperManager mWallManager = WallpaperManager.getInstance(context);
                        Bitmap bitmap = null;
                        try {
                            byte[] data = getBytesFromInputStream(new FileInputStream(s));
                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            if (bitmap != null) {
                                mWallManager.setBitmap(bitmap);
                                ToastUtil.showMessageCenter("设置壁纸成功");
                            } else {
                                ToastUtil.showMessageCenter("设置壁纸失败");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            ToastUtil.showMessageCenter("设置壁纸失败");
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showMessageCenter("设置壁纸失败");
                        } finally {
                            if (bitmap != null) {
                                bitmap.recycle();
                            }
                        }
                    }
                    MyApplication.getInstance().disMissProgressDialog();
                }
            });


        }

        private byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
            ByteArrayOutputStream outstream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024]; // 用数据装
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
            // 关闭流一定要记得。
            return outstream.toByteArray();
        }

        /**
         * 下载
         */
        private void downLoadImg(int position) {
            DownLoadUtil.downLoad(imgList.get(position).getImg(), new Action1<String>() {
                @Override
                public void call(String s) {
                    if (!TextUtils.isEmpty(s)) {
                        ToastUtil.showMessageCenter("已保存到SD卡");
                    } else {
                        ToastUtil.showMessageCenter("保存失败");
                    }
                }
            });
        }


        /**
         * 分享
         */
        private void share(final int position) {
            MyApplication.getInstance().showProgressDialog();
            DownLoadUtil.downLoad(imgList.get(position).getImg(), new Action1<String>() {
                @Override
                public void call(String s) {
                    MyApplication.getInstance().disMissProgressDialog();
                    if (!TextUtils.isEmpty(s)) {
                        HLUtil.toMyShare(context,
                                Constant.SHARE_TYPE_IMG,
                                "\t\t\t\t\t\t\t王朝黄历\n\t\t\t\t\t图片分享\n" + Utils.formatHtmStr(imgList.get(position).getTitle()), s);
                    }
                }
            });
        }

        @Override
        public void call(Boolean aBoolean) {
            titleShow();
        }

        private void titleShow() {
            if (title.getVisibility() == View.GONE) {
                title.setVisibility(View.VISIBLE);
                Animation in = AnimationUtils.loadAnimation(context, R.anim.gallery_title_in);
                in.setAnimationListener(animationListener);
                title.setAnimation(in);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        titleDisMiss();
                    }
                }, 2000);
            }
            if (dec.getVisibility() == View.GONE) {
                dec.setVisibility(View.VISIBLE);
            }
        }

        private void titleDisMiss() {
            title.setVisibility(View.GONE);
            Animation out = AnimationUtils.loadAnimation(context, R.anim.gallery_title_out);
            out.setAnimationListener(animationListener);
            title.setAnimation(out);
            dec.setVisibility(View.GONE);
        }

        private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setWallpaper.setClickable(false);
                downLoad.setClickable(false);
                share.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setWallpaper.setClickable(true);
                downLoad.setClickable(true);
                share.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

    }
}
