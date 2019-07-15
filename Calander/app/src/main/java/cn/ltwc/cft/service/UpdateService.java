package cn.ltwc.cft.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import cn.ltwc.cft.R;
import cn.ltwc.cft.utils.DownLoadUtil;
import cn.ltwc.utils.LogUtil;
import rx.functions.Action1;

/**
 * Description:
 * Created on 2019/7/5 0005 16:33:43
 * author:Ahuangshang
 */
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//                  佛祖保佑       永不宕机     永无BUG             //
////////////////////////////////////////////////////////////////////
public class UpdateService extends Service {
    private NotificationManager nm;
    private Notification notification;
    private RemoteViews views;
    private int notificationId = 4321;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        views = new RemoteViews(getPackageName(), R.layout.view_update);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "system";
            String channelName = "系统通知";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
            notification = new NotificationCompat.Builder(this, "system")
                    .setSmallIcon(android.R.drawable.stat_sys_download)
                    .setContentTitle("")
                    .setContentText("")
                    .setContent(views)
                    .build();
        } else {
            notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.stat_sys_download)
                    .setContentTitle("")
                    .setContentText("")
                    .setContent(views)
                    .build();
        }


        //将下载任务添加到任务栏中
        nm.notify(notificationId, notification);
        //启动线程开始执行下载任务
        if (intent != null) {
            String apkUrl = intent.getStringExtra("url");
            DownLoadUtil.downLoadWitheProgress(apkUrl, new Action1<Integer>() {
                @Override
                public void call(Integer progress) {
                    LogUtil.e("下载进度" + progress);
                    if (progress == 100000) {
                        LogUtil.e("下载完成了");
                        //下载完成了,不管成功与否
                        //下载完成后清除所有下载信息，执行安装提示
                        nm.cancel(notificationId);
                        //停止掉当前的服务
                        stopSelf();
                    } else {
                        //更新状态栏上的下载进度信息
                        views.setTextViewText(R.id.tvProcess, "已下载" + progress + "%");
                        views.setProgressBar(R.id.pbDownload, 100, progress, false);
                        notification.contentView = views;
                        nm.notify(notificationId, notification);
                    }
                }
            });

        }
        return START_REDELIVER_INTENT;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nm.cancel(notificationId);
    }

}
