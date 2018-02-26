package cn.ltwc.cft.net;

import java.util.Map;

import cn.ltwc.cft.entiy.Kaijiang;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by LZL on 2017/1/23.
 */

public interface APIs {
    /**
     * 可以通过该方法请求任何一个接口
     *
     * @param data 请求数据
     * @return 被观察者
     */
    @FormUrlEncoded
    @POST("/")
    Observable<Object> anyPost(@FieldMap Map<String, Object> data);

    @GET("?")
    Observable<Object> anyGet(@QueryMap Map<String, Object> options);

    /**
     * 获取双色球开奖信息
     *
     * @param options
     * @return
     */
    @GET("client/inf/v2/start.do?catid=11&version=5.1.0")
    Observable<Kaijiang> getLot(@QueryMap Map<String, Object> options);

    /**
     * 获取小米天气信息布局
     *
     * @param options
     * @return
     */
    @GET("layout?channel=03-1&r=CN&l=zh_cn")
    Observable<Object> getLaout(@QueryMap Map<String, Object> options);

    /**
     * 获取小米天气信息布局
     *
     * @param options
     * @return
     */
    @GET("page?sysVersion=development&appVersion=102&channelId=1020&imei=2cf2d43cdd80af370087652ec22d62a8&country=CN&language=zh")
    Observable<Object> getLaout2(@QueryMap Map<String, Object> options);

    /**
     * 获取小米天气信息布局详情
     *
     * @param options
     * @return
     */
    @GET("layout?r=CN&l=zh_cn")
    Observable<Object> getLaoutDetail(@QueryMap Map<String, Object> options);

    /**
     * 获取小米天气信息布局详情
     *
     * @param options
     * @return
     */
    @GET("page?sysVersion=development&appVersion=102&imei=2cf2d43cdd80af370087652ec22d62a8&country=CN&language=zh")
    Observable<Object> getLaoutDetail2(@QueryMap Map<String, Object> options);

    /**
     * 获取天气
     *
     * @param options
     * @return
     */
    @POST("weather.php")
    @FormUrlEncoded
    Observable<Object> getWeather(@FieldMap Map<String, Object> options);

    /**
     * 获取历史上的今天
     *
     * @param options
     * @return
     */
    @GET("queryEvent.php")
    Observable<Object> getTodayOnHistory(@QueryMap Map<String, Object> options);

    /**
     * 获取历史上的今天详细
     *
     * @param options
     * @return
     */
    @GET("queryDetail.php")
    Observable<Object> getTodayOnHistoryDetail(@QueryMap Map<String, Object> options);

    /**
     * 获取美女图片
     *
     * @param options
     * @return
     */
    @GET("wisejsonala")
    Observable<Object> getGirlImg(@QueryMap Map<String, Object> options);

    /**
     * 获取笑话图片
     *
     * @param options
     * @return
     */
    @POST("?json=gender/category_article_list_new_v2")
    @FormUrlEncoded
    Observable<Object> getJoke(@FieldMap Map<String, Object> options);
}
