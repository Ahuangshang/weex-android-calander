package cn.ltwc.cft;

import android.app.Activity;
import android.content.Context;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Control all activities.
 * Created by admin on 2016/12/29.
 */

public class AppManager {
    private static Stack<Activity> activityStack;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static AppManager instance = new AppManager();
    }


    /**
     * 添加Activity到堆栈中
     */
    public synchronized void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        if (activity != null) {
            activityStack.add(activity);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后压入的）
     */
    public synchronized Activity currentActivity() {
        try {
            if (activityStack != null) {
                return activityStack.lastElement();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后压入的）
     */
    public synchronized void finishActivity(Activity activity) {
        if (activity != null) {
            try {
                Activity c = activityStack.lastElement();
                if (activity.equals(c)) {
                    activityStack.remove(activityStack.size() - 1);
                }
                finish(activity);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }

    public void finishActivityErgodic(Activity activity) {
        Iterator<Activity> it = activityStack.iterator();
        while (it.hasNext()) {
            Activity act = it.next();
            if (activity != null) {
                if (act.equals(activity)) {
                    it.remove();
                }
                finish(activity);
            }
        }
    }

    /**
     * 结束指定类名的Activity(所有相同的类会全部关闭)
     */
    public synchronized void finishActivity(Class<?> cls) {
        Iterator<Activity> it = activityStack.iterator();
        while (it.hasNext()) {
            Activity activity = it.next();
            if (activity.getClass().equals(cls)) {
                it.remove();
                finish(activity);
            }

        }
    }

    private synchronized void finish(Activity activity) {
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(R.anim.in_from_left,
                    R.anim.out_to_right);
            activity = null;
        }
    }

    /**
     * 结束所有Activity
     */
    private void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public synchronized Activity getCurrentActivity() {
        Activity activity = null;
        if (activityStack != null) {
            activity = activityStack.lastElement();
        }
        return activity;
    }


    /**
     * 彻底退出应用程序，安全退出
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean NoActivity() {
        return activityStack == null || (activityStack.empty());
    }
}
