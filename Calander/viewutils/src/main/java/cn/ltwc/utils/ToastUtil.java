package cn.ltwc.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import cn.ltwc.ViewUtils;

/**
 * Toast显示
 * Created by LZL on 2016/11/17.
 */

public class ToastUtil {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Object synObj = new Object();

    /**
     * Toast发送消息，默认Toast.LENGTH_SHORT
     *
     * @param msg
     */
    public static void showMessage(final String msg) {
        showMessage(msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_SHORT
     *
     * @param msg
     */
    public static void showMessage(final int msg) {
        showMessage(msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_LONG
     *
     * @param msg
     */
    public static void showMessageLong(final String msg) {
        showMessage(msg, Toast.LENGTH_LONG);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_LONG
     *
     * @param msg
     */
    public static void showMessageLong(final int msg) {
        showMessage(msg, Toast.LENGTH_LONG);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_SHORT
     *
     * @param msg
     */
    public static void showMessageCenter(final String msg) {
        showMessageCenter(msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_SHORT
     *
     * @param msg
     */
    public static void showMessageCenter(final int msg) {
        showMessageCenter(msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_LONG
     *
     * @param msg
     */
    public static void showMessageLongCenter(final String msg) {
        showMessageCenter(msg, Toast.LENGTH_LONG);
    }

    /**
     * Toast发送消息，默认Toast.LENGTH_LONG
     *
     * @param msg
     */
    public static void showMessageLongCenter(final int msg) {
        showMessageCenter(msg, Toast.LENGTH_LONG);
    }

    /**
     * Toast发送消息
     *
     * @param msg
     * @param len
     */
    public static void showMessage(final int msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (synObj) {
                            Toast.makeText(ViewUtils.getApplicationContext(), msg, len).show();
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * Toast发送消息
     *
     * @param msg
     * @param len
     */
    public static void showMessage(final String msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            Toast.makeText(ViewUtils.getApplicationContext(), msg, len).show();
                        }
                    }
                });
            }
        }).start();
    }


    /**
     * Toast发送消息
     *
     * @param msg
     * @param len
     */
    public static void showMessageCenter(final int msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (synObj) {
                            Toast t = Toast.makeText(ViewUtils.getApplicationContext(), msg,
                                    len);
                            t.setGravity(Gravity.CENTER, 0, 0);
                            t.show();
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * Toast发送消息
     *
     * @param msg
     * @param len
     */
    public static void showMessageCenter(final String msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            Toast t = Toast.makeText(ViewUtils.getApplicationContext(), msg,
                                    len);
                            t.setGravity(Gravity.CENTER, 0, 0);
                            t.show();
                        }
                    }
                });
            }
        }).start();
    }


}
