package cn.ltwc.cft.x5web.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import cn.ltwc.cft.R;
import cn.ltwc.cft.weex.WXEventModule;
import cn.ltwc.utils.LogUtil;
import rx.functions.Action1;

public class X5WebView extends WebView {
    public static final int FILE_CHOOSER = 0;
    // private String resourceUrl = "";
    // private WebView smallWebView;
    private static boolean isSmallWebViewDisplayed = false;
    // private boolean isClampedY = false;
    private Map<String, Object> mJsBridges;
    // private TextView tog;
    RelativeLayout.LayoutParams layoutParams;
    // private RelativeLayout refreshRela;
    TextView title;
    private HashMap<String, Object> javaInterFaceModule = new HashMap<>();
    private Action1<WebView> loadSuccessListener;

    public void setLoadSuccessListener(Action1<WebView> loadSuccessListener) {
        this.loadSuccessListener = loadSuccessListener;
    }

    private WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void onReceivedHttpAuthRequest(
                WebView webview,
                com.tencent.smtt.export.external.interfaces.HttpAuthHandler httpAuthHandlerhost,
                String host, String realm) {
            //boolean flag = httpAuthHandlerhost.useHttpAuthUsernamePassword();
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            if (loadSuccessListener != null) {
                loadSuccessListener.call(webView);
            }

        }
    };
    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
                                   JsResult arg3) {
            return super.onJsConfirm(arg0, arg1, arg2, arg3);
        }

        View myVideoView;
        View myNormalView;
        CustomViewCallback callback;

        // /////////////////////////////////////////////////////////
        //

        /**
         * 全屏播放配置
         */
        @Override
        public void onShowCustomView(View view,
                                     CustomViewCallback customViewCallback) {
            FrameLayout normalView = (FrameLayout) ((Activity) getContext())
                    .findViewById(R.id.web_filechooser);
            ViewGroup viewGroup = (ViewGroup) normalView.getParent();
            viewGroup.removeView(normalView);
            viewGroup.addView(view);
            myVideoView = view;
            myNormalView = normalView;
            callback = customViewCallback;
        }

        @Override
        public void onHideCustomView() {
            if (callback != null) {
                callback.onCustomViewHidden();
                callback = null;
            }
            if (myVideoView != null) {
                ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                viewGroup.removeView(myVideoView);
                viewGroup.addView(myNormalView);
            }
        }

        @Override
        public void openFileChooser(ValueCallback<Uri> uploadFile,
                                    String acceptType, String captureType) {
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            ((Activity) (X5WebView.this.getContext())).startActivityForResult(
                    Intent.createChooser(i, "choose files"),
                    X5WebView.FILE_CHOOSER);
            super.openFileChooser(uploadFile, acceptType, captureType);
        }

        /**
         * webview 的窗口转移
         */
        @Override
        public boolean onCreateWindow(WebView arg0, boolean arg1, boolean arg2,
                                      Message msg) {
            // TODO Auto-generated method stub
            if (X5WebView.isSmallWebViewDisplayed == true) {

                WebViewTransport webViewTransport = (WebViewTransport) msg.obj;
                WebView webView = new WebView(X5WebView.this.getContext()) {

                    @SuppressLint("DrawAllocation")
                    protected void onDraw(Canvas canvas) {
                        super.onDraw(canvas);
                        Paint paint = new Paint();
                        paint.setColor(Color.GREEN);
                        paint.setTextSize(15);
                        canvas.drawText("新建窗口", 10, 10, paint);
                    }

                    ;
                };
                webView.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView arg0,
                                                            String arg1) {
                        arg0.loadUrl(arg1);
                        return true;
                    }

                    ;
                });
                LayoutParams lp = new LayoutParams(400, 600);
                lp.gravity = Gravity.CENTER_HORIZONTAL
                        | Gravity.CENTER_VERTICAL;
                X5WebView.this.addView(webView, lp);
                webViewTransport.setWebView(webView);
                msg.sendToTarget();
            }
            return true;
        }

        @Override
        public boolean onJsAlert(WebView arg0, String arg1, String arg2,
                                 JsResult arg3) {
            /**
             * 这里写入你自定义的window alert
             */
            // AlertDialog.Builder builder = new Builder(getContext());
            // builder.setTitle("X5内核");
            // builder.setPositiveButton("确定", new
            // DialogInterface.OnClickListener() {
            //
            // @Override
            // public void onClick(DialogInterface dialog, int which) {
            // // TODO Auto-generated method stub
            // dialog.dismiss();
            // }
            // });
            // builder.show();
            // arg3.confirm();
            // return true;
            //LogUtil.i( "setX5webview = null");
            return super.onJsAlert(null, "www.baidu.com", "aa", arg3);
        }

        /**
         * 对应js 的通知弹框 ，可以用来实现js 和 android之间的通信
         */
        @Override
        public boolean onJsPrompt(WebView arg0, String arg1, String arg2,
                                  String arg3, JsPromptResult arg4) {
            // 在这里可以判定js传过来的数据，用于调起android native 方法
            if (X5WebView.this.isMsgPrompt(arg1)) {
                if (X5WebView.this.onJsPrompt(arg2, arg3)) {
                    return true;
                } else {
                    return false;
                }
            }
            return super.onJsPrompt(arg0, arg1, arg2, arg3, arg4);
        }

        @Override
        public void onReceivedTitle(WebView arg0, final String arg1) {
            super.onReceivedTitle(arg0, arg1);
            //LogUtil.i("webpage title is " + arg1);
        }
    };

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        this.setWebViewClientExtension(new X5WebViewEventHandler(this));// 配置X5webview的事件处理
        this.setWebViewClient(client);
        this.setWebChromeClient(chromeClient);
        // WebStorage webStorage = WebStorage.getInstance();
        initWebViewSettings();
        this.getView().setClickable(true);
        this.getView().setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
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
        addJavascriptInterface(new WXEventModule(this), "ltwc");
        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean ret = super.drawChild(canvas, child, drawingTime);
        // canvas.save();
        // Paint paint = new Paint();
        // paint.setColor(0x7fff0000);
        // paint.setTextSize(24.f);
        // paint.setAntiAlias(true);
        // if (getX5WebViewExtension() != null) {
        // canvas.drawText(this.getContext().getPackageName() + "-pid:" +
        // android.os.Process.myPid(), 10, 50, paint);
        // canvas.drawText("X5 Core:" + QbSdk.getTbsVersion(this.getContext()),
        // 10, 100, paint);
        // } else {
        // canvas.drawText(this.getContext().getPackageName() + "-pid:" +
        // android.os.Process.myPid(), 10, 50, paint);
        // canvas.drawText("Sys Core", 10, 100, paint);
        // }
        // canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
        // canvas.drawText(Build.MODEL, 10, 200, paint);
        // canvas.restore();
        return ret;
    }

    public X5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
    }

    public static void setSmallWebViewEnabled(boolean enabled) {
        isSmallWebViewDisplayed = enabled;
    }

    public void addJavascriptBridge(SecurityJsBridgeBundle jsBridgeBundle) {
        if (this.mJsBridges == null) {
            this.mJsBridges = new HashMap<String, Object>(5);
        }

        if (jsBridgeBundle != null) {
            String tag = SecurityJsBridgeBundle.BLOCK
                    + jsBridgeBundle.getJsBlockName() + "-"
                    + SecurityJsBridgeBundle.METHOD
                    + jsBridgeBundle.getMethodName();
            this.mJsBridges.put(tag, jsBridgeBundle);
        }
    }

    /**
     * 当webchromeClient收到 web的prompt请求后进行拦截判断，用于调起本地android方法
     *
     * @param methodName 方法名称
     * @param blockName  区块名称
     * @return true ：调用成功 ； false ：调用失败
     */
    private boolean onJsPrompt(String methodName, String blockName) {
        String tag = SecurityJsBridgeBundle.BLOCK + blockName + "-"
                + SecurityJsBridgeBundle.METHOD + methodName;
        LogUtil.e(tag);
        if (this.mJsBridges != null && this.mJsBridges.containsKey(tag)) {
            ((SecurityJsBridgeBundle) this.mJsBridges.get(tag)).onCallMethod();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判定当前的prompt消息是否为用于调用native方法的消息
     *
     * @param msg 消息名称
     * @return true 属于prompt消息方法的调用
     */
    private boolean isMsgPrompt(String msg) {
        if (msg != null
                && msg.startsWith(SecurityJsBridgeBundle.PROMPT_START_OFFSET)) {
            return true;
        } else {
            return false;
        }
    }

    // TBS: Do not use @Override to avoid false calls
    public boolean tbs_dispatchTouchEvent(MotionEvent ev, View view) {
        boolean r = super.super_dispatchTouchEvent(ev);
//        LogUtil.d( "dispatchTouchEvent " + ev.getAction() + " "
//                + r);
        return r;
    }

    // TBS: Do not use @Override to avoid false calls
    public boolean tbs_onInterceptTouchEvent(MotionEvent ev, View view) {
        boolean r = super.super_onInterceptTouchEvent(ev);
        return r;
    }

    protected void tbs_onScrollChanged(int l, int t, int oldl, int oldt,
                                       View view) {
        super_onScrollChanged(l, t, oldl, oldt);
    }

    protected void tbs_onOverScrolled(int scrollX, int scrollY,
                                      boolean clampedX, boolean clampedY, View view) {
        // if (getContext() instanceof RefreshActivity) {
        // if (this.tog == null) {
        // this.tog = (TextView) ((Activity)
        // getContext()).findViewById(R.id.refreshText);
        // layoutParams = (RelativeLayout.LayoutParams)
        // (this.tog.getLayoutParams());
        // this.refreshRela = (RelativeLayout) ((Activity)
        // getContext()).findViewById(R.id.refreshPool);
        // }
        // if (isClampedY && !clampedY) {
        // this.reload();
        // }
        // if (clampedY) {
        // this.isClampedY = true;
        //
        // } else {
        // this.isClampedY = false;
        // }
        // }
        super_onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    protected void tbs_computeScroll(View view) {
        super_computeScroll();
    }

    protected boolean tbs_overScrollBy(int deltaX, int deltaY, int scrollX,
                                       int scrollY, int scrollRangeX, int scrollRangeY,
                                       int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent,
                                       View view) {
        // if (getContext() instanceof RefreshActivity) {
        // if (this.isClampedY) {
        // if ((refreshRela.getTop() + (-deltaY)) / 2 < 255) {
        // this.tog.setAlpha((refreshRela.getTop() + (-deltaY)) / 2);
        // } else
        // this.tog.setAlpha(255);
        // this.refreshRela.layout(refreshRela.getLeft(), refreshRela.getTop() +
        // (-deltaY), refreshRela.getRight(),
        // refreshRela.getBottom() + (-deltaY));
        // this.layout(this.getLeft(), this.getTop() + (-deltaY) / 2,
        // this.getRight(),
        // this.getBottom() + (-deltaY) / 2);
        // }
        // }
        return super_overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY,
                isTouchEvent);
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    protected boolean tbs_onTouchEvent(MotionEvent event, View view) {
//         if (getContext() instanceof RefreshActivity) {
//         if (event.getAction() == MotionEvent.ACTION_UP && this.tog != null) {
//         this.isClampedY = false;
//         this.tog.setAlpha(0);
//         this.refreshRela.layout(refreshRela.getLeft(), 0,
//         refreshRela.getRight(), refreshRela.getBottom());
//         this.layout(this.getLeft(), 0, this.getRight(), this.getBottom());
//         }
//
//         }
        return super_onTouchEvent(event);
    }
}
