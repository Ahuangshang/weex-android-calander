package cn.ltwc.cft.net;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.entiy.Kaijiang;
import cn.ltwc.cft.entiy.LocationInfo;
import cn.ltwc.cft.retrofit2.converter.string.StringConverterFactory;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * APIService
 * Created by LZL on 2017/1/23.
 */
public class APIService {
    private static final int DEFAULT_TIMEOUT = 15;
    private APIs apis;
    private Dispatcher dispatcher;
    public static int backParseData = -1;//返回解析的数据
    public static int backStringData = 1;//返回没有解析的数据

    private APIService(String baseUrl, int flag) {
        dispatcher = new Dispatcher(Executors.newFixedThreadPool(20));
        dispatcher.setMaxRequests(20);
        dispatcher.setMaxRequestsPerHost(1);

        OkHttpClient client = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new ErrorInterceptor())
                .build();
        Retrofit retrofit;
        if (flag == backParseData) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(StringConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        }

        apis = retrofit.create(APIs.class);
    }

    public static APIService getInstance(String baseUrl) {
        return new APIService(baseUrl, -1);
    }

    public static APIService getInstance(String baseUrl, int flag) {
        return new APIService(baseUrl, flag);
    }

    private <T> void toSubscribe(Observable<T> o, final Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    private class resultFunc implements Func1<Object, Object> {
        @Override
        public Object call(Object result) {
            return result;
        }
    }

    public void anyPost(HashMap<String, Object> params, String api, Subscriber<Object> subscriber) {
        params.put("UncertainURL", api);
        Observable observable = apis.anyPost(params).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void anyGet(HashMap<String, Object> params, String api, Subscriber<Object> subscriber) {
        Observable observable = apis.anyGet(params).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getLot(Subscriber<Kaijiang> subscriber) {
        Observable observable = apis.getLot(new HashMap<String, Object>() {
            {
            }
        }).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getLayout(final String cityCode, Subscriber<Object> subscriber) {
        Observable observable = apis.getLaout2(new HashMap<String, Object>() {
            {
                put("locationKey", "weathercn:" + cityCode);
            }
        }).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getLaoutDetail(final String channelId, Subscriber<Object> subscriber) {
        Observable observable = apis.getLaoutDetail2(new HashMap<String, Object>() {
            {
                put("channelId", channelId);
                put("locationKey", "weathercn:" + MyApplication.getInstance().getCityCode());
            }
        }).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getWeather(final LocationInfo locationInfo, Subscriber<Object> subscriber) {
        Observable observable = apis.getWeather(new HashMap<String, Object>() {
            {
                put("type", "0");
                put("lat", locationInfo.getLat());
                put("lon", locationInfo.getLon());
                put("city", locationInfo.getCityName());
            }
        }).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getTodayOnHistory(final String date, Subscriber<Object> subscriber) {
        Observable observable = apis.getTodayOnHistory(new HashMap<String, Object>() {
            {
                put("key", "4b38076dc77166f1d610d1697315c07d");
                put("date", date);
            }
        }).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getTodayOnHistoryDetail(final String eId, Subscriber<Object> subscriber) {
        Observable observable = apis.getTodayOnHistoryDetail(new HashMap<String, Object>() {
            {
                put("key", "4b38076dc77166f1d610d1697315c07d");
                put("e_id", eId);
            }
        }).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getGirlImg(final int pagerNum, final String query, Subscriber<Object> subscriber) {
        Observable observable = apis.getGirlImg(new HashMap<String, Object>() {
            {
                //tn=wisejsonala&word=赵丽颖pn=0&rn=2
                put("tn", "wisejsonala");
                put("pn", pagerNum);
                put("rn", Constant.PAGE_SIZE);
                put("word", query);
            }
        }).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getJoke(final boolean isLoadMore, final long beforeDate, final int categoryId, Subscriber<Object> subscriber) {
        Observable observable = apis.getJoke(new HashMap<String, Object>() {
            {
                if (isLoadMore) {
                    put("beforeDate", beforeDate);
                } else {
                    put("afterDate", System.currentTimeMillis());
                }
                put("categoryId", categoryId);
            }
        }).map(new resultFunc());
        toSubscribe(observable, subscriber);
    }

}
