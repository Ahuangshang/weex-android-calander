package cn.ltwc.viewutils.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.ltwc.viewutils.R;


/**
 * CircleProgressBarView
 * Created by Queen on 2016/4/01.
 */
public class CircleProgressBarView extends View {
    private static final int MIN_WIDTH = 20;//控件自适应大小的时候，控件宽度，单位dp
    private static final int MIN_HEIGHT = 20;//控件自适应大小的时候，控件高度，单位dp
    private float roundRectfHeight = 2;
    private int width;//控件的宽度
    private int height;//控件的高度
    private PaintFlagsDrawFilter pdf = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    private Canvas canvas;
    private int roundRectCount = 12;
    private int progress = 0;
    private Paint paint;
    private Path path;
    private final int default_progress_color = Color.rgb(255, 255, 255);

    public CircleProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleProgressBarView,
                defStyleAttr, 0);
        int color = attributes.getColor(R.styleable.CircleProgressBarView_progress_color, default_progress_color);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1f);
        path = new Path();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        attributes.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        width = widthSize < getMinWidth(getContext()) ? getMinWidth(getContext()) : widthSize;
        height = heightSize < getMinHeight(getContext()) ? getMinHeight(getContext()) : heightSize;
        if (width > height) {
            width = height;
        } else {
            height = width;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        canvas.save();
//        canvas.setDrawFilter(pdf);
        path.reset();
        path.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, Path.Direction.CCW);//CCW 逆时针方向 CW 顺时针方向

        canvas.clipPath(path, Region.Op.REPLACE);
        canvas.translate(getWidth() / 2, getHeight() / 2);


        int y = 10;
        // 生成色彩矩阵
        if (progress > roundRectCount) {
            progress = 1;
        }
        roundRectfHeight = (float) (2 * Math.PI * getWidth() / 4) / (float) (roundRectCount * 3);
        canvas.rotate(360 / roundRectCount * (progress++), 0f, 0f);
        for (int i = 0; i < roundRectCount; i++) {
            RectF rectF = new RectF(getWidth() / 4, -roundRectfHeight, getWidth() / 2 - 2, roundRectfHeight);
//            ColorMatrix colorMatrix = new ColorMatrix(new float[]{1, 0, 0, 0, 0,
//                                                                  0, 1, 0, 0, 0,
//                                                                  0, 0, 1, 0, 0,
//                                                                  0, 0, 0, roundRectCount / 360f * (i + 1), 0,});//透明度过滤矩阵
//            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            int alpha = (int) (255 * ((i + 1.0f) / roundRectCount));
            paint.setAlpha(alpha);
            canvas.drawRoundRect(rectF, roundRectfHeight, roundRectfHeight, paint);
            canvas.rotate(360 / roundRectCount, 0f, 0f);
        }
        if (progress == 1) {
            handler.sendEmptyMessageDelayed(0, 100);
        }
        canvas.restore();
    }

    public int dip2px(Context context, int dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            postInvalidate();
            handler.sendEmptyMessageDelayed(0, 100);
        }
    };

    public int getMinWidth(Context context) {
        return dip2px(context, MIN_WIDTH);
    }

    public int getMinHeight(Context context) {
        return dip2px(context, MIN_HEIGHT);
    }
}
