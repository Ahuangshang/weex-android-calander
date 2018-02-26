package cn.ltwc.viewutils.recycleviewutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import cn.ltwc.viewutils.recycleviewutils.base.ItemViewDelegate;
import cn.ltwc.viewutils.recycleviewutils.base.ViewHolder;

/**
 * Created by LZL on 2016/11/4.
 */


public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected View mView;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public View getItemView() {
                return null;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }



    protected abstract void convert(ViewHolder holder, T t, int position);
}
