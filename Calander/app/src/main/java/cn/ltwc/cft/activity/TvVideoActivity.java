package cn.ltwc.cft.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.liteav.demo.play.controller.TCVodControllerLarge;
import com.tencent.liteav.demo.play.inface.OverTurnListener;
import com.tencent.liteav.demo.play.inface.TvIconListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.R;
import cn.ltwc.cft.entiy.TV;
import cn.ltwc.viewutils.recycleviewutils.CommonAdapter;
import cn.ltwc.viewutils.recycleviewutils.MultiItemTypeAdapter;
import cn.ltwc.viewutils.recycleviewutils.base.ViewHolder;

/**
 * Description:电视直播播放器
 * Created on 2019/7/2 0002 17:06:13
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
public class TvVideoActivity extends Activity implements SuperPlayerView.PlayerViewCallback, TvIconListener, OverTurnListener {

    private View tvView;
    private boolean show;
    private View bg;
    private Animation in, out;
    private RecyclerView rv;
    private ArrayList<TV> tvList;
    private int position;
    private CommonAdapter adapter;
    private SuperPlayerView superPlayerView;
    private View menu;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_video);
        superPlayerView = findViewById(R.id.superVodPlayerView);
        superPlayerView.setPlayerViewCallback(this);
        superPlayerView.setTvIconListener(this);
        superPlayerView.setOverTurnListener(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent = getIntent();
        Bundle extraData = intent.getBundleExtra("extraData");
        tvList = (extraData.getParcelableArrayList("list"));
        position = extraData.getInt("position", 0);

        playNewVideo(tvList.get(position));

        menu = findViewById(R.id.view_menu);
        tvView = LayoutInflater.from(this).inflate(R.layout.view_tv_item, (ViewGroup) menu);

        show = true;
        cdt.start();
        bg = tvView.findViewById(R.id.bg);
        rv = tvView.findViewById(R.id.rv);
        in = AnimationUtils.loadAnimation(TvVideoActivity.this, R.anim.tv_choose_in);
        in.setFillAfter(true);
        out = AnimationUtils.loadAnimation(TvVideoActivity.this, R.anim.tv_choose_out);
        out.setFillAfter(true);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                menu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<TV>(this, R.layout.item_more, tvList) {
            @Override
            protected void convert(ViewHolder holder, TV tv, int index) {
                TextView name = holder.getView(R.id.more_adapter_txt);
                name.setText(tv.getChannelName());
                name.setTextSize(10);
                name.setTextColor(position == index ? getResources().getColor(R.color.blueColor) : getResources().getColor(R.color.white));
                ImageView icon = holder.getView(R.id.more_adapter_img);
                GlideUtil.loadImgWithErrorShow(TvVideoActivity.this, "http://imengu.cn/Ahuangshang/img/taibiao/" + tv.getChannelImg(), R.drawable.pre_load, icon);
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                itemClick(tvList.get(position), position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(adapter);
        rv.scrollToPosition(position);
        //mScreenOrientationListener = new ScreenOrientationListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            boolean mLockScreen = getIsLockScreen();
            float x = ev.getX();
            float y = ev.getY();
            if (!touchEventInView(menu, x, y) && !mLockScreen) {
                if (show) {
                    bg.startAnimation(out);
                    show = false;
                }
            } else {
                cdt.cancel();
            }
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            cdt.start();
        }
        return super.dispatchTouchEvent(ev);
    }

    //通过反射获取是否锁定屏幕
    private boolean getIsLockScreen() {
        boolean mLockScreen = false;
        try {
            Class<?> c = superPlayerView.getClass();
            Field field = c.getDeclaredField("mVodControllerLarge");
            // 取消语言访问检查
            field.setAccessible(true);
            TCVodControllerLarge vodControllerLarge = (TCVodControllerLarge) field.get(superPlayerView);
            Class<?> c1 = vodControllerLarge.getClass();
            Field f = c1.getSuperclass().getDeclaredField("mLockScreen");
            // 取消语言访问检查
            f.setAccessible(true);
            mLockScreen = Boolean.parseBoolean(f.get(vodControllerLarge).toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return mLockScreen;
    }

    private boolean touchEventInView(View view, float x, float y) {
        if (view == null) {
            return false;
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];

        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();

        if (y >= top && y <= bottom && x >= left && x <= right) {
            return true;
        }

        return false;
    }

    private void itemClick(TV tv, int position) {
        this.position = position;
        adapter.notifyDataSetChanged();
        playNewVideo(tv);
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
//        if (playMode == SuperPlayerConst.PLAYMODE_FLOAT) {
//            superPlayerView.resetPlayer();
//            finish();
//        } else if (playMode == SuperPlayerConst.PLAYMODE_WINDOW) {
//            if (superPlayerView.getPlayState() == SuperPlayerConst.PLAYSTATE_PLAY) {
//              // 返回桌面
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                startActivity(intent);
//            } else {
//                superPlayerView.resetPlayer();
//                finish();
//            }
//        }
    }

    private void playNewVideo(TV tv) {
        SuperPlayerModel superPlayerModel = new SuperPlayerModel();
        superPlayerModel.title = tv.getChannelName();
        superPlayerModel.videoURL = tv.getUrl();
        superPlayerView.playWithMode(superPlayerModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mScreenOrientationListener.enable();
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
        //mScreenOrientationListener.disable();
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
        if (cdt != null) {
            cdt.cancel();
        }
    }

    private CountDownTimer cdt = new CountDownTimer(5 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            if (show) {
                bg.startAnimation(out);
                show = false;
            }
        }
    };

    @Override
    public void tvMenuClick() {
        if (show) {
            bg.startAnimation(out);
        } else {
            menu.setVisibility(View.VISIBLE);
            bg.startAnimation(in);
        }
        show = !show;
    }

    @Override
    public void overTurn(boolean overTurn) {
        if (overTurn) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
