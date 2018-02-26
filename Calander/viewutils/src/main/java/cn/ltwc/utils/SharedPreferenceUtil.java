package cn.ltwc.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import cn.ltwc.ViewUtils;

/**
 * SharedPreference操作类
 * 配置文件存储
 */
public class SharedPreferenceUtil {
    /**
     * SharedPreference文件名
     */
    private static final String SHARED_FILE = "file";

    /**
     * 添加数据到sharedPreference
     */
    public static void put(String key, Object value) {
        try {
            SharedPreferences sharedPreferences = ViewUtils.getApplicationContext().getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else {
                editor.putString(key, value.toString());
            }
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取sharedPreference中存储的数据
     */
    public static Object get(String key, Object defValue) {
        try {
            SharedPreferences sharedPreferences = ViewUtils.getApplicationContext().getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE);
            if (defValue instanceof Integer) {
                return sharedPreferences.getInt(key, (Integer) defValue);
            } else if (defValue instanceof Boolean) {
                return sharedPreferences.getBoolean(key, (Boolean) defValue);
            } else if (defValue instanceof Float) {
                return sharedPreferences.getFloat(key, (Float) defValue);
            } else if (defValue instanceof Long) {
                return sharedPreferences.getLong(key, (Long) defValue);
            } else {
                return sharedPreferences.getString(key, defValue.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 判断某个key是否存在
     */
    public static boolean contains(String key) {
        try {
            SharedPreferences sharedPreferences = ViewUtils.getApplicationContext().getSharedPreferences(SHARED_FILE,
                    Context.MODE_PRIVATE);
            return sharedPreferences.contains(key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 删除某个键值对
     */
    public static void remove(String key) {
        try {
            SharedPreferences sharedPreferences = ViewUtils.getApplicationContext().getSharedPreferences(SHARED_FILE,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (sharedPreferences.contains(key)) {
                editor.remove(key);
            }
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 清空所有数据
     */
    public static void clear() {
        try {
            SharedPreferences sharedPreferences = ViewUtils.getApplicationContext().getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 返回所有键值对
     */
    public static HashMap<String, ?> getAll() {
        try {
            SharedPreferences sharedPreferences = ViewUtils.getApplicationContext().getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE);
            return (HashMap<String, ?>) sharedPreferences.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
