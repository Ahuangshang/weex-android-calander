package cn.ltwc.cft.helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.R;
import cn.ltwc.cft.utils.FileUtils;
import cn.ltwc.utils.LogUtil;

/**
 * CrashHandler
 * Created by admin on 2017/3/27.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "AA";

    // CrashHandler 实例
    private static CrashHandler INSTANCE = new CrashHandler();

    // 程序的 Context 对象
    private Context mContext;
    private MyApplication app;

    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 保证只有一个 CrashHandler 实例
     */
    private CrashHandler() {
    }

    /**
     * 获取 CrashHandler 实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * @param context
     * @param app     传入的app
     * @throws
     * @Title: init
     * @Description: 初始化
     */
    public void init(Context context, MyApplication app) {
        // 传入app对象，为完美终止app
        this.app = app;
        mContext = context;
        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当 UncaughtException 发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(thread, ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            app.onTerminate();
        }
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean handleException(Thread thread, Throwable ex) {
        if (ex == null) {
            return false;
        }
        showErrorToast();
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    private void showErrorToast() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                LayoutInflater inflater = LayoutInflater.from(mContext);
                View layout = inflater.inflate(R.layout.custom,
                        null);
                ImageView image = (ImageView) layout
                        .findViewById(R.id.tvImageToast);
                image.setImageResource(R.drawable.warn);
                TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
                text.setText("很抱歉，程序发生异常，即将退出!");
                Toast t = new Toast(mContext);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.setDuration(Toast.LENGTH_LONG);
                t.setView(layout);
                t.show();
                Looper.loop();
            }
        }).start();
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e("an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                LogUtil.e("an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中 *
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
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
        String result = writer.toString();
        sb.append(result);
        try {
            String time = formatter.format(new Date());
            String fileName = time + " error" + ".txt";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File dir = FileUtils.getCacheDirectory(MyApplication.getInstance(), "log");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(new File(dir, fileName));
                fos.write(sb.toString().getBytes());
                fos.flush();
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            LogUtil.e("an error occured while writing file...", e);
        }
        return null;
    }
}
