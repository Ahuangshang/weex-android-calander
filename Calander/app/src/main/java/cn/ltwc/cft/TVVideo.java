package cn.ltwc.cft;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import cn.ltwc.cft.activity.TvVideoActivity;

/**
 * Description:
 * Created on 2019/7/2 0002 17:09:00
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
public class TVVideo {
    public TVVideo() {
    }
    public static void openVideo(Context var0, String var1) {
        openVideo(var0, var1, (Bundle)null);
    }
    public static void openVideo(Context context, String url, Bundle bundle) {
        if (TextUtils.isEmpty(url)) {
            Log.e("TbsVideo", "videoUrl is empty!");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }

            bundle.putString("videoUrl", url);
            Intent intent = new Intent(context, TvVideoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage(context.getPackageName());
            intent.putExtra("extraData", bundle);
            context.startActivity(intent);
        }
    }
}
