package cn.ltwc.cft.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.OrientationEventListener;

/**
 * Description:
 * Created on 2019/7/10 0010 17:49:12
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
public class ScreenOrientationListener extends OrientationEventListener {
private Activity activity;
    public ScreenOrientationListener(Activity activity) {
        super(activity);
        this.activity=  activity;
    }

    @Override
    public void onOrientationChanged(int orientation) {
        if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
            return; // 手机平放时，检测不到有效的角度
        }
        // 只检测是否有四个角度的改变
        if (orientation > 350 || orientation < 10) {
            // 0度：手机默认竖屏状态（home键在正下方）
            orientation = 0;
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
            Log.i("orientation","orientation"+orientation);
        } else if (orientation > 80 && orientation < 100) {
            // 90度：手机顺时针旋转90度横屏（home建在左侧）
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
            Log.i("orientation","orientation"+orientation);
        } else if (orientation > 170 && orientation < 190) {
            // 手机顺时针旋转180度竖屏（home键在上方）
            orientation = 180;
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Log.i("orientation","orientation"+orientation);
        } else if (orientation > 260 && orientation < 280) {
            // 手机顺时针旋转270度横屏，（home键在右侧）
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Log.i("orientation","orientation"+orientation);
        }
    }
}
