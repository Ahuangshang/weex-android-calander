package cn.ltwc.cft.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {
    @TargetApi(21)
    public MyListView(Context context, AttributeSet attrs, int defStyleAttr,
                      int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        // TODO Auto-generated constructor stub
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
