package cn.ltwc.cft.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.tencent.bugly.beta.Beta;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.R;
import cn.ltwc.cft.activity.DayDetailActivity;
import cn.ltwc.cft.activity.MoreActivity;
import cn.ltwc.cft.activity.MyX5WebView;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.entiy.LocationInfo;
import cn.ltwc.cft.helper.HomeFragmentHelper;
import cn.ltwc.cft.rxbus.Event;
import cn.ltwc.cft.rxbus.RxBus;
import cn.ltwc.cft.utils.FileUtils;
import cn.ltwc.cft.utils.PermissionUtils;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.utils.ToastUtil;

/**
 * HomeFragment
 * Created by Queen on 2018/1/25 0025.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private View nongLiInfo;// 农历信息栏
    private View lotterMore;// 更多开奖，双色球开奖
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    private LocationInfo result;

    @Override
    public void initView() {
        if (view != null) {
            HomeFragmentHelper.getInstance().init(view, c);
            lotterMore = view.findViewById(R.id.more_lotter);
            nongLiInfo = view.findViewById(R.id.nongli_show);
        }
    }

    @Override
    public void initData() {
        HomeFragmentHelper.getInstance().getLot();
        initLocation();
        Beta.checkUpgrade(false, false);
    }

    @Override
    public void bindView() {
        if (view != null) {
            nongLiInfo.setOnClickListener(this);
            lotterMore.setOnClickListener(this);
            view.findViewById(R.id.ssq).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.nongli_show:// 农历信息栏的点击事件
                Intent intent = new Intent(c,
                        DayDetailActivity.class);
                intent.putExtra(Constant.RILIINFO, HomeFragmentHelper.getInstance().rbean);
                jumpToActivity(intent);
                break;
            case R.id.more_lotter:// 更多开奖
                Intent more = new Intent(c, MyX5WebView.class);
                more.putExtra(
                        Constant.WEBURL,
                        "http://cp.lexiucp.com/android_asset/www/newmicai/lotteryinfo/kjgg.html?tag_from=500#?page=kjgg_index");
                more.putExtra(Constant.WEBTITLE, "开奖公告");
                jumpToActivity(more);
                break;
            case R.id.ssq:// 双色球
                Intent ss = new Intent(c, MyX5WebView.class);
                ss.putExtra(
                        Constant.WEBURL,
                        "http://cp.lexiucp.com/android_asset/www/newmicai/lotteryinfo/kjgg.html?tag_from=500#?page=kjgg_list&lot_code=50");
                ss.putExtra(Constant.WEBTITLE, "双色球开奖详情");
                jumpToActivity(ss);
                break;
        }
    }

    private void initLocation() {
        // TODO Auto-generated method stub
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtils.isRejected(this, Constant.ACCESS_LOCATION_EXTRA_COMMANDS) && PermissionUtils.isRejected(this, Constant.ACCESS_FINE_LOCATION) && PermissionUtils.isRejected(this, Constant.ACCESS_COARSE_LOCATION)) {
                ToastUtil.showMessageLongCenter("您已关闭定位权限，如需获取准备服务，请开启对应的权限");
            } else {
                if (!(PermissionUtils.checkPermission(this, Constant.ACCESS_LOCATION_EXTRA_COMMANDS) && PermissionUtils.checkPermission(this, Constant.ACCESS_FINE_LOCATION) && PermissionUtils.checkPermission(this, Constant.ACCESS_COARSE_LOCATION))) {
                    PermissionUtils.requestPermissionsWrapper(this, new String[]{Constant.ACCESS_LOCATION_EXTRA_COMMANDS, Constant.ACCESS_FINE_LOCATION, Constant.ACCESS_COARSE_LOCATION}, 0x0010);
                }
            }
        }
        // 初始化client
        locationClient = new AMapLocationClient(c.getApplicationContext());
        // 设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        startLocation();
    }

    int count = 0;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x0010) {
            if (PermissionUtils.isRejected(this, Constant.ACCESS_LOCATION_EXTRA_COMMANDS) && PermissionUtils.isRejected(this, Constant.ACCESS_FINE_LOCATION) && PermissionUtils.isRejected(this, Constant.ACCESS_COARSE_LOCATION)) {
                ToastUtil.showMessageCenter("您已拒绝定位权限，如需获取准确服务，请开启对应的权限");
            } else {
                if (count > 0) {
                    ToastUtil.showMessageCenter("您已拒绝定位权限，如需获取准确服务，请开启对应的权限");
                } else {
                    initLocation();
                }
                count++;
            }
        }
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 根据控件的选择，重新设置定位参数
        // resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                // 解析定位结果
                stopLocation();
                result = Utils.getLocationStr(loc);
                if (result != null) {
                    String code = FileUtils.getCityCode(result.getCityName());
                    if (!TextUtils.isEmpty(code)) {
                        MyApplication.getInstance().setCityCode(code);
                        HomeFragmentHelper.getInstance().getLayout(code);
                    }
                    //HomeFragmentHelper.getInstance().getWeather(result);
                    RxBus.getInstance().post(new Event(Constant.LOCATION_SUCCESS, result));
                } else {
                    //HomeFragmentHelper.getInstance().jumptoToday.setText("定位失败");
                    ToastUtil.showMessage("定位失败");
                }
            } else {
                ToastUtil.showMessage("定位失败");
                //HomeFragmentHelper.getInstance().jumptoToday.setText("定位失败");
            }
        }
    };

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);// 可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);// 可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);// 可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(200000);// 可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);// 可选，设置是否返回逆地理地址信息。默认是ture
        mOption.setOnceLocation(false);// 可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);// 可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);// 可选，
        // 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        return mOption;
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    public LocationInfo getLocationInfo() {
        return result;
    }
}
