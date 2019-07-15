package cn.ltwc.cft.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.utils.LogUtil;

/**
 * 跑马灯:支持从上往下和从下往上滚动 on 2017/10/28.
 */

public class MarqueeView extends FrameLayout {

    private int mTextColor = Color.BLACK;       //文字的颜色,默认黑色
    private int mTextSize;                      //文字大小,默认16sp
    private int mInternal = 1800;               //滚动的间隔时间,默认1800毫秒
    private int mTextGravity = 1;               //文字居左,居中,居右,默认居中
    private int mFlipOrientation = 1;           //flip方向,默认是从下往上
    private int mCurrentTextIndex = 1;          //text的索引,因为有两个TextView已经设置了text,所以当前的text的index从childCount减1开始
    private int mFlipDuration = 1000;           //滚动的所耗时间,即动画的执行时间,默认1000毫秒

    private TextView mChild1;
    private TextView mChild2;
    private List<String> mData;
    private FloatEvaluator mEvaluator = new FloatEvaluator();
    private boolean isRunning;
    private boolean isAnimFinished = true;

    public MarqueeView(@NonNull Context context) {
        this(context, null);
    }

    public MarqueeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MarqueeView);
        mTextColor = array.getColor(R.styleable.MarqueeView_Color, mTextColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.MarqueeView_Size, (int) sp2Px(16));
        mInternal = array.getInt(R.styleable.MarqueeView_internal, mInternal);//滚动的间隔时间,默认1500毫秒
        mFlipDuration = array.getInt(R.styleable.MarqueeView_flipDuration, mFlipDuration);//滚动的所耗时间,即动画的执行时间,默认1000毫秒
        mFlipOrientation = array.getInt(R.styleable.MarqueeView_flipOrientation, 1);//bottomToTop=1

        int textGravityFlag = array.getInt(R.styleable.MarqueeView_textGravity, mTextGravity);
        switch (textGravityFlag) {
            case 0:
                mTextGravity = Gravity.LEFT;
                break;
            case 1:
                mTextGravity = Gravity.CENTER;
                break;
            case 2:
                mTextGravity = Gravity.RIGHT;
                break;
        }

        array.recycle();
        initChild();
    }

    private void initChild() {
        int childCount = getChildCount();
        if (childCount == 2) {
            return;
        }
        mChild1 = createTextView();
        mChild2 = createTextView();
        addView(mChild1);
        addView(mChild2);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (mFlipOrientation == 1) {
                    //bottom2top
//                    ViewCompat.setTranslationY(mChild2, getHeight());
                    mChild2.setTranslationY(getHeight());
                } else if (mFlipOrientation == 0) {
                    //top2bottom
//                    ViewCompat.setTranslationY(mChild2, -getHeight());
                    mChild2.setTranslationY(-getHeight());
                }
            }
        });
    }

    private TextView createTextView() {
        TextView textView = new TextView(getContext());
        textView.setTextColor(mTextColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mTextSize);
        textView.setGravity(Gravity.CENTER_VERTICAL | mTextGravity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public void setTextColor(int color) {
        if (mChild1 != null) {
            mChild1.setTextColor(color);
        }
        if (mChild2 != null) {
            mChild2.setTextColor(color);
        }
    }

    /**
     * 设置数据,改方法会调用start方法开始flip
     */
    public void setData(List<String> data) {
        mData = data;
        if (mData == null || mData.size() == 0) {
            return;
        }
        if (mData.size() == 1) {
            mChild1.setText(mData.get(0));
            return;
        }
        mChild1.setText(mData.get(0));
        mChild2.setText(mData.get(1));
        start();

    }

    /**
     * 开始flip
     */
    private void start() {
        if (isRunning) return;
        if (!isAnimFinished) return;

        postDelayed(animAction, mInternal);

        isRunning = true;
    }

    private float child1EndPositionY;
    private float child2EndPositionY;

    private Runnable animAction = new Runnable() {
        @Override
        public void run() {
            final float child1StartPositionY = mChild1.getTranslationY();
            final float child2StartPositionY = mChild2.getTranslationY();
            if (mFlipOrientation == 1) {
                //bottom2top
                child1EndPositionY = child1StartPositionY == 0 ? -getHeight() : 0;
                child2EndPositionY = child2StartPositionY == 0 ? -getHeight() : 0;
            } else if (mFlipOrientation == 0) {
                //top2bottom
                child1EndPositionY = child1StartPositionY == 0 ? getHeight() : 0;
                child2EndPositionY = child2StartPositionY == 0 ? getHeight() : 0;
            }

            ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float fraction = animation.getAnimatedFraction();
                    mChild1.setTranslationY(mEvaluator.evaluate(fraction, child1StartPositionY, child1EndPositionY));
                    mChild2.setTranslationY(mEvaluator.evaluate(fraction, child2StartPositionY, child2EndPositionY));
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnimFinished = false;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mCurrentTextIndex = (mCurrentTextIndex + 1) % mData.size();
                    TextView prepareFlipTextView = null;
                    if (mFlipOrientation == 1) {
                        //bottom2top
                        prepareFlipTextView = mChild1.getTranslationY() == -getHeight() ? mChild1 : mChild2;
                        prepareFlipTextView.setTranslationY(getHeight() * 2);

                    } else if (mFlipOrientation == 0) {
                        //top2bottom
                        prepareFlipTextView = mChild1.getTranslationY() == getHeight() ? mChild1 : mChild2;
                        prepareFlipTextView.setTranslationY(-getHeight() * 2);
                    }
                    prepareFlipTextView.setText(mData.get(mCurrentTextIndex));
                    isAnimFinished = true;

                    //再来一次
                    postDelayed(animAction, mInternal);
                }
            });
            animator.setDuration(mFlipDuration);
            animator.start();
        }
    };

    /**
     * 停止flip
     */
    public void stop() {
        if (isAnimFinished) {
            removeCallbacks(animAction);
            isRunning = false;
        }
    }

    public void stopNow() {
        removeCallbacks(animAction);
        isRunning = true;
    }

    /**
     * 切换flip的状态:若正在flip则停止;若停止,则开始flip
     */
    public void toggleFlip() {
        if (isRunning) {
            stop();
        } else {
            start();
        }
    }

    private float sp2Px(float spValue) {
        float scaledDensity = getContext().getResources().getDisplayMetrics().scaledDensity;
        return scaledDensity * spValue;
    }

}
