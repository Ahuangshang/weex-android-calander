package cn.ltwc.cft.datapick;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import java.util.Calendar;

import cn.ltwc.cft.R;
import cn.ltwc.cft.utils.PopWindowUtil;

/**
 * TODO:日期选择器的帮助类(用于创建日期选择器的界面和返回选中的日期)
 *
 * @author huangshang 2015-11-21 上午1:18:55
 * @Modified_By:
 */
public class PickUtils {
    //PopupWindow menuWindow;// 流行窗口
    PopWindowUtil make;
    private LayoutInflater inflater = null;
    private WheelView year;
    private WheelView month;
    private WheelView day;
    private String str;// 选中的日期，用于返回
    private CallBack callback;// 回调接口
    private int startYear = 1901;
    private int endYear = 2099;

    // 单例
    private PickUtils() {

    }

    public static class UtilHolder {
        private static PickUtils instance = new PickUtils();
    }

    public static PickUtils getInstance() {
        return UtilHolder.instance;
    }

    /**
     * 返回选中的日期
     */
    public String getStr() {
        return str;
    }

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    /**
     * 配置视图转换器
     */
    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    /**
     * 初始化popupWindow
     *
     * @param view
     */
    @SuppressWarnings("deprecation")
    public void showPopwindow(View view) {
//        menuWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
//                LayoutParams.WRAP_CONTENT);
//        menuWindow.setFocusable(true);
//        Drawable drawable = new ColorDrawable(Color.rgb(79, 210, 190));
//        menuWindow.setBackgroundDrawable(null);
//        menuWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
//        menuWindow.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                menuWindow = null;
//            }
//        });
         make = PopWindowUtil.make(view);
        make.show();
    }

    /**
     * 日期选择器
     *
     * @param cyear  上次选择的年份
     * @param cmonth 上次选择的月份
     * @return
     */
    @SuppressLint("InflateParams")
    public View getDataPick(int cyear, int cmonth, int cday) {
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;// 通过Calendar算出的月数要+1
        // int curDate = c.get(Calendar.DATE);
        final View view = inflater.inflate(R.layout.datapick, null);

        year = (WheelView) view.findViewById(R.id.year);
        year.setAdapter(new NumericWheelAdapter(startYear, endYear));// 因为当前日历只能到2049年
        year.setLabel("年");
        year.setCyclic(false);
        year.addScrollingListener(scrollListener);

        month = (WheelView) view.findViewById(R.id.month);
        month.setAdapter(new NumericWheelAdapter(1, 12));
        month.setLabel("月");
        month.setCyclic(true);
        month.addScrollingListener(scrollListener);

        day = (WheelView) view.findViewById(R.id.day);
        initDay(curYear, curMonth);
        day.setLabel("日");
        day.setCyclic(true);

        year.setCurrentItem(cyear - startYear);
        month.setCurrentItem(cmonth - 1);
        day.setCurrentItem(cday - 1);

        TextView bt = (TextView) view.findViewById(R.id.set);
        bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                str = (year.getCurrentItem() + startYear) + "年"
                        + (month.getCurrentItem() + 1) + "月"
                        + (day.getCurrentItem() + 1);
                // Toast.makeText(context, str, Toast.LENGTH_LONG).show();
                // currentMonth.setText(str);
                if (callback != null) {
                    callback.SetStr(str);
                }
                make.dismiss();
            }
        });
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                make.dismiss();
            }
        });
        return view;
    }

    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            // TODO Auto-generated method stub
            int n_year = year.getCurrentItem() + startYear;
            int n_month = month.getCurrentItem() + 1;
            initDay(n_year, n_month);
        }
    };

    /**
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    /**
     */
    private void initDay(int arg1, int arg2) {
        day.setAdapter(new NumericWheelAdapter(1, getDay(arg1, arg2), "%02d"));
    }

    /**
     * TODO:回调接口
     *
     * @author huangshang 2015-11-21 上午2:01:36
     * @Modified_By:
     */
    public interface CallBack {
        public void SetStr(String str);
    }
}
