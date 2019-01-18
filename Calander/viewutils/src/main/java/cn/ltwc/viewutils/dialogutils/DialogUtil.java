package cn.ltwc.viewutils.dialogutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import cn.ltwc.viewutils.R;


/**
 * Created by admin on 2017/8/25.
 */

public class DialogUtil implements View.OnClickListener {
    public final static int TOP_TYP_TEXT = 0x0001;
    public final static int TOP_TYPE_IMG = 0x0002;
    public final static int TOP_TYPE_VIEW = 0x0003;

    public final static int CONTENT_TYPE_TEXT = 0x0004;
    public final static int CONTENT_TYPE_VIEW = 0x0005;

    public final static int BUTTON_TYPE_ONE_BTN = 0x0006;
    public final static int BUTTON_TYPE_TWO_BTN = 0x0007;
    public final static int BUTTON_TYPE_NO_BTN = 0x0008;
    private Context context;
    private Dialog dialog;
    private View showViewBox;//显示dialog的盒子
    private int topShowType = TOP_TYP_TEXT;
    private int contentShowType = CONTENT_TYPE_TEXT;
    private int buttonShowType = BUTTON_TYPE_TWO_BTN;
    private BtnClickListener leftBtnClickListenter, rightBtnClickListener;
    TextView topTv;
    ImageView topImg;
    LinearLayout topView;
    TextView contentTv;
    LinearLayout contentView;

    View buttonTwoBtnLayout, buttonOneBtnLayout;
    TextView leftBtn, rightBtn, btn;
    int dialogWith;
    private boolean isReturn;

    public DialogUtil(WeakReference<Activity> context) {
        this(context, false);
    }

    public DialogUtil(WeakReference<Activity> context, boolean showSoftInput) {
        isReturn = false;
        if (context.get() == null) {
            isReturn = true;
            return;
        }
        this.context = context.get();
        Resources resources = this.context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        dialogWith = (int) (dm.widthPixels * 0.8);
        showViewBox = LayoutInflater.from(this.context).inflate(R.layout.view_dialog, null);
        if (showSoftInput) {
            dialog = new Dialog(this.context, R.style.My_Dialog_Fullscreen_input);
        } else {
            dialog = new Dialog(this.context, R.style.My_Dialog_Fullscreen);
        }
        init();
    }

    public int getTopShowType() {
        return topShowType;
    }

    public DialogUtil setTopShowType(int topShowType) {
        this.topShowType = topShowType;
        return this;
    }

    public int getContentShowType() {
        return contentShowType;
    }

    public DialogUtil setContentShowType(int contentShowType) {
        this.contentShowType = contentShowType;
        return this;
    }

    public int getButtonShowType() {
        return buttonShowType;
    }

    public DialogUtil setButtonShowType(int buttonShowType) {
        this.buttonShowType = buttonShowType;
        return this;
    }

