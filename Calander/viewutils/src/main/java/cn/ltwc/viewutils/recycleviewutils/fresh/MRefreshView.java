 package cn.ltwc.viewutils.recycleviewutils.fresh;

import android.content.Context;
import android.util.AttributeSet;

import com.andview.refreshview.XRefreshView;

/**
 * Created by admin on 2017/2/23.
 */

public class MRefreshView extends XRefreshView {
    private RefreshDownView refreshDownView;
    public MRefreshView(Context context) {
        super(context);
        init(context);
    }

    public MRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        refreshDownView = new RefreshDownView(context);
        this.setCustomHeaderView(refreshDownView);
        this.setPullLoadEnable(true);
        this.setAutoLoadMore(true);
        this.setPinnedTime(600);
        this.setHideFooterWhenComplete(false);
    }
}
