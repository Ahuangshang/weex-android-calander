package cn.ltwc.cft.helper;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * MyGestureListener
 * Created by LZL on 2017/2/21.
 */

public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        int gvFlag = 0; // 每次添加gridView到viewFlipper中时给的标记
        try {
            if (e1.getX() - e2.getX() > 120) {
                // 像左滑动
                HomeFragmentHelper.getInstance().enterNextMonth(gvFlag);
                return true;
            }
            if (e1.getX() - e2.getX() < -120) {
                // 向右滑动
                HomeFragmentHelper.getInstance().enterPrevMonth(gvFlag);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

}
