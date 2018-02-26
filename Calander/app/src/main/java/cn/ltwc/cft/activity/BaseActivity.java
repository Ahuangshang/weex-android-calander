package cn.ltwc.cft.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.Toast;

import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.R;
import cn.ltwc.viewutils.statusbar.StatusBarUtil;


/**
 * TODO:本应用Activity的基类
 *
 * @author huangshang 2015-11-10 下午2:20:20
 * @Modified_By:
 */
@SuppressLint("InlinedApi")
public abstract class BaseActivity extends FragmentActivity {
    private int layoutResId = -1;// 布局资源
    public Context c = BaseActivity.this;// 环境变量
    // 定义当前屏幕的宽高
    public int width;
    public int height;

    public BaseActivity(int layoutResID) {
        this.layoutResId = layoutResID;
    }

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 得到当前屏幕的宽和高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        // 沉浸式导航栏
        StatusBarUtil.statusBarColor(this);
        AppManager.getAppManager().addActivity(this);
        if (layoutResId != -1) {
            setContentView(layoutResId);
        }
        initData();
        initView();
        bindView();
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

    /**
     * Toast显示的方法
     *
     * @param msg 要显示的消息
     */
    public void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 隐式意图跳转Activity(不传递数据)
     */
    public void jumpToActivity(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 隐式意图跳转Activity(传递数据)
     */
    public void jumpToActivity(String action, Uri data) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setData(data);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public void jumpToActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackKeyDown();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onBackKeyDown() {
        AppManager.getAppManager().finishActivity(this);
    }
}