    /**
     * 初始化视图
     */
    private void init() {
        topTv = (TextView) showViewBox.findViewById(R.id.top_tv);
        topImg = (ImageView) showViewBox.findViewById(R.id.top_img);
        topView = (LinearLayout) showViewBox.findViewById(R.id.top_view);

        contentTv = (TextView) showViewBox.findViewById(R.id.content_tv);
        contentView = (LinearLayout) showViewBox.findViewById(R.id.content_view);

        buttonOneBtnLayout = showViewBox.findViewById(R.id.button_one_btn);
        buttonTwoBtnLayout = showViewBox.findViewById(R.id.button_two_btn);
        leftBtn = (TextView) showViewBox.findViewById(R.id.button_left_btn);
        rightBtn = (TextView) showViewBox.findViewById(R.id.button_right_btn);
        btn = (TextView) showViewBox.findViewById(R.id.button_btn);

        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        buttonOneBtnLayout.setOnClickListener(this);
        dialog.setContentView(showViewBox);
        showViewBox.setLayoutParams(new FrameLayout.LayoutParams(dialogWith, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public DialogUtil setTop(@NonNull String topTitle) {
        if (!TextUtils.isEmpty(topTitle)) {
            this.topTv.setText(topTitle);
        }
        return this;
    }

    /**
     * 添加图片的，暂时还没处理
     *
     * @return
     */
    public DialogUtil setTop() {

        return this;
    }

    public DialogUtil setTop(View topView) {
        this.topView.removeAllViews();
        this.topView.addView(topView);
        return this;
    }

    public DialogUtil setLeftBtnClickListener(BtnClickListener leftBtnClickListener) {
        this.leftBtnClickListenter = leftBtnClickListener;
        return this;
    }

    public DialogUtil setRightBtnClickListener(BtnClickListener rightBtnClickListener) {
        this.rightBtnClickListener = rightBtnClickListener;
        return this;
    }

    public DialogUtil setContent(@NonNull String content) {
        this.contentTv.setText(content);
        this.contentTv.setMovementMethod(ScrollingMovementMethod.getInstance());//让文字行数过多时可以滚动
        return this;
    }

    public DialogUtil setContent(View contentView) {
        this.contentView.removeAllViews();
        this.contentView.addView(contentView);
        return this;
    }

    /**
     * @param leftBtnText 默认取消
     * @return
     */
    public DialogUtil setLeftBtn(@NonNull String leftBtnText) {
        if (!TextUtils.isEmpty(leftBtnText)) {
            this.leftBtn.setText(leftBtnText);
        }
        return this;
    }

    /**
     * @param rightBtnText 默认确定
     * @return
     */
    public DialogUtil setRightBtn(@NonNull String rightBtnText) {
        if (!TextUtils.isEmpty(rightBtnText)) {
            this.rightBtn.setText(rightBtnText);
        }
        return this;
    }

    public DialogUtil setBtn(@NonNull String btnText) {
        if (!TextUtils.isEmpty(btnText)) {
            this.btn.setText(btnText);
        }
        return this;
    }

    public DialogUtil setCancelable(boolean cancelable) {
        if (dialog != null) {
            dialog.setCancelable(cancelable);
        }
        return this;
    }

    public DialogUtil setCanceledOnTouchOutside(boolean cancel) {
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(cancel);
        }
        return this;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_left_btn) {
            if (leftBtnClickListenter != null) {
                leftBtnClickListenter.btnClick();
            }
        } else if (view.getId() == R.id.button_right_btn) {
            if (rightBtnClickListener != null) {
                rightBtnClickListener.btnClick();
            }
        } else if (view.getId() == R.id.button_one_btn) {
        }
        dismiss();
    }

    public void dismiss() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void show() {
        switch (topShowType) {
            case TOP_TYP_TEXT:
                topTv.setVisibility(View.VISIBLE);
                topImg.setVisibility(View.GONE);
                topView.setVisibility(View.GONE);
                break;
            case TOP_TYPE_IMG:
                topTv.setVisibility(View.GONE);
                topImg.setVisibility(View.VISIBLE);
                topView.setVisibility(View.GONE);
                break;
            case TOP_TYPE_VIEW:
                topTv.setVisibility(View.GONE);
                topImg.setVisibility(View.GONE);
                topView.setVisibility(View.VISIBLE);
                break;
        }
        switch (contentShowType) {
            case CONTENT_TYPE_TEXT:
                contentTv.setVisibility(View.VISIBLE);
                contentView.setVisibility(View.GONE);
                break;
            case CONTENT_TYPE_VIEW:
                contentTv.setVisibility(View.GONE);
                contentView.setVisibility(View.VISIBLE);
                break;
        }
        switch (buttonShowType) {
            case BUTTON_TYPE_TWO_BTN:
                buttonOneBtnLayout.setVisibility(View.GONE);
                buttonTwoBtnLayout.setVisibility(View.VISIBLE);
                break;
            case BUTTON_TYPE_ONE_BTN:
                buttonOneBtnLayout.setVisibility(View.VISIBLE);
                buttonTwoBtnLayout.setVisibility(View.GONE);
                break;
            case BUTTON_TYPE_NO_BTN:
                buttonOneBtnLayout.setVisibility(View.GONE);
                buttonTwoBtnLayout.setVisibility(View.GONE);
                if (dialog != null) {
                    dialog.setCanceledOnTouchOutside(true);
                }
                return;
        }
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public boolean isReturn() {
        return isReturn;
    }
}