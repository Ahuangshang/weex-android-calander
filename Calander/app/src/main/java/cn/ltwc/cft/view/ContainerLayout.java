package cn.ltwc.cft.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import cn.ltwc.cft.R;

/**
 * Title:日历和内容的容器，处理滑动冲突和 Description:
 *
 * @author LZL
 * @date 2016/9/27.
 */
public class ContainerLayout extends LinearLayout {

    /**
     * 日历控件，viewpager
     */
    private View mHeader;

    /**
     * scrollview内容
     */
    private ViewGroup mContent;

    /**
     * 日历原始高度
     */
    private int headerOriginalHeight;
    /**
     * 滚动距离
     */
    private int scrollCountY;

    /**
     * 日历控件顶部隐藏的高度
     */
    private int hideTop = 0;
    /**
     * 日历控件底部隐藏的高度
     */
    private int hideBottom = 0;

    /**
     * 最小的滑动距离， 小于该值认为没有滑动
     */
    private int minDistance;

    /**
     * onInterceptTouchEvent最后坐标
     */
    private int mLastXIntercept;
    private int mLastYIntercept;

    /**
     * onTouchEvent的最后坐标
     */
    private int mLastX;
    private int mLastY;
    /**
     * onTouchEvent每次move产生的距离
     */
    private int deltaY;

    /**
     * 是否还在动画过渡中，为true时不分发touch事件
     */
    private boolean isInAnimation = false;

    public ContainerLayout(Context context) {
        super(context);
    }

