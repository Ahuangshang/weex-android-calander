package cn.ltwc.cft.weex;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

import cn.ltwc.cft.R;

/**
 *
 */

public class ImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(final String url, final ImageView view, WXImageQuality quality, final WXImageStrategy strategy) {
        WXSDKManager.getInstance().postOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (view == null || view.getLayoutParams() == null) {
                    return;
                }
                if (TextUtils.isEmpty(url)) {
                    view.setImageBitmap(null);
                    return;
                }
                if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
                    return;
                }
                if (TextUtils.isEmpty(strategy.placeHolder)) {
                    if (url.contains("weather/")) {
                        Glide.with(WXEnvironment.getApplication()).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(view);
                    } else {
                        if (url.contains("api.jisuapi.com")) {
                            //不想在新闻页面加载gif图，太费内存
                            Glide.with(WXEnvironment.getApplication()).load(url).asBitmap().error(R.drawable.pre_load).listener(new MyRequestListener(view)).diskCacheStrategy(DiskCacheStrategy.RESULT).override(270, 140).into(view);

                        } else {
                            Glide.with(WXEnvironment.getApplication()).load(url).error(R.drawable.pre_load).listener(new MyRequestListener(view)).diskCacheStrategy(DiskCacheStrategy.NONE).into(view);

                        }
                    }
                } else {
                    if (url.contains("api.jisuapi.com")) {
                        //不想在新闻页面加载gif图，太费内存
                        Glide.with(WXEnvironment.getApplication()).load(url).asBitmap().placeholder(R.drawable.pre_load).error(R.drawable.pre_load).listener(new MyRequestListener(view)).diskCacheStrategy(DiskCacheStrategy.RESULT).override(270, 140).into(view);

                    } else {
                        Glide.with(WXEnvironment.getApplication()).load(url).placeholder(R.drawable.pre_load).error(R.drawable.pre_load).listener(new MyRequestListener(view)).diskCacheStrategy(DiskCacheStrategy.NONE).into(view);

                    }
                }

            }
        }, 0);
    }

    class MyRequestListener implements RequestListener {

        private ImageView imageView;

        public MyRequestListener(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public boolean onException(Exception e, Object o, Target target, boolean b) {
            imageView.setBackgroundColor(Color.parseColor("#c3c3c3"));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return false;
        }

        @Override
        public boolean onResourceReady(Object o, Object o2, Target target, boolean b, boolean b1) {
            return false;
        }
    }
}
