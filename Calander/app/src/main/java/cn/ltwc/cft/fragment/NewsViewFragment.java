package cn.ltwc.cft.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import cn.ltwc.cft.R;
import cn.ltwc.cft.weex.WeexUtil;

/**
 * NewsViewFragment
 * Created by Queen on 2018/1/25 0025.
 */
@SuppressLint("ValidFragment")
public class NewsViewFragment extends BaseFragment {

    private WeexUtil weexUtil;
    private ViewGroup parent;
    private String name;
    private Map<String, Object> options = null;
    private int index;

    @Override
    public void initView() {
        parent = view.findViewById(R.id.parent);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindView() {
        if (index == 0 && getUserVisibleHint()) {
            createWeex();
            firstViewCreateVisibleToUser = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (view != null && isVisibleToUser && !firstViewCreateVisibleToUser) {
            firstViewCreateVisibleToUser = true;
            createWeex();
        }
    }

    private void createWeex() {
        if (parent == null) {
            parent = view.findViewById(R.id.parent);
        }
        weexUtil = new WeexUtil("news", options, parent, (Activity) c);
        weexUtil.fireFresh();
    }

    public void setName(String name) {
        this.name = name;
        options = new HashMap<>();
        options.put("channel", name);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
