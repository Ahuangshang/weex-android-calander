package cn.ltwc.cft.weex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.tencent.smtt.sdk.WebView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.ltwc.cft.R;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.cft.view.ChooseView;
import cn.ltwc.cft.x5web.utils.X5WebView;
import cn.ltwc.utils.LogUtil;
import cn.ltwc.utils.SharedPreferenceUtil;
import cn.ltwc.viewutils.dialogutils.DialogUtil;
import rx.functions.Action1;

import static cn.ltwc.cft.data.Constant.DEBUG;
import static cn.ltwc.cft.data.Constant.DEFAULT_HOST_NAME;
import static cn.ltwc.cft.view.ChooseView.SELECT_WEEX_URL;

/**
 * 带下拉刷新的界面
 * Created by LZL on 2017/11/1.
 */

public class WeexUtil implements IWXRenderListener {
    private String serve = DEFAULT_HOST_NAME + "Ahuangshang/android/";
    //private String serve = "https://ahuangshang.github.io/MyWebsite/android";
    WXSDKInstance mWXSDKInstance;
    private ViewGroup parent;
    private Action1<View> viewLoadSuccessListener;//页面和数据加载成功
    private Action1<String> viewReLoadListener;//js界面重新加载的监听
    private boolean viewCreateSuccess;//js界面渲染成功为true，否则为false。
    private boolean setDateViewCreate = true;
    private Object[] data;
    private String[] type;
    private View weexView;
    private View loadingView, errorView;
    private Context context;
    private String jsName;
    private Map<String, Object> options;
    private List<String> prefix;
    private ImageView errorImg;
    private TextView errorDec, errorAgain;
    private String h5Server = DEFAULT_HOST_NAME + "Ahuangshang/html/";
    //private String h5Server = "https://ahuangshang.github.io/MyWebsite/html/";
    //private String h5Server = "http://192.168.31.150:8081/dist/web/";
    private boolean showLoad, showError;

    public Action1<String> getViewReLoadListener() {
        return viewReLoadListener;
    }

    public void setViewReLoadListener(Action1<String> viewReLoadListener) {
        this.viewReLoadListener = viewReLoadListener;
    }

    public WeexUtil(boolean showLoad, boolean showError, String jsName, Map<String, Object> options, ViewGroup group, Context context) {
        this.showLoad = showLoad;
        this.showError = showError;
        this.jsName = jsName;
        this.context = context;
        this.parent = group;
        this.options = options;
        init();
        loadUrl();
    }

    public WeexUtil(String jsName, Map<String, Object> options, ViewGroup group, Context context) {
        this(true, true, jsName, options, group, context);
    }

    @SuppressLint("InflateParams")
    private void init() {
        errorView = LayoutInflater.from(context).inflate(R.layout.weex_load_error, null);
        loadingView = LayoutInflater.from(context).inflate(R.layout.weex_loading, null);
        initErrorView();
    }

