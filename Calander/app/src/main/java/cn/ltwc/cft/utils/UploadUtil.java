package cn.ltwc.cft.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.utils.LogUtil;

/**
 * Description: 上传日志的工具类
 * Created on 2018/8/31 0031 09:22:48
 * author:Ahuangshang
 */

@SuppressWarnings("ALL")
public class UploadUtil {
    /**
     * 用来存储设备信息和异常信息
     */
    private Map<String, String> infos;
    private Context mContext;

    public static final String ERROR = "error";
    public static final String WARN = "warn";
    public static final String INFO = "info";


    private UploadUtil() {
        mContext = MyApplication.getInstance();
        infos = new HashMap<String, String>();
    }


    private static class SingletonHolder {
        private static final UploadUtil INSTANCE = new UploadUtil();
    }

    public static UploadUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public synchronized void upload(Throwable ex, String type) {
        collectDeviceInfo(ex);
        infos.put("type", type);
        doUpload();
    }

    private synchronized void doUpload() {


    }

    /**
     * 收集设备参数信息
     */
    public void collectDeviceInfo(Throwable ex) {
        try {
            infos.put("pkgName", mContext.getPackageName());
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                infos.put("version", versionName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e("an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        Map<String, String> info = new HashMap<String, String>();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                info.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                LogUtil.e("an error occured when collect crash info", e);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        sb.append(writer.toString());
        infos.put("info", sb.toString());

    }
}