    public ContainerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContainerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContainerLayout(Context context, AttributeSet attrs,
                           int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            init();
        }
    }

    private void init() {
        if (mHeader == null && mContent == null) {
            minDistance = getResources().getDimensionPixelOffset(
                    R.dimen.mindistance);
            int headerId = getResources().getIdentifier("flipper", "id",
                    getContext().getPackageName());
            int contentId = getResources().getIdentifier("view_content", "id",
                    getContext().getPackageName());
            if (headerId != 0 && contentId != 0) {
                mHeader = findViewById(headerId);
                mContent = (ViewGroup) findViewById(contentId);

                if (mHeader != null) {
                    headerOriginalHeight = mHeader.getMeasuredHeight();
                }
                // collapse();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isInAnimation) {
            // 如果还在动画过程中则不变
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        if (mContent == null || mHeader == null) {
            return intercepted;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastXIntercept = x;
                mLastYIntercept = y;
                // @TODO 这里看看是不是还能优化，因为还要处理日历等子view的点击事件，必须不拦截，否则点击传不下去
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastYIntercept;
                int deltaX = x - mLastXIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    // 横向移动的时候不拦截touch事件，让viewpager处理
                    intercepted = false;
                } else {
                    if (mContent.getTop() == mHeader.getBottom()) {
                        // 如果头部完全展开则直接拦截
                        if (Math.abs(deltaY) > 5) {
                            intercepted = true;
                        } else {
                            intercepted = false;
                        }
                    } else {
                        // mContent.getTop() - mHeader.getTop() == hideBottom -
                        // hideTop
                        if (mContent.getTop() - mHeader.getTop() == hideBottom
                                - hideTop) {
                            // 头部完全收缩
                            if (canChildScrollDown(mContent)) {
                                // 底部还可以继续往下滚动
                                intercepted = false;
                            } else {
                                // 底部已经滚动到了最顶，不能滚动了
                                if (deltaY > 0) {
                                    // 从上向下滚动拦截
                                    intercepted = true;
                                } else {
                                    // 从下向上滚动不拦截
                                    intercepted = false;
                                }
                            }
                        } else {
                            intercepted = true;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                intercepted = false;
                mLastYIntercept = 0;
                mLastXIntercept = 0;
                break;
        }
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean canTouched = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mLastX != 0 && mLastY != 0) {
                    // 因为 ACTION_DOWN拦截不到，所以通过mLastX，mLastY是否有值来完成
                    int deltaX = x - mLastX;
                    deltaY = y - mLastY;
                    if (Math.abs(deltaY) > Math.abs(deltaX)) {
                        moveToScroll(-deltaY);
                        canTouched = true;
                    } else {
                        canTouched = false;
                    }
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (deltaY > 0) {
                    // 从上向下滚动,判断一下最终距离
                    if (mContent.getTop() - mHeader.getTop() >= 2 * (hideBottom - hideTop)
                            || deltaY > minDistance) {
                        // 拉伸的长度足够,可以展开，如果还没有完全展开则使用动画
                        // 向下拉伸长度够了，可以展开
                        smoothExpand(-deltaY);
                    } else {
                        // 拉伸的长度不够，还是要恢复刚才的状态
                        smoothCollapse(-minDistance);
                    }

                } else {
                    // 从下向上滚动不拦截
                    if (mHeader.getScrollY() >= hideBottom - hideTop
                            || (scrollCountY >= hideBottom - hideTop && mHeader
                            .getScrollY() == 0) || deltaY < -minDistance) {
                        // 拉伸的长度足够,可以展开，如果还没有完全展开则使用动画
                        smoothCollapse(-deltaY);
                    } else {
                        // 拉伸的长度不够，还是要恢复刚才的状态
                        smoothExpand(minDistance);
                    }
                }

                mLastX = 0;
                mLastY = 0;
                canTouched = false;
                break;
        }
        return canTouched;
    }

    /**
     * 判断scrollview / listview是否能滑动
     *
     * @param view
     * @return
     */
    public boolean canChildScrollDown(View view) {
        if (Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView
                        .getChildAt(0).getTop() < absListView
                        .getPaddingTop());
            } else {
                return view.getScrollY() > 0;
            }
        } else {
            boolean canScrollVertically = view.canScrollVertically(-1);
            return canScrollVertically;
        }
    }

    /**
     * 设置当前选中日期所属行
     *
     * @param rowNum
     */
    public void setRowNum(int rowNum) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(
                R.dimen.calender_item_height);
        hideTop = rowNum * dimensionPixelSize;
        hideBottom = (rowNum + 1) * dimensionPixelSize;
    }

    /**
     * 惯性展开
     *
     * @param deltaY
     */
    private void smoothExpand(int deltaY) {
        final int distanceY = Math.min(-Math.abs(deltaY), -minDistance);
        new Thread("Thread#smoothCollapse") {
            @Override
            public void run() {
                while (scrollCountY + distanceY >= 0) {
                    isInAnimation = true;
                    post(new Runnable() {
                        public void run() {
                            scrollCountY += distanceY;
                            if (scrollCountY >= 0 && scrollCountY <= hideTop) {
                                mHeader.scrollTo(mHeader.getScrollX(),
                                        scrollCountY);
                            } else {
                                mHeader.scrollTo(mHeader.getScrollX(), hideTop);
                            }
                            LayoutParams params2 = (LayoutParams) mContent
                                    .getLayoutParams();
                            params2.setMargins(0, -scrollCountY, 0, 0);
                            mContent.setLayoutParams(params2);

                        }
                    });
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                post(new Runnable() {
                    public void run() {
                        mHeader.scrollTo(mHeader.getScrollX(), 0);
                        scrollCountY = 0;
                        LayoutParams params = (LayoutParams) mContent
                                .getLayoutParams();
                        params.setMargins(0, 0, 0, 0);
                        mContent.setLayoutParams(params);
                        isInAnimation = false;
                    }
                });
            }
        }.start();
    }

    /**
     * 惯性收缩
     *
     * @param deltaY
     */
    public void smoothCollapse(int deltaY) {
        final int distanceY = Math.max(Math.abs(deltaY), minDistance);
        new Thread("Thread#smoothCollapse") {
            @Override
            public void run() {
                while (Math.abs(scrollCountY + distanceY) <= Math
                        .abs(headerOriginalHeight - hideBottom + hideTop)) {
                    isInAnimation = true;
                    post(new Runnable() {
                        public void run() {
                            scrollCountY += distanceY;
                            if (scrollCountY >= 0 && scrollCountY <= hideTop) {
                                mHeader.scrollTo(mHeader.getScrollX(),
                                        scrollCountY);
                            } else {
                                mHeader.scrollTo(mHeader.getScrollX(), hideTop);
                            }
                            LayoutParams params2 = (LayoutParams) mContent
                                    .getLayoutParams();
                            params2.setMargins(0, -scrollCountY, 0, 0);
                            mContent.setLayoutParams(params2);

                        }
                    });
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                collapse();
            }
        }.start();
    }

    public void collapse() {
        post(new Runnable() {
            public void run() {
                mHeader.scrollTo(mHeader.getScrollX(), hideTop);
                scrollCountY = headerOriginalHeight - hideBottom + hideTop;
                LayoutParams params2 = (LayoutParams) mContent
                        .getLayoutParams();
                params2.setMargins(0,
                        -(headerOriginalHeight - hideBottom + hideTop), 0, 0);
                mContent.setLayoutParams(params2);
                isInAnimation = false;
            }
        });
    }

    public void collapse2() {
        if (mContent == null || mHeader == null) {
            return;
        }
        if (mContent.getTop() == mHeader.getBottom()) {
            return;
        }
        collapse();
    }

    /**
     * 手指移动时的
     *
     * @param distanceY
     */
    private void moveToScroll(float distanceY) {
        scrollCountY = scrollCountY + (int) distanceY;
        if ((scrollCountY >= 0 && scrollCountY <= hideTop)) {
            // 判断顶部是否还能往上顶
            mHeader.scrollTo(mHeader.getScrollX(), scrollCountY);
            LayoutParams params = (LayoutParams) mContent.getLayoutParams();
            params.setMargins(0, -scrollCountY, 0, 0);
            mContent.setLayoutParams(params);

        } else {
            if (scrollCountY <= 0) {
                // 滑动到了底部
                // 重新设值是因为float转int会有精度丢失的问题
                mHeader.scrollTo(mHeader.getScrollX(), 0);
                scrollCountY = 0;

                LayoutParams params = (LayoutParams) mContent.getLayoutParams();
                params.setMargins(0, 0, 0, 0);
                mContent.setLayoutParams(params);
            } else if (scrollCountY > hideTop) {
                // 日历头滑动到了顶部
                mHeader.scrollTo(mHeader.getScrollX(), hideTop);
                if (scrollCountY <= headerOriginalHeight - hideBottom + hideTop
                        + distanceY) {
                    if (scrollCountY <= headerOriginalHeight - hideBottom
                            + hideTop) {
                        LayoutParams params2 = (LayoutParams) mContent
                                .getLayoutParams();
                        params2.setMargins(0, -scrollCountY, 0, 0);
                        mContent.setLayoutParams(params2);
                    } else {
                        LayoutParams params2 = (LayoutParams) mContent
                                .getLayoutParams();
                        params2.setMargins(0, -(headerOriginalHeight
                                - hideBottom + hideTop), 0, 0);
                        mContent.setLayoutParams(params2);
                    }
                }
            }
        }
    }

}
