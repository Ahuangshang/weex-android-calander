package cn.ltwc.cft.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import cn.ltwc.cft.R;


/**
 * 进度条的显示 与 隐藏
 */
public class LoadingDialog extends Dialog {
    private Context context;
    private View view;

    public LoadingDialog(Context context) {
        super(context, R.style.Dialog_Fullscreen);
        this.context = context;

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            setContentView(view);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (view != null) {
            view = null;
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setOnDismissListener(OnDismissListener listener) {
        super.setOnDismissListener(listener);

    }
}
