package cn.ltwc.cft.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.ltwc.cft.AppManager;
import cn.ltwc.cft.R;
import cn.ltwc.cft.view.numberbar.RoundProgressBar;

/**
 * TODO:标题栏
 *
 * @author huangshang 2015-11-15 下午10:44:13
 * @Modified_By:
 */
public class TitleView extends RelativeLayout {

    private ImageButton leftIcon, rightIcon;// 左右侧图片
    private TextView titletext;// 中间标题
    private View top, topf, content;// 顶部空白和小面的内容
    private Context c;
    private MyOnClickListener myOnClickListener;
    private TextView close;
    private View downView;
    private RoundProgressBar par;

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }

    @SuppressLint("NewApi")
    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public TitleView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }

    private void init(Context context) {
        this.c = context;
        LayoutInflater.from(getContext()).inflate(R.layout.title, this);
        leftIcon = (ImageButton) findViewById(R.id.title_left);
        titletext = (TextView) findViewById(R.id.title_center);
        rightIcon = (ImageButton) findViewById(R.id.title_right_icon);
        top = findViewById(R.id.title_view_top);
        topf = findViewById(R.id.title_view_top_f);
        close = (TextView) findViewById(R.id.close);
        this.content = findViewById(R.id.title_view_content);
        downView = findViewById(R.id.down_view);
        par = (RoundProgressBar) findViewById(R.id.down_progress);
        setTopVisibility();
        leftIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (myOnClickListener == null) {
                    AppManager.getAppManager().finishActivity((Activity) c);
                } else {
                    myOnClickListener.monClick(v);
                }
            }
        });
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity((Activity) c);
            }
        });
    }

    public interface MyOnClickListener {
        void monClick(View v);
    }

    public ImageButton getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(int resId) {
        this.leftIcon.setImageResource(resId);
    }

    public ImageButton getRightIcon() {

        return rightIcon;

    }

    public void setRightIcon(int resId) {
        this.rightIcon.setImageResource(resId);

    }

    public TextView getTitletext() {
        return titletext;
    }

    public void setTitletext(String text) {
        this.titletext.setText(text);
    }

    /**
     * 设置左边图片是否显示(默认显示)
     *
     * @param visibility
     */
    public void setLeftVisibility(int visibility) {
        leftIcon.setVisibility(visibility);
    }

    /**
     * 设置右边图片是否显示(默认不显示)
     *
     * @param visibility
     */
    public void setRightVisibility(int visibility) {
        rightIcon.setVisibility(visibility);
    }

    /**
     * 设置顶部空白是否显示
     *
     * @param
     */
    public void setTopVisibility() {
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            top.setVisibility(VISIBLE);
            topf.setVisibility(VISIBLE);
        } else {
            top.setVisibility(GONE);
            topf.setVisibility(GONE);
        }

    }

    /**
     * 设置title的透明度
     *
     * @param alpha
     */
    public void setTitleAlpha(float alpha) {
        top.setAlpha(alpha);
        content.setAlpha(alpha);
    }

    public void setBackgroundColor(int color) {
        top.setBackgroundColor(color);
        topf.setBackgroundColor(color);
        content.setBackgroundColor(color);
    }

    public void setCloseVisibility(int visibility) {
        close.setVisibility(visibility);
    }

    public void setDownViewVisibility(int visibility) {
        downView.setVisibility(visibility);
    }

    public void setDownProgress(int progress) {
        par.setProgress(progress);

    }
}
