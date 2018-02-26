package cn.ltwc.cft.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.R;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.utils.SharedPreferenceUtil;
import rx.Subscriber;

@SuppressLint("HandlerLeak")
public class StartActivity extends Activity {
    private ImageView adImg;
    private TextView jump;


    private Subscriber subscriber;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    gotoHomeActivity();
                    break;
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(R.layout.activity_start);
        adImg = (ImageView) findViewById(R.id.ad_img);
        jump = (TextView) findViewById(R.id.jump);
        bindView();
        initX5();
        countdown();
    }

    private void countdown() {
        subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                subscriber.unsubscribe();
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);

            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onNext(Integer time) {
                jump.setText("跳过 ( " + time + " 秒 )");
            }
        };
        HLUtil.countdown(10, subscriber);
    }

    private void initX5() {
        // 搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
            }

            @Override
            public void onInstallFinish(int i) {

            }

            @Override
            public void onDownloadProgress(int i) {
            }
        });
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    private void gotoHomeActivity(String adURLScheme) {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
        Intent intent = new Intent(StartActivity.this, HomeActivity.class);
        intent.putExtra(Constant.AD_SCHEME_URL, adURLScheme);
        startActivity(intent);
        AppManager.getAppManager().finishActivity(StartActivity.class);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    private void gotoHomeActivity() {
        gotoHomeActivity("");
    }

    private void bindView() {
        String adUrl = (String) SharedPreferenceUtil.get(Constant.AD_IMG_URL, "");
        Glide.with(this).load(adUrl).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                jump.setVisibility(View.VISIBLE);
                adImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String adURLScheme = (String) SharedPreferenceUtil.get(Constant.AD_SCHEME_URL, "");
                        if (!TextUtils.isEmpty(adURLScheme)) {
                            gotoHomeActivity(adURLScheme);
                        }
                    }
                });

                return false;
            }
        }).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(adImg);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHomeActivity();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (subscriber != null && !subscriber.isUnsubscribed()) {
                subscriber.unsubscribe();
            }
            AppManager.getAppManager().AppExit(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
