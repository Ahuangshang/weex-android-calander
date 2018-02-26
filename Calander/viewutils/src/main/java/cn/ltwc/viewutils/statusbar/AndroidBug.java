package cn.ltwc.viewutils.statusbar;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;

import rx.functions.Action1;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;

/**
 * 解决沉浸式状态栏状态下软键盘不能顶起下面的布局的问题
 * Created by LZL on 2017/3/10.
 */

public class AndroidBug {

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private Action1<Object> layoutChangeListener;

    public static void assistActivity(Activity activity, Action1<Object> layoutChangeListener) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        if (attributes.softInputMode >= SOFT_INPUT_ADJUST_RESIZE) {
            //只有当设置了ADJUST_RESIZE的activity才进行布局监听
            new AndroidBug(activity, layoutChangeListener);
        }
    }

    private AndroidBug(Activity activity, Action1<Object> layoutChangeListener) {
        this.layoutChangeListener = layoutChangeListener;
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
        int heightDifferent = usableHeightSansKeyboard - usableHeightNow;
        if (usableHeightNow != usableHeightPrevious) {
            //usableHeightSansKeyboard当前activity所占有的高度
            if (heightDifferent > (usableHeightSansKeyboard / 4)) {
                //认为弹起了软键盘，对布局高度进行更改
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifferent;
                if (layoutChangeListener != null) {
                    layoutChangeListener.call(1);
                }
            } else {
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        } else {
            //一些下面有键盘的手机要重新设置布局的高度
            if (frameLayoutParams.height != usableHeightPrevious && heightDifferent < (usableHeightSansKeyboard / 4)) {
                frameLayoutParams.height = usableHeightPrevious;
                mChildOfContent.requestLayout();
            }
        }
    }

    /**
     * 获取activity根布局文件在屏幕中的高度，即位置
     *
     * @return
     */
    private int computeUsableHeight() {
        Rect rect = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }
}
