package cn.ltwc.cft.utils;

import android.content.Intent;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.R;
import cn.ltwc.utils.LogUtil;
import cn.ltwc.utils.ToastUtil;

/**
 * Created by Queen on 2018/1/28 0028.
 */

public class SchemeUtil {
    private static class SingletonHolder {
        private static SchemeUtil instance = new SchemeUtil();
    }

    public static SchemeUtil getInstance() {
        return SingletonHolder.instance;
    }

    public void jump(String url) {
        if (!TextUtils.isEmpty(url)) {
            try {
                dealJump(url);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                ToastUtil.showMessage("没有找到对应的页面");
            } catch (Exception e) {
                LogUtil.e("***" + e.toString() + "****" + e.getMessage());
                ToastUtil.showMessage(e.toString());
            }
        }

    }

    private void dealJump(String url) throws ClassNotFoundException {
        HashMap<String, Object> params = getParams(url);
        String className = params.get("className").toString();
        Class viewType = Class.forName(className);
        Intent intent = new Intent(AppManager.getAppManager().currentActivity(), viewType);
        HashMap<String, Class> viewFields = getClassFields(viewType);
        Set<Map.Entry<String, Object>> entrySet = params.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            Class filed = viewFields.get(entry.getKey());
            if (filed != null) {
                String filedString = filed.toString().replace("class ", "");
                switch (filedString) {
                    case "java.lang.String": {
                        intent.putExtra(entry.getKey(), entry.getValue().toString());
                        break;
                    }
                    case "int": {
                        intent.putExtra(entry.getKey(), Integer.valueOf(entry.getValue().toString()).intValue());
                        break;
                    }
                    case "java.lang.Integer": {
                        intent.putExtra(entry.getKey(), Integer.valueOf(entry.getValue().toString()));
                        break;
                    }
                    case "long": {
                        intent.putExtra(entry.getKey(), Long.valueOf(entry.getValue().toString()).longValue());
                        break;
                    }
                    case "java.lang.Long": {
                        intent.putExtra(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                        break;
                    }
                    case "float": {
                        intent.putExtra(entry.getKey(), Float.valueOf(entry.getValue().toString()).floatValue());
                        break;
                    }
                    case "java.lang.Float": {
                        intent.putExtra(entry.getKey(), Float.valueOf(entry.getValue().toString()));
                        break;
                    }
                    case "double": {
                        intent.putExtra(entry.getKey(), Double.valueOf(entry.getValue().toString()).doubleValue());
                        break;
                    }
                    case "java.lang.Double": {
                        intent.putExtra(entry.getKey(), Double.valueOf(entry.getValue().toString()));
                        break;
                    }
                    case "boolean": {
                        intent.putExtra(entry.getKey(), entry.getValue().toString().equals("1"));
                        break;
                    }
                    case "java.lang.Boolean": {
                        intent.putExtra(entry.getKey(), Boolean.valueOf(entry.getValue().toString().equals("1")));
                        break;
                    }
                }
            }
        }
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppManager.getAppManager().getCurrentActivity().startActivity(intent);
        if (className.contains("ShareActivity")) {
            AppManager.getAppManager().getCurrentActivity().overridePendingTransition(R.anim.share_open_in_anim, 0);
        } else {
            AppManager.getAppManager().getCurrentActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    private HashMap<String, Object> getParams(String queryString) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (!TextUtils.isEmpty(queryString)) {
            String[] queryList = queryString.split("&ltkj&");
            for (String queryItem : queryList) {
                if (!TextUtils.isEmpty(queryItem)) {
                    String[] queryParts = queryItem.split("=", 2);
                    hashMap.put(queryParts[0], queryParts[1]);
                }
            }
        }
        return hashMap;
    }

    /**
     * 获取某个类中的属性列表
     *
     * @param target 类对象
     * @return 属性列表(Key:属性名称 Value:属性类型)
     */
    private HashMap<String, Class> getClassFields(Class<?> target) {
        HashMap<String, Class> map = new HashMap<>();
        try {
            Field[] fa = target.getDeclaredFields();
            for (Field aFa : fa) {
                Class cl = aFa.getType();
                if ((!cl.toString().startsWith("class") || cl.toString().startsWith("class java.lang."))) {
                    map.put(aFa.getName(), cl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
