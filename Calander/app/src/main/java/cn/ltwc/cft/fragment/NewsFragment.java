package cn.ltwc.cft.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.FragmentViewPagerAdapter;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.cft.weex.WeexUtil;
import cn.ltwc.utils.SharedPreferenceUtil;
import cn.ltwc.viewutils.customview.MainViewPager;

/**
 * NewsFragment
 * Created by Queen on 2018/1/25 0025.
 */
@SuppressLint("ValidFragment")
public class NewsFragment extends BaseFragment {
    private TitleView titleView;
    private WeexUtil weexUtil;
    private ViewGroup parent;

    @Override
    public void initView() {
        if (view != null) {
            titleView = (TitleView) view.findViewById(R.id.title);
            parent = view.findViewById(R.id.parent);
            titleView.setLeftVisibility(View.INVISIBLE);
            titleView.setRightVisibility(View.INVISIBLE);// 设置右边图片不显示
            titleView.setTitletext("新闻资讯");
            titleView.getTitletext().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (weexUtil != null) {
                        weexUtil.selectWeexUrl();
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (view != null && isVisibleToUser && !firstViewCreateVisibleToUser) {
            firstViewCreateVisibleToUser = true;
            if (parent == null) {
                parent = view.findViewById(R.id.parent);
            }
            weexUtil = new WeexUtil("news", new HashMap<String, Object>() {{

            }}, parent, (Activity) c);
            weexUtil.fireFresh();
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindView() {

    }
}
