package cn.ltwc.bitmaputils.glide;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Glide加载图片的监听回调 Created by LZL on 16/3/25.
 */
@SuppressWarnings("rawtypes")
public class GlideListener implements RequestListener {

    private View view;
    private int Type;
    public static int SETBACKGROUD = 0x0001;// 设置图片的背景颜色
    public static int SETSCALTYPE = 0x0002; // 设置图片的缩放类型
    public static int SETALL = 0x0003; // 既设置背景颜色又设置缩放类型
    public static int NULL = 0x0004;//什么操作都不做
    private View par;
    private ImageView.ScaleType scaleType;

    public GlideListener(View view, int type) {
        this.view = view;
        Type = type;
        this.scaleType = ((ImageView) view).getScaleType();
    }

    public GlideListener(View view, int type, View par) {
        this.view = view;
        Type = type;
        if (par != null) {
            this.par = par;
            par.setVisibility(View.VISIBLE);
        }
        this.scaleType = ((ImageView) view).getScaleType();
    }

    public GlideListener setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    @Override
    public boolean onException(Exception e, Object model, Target target,
                               boolean isFirstResource) {
        if (par != null) {
            par.setVisibility(View.GONE);
        }
        if (Type == SETBACKGROUD) {
            view.setBackgroundColor(Color.parseColor("#e1e1e1"));
        } else if (Type == SETSCALTYPE) {
            ((ImageView) view).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else if (Type == SETALL) {
            view.setBackgroundColor(Color.parseColor("#e1e1e1"));
            ((ImageView) view).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model,
                                   Target target, boolean isFromMemoryCache, boolean isFirstResource) {
        if (par != null) {
            par.setVisibility(View.GONE);
        }
        if (Type == SETBACKGROUD) {
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        } else if (Type == SETSCALTYPE) {
            ((ImageView) view).setScaleType(scaleType);
        } else if (Type == SETALL) {
            //view.setBackgroundColor(Color.parseColor("#ffffff"));
            ((ImageView) view).setScaleType(scaleType);
        }
        return false;
    }
}
