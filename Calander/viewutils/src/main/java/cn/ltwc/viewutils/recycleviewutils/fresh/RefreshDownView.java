package cn.ltwc.viewutils.recycleviewutils.fresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.callback.IHeaderCallBack;

import cn.ltwc.viewutils.R;

/**
 * Created by admin on 2016/11/22.
 */

public class RefreshDownView extends LinearLayout implements IHeaderCallBack {
    TextView pullDownText;
    ImageView img;
    View bar;
    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;
    private final int ROTATE_ANIM_DURATION = 180;

    public RefreshDownView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(21)
    public RefreshDownView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        View pullDownView = LayoutInflater.from(context).inflate(R.layout.view_refresh_down, this);
        init(pullDownView);
        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(0);
        mRotateDownAnim.setFillAfter(true);
    }

    private void init(View pullDownView) {
        pullDownText = (TextView) pullDownView.findViewById(R.id.text);
        img = (ImageView) pullDownView.findViewById(R.id.refresh_img);
        bar = pullDownView.findViewById(R.id.bar);
    }


    @Override
    public void onStateNormal() {
        bar.setVisibility(View.GONE);
        img.setVisibility(View.VISIBLE);
        img.startAnimation(mRotateDownAnim);
        pullDownText.setText(com.andview.refreshview.R.string.xrefreshview_header_hint_normal);
    }

    @Override
    public void onStateReady() {
        bar.setVisibility(View.GONE);
        img.setVisibility(View.VISIBLE);
        img.clearAnimation();
        img.startAnimation(mRotateUpAnim);
        pullDownText.setText(com.andview.refreshview.R.string.xrefreshview_header_hint_ready);
    }

    @Override
    public void onStateRefreshing() {
        img.clearAnimation();
        img.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);
        pullDownText.setText(com.andview.refreshview.R.string.xrefreshview_header_hint_loading);
    }

    @Override
    public void onStateFinish(boolean success) {
        img.setVisibility(View.GONE);
        bar.setVisibility(View.GONE);
        pullDownText.setText(success ? com.andview.refreshview.R.string.xrefreshview_header_hint_loaded : com.andview.refreshview.R.string.xrefreshview_header_hint_loaded_fail);
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {
    }

    @Override
    public void setRefreshTime(long lastRefreshTime) {
    }

    @Override
    public void hide() {
        setVisibility(View.GONE);
    }

    @Override
    public void show() {
        setVisibility(View.VISIBLE);
    }

    @Override
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }


}
