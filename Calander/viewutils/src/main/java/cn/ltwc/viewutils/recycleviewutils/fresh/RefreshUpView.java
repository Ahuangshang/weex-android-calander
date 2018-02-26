package cn.ltwc.viewutils.recycleviewutils.fresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.callback.IFooterCallBack;

import cn.ltwc.viewutils.R;

/**
 * Created by admin on 2016/11/25.
 */

public class RefreshUpView extends LinearLayout implements IFooterCallBack {
    private Context mContext;

    private View mContentView;
    private View mProgressBar;
    private TextView mHintView;
    private boolean showing = true;

    public RefreshUpView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshUpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshUpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(21)
    public RefreshUpView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        ViewGroup moreView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.view_refresh_up, this);
        moreView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        mContentView = moreView.findViewById(R.id.xrefreshview_footer_content);
        mProgressBar = moreView
                .findViewById(R.id.pull_down_view_pb);
        mHintView = (TextView) moreView
                .findViewById(R.id.xrefreshview_footer_hint_textview);

    }

    @Override
    public void callWhenNotAutoLoadMore(final XRefreshView xRefreshView) {
    }

    @Override
    public void onStateRefreshing() {
        mHintView.setText("加载中...");
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        show(true);
    }

    @Override
    public void onReleaseToLoadMore() {

    }

    private boolean flag = false;

    @Override
    public void onStateReady() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        if (flag) {
            mHintView.setText("没有更多数据");
            flag = false;
        } else {
            mHintView.setText("加载中...");
        }
    }

    @Override
    public void onStateFinish(boolean hideFooter) {
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStateComplete() {
        flag = true;
        mHintView.setText("没有更多数据");
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void show(boolean show) {
        if (show == showing) {
            return;
        }
        showing = show;
        LayoutParams lp = (LayoutParams) mContentView
                .getLayoutParams();
        lp.height = show ? LayoutParams.WRAP_CONTENT : 0;
        mContentView.setLayoutParams(lp);
    }

    @Override
    public boolean isShowing() {
        return showing;
    }

    @Override
    public int getFooterHeight() {
        return getMeasuredHeight();
    }
}
