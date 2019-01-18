package cn.ltwc.cft.helper;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cn.ltwc.utils.LogUtil;

/**
 * HideService
 * Created by LZL on 2018/3/7.
 */

public class HideService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("onDestroy");
        System.exit(0);
    }
}
