package cn.ltwc.cft;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;

import com.alibaba.android.bindingx.plugin.weex.BindingX;
import com.mob.MobSDK;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.ltwc.ViewUtils;
import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.activity.HomeActivity;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.helper.CrashHandler;
import cn.ltwc.cft.utils.FileUtils;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.view.LoadingDialog;
import cn.ltwc.cft.weex.ImageAdapter;
import cn.ltwc.cft.weex.WXEventModule;
import cn.ltwc.cft.weex.WXNetModule;
import cn.ltwc.utils.LogUtil;

import static cn.ltwc.cft.data.Constant.DEBUG;

/**
 * TODO:本应用的全局变量管理类
 *
 * @author huangshang 2015-11-10 下午2:22:09
 * @Modified_By:
 */
public class MyApplication extends Application {

    private static final String TAG = "JPush";
    private static MyApplication instance;
    public LoadingDialog dialog;
    private String cityCode;

    /**
     * 返回本类的操作对象
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
        ViewUtils.init(this);
        LogUtil.enableLog(DEBUG);
        LogUtil.e("isOriginProcess=" + HLUtil.isOriginProcess(this));
        if (!HLUtil.isOriginProcess(this)) {
            return;
        }
        initJPush();
        CrashHandler.getInstance().init(this, MyApplication.getInstance());
        registerComponentCallbacks(componentCallbacks2);
        MobSDK.init(this, "145da38f81409 ", "7a09f126f84f67c1a7c8166ef09d4af0");
        //CrashReport.initCrashReport(getApplicationContext(), "9b9cb6f069", false);
        Bugly.init(getApplicationContext(), "9b9cb6f069", false);
        /***** Beta高级设置 *****/
        /**
         * true表示app启动自动初始化升级模块;
         * false不会自动初始化;
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false，
         * 在后面某个时刻手动调用Beta.init(getApplicationContext(),false);
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级;
         * false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
        Beta.autoCheckUpgrade = !DEBUG;

        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
        Beta.upgradeCheckPeriod = 60 * 1000;

        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 1 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源;
         */
        Beta.largeIconId = R.drawable.ic_launcher;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源Id;
         */
        Beta.smallIconId = R.drawable.ic_launcher;

        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.drawable.ic_launcher;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = FileUtils.getCacheDirectory(this, "APK");

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;
        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(HomeActivity.class);
        /***** 统一初始化Bugly产品，包含Beta *****/
        Bugly.init(this, "9b9cb6f069", false);

        try {
            BindingX.register();
        } catch (WXException e) {
            e.printStackTrace();
        }
        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this, config);
        try {
            WXSDKEngine.registerModule("event", WXEventModule.class);
            WXSDKEngine.registerModule("net", WXNetModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }

    private void initJPush() {
        //JPush
        JPushInterface.setDebugMode(Constant.DEBUG);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        Set<String> set = new HashSet<>();
        set.add(HLUtil.getVersionName());
        //标签
        JPushInterface.setTags(this.getApplicationContext(), set, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                switch (i) {
                    case 0:
                        break;
                    case 6002:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void showProgressDialog() {
        if (dialog == null) {
            dialog = new LoadingDialog(AppManager.getAppManager().currentActivity());
        }
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        try {
            dialog.show();
        } catch (Exception e) {
            LogUtil.e("****" + e);
            dialog = null;
            showProgressDialog();
        }
    }


    public void disMissProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppManager.getAppManager().AppExit(this);
    }

    /**
     * 防止一些环境会编译报错
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    ComponentCallbacks2 componentCallbacks2 = new ComponentCallbacks2() {
        @Override
        public void onTrimMemory(int level) {
            if (level == TRIM_MEMORY_UI_HIDDEN) {
                GlideUtil.clearImageMemoryCache(MyApplication.getInstance());
            }
            GlideUtil.trimMemory(MyApplication.getInstance(), level);
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {

        }

        @Override
        public void onLowMemory() {
            LogUtil.e("***onLowMemory***");
            GlideUtil.clearImageMemoryCache(MyApplication.getInstance());
        }
    };
}
