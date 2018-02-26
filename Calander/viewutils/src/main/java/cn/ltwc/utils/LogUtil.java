package cn.ltwc.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

/**
 * LogUtil
 * Created by LZL on 2018/2/8.
 */

public class LogUtil {
    private static String customTagPrefix = "AA";
    private static boolean allow = true;
    private static LogUtil.CustomLogger customLogger;

    private LogUtil() {
    }

    public static String getCustomTagPrefix() {
        return customTagPrefix;
    }

    public static void setCustomTagPrefix(String customTagPrefix) {
        LogUtil.customTagPrefix = customTagPrefix;
    }

    public static void enableLog(boolean enable) {
        allow = enable;
    }

    @SuppressLint("DefaultLocale")
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(line:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        //tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = String.format(tag, new Object[]{callerClazzName, caller.getMethodName(), Integer.valueOf(caller.getLineNumber())});
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void d(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.d(tag, content);
            } else {
                Log.d(tag, content);
            }

        }
    }

    public static void d(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.d(tag, content, tr);
            } else {
                Log.d(tag, content, tr);
            }

        }
    }

    public static void e(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.e(tag, content);
            } else {
                Log.e(tag, content);
            }

        }
    }

    public static void e(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.e(tag, content, tr);
            } else {
                Log.e(tag, content, tr);
            }

        }
    }

    public static void i(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.i(tag, content);
            } else {
                Log.i(tag, content);
            }

        }
    }

    public static void i(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.i(tag, content, tr);
            } else {
                Log.i(tag, content, tr);
            }

        }
    }

    public static void v(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.v(tag, content);
            } else {
                Log.v(tag, content);
            }

        }
    }

    public static void v(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.v(tag, content, tr);
            } else {
                Log.v(tag, content, tr);
            }

        }
    }

    public static void w(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.w(tag, content);
            } else {
                Log.w(tag, content);
            }

        }
    }

    public static void w(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.w(tag, content, tr);
            } else {
                Log.w(tag, content, tr);
            }

        }
    }

    public static void w(Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.w(tag, tr);
            } else {
                Log.w(tag, tr);
            }

        }
    }

    public static void wtf(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.wtf(tag, content);
            } else {
                Log.wtf(tag, content);
            }

        }
    }

    public static void wtf(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.wtf(tag, content, tr);
            } else {
                Log.wtf(tag, content, tr);
            }

        }
    }

    public static void wtf(Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.wtf(tag, tr);
            } else {
                Log.wtf(tag, tr);
            }

        }
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public interface CustomLogger {
        void d(String var1, String var2);

        void d(String var1, String var2, Throwable var3);

        void e(String var1, String var2);

        void e(String var1, String var2, Throwable var3);

        void i(String var1, String var2);

        void i(String var1, String var2, Throwable var3);

        void v(String var1, String var2);

        void v(String var1, String var2, Throwable var3);

        void w(String var1, String var2);

        void w(String var1, String var2, Throwable var3);

        void w(String var1, Throwable var2);

        void wtf(String var1, String var2);

        void wtf(String var1, String var2, Throwable var3);

        void wtf(String var1, Throwable var2);
    }
}
