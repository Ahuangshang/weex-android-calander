package cn.ltwc.viewutils.recycleviewutils.base;

import android.view.View;

/**
 * Created by LZL on 2016/11/4.
 */

public interface ItemViewDelegate<T> {
    int getItemViewLayoutId();

    View getItemView();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);
}
