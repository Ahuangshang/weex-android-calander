package cn.ltwc.bitmaputils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.functions.Action1;


public class BitMapUtil {
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将布局控件变成图片
     *
     * @param view
     * @return
     */
    public static Bitmap view2Bitmap(View view) {
        final Bitmap bmp = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bmp));
        return bmp;
    }

    /**
     * 保存图片
     *
     * @param qrcodeBitmap
     * @param filePath
     * @throws IOException
     */
    public static void saveImg(Bitmap qrcodeBitmap, String filePath,
                               Action1 listener) throws IOException {
        File img = new File(filePath);
        if (!img.exists()) {
            img.mkdirs();
        } else {
            img.delete();
        }
        save(qrcodeBitmap, img, listener);
    }

    /**
     * 保存位图到指定文件
     *
     * @param bm
     * @param file
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void save(Bitmap bm, File file, Action1 listener)
            throws IOException, FileNotFoundException {
        // 如果父目录不存在 则创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 如果文件不存在 则创建文件
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.exists()) {
            file.delete();
        }
        // 保存图片到文件
        if (bm != null) {
            FileOutputStream fos = new FileOutputStream(file);
            bm.compress(CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            if (listener != null) {
                listener.call("");
            }
        }
    }

}
