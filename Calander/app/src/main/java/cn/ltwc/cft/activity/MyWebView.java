package cn.ltwc.cft.activity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import cn.ltwc.cft.R;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.DownLoadUtil;
import cn.ltwc.cft.view.TitleView;
import rx.functions.Action1;

@SuppressLint("InlinedApi")
public class MyWebView extends BaseActivity {
    /**
     * 用于演示X5webview实现视频的全屏播放功能 其中注意 X5的默认全屏方式 与 android 系统的全屏方式
     */

    private WebView webView;
    private TitleView title;
    private String webUrl;// 加载的网址
    private String webTitle;// 加载网页的标题
    private ProgressBar bar;

    public MyWebView() {
        super(R.layout.activity_my_web);
    }

    public void initView() {
        // TODO Auto-generated method stub
        webView = (WebView) findViewById(R.id.web_filechooser);
        initWebViewSettings();


        title = (TitleView) findViewById(R.id.xweb_title);
        bar = (ProgressBar) findViewById(R.id.bar);
        title.setTitletext(webTitle);
        title.setCloseVisibility(View.VISIBLE);
        webView.loadUrl(webUrl);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

    public void bindView() {
        // TODO Auto-generated method stub
        //webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView arg0, int process) {
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
                if (TextUtils.isEmpty(webTitle)) {
                    title.setTitletext(s);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                webView.loadUrl(url);
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

        title.getLeftIcon().setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (webView != null && webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    public void initData() {
        // TODO Auto-generated method stub
        webUrl = getIntent().getStringExtra(Constant.WEBURL);
        webTitle = getIntent().getStringExtra(Constant.WEBTITLE);
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

    /**
     * 按键响应，在WebView中查看网页时，按返回键的时候按浏览历史退回,如果不做此项处理则整个WebView返回退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView != null
                && webView.canGoBack()) {
            // 返回键退回
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


}
