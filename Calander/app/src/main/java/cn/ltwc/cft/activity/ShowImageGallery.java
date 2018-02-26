package cn.ltwc.cft.activity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.ltwc.bitmaputils.gallery.HackyViewPager;
import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.ShowIamgeGalleryAdapter;
import cn.ltwc.cft.beans.TiangouBean;
import cn.ltwc.cft.data.Constant;

import cn.ltwc.cft.utils.DownLoadUtil;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.utils.ToastUtil;
import rx.functions.Action1;

import static cn.ltwc.cft.data.Constant.VIEW_PAGER_OFF_SCREEN_PAGE_LIMIT;

/**
 * 显示大图片（cn.ltwc.cft.gallery包下面就是显示该效果的文件）
 *
 * @author LZL
 */
public class ShowImageGallery extends BaseActivity implements
        OnPageChangeListener, Action1<Boolean>, View.OnClickListener {
    private HackyViewPager pager;
    private List<TiangouBean> imgList;// 图片信息的集合
    private int position;// 当前点击进来的是第几张
    private ShowIamgeGalleryAdapter adapter;
    public static ShowImageGallery instance;
    private View title, setWallpaper, downLoad, share;
    private TextView dec;

    public ShowImageGallery() {
        super(R.layout.activity_show_image_gallery);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        pager = (HackyViewPager) findViewById(R.id.img_pager);
        title = findViewById(R.id.title);
        setWallpaper = findViewById(R.id.set_wallpaper);
        downLoad = findViewById(R.id.down_load);
        share = findViewById(R.id.share);
        dec = (TextView) findViewById(R.id.dec);
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        imgList = getIntent().getParcelableArrayListExtra(Constant.IMGURL_LIST);
        position = getIntent().getIntExtra(Constant.POSITION, 0);
        instance = this;
    }

    @Override
    public void bindView() {
        // TODO Auto-generated method stub
        pager.setPageMargin(12);
        adapter = new ShowIamgeGalleryAdapter(this, imgList, this);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
        pager.setCurrentItem(position);
        dec.setText(Html.fromHtml(imgList.get(position).getTitle()));
        pager.setOffscreenPageLimit(VIEW_PAGER_OFF_SCREEN_PAGE_LIMIT);
        setWallpaper.setOnClickListener(this);
        downLoad.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub
        position = pager.getCurrentItem();
        dec.setText(Html.fromHtml(imgList.get(position).getTitle()));
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        instance = null;
    }

    /**
     * 点击了一下的回调
     *
     * @param aBoolean
     */
    @Override
    public void call(Boolean aBoolean) {
        titleShow();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    titleDisMiss();
                    break;
            }
        }
    };

    public void titleShow() {
        if (title.getVisibility() == View.GONE) {
            title.setVisibility(View.VISIBLE);
            Animation in = AnimationUtils.loadAnimation(this, R.anim.gallery_title_in);
            in.setAnimationListener(animationListener);
            title.setAnimation(in);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.handleMessage(msg);
                }
            }, 2000);
        }
        if (dec.getVisibility() == View.GONE) {
            dec.setVisibility(View.VISIBLE);
        }
    }

    public void titleDisMiss() {
        title.setVisibility(View.GONE);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.gallery_title_out);
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

    @Override
    public void onClick(View v) {
        //title.setVisibility(View.GONE);
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
     *
     * @param position
     */
    public void setImgToWallpaper(int position) {
        MyApplication.getInstance().showProgressDialog();
        DownLoadUtil.downLoad(imgList.get(position).getImg(), new Action1<String>() {
            @Override
            public void call(String s) {
                if (!TextUtils.isEmpty(s)) {
                    WallpaperManager mWallManager = WallpaperManager.getInstance(ShowImageGallery.this);
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
     *
     * @param position
     */
    public void downLoadImg(int position) {
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
     *
     * @param position
     */
    public void share(int position) {
        MyApplication.getInstance().showProgressDialog();
        DownLoadUtil.downLoad(imgList.get(position).getImg(), new Action1<String>() {
            @Override
            public void call(String s) {
                MyApplication.getInstance().disMissProgressDialog();
                if (!TextUtils.isEmpty(s)) {
                    HLUtil.toMyShare(ShowImageGallery.this,
                            Constant.SHARE_TYPE_IMG,
                            "\t\t\t\t\t\t\t王朝黄历\n\t\t\t\t\t图片分享\n", s);
                }
            }
        });
    }

}
