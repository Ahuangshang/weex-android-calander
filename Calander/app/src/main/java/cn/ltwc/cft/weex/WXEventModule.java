package cn.ltwc.cft.weex;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.tencent.smtt.sdk.TbsVideo;

import java.lang.ref.WeakReference;

import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.R;
import cn.ltwc.cft.activity.MyX5WebView;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.DownLoadUtil;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.utils.SchemeUtil;
import cn.ltwc.cft.x5web.utils.X5WebView;
import cn.ltwc.utils.SharedPreferenceUtil;
import cn.ltwc.utils.ToastUtil;
import cn.ltwc.viewutils.dialogutils.BtnClickListener;
import cn.ltwc.viewutils.dialogutils.DialogUtil;

import static cn.ltwc.cft.data.Constant.JS_NAME;
import static cn.ltwc.cft.data.Constant.SHARE_URL;
import static cn.ltwc.cft.data.Constant.WEBTITLE;

/**
 * 跳转操作
 * Created by LZL on 2017/9/8.
 */

public class WXEventModule extends WXModule {
    private X5WebView webView;

    public WXEventModule() {
    }

    public WXEventModule(X5WebView webView) {
        this.webView = webView;
        //new WXNetModule(webView);
    }

    @JSMethod(uiThread = true)
    @JavascriptInterface
    public void openWeexView(String viewName, String title) {
        if (TextUtils.isEmpty(viewName)) {
            return;
        }
        Intent intent = new Intent(mWXSDKInstance.getContext(), WeexActivity.class);
        intent.putExtra(JS_NAME, viewName);
        intent.putExtra(WEBTITLE, title);
        mWXSDKInstance.getContext().startActivity(intent);
        ((Activity) mWXSDKInstance.getContext()).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @JSMethod(uiThread = true)
    @JavascriptInterface
    public void openWeexView(String viewName, String title, String shareUrl) {
        if (TextUtils.isEmpty(viewName)) {
            return;
        }
        Intent intent = new Intent(mWXSDKInstance.getContext(), WeexActivity.class);
        intent.putExtra(JS_NAME, viewName);
        intent.putExtra(WEBTITLE, title);
        intent.putExtra(SHARE_URL, shareUrl);
        mWXSDKInstance.getContext().startActivity(intent);
        ((Activity) mWXSDKInstance.getContext()).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @JSMethod(uiThread = true)
    @JavascriptInterface
    public void openWebView(String webUrl, String title) {
        if (TextUtils.isEmpty(webUrl)) {
            return;
        }
        Intent intent = new Intent(mWXSDKInstance.getContext(), MyX5WebView.class);
        intent.putExtra(Constant.WEBURL,
                webUrl);
        intent.putExtra(Constant.WEBTITLE, title);
        intent.putExtra(Constant.CAN_GO_BACK, false);
        intent.putExtra(Constant.BAR_SHOW, false);
        mWXSDKInstance.getContext().startActivity(intent);
        ((Activity) mWXSDKInstance.getContext()).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @JSMethod(uiThread = true)
    @JavascriptInterface
    public void openWebView(String webUrl, String title, String shareUrl) {
        if (TextUtils.isEmpty(webUrl)) {
            return;
        }
        Intent intent = new Intent(mWXSDKInstance.getContext(), MyX5WebView.class);
        intent.putExtra(Constant.WEBURL,
                webUrl);
        intent.putExtra(Constant.WEBTITLE, title);
        intent.putExtra(Constant.CAN_GO_BACK, false);
        intent.putExtra(Constant.BAR_SHOW, false);
        mWXSDKInstance.getContext().startActivity(intent);
        ((Activity) mWXSDKInstance.getContext()).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @JSMethod(uiThread = true)
    @JavascriptInterface
    public void openView(String uri) {
        if (!TextUtils.isEmpty(uri)) {
            SchemeUtil.getInstance().jump(uri);
        }
    }

    @JSMethod
    @JavascriptInterface
    public void getFilePath(String name, String type, JSCallback callback) {
        if (webView != null) {
            webView.loadUrl("javascript:getReturnData('getFilePath','" + "file:///android_asset/" + name + "." + type + "')");
        } else {
            callback.invokeAndKeepAlive("file:///android_asset/" + name + "." + type);
        }
    }

    @JSMethod
    @JavascriptInterface
    public void getVersion(JSCallback callback) {
        if (webView != null) {
            webView.loadUrl("javascript:getReturnData('getVersion','" + HLUtil.getVersionInfo() + "')");
        } else {
            callback.invokeAndKeepAlive(HLUtil.getVersionInfo());
        }
    }

    @JSMethod
    @JavascriptInterface
    public void update(final String url) {
        DialogUtil dialogUtil = new DialogUtil(new WeakReference<Activity>((Activity) mWXSDKInstance.getContext()));
        dialogUtil.setTop("提示").setContent("下载新版本").setButtonShowType(DialogUtil.BUTTON_TYPE_TWO_BTN).setLeftBtn("取消").setRightBtn("立即下载").setRightBtnClickListener(new BtnClickListener() {
            @Override
            public void btnClick() {
                DownLoadUtil.downLoad(url);
                ToastUtil.showMessage("应用后台下载中。。。");
            }
        }).show();

    }

    @JSMethod
    @JavascriptInterface
    public void showDialogKnow(String title,String content) {
        DialogUtil dialogUtil = new DialogUtil(new WeakReference<Activity>((Activity) mWXSDKInstance.getContext()));
        dialogUtil.setTop("提示").setContent("下载新版本").setButtonShowType(DialogUtil.BUTTON_TYPE_ONE_BTN).setTop(title).setContent(content).show();
    }


    /**
     * 存取App的一些参数设置
     *
     * @param adImgUrl    广告图片的url
     * @param adSchemeUrl 广告图片点击跳转的链接
     */
    @JSMethod
    @JavascriptInterface
    public void setConfig(String adImgUrl, String adSchemeUrl) {
        SharedPreferenceUtil.put(Constant.AD_IMG_URL, adImgUrl);
        SharedPreferenceUtil.put(Constant.AD_SCHEME_URL, adSchemeUrl);
    }

    @JSMethod
    @JavascriptInterface
    public void playVideo(String url) {
        if (TbsVideo.canUseTbsPlayer(AppManager.getAppManager().currentActivity())) {
            TbsVideo.openVideo(AppManager.getAppManager().currentActivity(), url);
        }
    }

    @JavascriptInterface
    public void requestNetData(String methodType, String url, String api, String jsonParams, boolean showLoading) {
        new WXNetModule().requestNetData(methodType, url, api, jsonParams, showLoading, new JSCallback() {
            @Override
            public void invoke(Object data) {

            }

            @Override
            public void invokeAndKeepAlive(Object data) {
                webView.loadUrl("javascript:getReturnData('requestNetData','" + data.toString().replace("\\", "\\\\") + "')");
            }
        });
    }
}
