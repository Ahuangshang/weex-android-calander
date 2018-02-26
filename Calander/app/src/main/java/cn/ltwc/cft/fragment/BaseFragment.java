package cn.ltwc.cft.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ltwc.cft.R;

/**
 * BaseFragment
 * Created by Queen on 2018/1/25 0025.
 */

public abstract class BaseFragment extends Fragment {
    private int layoutResId = -1;// 布局资源
    public Context c;// 环境变量
    // 定义当前屏幕的宽高
    public int width;
    public int height;
    public View view;
    public boolean firstViewCreateVisibleToUser;//第一次创建视图成功并被用户可见

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        c = getContext();
        // 得到当前屏幕的宽和高
        if (getActivity() != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            width = dm.widthPixels;
            height = dm.heightPixels;
        }
        if (getArguments() != null) {
            this.layoutResId = getArguments().getInt("layoutID");
        }
        if (this.layoutResId != -1) {
            view = inflater.inflate(this.layoutResId, container, false);
        }
        initView();
        initData();
        bindView();
        return view;
    }

    /**
     * 初始化视图
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 数据与视图的绑定
     */
    public abstract void bindView();
    public void jumpToActivity(Intent intent) {
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }
}
