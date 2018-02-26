package cn.ltwc.bitmaputils.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

import cn.ltwc.bitmaputils.BitMapUtil;
import cn.ltwc.bitmaputils.R;
import cn.ltwc.bitmaputils.ScreenUtils;

/**
 * GlideUtil
 * Created by admin on 2017/3/21.
 */

public class GlideUtil {

    public static void loadImg(Activity activity, String url, ImageView targetImg) {
        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    public static void loadImg(Context context, String url, ImageView targetImg) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    public static void loadImg(Fragment fragment, String url, ImageView targetImg) {
        Glide.with(fragment).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    public static void loadImg(android.app.Fragment fragment, String url, ImageView targetImg) {
        Glide.with(fragment).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    public static void loadImgWithErrorShow(Activity activity, String url, int errorResourceId, ImageView targetImg) {
        Glide.with(activity).load(url).error(errorResourceId).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    public static void loadImgWithErrorShow(Context context, String url, int errorResourceId, ImageView targetImg) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).error(errorResourceId).into(targetImg);
    }

    public static void loadImgWithErrorShow(Fragment fragment, String url, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).error(errorResourceId).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    public static void loadImgWithErrorShow(android.app.Fragment fragment, String url, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).error(errorResourceId).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Activity activity, String url, int listenerType, int errorResourceId, ImageView targetImg) {
        Glide.with(activity).load(url).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Context context, String url, int listenerType, int errorResourceId, ImageView targetImg) {
        Glide.with(context).load(url).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Fragment fragment, String url, int listenerType, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(android.app.Fragment fragment, String url, int listenerType, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Activity activity, String url, int listenerType, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(activity).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Context context, String url, int listenerType, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(context).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Fragment fragment, String url, int listenerType, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(android.app.Fragment fragment, String url, int listenerType, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }


    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Activity activity, String url, int listenerType, View bar, int errorResourceId, ImageView targetImg) {
        Glide.with(activity).load(url).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Context context, String url, int listenerType, View bar, int errorResourceId, ImageView targetImg) {
        Glide.with(context).load(url).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Fragment fragment, String url, int listenerType, View bar, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(android.app.Fragment fragment, String url, int listenerType, View bar, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Activity activity, String url, int listenerType, View bar, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(activity).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Context context, String url, int listenerType, View bar, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(context).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar)).diskCacheStrategy(DiskCacheStrategy.NONE).into(new GlideDrawableImageViewTarget(targetImg, 1));
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Fragment fragment, String url, int listenerType, View bar, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(android.app.Fragment fragment, String url, int listenerType, View bar, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }


    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Activity activity, String url, int listenerType, ImageView.ScaleType scaleType, View bar, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(activity).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar).setScaleType(scaleType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(new GlideDrawableImageViewTarget(targetImg, 1));
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Context context, String url, int listenerType, ImageView.ScaleType scaleType, View bar, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(context).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar).setScaleType(scaleType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(Fragment fragment, String url, int listenerType, ImageView.ScaleType scaleType, View bar, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar).setScaleType(scaleType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListener(android.app.Fragment fragment, String url, int listenerType, ImageView.ScaleType scaleType, View bar, int preResourceId, int errorResourceId, ImageView targetImg) {
        Glide.with(fragment).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType, bar).setScaleType(scaleType)).diskCacheStrategy(DiskCacheStrategy.NONE).into(targetImg);
    }

    @SuppressWarnings("unchecked")
    public static void loadImgWithListenerCenterCrop(Context context, String url, int listenerType, int errorResourceId, int preResourceId, ImageView targetImg) {
        Glide.with(context).load(url).placeholder(preResourceId).error(errorResourceId).listener(new GlideListener(targetImg, listenerType)).diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().into(targetImg);
    }

    /**
     * 加载图片使用不同的高度
     *
     * @param context    context
     * @param urlPath    图片地址
     * @param img        加载图片的容器
     * @param marginWith 容器的margin
     */
    public static void loadImgWithDifferentHeight(final Context context, final String urlPath, final ImageView img, final int marginWith) {
        String tag = (String) img.getTag();
        if (tag == null || !tag.equals(urlPath)) {
            ViewGroup.LayoutParams params = img.getLayoutParams();
            float d = (((float) ScreenUtils.getScreenWith(context) - BitMapUtil.dip2px(context, marginWith)) / 1024);
            params.height = (int) (d * 628);
            img.setImageResource(R.drawable.pre_load);
        }
        Glide.with(context).load(urlPath).diskCacheStrategy(DiskCacheStrategy.RESULT)
                .dontAnimate()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        //计算 修改ImageView的高
                        ViewGroup.LayoutParams params = img.getLayoutParams();
                        float d = (((float) ScreenUtils.getScreenWith(context) - BitMapUtil.dip2px(context, marginWith)) / resource.getIntrinsicWidth());
                        params.height = (int) (d * resource.getIntrinsicHeight());
                        img.setTag(urlPath);
                        img.setImageDrawable(resource);
                    }
                });
    }

    /**
     * 加载图片使用不同的高度
     *
     * @param context    context
     * @param urlPath    图片地址
     * @param img        加载图片的容器
     * @param marginWith 容器的margin
     */
    public static void loadImgWithDifferentHeight(final Context context, final String urlPath, final ImageView img, final int marginWith, final int with) {
        String tag = (String) img.getTag();
        if (tag == null || !tag.equals(urlPath)) {
            img.setBackgroundColor(Color.parseColor("#ffffff"));
            (img).setScaleType(ImageView.ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams params = img.getLayoutParams();
            float d = (((float) 2 * with - BitMapUtil.dip2px(context, marginWith)) / 1024);
            params.height = (int) (d * 628);
            img.setImageResource(R.drawable.pre_load);
        }
        Glide.with(context).load(urlPath).diskCacheStrategy(DiskCacheStrategy.RESULT)
                .dontAnimate()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        //计算 修改ImageView的高
                        img.setBackgroundColor(Color.parseColor("#ffffff"));
                        (img).setScaleType(ImageView.ScaleType.FIT_CENTER);
                        ViewGroup.LayoutParams params = img.getLayoutParams();
                        float d = (((float) with - BitMapUtil.dip2px(context, marginWith)) / resource.getIntrinsicWidth());
                        params.height = (int) (d * resource.getIntrinsicHeight());
                        img.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        String tag = (String) img.getTag();
                        if (tag != null && tag.equals(urlPath)) {
                            img.setBackgroundColor(Color.parseColor("#e1e1e1"));
                            (img).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            img.setImageResource(R.drawable.load_failed2);
                        }
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        img.setTag(urlPath);
                    }
                });
    }

    /**
     * 清除图片磁盘缓存
     */
    private static void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    private static void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片所有缓存
     */
    public static void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        String ImageExternalCatchDir = context.getExternalCacheDir() + ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        deleteFolderFile(ImageExternalCatchDir, true);
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    private static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
