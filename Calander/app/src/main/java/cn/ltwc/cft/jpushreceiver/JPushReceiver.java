package cn.ltwc.cft.jpushreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;
import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.activity.StartActivity;
import cn.ltwc.cft.rxbus.Event;
import cn.ltwc.cft.rxbus.RxBus;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.cft.weex.WeexActivity;
import cn.ltwc.viewutils.dialogutils.BtnClickListener;
import cn.ltwc.viewutils.dialogutils.DialogUtil;
import rx.functions.Action1;

import static cn.ltwc.cft.data.Constant.JS_NAME;
import static cn.ltwc.cft.data.Constant.START_ACTIVITY_FINISH;
import static cn.ltwc.cft.data.Constant.WEBTITLE;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */

public class JPushReceiver extends BroadcastReceiver {
    private static final String ACTION = "cn.jpush.android.intent.NOTIFICATION_RECEIVED";//通知
    private static final String ACTION_MESSAGE = "cn.jpush.android.intent.MESSAGE_RECEIVED";//自定义消息
    private static final String ACTION_NOTIFICATION_OPENED = "cn.jpush.android.intent.NOTIFICATION_OPENED";//通知栏的点击事件
    private static final String JS_DOWN_LOAD_URL = "JsDownLoadUrl";
    private static final String DOWNLOAD_JS_NAME = "JsName";
    private static final String TITLE = "title";
    private String downLoadUrl;
    private String jsName;
    private String title;
    private String url = "";
    String content;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (action.equals(ACTION)) {
            getDetail(intent);
            //http://14647267.s21d-14.faiusrd.com/0/ABUIABAAGAAgsaH6zAUot7iP9QY?f=login.weex.js&v=1503563953
        } else if (action.equals(ACTION_MESSAGE)) {
        } else if (action.equals(ACTION_NOTIFICATION_OPENED)) {
            //用户点击了通知栏
            getDetail(intent);
        }
    }

    private void getDetail(Intent intent) {
        Bundle bundle = intent.getExtras();
        content = bundle.getString(JPushInterface.EXTRA_ALERT);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        if (extras != null && extras.contains(DOWNLOAD_JS_NAME)) {
            HashMap<String, Object> receiver = Utils.jsonToMap(extras);
            jsName = (String) receiver.get(DOWNLOAD_JS_NAME);
            title = (String) receiver.get(TITLE);
        }
        deal();
    }

    private void deal() {
        if (AppManager.getAppManager().NoActivity()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName cn = new ComponentName(MyApplication.getInstance(), StartActivity.class);
            intent.setComponent(cn);
            MyApplication.getInstance().startActivity(intent);
        } else {
            showReceivedDialog();
        }
    }

    private void showReceivedDialog() {
        if (AppManager.getAppManager().currentActivity() != null) {
            if ((AppManager.getAppManager().currentActivity()).getClass().equals(StartActivity.class)) {
                RxBus.getInstance().addSubscription(this, new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        if (event.id == START_ACTIVITY_FINISH) {
                            showDialog();
                            RxBus.getInstance().unsubscribe(JPushReceiver.this);
                        }
                    }
                });
            } else {
                showDialog();
            }
        }
    }

    private void showDialog() {
        if (AppManager.getAppManager().currentActivity() != null) {
            DialogUtil dialogUtil = new DialogUtil(new WeakReference<Activity>(AppManager.getAppManager().currentActivity()));
            if (!dialogUtil.isReturn()) {
                dialogUtil.setTop(title).setContent(content).setTopShowType(DialogUtil.TOP_TYP_TEXT).setContentShowType(DialogUtil.CONTENT_TYPE_TEXT).setButtonShowType(DialogUtil.BUTTON_TYPE_TWO_BTN).setRightBtn("立即查看").setRightBtnClickListener(new BtnClickListener() {
                    @Override
                    public void btnClick() {
                        startMessageActivity();
                    }
                }).show();
            }
        }
    }

    private void startMessageActivity() {
        Intent t = new Intent(MyApplication.getInstance(), WeexActivity.class);
        t.putExtra(JS_NAME, jsName);
        t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        t.putExtra(WEBTITLE, title);
        MyApplication.getInstance().startActivity(t);
    }

    private void startWebViewActivity(String url) {

    }
}
