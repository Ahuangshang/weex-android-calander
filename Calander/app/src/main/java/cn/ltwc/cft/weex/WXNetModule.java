package cn.ltwc.cft.weex;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import cn.ltwc.cft.net.APIService;
import cn.ltwc.cft.net.ProgressSubscriber;
import cn.ltwc.cft.utils.Utils;
import rx.functions.Action1;

/**
 * WXNetModule
 * Created by Queen on 2018/1/4 0004.
 */

public class WXNetModule extends WXModule {
    /**
     * @param methodType 请求的方法名（get、post等）
     * @param url        请求的URL地址
     * @param jsonParams 请求的json类型的参数
     * @param callback   返回的回调
     */
    @JSMethod
    public void requestNetData(String methodType, String url, String api, String jsonParams, boolean showLoading, final JSCallback callback) {
        if (methodType.toLowerCase().equals("get")) {
            APIService.getInstance(url, APIService.backStringData).anyGet(Utils.jsonToMap(jsonParams), api, new ProgressSubscriber<Object>(new Action1<Object>() {
                @Override
                public void call(Object o) {
                    callback.invokeAndKeepAlive(o.toString());
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    callback.invokeAndKeepAlive(null);

                }
            }, showLoading));
        } else if (methodType.toLowerCase().equals("post")) {
            APIService.getInstance(url, APIService.backStringData).anyPost(Utils.jsonToMap(jsonParams), api, new ProgressSubscriber<Object>(new Action1<Object>() {
                @Override
                public void call(Object o) {
                    callback.invokeAndKeepAlive(o.toString());
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    callback.invokeAndKeepAlive(null);
                }
            }, showLoading));

        }

    }
}
