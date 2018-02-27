package cn.ltwc.cft.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;

import java.util.HashMap;

import cn.ltwc.cft.R;
import cn.ltwc.cft.activity.HomeActivity;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.entiy.LocationInfo;
import cn.ltwc.cft.rxbus.Event;
import cn.ltwc.cft.rxbus.RxBus;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.cft.weex.WeexUtil;
import rx.functions.Action1;

/**
 * WeatherFragment
 * Created by Queen on 2018/1/25 0025.
 */
@SuppressLint("ValidFragment")
public class WeatherFragment extends BaseFragment {
    private TitleView titleView;
    private WeexUtil weexUtil;
    private ViewGroup parent;
    private LocationInfo locationInfo;//定位信息

    @Override
    public void initView() {
        //titleView = (TitleView) view.findViewById(R.id.title);
        parent = view.findViewById(R.id.parent);
//        titleView.setLeftVisibility(View.INVISIBLE);
//        titleView.setRightVisibility(View.INVISIBLE);// 设置右边图片不显示
//        titleView.setTitletext("天气");
//        titleView.getTitletext().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                if (weexUtil != null) {
//                    weexUtil.selectWeexUrl();
//                }
//                return false;
//            }
//        });
    }

    @Override
    public void initData() {
        if (getActivity() != null) {
            locationInfo = ((HomeActivity) getActivity()).homeFragment.getLocationInfo();
        }
        if (locationInfo == null) {
            RxBus.getInstance().addSubscription(this, new Action1<Event>() {
                @Override
                public void call(Event event) {
                    if (event.id == Constant.LOCATION_SUCCESS) {
                        RxBus.getInstance().unsubscribe(WeatherFragment.this);
                        locationInfo = (LocationInfo) event.data;
                        notifyWeather();
                    }
                }
            });
        } else {
            notifyWeather();
        }
    }

    @Override
    public void bindView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (view != null && isVisibleToUser && !firstViewCreateVisibleToUser) {
            firstViewCreateVisibleToUser = true;
            if (parent == null) {
                parent = view.findViewById(R.id.parent);
            }
            weexUtil = new WeexUtil("weather", new HashMap<String, Object>() {{
                put("city", locationInfo != null ? TextUtils.isEmpty(locationInfo.getDistrict()) ? TextUtils.isEmpty(locationInfo.getCityName()) ? "杭州" : locationInfo.getCityName() : locationInfo.getDistrict() : "杭州");
            }}, parent, (Activity) c);
            weexUtil.fireFresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        notifyWeather();

    }

    private void notifyWeather() {
        if (weexUtil != null) {
            weexUtil.setData(locationInfo != null ? TextUtils.isEmpty(locationInfo.getDistrict()) ? TextUtils.isEmpty(locationInfo.getCityName()) ? "杭州" : locationInfo.getCityName() : locationInfo.getDistrict() : "杭州");
            weexUtil.setType("locationInfo");
            weexUtil.fireFresh(false);
        }
    }
}
