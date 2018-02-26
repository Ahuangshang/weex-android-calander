package cn.ltwc.cft.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * TODO: 解决ViewPager与RecycleView滑动的冲突
 *
 * @author huangshang 2016-9-11 上午3:30:46
 * @Modified_By:
 */
public class MyViewPager extends ViewPager {
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyViewPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    private float preX = 0;

    /**
     * onInterceptTouchEvent()是ViewGroup的一个方法，
     * 目的是在系统向该ViewGroup及其各个childView触发onTouchEvent()之前对相关事件进行一次拦截
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        super.onInterceptTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            preX = event.getX();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (Math.abs(event.getX() - preX) > 10) {
                return true;
            } else {
                preX = event.getX();
            }
        }
        return false;
    }

}
