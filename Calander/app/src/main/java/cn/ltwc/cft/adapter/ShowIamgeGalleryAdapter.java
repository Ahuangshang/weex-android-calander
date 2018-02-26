package cn.ltwc.cft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.ltwc.bitmaputils.gallery.PhotoView;
import cn.ltwc.bitmaputils.glide.GlideListener;
import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.TiangouBean;

import rx.functions.Action1;

public class ShowIamgeGalleryAdapter extends PagerAdapter {
    private Context context;
    private List<TiangouBean> imgList;
    private Action1<Boolean> signalTapListener;

    public ShowIamgeGalleryAdapter(Context context, List<TiangouBean> imgList, Action1<Boolean> signalTapListener) {
        super();
        this.context = context;
        this.imgList = imgList;
        this.signalTapListener = signalTapListener;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // TODO Auto-generated method stub
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.item_show_img, null);
        PhotoView
                photoView = (PhotoView) view.findViewById(R.id.img);
        photoView.setSignalTapListener(signalTapListener);
        View bar = view.findViewById(R.id.par);
        GlideUtil.loadImgWithListener(context, imgList.get(position).getImg(), GlideListener.NULL, bar, R.drawable.placeholder_black, R.drawable.loading_failed, photoView);
        container.addView(view);
        return view;
    }
}
