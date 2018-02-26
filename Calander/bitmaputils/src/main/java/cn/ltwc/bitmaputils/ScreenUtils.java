package cn.ltwc.bitmaputils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;

public class ScreenUtils {

    public static int getScreenHeight(Context mContext) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getScreenWith(Context mContext) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取视图的高度
     *
     * @param view
     * @return
     */
    public static int getViewHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        // ZMUtils.showMessage(c,view.getMeasuredHeight()+"");
        return view.getMeasuredHeight();
    }
}