    private void initErrorView() {
        errorImg = (ImageView) errorView.findViewById(R.id.img);
        errorDec = (TextView) errorView.findViewById(R.id.dec);
        errorAgain = (TextView) errorView.findViewById(R.id.try_again);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUrl();
            }
        });
        errorView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                selectWeexUrl();
                return false;
            }
        });
    }

    private void loadUrl() {
        parent.removeAllViews();
        if (showLoad) {
            parent.addView(loadingView);
        }
        if (!TextUtils.isEmpty(jsName)) {
            if (mWXSDKInstance != null) {
                mWXSDKInstance.destroy();
            }
            mWXSDKInstance = new WXSDKInstance(context);
            mWXSDKInstance.registerRenderListener(this);
            read();
        }
    }

    /**
     * 读取js文件,如果是读取assets目录下的js文件，则jsName不包含  .js 后缀
     */
    private void read() {
        //读取服务端的js文件
        setDateViewCreate = false;
        mWXSDKInstance.renderByUrl(context.getPackageName(), getUrl(), options, null, WXRenderStrategy.APPEND_ASYNC);
    }

    public void setData(Object... data) {
        this.data = data;
    }

    public void setType(String... type) {
        this.type = type;
    }

    public void fireFresh() {
        fireFresh(true);
    }

    public void fireFresh(boolean addViewDelay) {
        if (viewCreateSuccess) {
            setDateViewCreate = true;
            if (Utils.notNull(data) && Utils.notNull(type) && data.length == type.length) {
                for (int i = 0; i < data.length; i++) {
                    setData(data[i], type[i], addViewDelay);
                }
            } else {
                if (Utils.isNull(data) && Utils.isNull(type)) {
                    setData(null, null, addViewDelay);
                } else {
                    throw new RuntimeException("data type must have same length");
                }
            }
        } else {
            setDateViewCreate = false;
        }

    }

    private void setData(Object data, String type, boolean addViewDelay) {
        fireGlobalEvent(data, type, addViewDelay);
    }

    public void setViewLoadSuccessListener(Action1<View> viewLoadSuccessListener) {
        this.viewLoadSuccessListener = viewLoadSuccessListener;
    }

    private void fireGlobalEvent(Object data, String type, boolean addViewDelay) {
        if (mWXSDKInstance != null && Utils.notNull(data)) {
            Map<String, Object> params = new HashMap<>();
            params.put("jsonData", data);
            //触发刷新操作 只更新数据 不重载页面
            mWXSDKInstance.fireGlobalEventCallback(type, params);
        }
        if (weexView != null) {
            addView(weexView, addViewDelay);
            if (viewLoadSuccessListener != null) {
                viewLoadSuccessListener.call(weexView);
            }
        }

    }

    public String getUrl() {
        String prefix = (String) SharedPreferenceUtil.get("prefix_weex_url", "");
        String url = "";
        if (TextUtils.isEmpty(prefix)) {
            url = serve + HLUtil.getVersionName() + File.separator + jsName + ".weex.js";
        } else {
            if (prefix.contains("ahuangshang.github.io") || prefix.contains("imengu.cn")) {
                url = serve + HLUtil.getVersionName() + File.separator + jsName + ".weex.js";
            } else {
                url = prefix + jsName + ".weex.js?hot-reload_controller=1&_wx_tpl=" + prefix + jsName + ".weex.js";
            }
        }
        return url;
    }

    @Override
    public void onViewCreated(WXSDKInstance wxsdkInstance, View view) {
        weexView = view;
    }

    @Override
    public void onRenderSuccess(WXSDKInstance wxsdkInstance, int i, final int i1) {
        viewCreateSuccess = true;
        if (!setDateViewCreate) {
            fireFresh();
        }
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance wxsdkInstance, int i, int i1) {
    }

    @Override
    public void onException(WXSDKInstance wxsdkInstance, String errorCode, String msg) {
        LogUtil.e("errorCode=" + errorCode + "\nmsg=" + msg + "\n" + wxsdkInstance.getBundleUrl());
//        if (DEBUG) {
//            loadLocal();
//            //loadH5();
//        } else {
        if (!errorCode.equals("-1002")) {
            if (showError) {
                showErrorUnSopport();
                addView(errorView);
            }
        } else {
            viewCreateSuccess = false;
            if (showError) {
                DialogUtil dialogUtil = new DialogUtil(new WeakReference<Activity>((Activity) context));
                dialogUtil.setTop("提示").setContent(msg).setButtonShowType(DialogUtil.BUTTON_TYPE_ONE_BTN).show();
                addView(errorView);
            }

        }
//        }

    }

    private void loadLocal() {
        if (mWXSDKInstance != null) {
            mWXSDKInstance.destroy();
        }
        mWXSDKInstance = new WXSDKInstance(context);
        mWXSDKInstance.registerRenderListener(this);

        mWXSDKInstance.renderByUrl(jsName, "file:///js/" + jsName + ".weex.js", options, null, WXRenderStrategy.APPEND_ASYNC);
    }

    private void loadH5() {
        parent.removeAllViews();
        X5WebView webView = new X5WebView(context, null);
        webView.setLoadSuccessListener(new Action1<WebView>() {
            @Override
            public void call(WebView view) {
                parent.removeView(loadingView);
            }
        });
        parent.addView(webView);
        ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        webView.setLayoutParams(layoutParams);
        if (showLoad) {
            parent.addView(loadingView);
        }
        webView.loadUrl(getHtmlUrl(h5Server + jsName + ".html"));
    }

    private String getHtmlUrl(String shareUrl) {
        if (Utils.notNull(options)) {
            shareUrl += "?";
            Iterator<Map.Entry<String, Object>> entryIterator = options.entrySet().iterator();
            String key;
            String value;
            StringBuilder shareUrlBuilder = new StringBuilder(shareUrl);
            for (Map.Entry<String, Object> entry : options.entrySet()) {

                key = entry.getKey();
                value = (String) entry.getValue();
                value = URLEncoder.encode(value);
                if (key.equals("hot-reload_controller") || key.equals("_wx_tpl") || key.equals("bundleUrl")) {
                    continue;
                }
                shareUrlBuilder.append(key).append("=").append(value).append("&");
            }
            shareUrl = shareUrlBuilder.toString();
            shareUrl = shareUrl.substring(0, shareUrl.length() - 1);
        }
        return shareUrl;
    }

    private void addView(View view) {
        addView(view, true);
    }

    private void addView(final View view, boolean addViewDelay) {
        parent.removeAllViews();
        if (addViewDelay) {
            view.setVisibility(View.INVISIBLE);
        }
        parent.addView(view);
        if (view instanceof X5WebView) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(layoutParams);
        }
        if (addViewDelay) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.VISIBLE);
                }
            }, 100);
        }
    }

    public void selectWeexUrl() {
        if (DEBUG) {
            if (prefix == null) {
                prefix = new ArrayList<>();
                prefix.add(serve);
//                prefix.add("http://192.168.88.101:8081/");
//                prefix.add("http://192.168.88.102:8081/");
//                prefix.add("http://192.168.88.103:8081/");
//                prefix.add("http://192.168.88.104:8081/");
//                prefix.add("http://192.168.88.105:8081/");
                prefix.add("http://192.168.88.106:8081/");

            }
            ChooseView chooseView = new ChooseView(context, SELECT_WEEX_URL);
            chooseView.setList(prefix);
            chooseView.setChooseBack(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    SharedPreferenceUtil.put("prefix_weex_url", prefix.get(integer) + "dist/weex/");
                    loadUrl();
                }
            });
            chooseView.setSearchCall(new Action1<String>() {
                @Override
                public void call(String s) {
                    SharedPreferenceUtil.put("prefix_weex_url", s);
                    loadUrl();
                }
            });
            chooseView.show();
        }
    }

    private void showErrorUnSopport() {
        errorImg.setImageResource(R.drawable.empty);
        errorDec.setText("十分抱歉，加载的时候出了一些问题,点击按钮可以加载网页版，但是可能会有一些问题。");
        errorAgain.setText("点击加载");
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadH5();
            }
        });

    }
}
