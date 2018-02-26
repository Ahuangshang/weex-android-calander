package cn.ltwc.cft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.R;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.DownLoadUtil;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.cft.x5web.utils.WebViewJavaScriptFunction;
import cn.ltwc.cft.x5web.utils.X5WebView;
import cn.ltwc.utils.LogUtil;
import cn.ltwc.utils.ToastUtil;
import rx.functions.Action1;

@SuppressLint("InlinedApi")
public class MyX5WebView extends BaseActivity {
    /**
     * 用于演示X5webview实现视频的全屏播放功能 其中注意 X5的默认全屏方式 与 android 系统的全屏方式
     */

    private X5WebView webView;
    private TitleView title;
    private String webUrl, shareUrl;// 加载的网址
    private String webTitle;// 加载网页的标题
    private ProgressBar bar;
    private boolean barShow = true;
    private boolean canGoBack = true;

    public MyX5WebView() {
        super(R.layout.activity_myx5_web);
    }

    public void initView() {
        // TODO Auto-generated method stub
        webView = (X5WebView) findViewById(R.id.web_filechooser);
        title = (TitleView) findViewById(R.id.xweb_title);
        bar = (ProgressBar) findViewById(R.id.bar);
        title.setTitletext(webTitle);
        title.setCloseVisibility(View.VISIBLE);
        webView.loadUrl(webUrl);

    }

    boolean receivedTitle;

    public void bindView() {
        // TODO Auto-generated method stub
        if (!TextUtils.isEmpty(shareUrl)) {
            title.setRightVisibility(View.VISIBLE);
            title.setRightIcon(R.drawable.title_share);
            title.getRightIcon().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    HLUtil.toMyShare(MyX5WebView.this, Constant.SHARE_TYPE_WEB, "王朝黄历\n" + webTitle + "\n" + shareUrl, null, shareUrl);
                }
            });
        }
        webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView arg0, int process) {
                if (!barShow && receivedTitle) {
                    bar.setVisibility(View.GONE);
                    return;
                }
                bar.setProgress(process);
                if (process >= 100) {
                    bar.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            bar.setVisibility(View.GONE);
                        }
                    }, 200);
                } else {
                    bar.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                receivedTitle = true;
                if (TextUtils.isEmpty(webTitle)) {
                    title.setTitletext(s);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                if (url.startsWith("http") || url.startsWith("https")) {
                    view.loadUrl(url);
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        ToastUtil.showMessageCenter("手机里没有打开此链接的应用");
                    }
                }
                return true;
            }
        });
        webView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                // TODO Auto-generated method stub
                title.setDownViewVisibility(View.VISIBLE);
                DownLoadUtil.downLoadWitheProgress(url, new Action1<Integer>() {
                    @Override
                    public void call(Integer progress) {
                        if (progress == 100000) {
                            title.setDownViewVisibility(View.GONE);
                        }
                        title.setDownProgress(progress);
                    }
                });
            }

        });
        webView.addJavascriptInterface(new WebViewJavaScriptFunction() {

            @Override
            public void onJsFunctionCalled(String tag) {
                // TODO Auto-generated method stub

            }

            @JavascriptInterface
            public void onX5ButtonClicked() {
                MyX5WebView.this.enableX5FullscreenFunc();
            }

            @JavascriptInterface
            public void onCustomButtonClicked() {
                MyX5WebView.this.disableX5FullscreenFunc();
            }

            @JavascriptInterface
            public void onLiteWndButtonClicked() {
                MyX5WebView.this.enableLiteWndFunc();
            }

            @JavascriptInterface
            public void onPageVideoClicked() {
                MyX5WebView.this.enablePageVideoFunc();
            }
        }, "Android");

        title.getLeftIcon().setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (webView != null && webView.canGoBack() && canGoBack) {
                    webView.goBack();
                } else {
                    AppManager.getAppManager().finishActivity(MyX5WebView.this);
                }
            }
        });
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    public void initData() {
        // TODO Auto-generated method stub
        webUrl = getIntent().getStringExtra(Constant.WEBURL);
        webTitle = getIntent().getStringExtra(Constant.WEBTITLE);
        shareUrl = getIntent().getStringExtra(Constant.SHARE_URL);
        barShow = getIntent().getBooleanExtra(Constant.BAR_SHOW, true);
        canGoBack = getIntent().getBooleanExtra(Constant.CAN_GO_BACK, true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        try {
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // /////////////////////////////////////////
    // 向webview发出信息
    private void enableX5FullscreenFunc() {
        LogUtil.i("enableX5FullscreenFunc happend!");

        if (webView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "开启X5全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void disableX5FullscreenFunc() {
        LogUtil.i("disableX5FullscreenFunc happend!");
        if (webView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "恢复webkit初始状态", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", true);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void enableLiteWndFunc() {
        LogUtil.i("enableLiteWndFunc happend!");
        if (webView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "开启小窗模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", true);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void enablePageVideoFunc() {
        LogUtil.i("enablePageVideoFunc happend!");
        if (webView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "页面内全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 1);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    /**
     * 按键响应，在WebView中查看网页时，按返回键的时候按浏览历史退回,如果不做此项处理则整个WebView返回退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if (canGoBack) {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && webView != null
                    && webView.canGoBack()) {
                // 返回键退回
                webView.goBack();
                return true;
            }
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
