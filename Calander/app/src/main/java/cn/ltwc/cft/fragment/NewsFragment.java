package cn.ltwc.cft.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.FragmentViewPagerAdapter;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.utils.SharedPreferenceUtil;
import cn.ltwc.viewutils.customview.MainViewPager;

/**
 * NewsFragment
 * Created by Queen on 2018/1/25 0025.
 */
@SuppressLint("ValidFragment")
public class NewsFragment extends BaseFragment {
    private TitleView titleView;
    private TabLayout tabLayout;
    private MainViewPager vp;
    private List<Fragment> fragments;
    private List<String> tabs;
    private int index;

    @Override
    public void initView() {
        titleView = (TitleView) view.findViewById(R.id.news_fragment_title_view);
        titleView.setLeftVisibility(View.INVISIBLE);
        titleView.setRightVisibility(View.INVISIBLE);// 设置右边图片不显示
        titleView.setTitletext("新闻资讯");
        tabLayout = (TabLayout) view.findViewById(R.id.tab_lay);
        vp = (MainViewPager) view.findViewById(R.id.vp);
    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        String tabString = (String) SharedPreferenceUtil.get(Constant.TABS, "");
        if (tabs == null) {
            tabs = new ArrayList<>();
        }
        if (TextUtils.isEmpty(tabString)) {
            tabs.addAll(Arrays.asList(Constant.NEWS_DEFAULT_TABS));
        } else {
            tabs.addAll(Arrays.asList(tabString.split("&")));
        }
    }

    private Fragment getFragment(String name) {
        NewsViewFragment newsViewFragment = new NewsViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("layoutID", R.layout.fragmet_news_weex);
        newsViewFragment.setArguments(bundle);
        newsViewFragment.setIndex(index);
        newsViewFragment.setName(name);
        index++;
        return newsViewFragment;
    }

    @Override
    public void bindView() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (view != null && isVisibleToUser && !firstViewCreateVisibleToUser) {
            firstViewCreateVisibleToUser = true;
            for (String name : tabs) {
                fragments.add(getFragment(name));
            }
            vp.setAdapter(new FragmentViewPagerAdapter(getChildFragmentManager(), fragments));
            vp.setOffscreenPageLimit(tabs.size());
            tabLayout.setupWithViewPager(vp);
            tabLayout.removeAllTabs();
            for (String name : tabs) {
                tabLayout.addTab(tabLayout.newTab().setText(name));
            }
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    vp.setCurrentItem(tab.getPosition(), false);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }
}
