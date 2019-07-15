package cn.ltwc.cft.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.liteav.demo.play.inface.OverTurnListener;

import cn.ltwc.cft.R;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.FileUtils;
import cn.ltwc.cft.utils.PermissionUtils;
import cn.ltwc.utils.LogUtil;
import cn.ltwc.utils.ToastUtil;

/**
 * Description:外部调用播放器的播放页面
 * Created on 2019/7/10 0010 18:40:15
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
public class PlayVideoActivity extends Activity implements SuperPlayerView.PlayerViewCallback, OverTurnListener {
    private SuperPlayerView superPlayerView;
    private View menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_video);
        superPlayerView = findViewById(R.id.superVodPlayerView);
        superPlayerView.setPlayerViewCallback(this);
        superPlayerView.setOverTurnListener(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        menu = findViewById(R.id.view_menu);
        menu.setVisibility(View.GONE);
        String url = getIntent().getDataString();
        url = Uri.decode(url);

        if (url != null) {
            if (url.startsWith("content://")) {
                PermissionUtils.requestPermissionsWrapper(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0x0020);
                url = FileUtils.getPath(this, getIntent().getData());
                url = "file://" + url;
            }
            SuperPlayerModel superPlayerModel = new SuperPlayerModel();
            superPlayerModel.title = url.substring(url.lastIndexOf("/") + 1);
            superPlayerModel.videoURL = url;
            superPlayerView.playWithMode(superPlayerModel);
        } else {
            ToastUtil.showMessage("传入的视频地址错误");
            finish();
        }

    }

    @Override
    public void hideViews() {

    }

    @Override
    public void showViews() {

    }

    @Override
    public void onQuit(int playMode) {
        superPlayerView.resetPlayer();
        finish();
    }

    @Override
    public void overTurn(boolean overTurn) {
        if (overTurn) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (superPlayerView.getPlayState() == SuperPlayerConst.PLAYSTATE_PLAY) {
            superPlayerView.onResume();
            if (superPlayerView.getPlayMode() == SuperPlayerConst.PLAYMODE_FLOAT) {
                superPlayerView.requestPlayMode(SuperPlayerConst.PLAYMODE_WINDOW);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (superPlayerView.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
            superPlayerView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        superPlayerView.release();
        if (superPlayerView.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
            superPlayerView.resetPlayer();
        }
    }
}
