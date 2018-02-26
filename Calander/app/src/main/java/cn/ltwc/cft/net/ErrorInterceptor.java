package cn.ltwc.cft.net;


import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.utils.LogUtil;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.Okio;

/**
 * ErrorInterceptor
 * Created by LZL on 2017/1/23.
 */

public class ErrorInterceptor implements Interceptor {
    String postData;
    private String url = "";

    @Override
    public synchronized Response intercept(Chain chain) throws IOException {
        try {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Request.Builder builder = request.newBuilder();
            String body = bodyToJson(request.body());
            postData = body;
            if (request.method().equals("GET")) {
                if (needChangeUserAgent(request)) {
                    request = builder.removeHeader("User-Agent").addHeader("User-Agent",
                            "Dalvik/2.1.0 (Linux; U; Android 5.0.2; MI 2S MIUI/7.11.9)").build();
                }
                builder.method(request.method(), null);
            } else {
                builder.method(request.method(), request.body());
            }
            Request actualRequest;//实际的请求
            actualRequest = builder.url(chain.request().url() + url).build();
            url = "";
            String requestUrl = actualRequest.url().toString();
            String[] split = requestUrl.split("\\?");
            if (split[0].endsWith("/")) {
                split[0] = split[0].substring(0, split[0].length() - 1);
            }
            if (split.length > 1 && split[1].startsWith("&")) {
                split[1] = split[1].substring(1, split[1].length());
            }
            String dealUrl;
            if (split.length > 1) {
                dealUrl = split[0] + "?" + split[1];
            } else {
                dealUrl = split[0];
            }
            actualRequest = builder.url(dealUrl).build();
            Response response = chain.proceed(actualRequest);
            String resultData = response.body().string();
            if (response.code() == 200) {
                RealResponseBody newBody = new RealResponseBody(response.headers(), Okio.buffer(Okio.source(new ByteArrayInputStream(resultData.getBytes()))));
                long t2 = System.nanoTime();
                LogUtil.e(String.format(Locale.getDefault(), "\n" +
                        "请求链接:%s\n" +
                        "耗时%.1fms\n" +
                        "传入数据:%s\n" +
                        "实际传入:%s\n" +
                        "返回数据:%s\n", response.request().url(), (t2 - t1) / 1e6d, body, postData, resultData));
                if (Utils.isJsonString(resultData)) {
                    return response.newBuilder().headers(response.headers()).body(newBody).build();
                } else {
                    throw new ErrorException(99999);
                }
            } else {
                long t2 = System.nanoTime();
                LogUtil.e(String.format(Locale.getDefault(), "\n" +
                        "请求链接:%s\n" +
                        "耗时%.1fms\n" +
                        "传入数据:%s\n" +
                        "实际传入:%s\n" +
                        "错误信息:%s\n", request.url(), (t2 - t1) / 1e6d, body, postData, resultData));

                throw new ErrorException(response.code());
            }
        } catch (Throwable e) {
            throw new ErrorException(99999);
        }
    }


    private String bodyToJson(Object body) {
        String json = "";
        try {
            if (body instanceof FormBody) {
                FormBody formBody = (FormBody) body;
                HashMap<String, Object> map = new HashMap<>();
                for (int i = 0; i < formBody.size(); i++) {
                    if (formBody.name(i).equals("UncertainURL")) {
                        this.url = formBody.value(i);
                    } else {
                        map.put(formBody.name(i), formBody.value(i));
                    }
                }
                Gson gson = new Gson();
                json = gson.toJson(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return json;
    }

    public boolean needChangeUserAgent(Request request) {
        if (request.url().toString().contains(Constant.GET_XIAO_MI_LAYOUT2)) {
            return true;
        }
        return false;
    }
}

