/**
 *
 */
package cn.ltwc.cft.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.entiy.LocationInfo;

/**
 * 辅助工具类
 *
 * @author hongming.wang
 *         创建时间： 2015年11月24日 上午11:46:50
 *         项目名称： AMapLocationDemo2.x
 *         文件名称: Utils.java
 *         类型名称: Utils
 */
public class Utils {
    /**
     * 开始定位
     */
    public final static int MSG_LOCATION_START = 0;
    /**
     * 定位完成
     */
    public final static int MSG_LOCATION_FINISH = 1;
    /**
     * 停止定位
     */
    public final static int MSG_LOCATION_STOP = 2;

    public final static String KEY_URL = "URL";
    public final static String URL_H5LOCATION = "file:///android_asset/location.html";

    /**
     * 根据定位结果返回定位信息的字符串
     *
     * @param location
     * @return
     */
    public synchronized static LocationInfo getLocationStr(AMapLocation location) {
        if (null == location) {
            return null;
        }
        LocationInfo locationInfo = new LocationInfo();
        StringBuffer sb = new StringBuffer();
        // errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
        if (location.getErrorCode() == 0) {
            // sb.append("定位成功" + "\n");
            // sb.append("定位类型: " + location.getLocationType() + "\n");
            sb.append("经    度    : " + location.getLongitude() + "\n");
            sb.append("纬    度    : " + location.getLatitude() + "\n");
            // sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
            // sb.append("提供者    : " + location.getProvider() + "\n");
            locationInfo.setLat(location.getLatitude() + "");
            locationInfo.setLon(location.getLongitude() + "");
            if (location.getProvider().equalsIgnoreCase(
                    android.location.LocationManager.GPS_PROVIDER)) {
                // 以下信息只有提供者是GPS时才会有
                // sb.append("速    度    : " + location.getSpeed() + "米/秒" +
                // "\n");
                // sb.append("角    度    : " + location.getBearing() + "\n");
                // // 获取当前提供定位服务的卫星个数
                // sb.append("星    数    : "
                // + location.getSatellites() + "\n");
            } else {
                // 提供者是GPS时是没有以下信息的
                // sb.append("国    家    : " + location.getCountry() + "\n");
                // sb.append("省            : " + location.getProvince() + "\n");
                sb.append(location.getCity());
                locationInfo.setCityName(location.getCity());
                // sb.append("城市编码 : " + location.getCityCode() + "\n");
                sb.append("区            : " + location.getDistrict() + "\n");
                locationInfo.setDistrict(location.getDistrict());
                // sb.append("区域 码   : " + location.getAdCode() + "\n");
                sb.append("地    址    : " + location.getAddress() + "\n");
                locationInfo.setAddress(location.getAddress());
                locationInfo.setRoad(location.getRoad());
                locationInfo.setStreet(location.getStreet());
                // sb.append("兴趣点    : " + location.getPoiName() + "\n");
                // //定位完成的时间
                // sb.append("定位时间: " + formatUTC(location.getTime(),
                // "yyyy-MM-dd HH:mm:ss") + "\n");
            }
        } else {
            // 定位失败
            sb.append("定位失败");
//            locationInfo.setCityName("杭州市");
//            locationInfo.setLat("30.308812");
//            locationInfo.setLon("120.0953");
            locationInfo = null;
            // sb.append("错误码:" + location.getErrorCode() + "\n");
            // sb.append("错误信息:" + location.getErrorInfo() + "\n");
            // sb.append("错误描述:" + location.getLocationDetail() + "\n");
        }
        // 定位之后的回调时间
        // sb.append("回调时间: " + formatUTC(System.currentTimeMillis(),
        // "yyyy-MM-dd HH:mm:ss") + "\n");
        return locationInfo;
    }

    private static SimpleDateFormat sdf = null;

    public synchronized static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }

    public static boolean isNull(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNull(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static boolean notNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isJsonString(String str) {
        boolean isJsonStr;
        try {
            JSONObject jsonObject = new JSONObject(str);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            isJsonStr = false;
        }
        try {
            JSONArray jsonArray = new JSONArray(str);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            isJsonStr = false;
        }
        return isJsonStr;
    }

    /**
     * 返回app是否进入过后台 ：返回false说明到后台
     *
     * @return
     */
    public static boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = MyApplication.getInstance().getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将json格式的字符串解析成Map对象 <li>
     * json格式：{"name":"admin","retries":"3fff","testname"
     * :"ddd","testretries":"fffffffff"}
     */
    public static <T> HashMap<String, T> jsonToMap(String json) {
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = null;
        HashMap<String, T> hashMap = new HashMap<>();
        try {
            jsonObject = new JSONObject(json);
            Iterator it = jsonObject.keys();
            // 遍历jsonObject数据，添加到Map对象
            String key;
            Object value;
            while (it.hasNext()) {
                key = String.valueOf(it.next());
                value = jsonObject.get(key);
                if (isNull(hashMap)) {
                    hashMap = new HashMap<>();
                }
                hashMap.put(key, (T) value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     * 将Map转换成Json
     *
     * @param jsonMap
     * @return
     * @throws Exception
     */
    public static String mapToJson(Map<String, Object> jsonMap) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("{");
        Iterator<Map.Entry<String, Object>> entryIterator = jsonMap.entrySet().iterator();
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                System.out.println("\n Object Key : " + key);
                String mapJson = mapToJson((Map<String, Object>) value);
                result.append("\"").append(key).append("\":").append(mapJson);
            } else {
                Gson gson = new Gson();
                String json = gson.toJson(value);
                result.append("\"").append(key).append("\":").append(json);
            }
            // 最后一个去掉 逗号
            entryIterator.next();
            if (entryIterator.hasNext()) {
                result.append(",");
            }
        }
        result.append("}");
        return result.toString();
    }

    public static String unicodeToChinese(String dataStr) {
        final StringBuilder buffer = new StringBuilder();
        Pattern p = Pattern.compile("\\\\u([\\S]{4})([^\\\\]*)");
        Matcher match = p.matcher(dataStr);
        if (match.find()) {
            while (match.find()) {
                char letter = (char) Integer.parseInt(match.group(1), 16);
                buffer.append(Character.toString(letter));
                buffer.append(match.group(2));
            }
        } else {
            buffer.append(dataStr);
        }

        return buffer.toString();
    }

}
