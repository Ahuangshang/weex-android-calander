package cn.ltwc;

import android.content.Context;

/**
 * Created by admin on 2017/9/12.
 */

public class ViewUtils {
    private static Context applicationContext;

    public static void init(Context context) {
        if (context == null) {
            applicationContext = null;
        } else {
            applicationContext = context.getApplicationContext();
        }
    }

    public static Context getApplicationContext() {
        return applicationContext;
    }
}
