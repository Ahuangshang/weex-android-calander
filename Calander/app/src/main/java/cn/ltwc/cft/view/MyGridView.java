package cn.ltwc.cft.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
