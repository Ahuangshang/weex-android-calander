package cn.ltwc.cft.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.FragmentViewPagerAdapter;
import cn.ltwc.cft.adapter.MenuAdapter;
import cn.ltwc.cft.beans.MenuBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.fragment.BaseFragment;
import cn.ltwc.cft.fragment.HomeFragment;
import cn.ltwc.cft.fragment.MoreFragment;
import cn.ltwc.cft.fragment.NewsFragment;
import cn.ltwc.cft.fragment.WeatherFragment;
import cn.ltwc.cft.utils.SchemeUtil;
import cn.ltwc.viewutils.customview.MainViewPager;

/**
 * Created by Queen on 2018/1/25 0025.
 */

public class HomeActivity extends BaseActivity {

    public MainViewPager vp;
    private GridView menu;//菜单组
    private Fragment weatherFragment, newsFragment, moreFragment;
    public HomeFragment homeFragment;
    private List<Fragment> fragments;
    private boolean isCanScroll = false;//viewPager是否可以滑动的标志（默认为false,不可以滑动）
    private boolean whichCheck;//是谁触发了RadioGroup切换的（默认值为false，代表用户点击，true为viewPager切换触发的）
    private List<MenuBean> menuBeanList;
    MenuAdapter menuAdapter;
    private long lasttime = 0;// 上次点击时间

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    @Override
    public void initView() {
        vp = (MainViewPager) findViewById(R.id.vp);
        menu = (GridView) findViewById(R.id.main_menu);
        vp.setCanScroll(isCanScroll);
        vp.setOffscreenPageLimit(fragments.size());
    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        homeFragment = (HomeFragment) getFragment(new HomeFragment(), R.layout.fragment_home);
        weatherFragment = getFragment(new WeatherFragment(), R.layout.fragmet_news_weex);
        newsFragment = getFragment(new NewsFragment(), R.layout.fragment_news);
        moreFragment = getFragment(new MoreFragment(), R.layout.fragmet_weex);
        fragments.add(homeFragment);
        fragments.add(weatherFragment);
        fragments.add(newsFragment);
        fragments.add(moreFragment);
    }

    @Override
    public void bindView() {
        String adSchemeUrl = getIntent().getStringExtra(Constant.AD_SCHEME_URL);
        if (!TextUtils.isEmpty(adSchemeUrl)) {
            SchemeUtil.getInstance().jump(adSchemeUrl);
        }
        menu.setSelector(new ColorDrawable(Color.TRANSPARENT));// 去除点击效果
        initMenu();
        menu.setNumColumns(menuBeanList.size());
        menuAdapter = new MenuAdapter(this, menuBeanList);
        menu.setAdapter(menuAdapter);
        vp.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments));
        vp.setOffscreenPageLimit(fragments.size());
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setMenuChecked(position);
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                whichCheck = true;
                setMenuChecked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private Fragment getFragment(BaseFragment fragment, int layoutId) {
        Bundle bundle = new Bundle();
        bundle.putInt("layoutID", layoutId);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initMenu() {
        menuBeanList = new ArrayList<>();
        menuBeanList.add(new MenuBean(R.drawable.home_select, R.drawable.home_unselect, "首页", true));
        menuBeanList.add(new MenuBean(R.drawable.weather_select, R.drawable.weather_unselect, "天气", false));
        menuBeanList.add(new MenuBean(R.drawable.news_select, R.drawable.news_unselect, "资讯", false));
        menuBeanList.add(new MenuBean(R.drawable.more_select, R.drawable.more_unselect, "更多", false));
    }

    public void setMenuChecked(int position) {
        vp.setCurrentItem(position, whichCheck);
        whichCheck = false;
        if (menuBeanList == null) {
            initMenu();
        }
        for (int i = 0; i < menuBeanList.size(); i++) {
            if (i == position) {
                menuBeanList.get(i).setSelected(true);
            } else {
                menuBeanList.get(i).setSelected(false);
            }
        }
        menuAdapter.notifyDataSetChanged();
    }

    /**
     * 监听返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 如果按下的是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 如果当前点击时间减去上次点击时间小于退出时间
            if (System.currentTimeMillis() - lasttime > Constant.exitTime) {
                lasttime = System.currentTimeMillis();
                show("再次点击退出程序");
            } else {
                AppManager.getAppManager().AppExit(c);// 程序退出
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
