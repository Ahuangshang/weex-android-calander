package cn.ltwc.cft.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import cn.ltwc.bitmaputils.ScreenUtils;
import cn.ltwc.cft.R;
import cn.ltwc.cft.activity.BaseActivity;

/**
 * Created by admin on 2017/3/10.
 */

public class PopWindowUtil {
    private View contentView;
    private Context context;//锚点
    private PopupWindow pop;
    private boolean defaultShowAnimation;//true从下往上弹出动画，false没有动画 默认true
    private int animationStyle;//-1的时候使用默认的显示动画，其他值为自定义的显示动画（在anim里定义的动画）默认-1
    private int gravity;//pop显示的位置 默认是显示在底部
    private boolean backKeyDismiss;//返回键是否隐藏pop 默认是可以消失的
    private static PopWindowUtil popWindowUtil;

    private PopWindowUtil() {
    }

    public static PopWindowUtil getPopWindowUtil() {
        return popWindowUtil = popWindowUtil == null ? new PopWindowUtil() : popWindowUtil;
    }

    private PopWindowUtil(View contentView, boolean defaultShowAnimation, int animationStyle, int gravity, boolean backKeyDismiss) {
        this.contentView = contentView;
        this.defaultShowAnimation = defaultShowAnimation;
        this.animationStyle = animationStyle;
        this.gravity = gravity;
        this.backKeyDismiss = backKeyDismiss;
        this.context = contentView.getContext();
        pop = new PopupWindow(this.context);
    }

    public PopWindowUtil setBackKeyDismiss(boolean backKeyDismiss) {
        this.backKeyDismiss = backKeyDismiss;
        return popWindowUtil;
    }

    public PopWindowUtil setDefaultShowAnimation(boolean defaultShowAnimation) {
        this.defaultShowAnimation = defaultShowAnimation;
        return popWindowUtil;
    }

    public PopWindowUtil setAnimationStyle(int animationStyle) {
        this.animationStyle = animationStyle;
        return popWindowUtil;
    }

    public PopWindowUtil setGravity(int gravity) {
        this.gravity = gravity;
        return popWindowUtil;
    }

    public static PopWindowUtil make(View contentView) {
        return make(contentView, true);
    }

    public static PopWindowUtil make(View contentView, boolean defaultShowAnimation) {
        return make(contentView, defaultShowAnimation, -1);
    }

    public static PopWindowUtil make(View contentView, boolean defaultShowAnimation, int animationStyle) {
        return make(contentView, defaultShowAnimation, animationStyle, Gravity.BOTTOM);
    }

    public static PopWindowUtil make(View contentView, boolean defaultShowAnimation, int animationStyle, int gravity) {
        return make(contentView, defaultShowAnimation, animationStyle, gravity, false);
    }

    public static PopWindowUtil make(View contentView, boolean defaultShowAnimation, int animationStyle, int gravity, boolean backKeyDismiss) {
        popWindowUtil = new PopWindowUtil(contentView, defaultShowAnimation, animationStyle, gravity, backKeyDismiss);
        return popWindowUtil;
    }

    public void show() {
        if (context instanceof BaseActivity) {
            show((BaseActivity) context, contentView, defaultShowAnimation, gravity, animationStyle, backKeyDismiss);
        } else {
            show((Activity) context, contentView, defaultShowAnimation, gravity, animationStyle, backKeyDismiss);
        }
    }

    public void dismiss() {
        dismiss(defaultShowAnimation);
    }

    private void show(final Activity parent, View contentView, boolean animation, int gravity, int animationStyle, boolean backKeyDismiss) {
        if (pop.isShowing()) {
            return;
        }
        try {
            pop.setWidth(Float.valueOf(ScreenUtils.getScreenWith(context)).intValue());
            pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            pop.setContentView(contentView);
            setBackground(parent, 0.4f);
            pop.setTouchable(true);
            pop.setFocusable(true);
            if (backKeyDismiss) {
                pop.setBackgroundDrawable(new BitmapDrawable());
            } else {
                pop.setBackgroundDrawable(null);
            }
            pop.setOutsideTouchable(backKeyDismiss);
            if (animationStyle != -1) {
                pop.setAnimationStyle(animationStyle);
            } else {
                if (animation) {
                    pop.setAnimationStyle(R.style.AnimationPopupBottom);
                } else {
                    pop.setAnimationStyle(R.style.AnimationPopupBottomNoEnter);
                }
            }
            pop.update();
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                // 在dismiss中恢复透明度
                public void onDismiss() {
                    setBackground(parent, 1.0f);
                }
            });
            pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            pop.showAtLocation(parent.getWindow().getDecorView(), gravity, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setBackground(Activity parent, float alpha) {
        WindowManager.LayoutParams lp = parent.getWindow().getAttributes();
        lp.alpha = alpha;
        parent.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        if (alpha == 1.0f) {
            parent.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        parent.getWindow().setAttributes(lp);
    }

    private void dismiss(boolean animation) {
        if (pop == null || !pop.isShowing()) {
            return;
        }
        if (animation) {
            pop.setAnimationStyle(R.style.AnimationPopupBottom);
        } else {
            pop.setAnimationStyle(R.style.AnimationPopupBottomNoExit);
        }
        pop.update();
        pop.dismiss();
    }

    public boolean isShowing() {
        if (pop != null) {
            pop.isShowing();
        }
        return false;
    }
}
