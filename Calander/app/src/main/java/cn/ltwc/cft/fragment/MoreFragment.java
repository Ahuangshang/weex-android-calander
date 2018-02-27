package cn.ltwc.cft.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.TbsVideo;

import java.util.HashMap;

import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.R;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.cft.weex.WeexUtil;

/**
 * MoreFragment
 * Created by Queen on 2018/1/25 0025.
 */
@SuppressLint("ValidFragment")
public class MoreFragment extends BaseFragment {
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
            titleView.setTitletext("更多");
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
            weexUtil = new WeexUtil("more", new HashMap<String, Object>() {{
                put("showLive", String.valueOf(TbsVideo.canUseTbsPlayer(AppManager.getAppManager().currentActivity())));
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
