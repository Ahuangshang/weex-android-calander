package cn.ltwc.cft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.data.LunarCalendar;
import cn.ltwc.cft.data.SpecialCalendar;
import cn.ltwc.cft.view.MarqueeView;

/**
 * TODO:日历GridView中的每一个item显示的TextView
 *
 * @author huangshang 2015-11-18 下午7:46:04
 * @Modified_By:
 */
@SuppressLint("SimpleDateFormat")
public class CalendarAdapter extends BaseAdapter {
    private boolean isLeapyear = false; // 是否为闰年
    private int daysOfMonth = 0; // 某月的天数
    private int dayOfWeek = 0; // 具体某一天是星期几
    private int lastDaysOfMonth = 0; // 上一个月的总天数
    private Context context;
    private String[] dayNumber = new String[42]; // 一个GridView中的日期存入此数组中
    private SpecialCalendar sc = null;
    private LunarCalendar lc = null;
    private String currentYear = "";// 当前年份
    private String currentMonth = "";// 当前月份
    private String currentDay = "";// 当前日期
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    public int currentFlag = -1; // 用于标记系统的当天
    public int currentFlag_ = -1; // 用于标记当天
    public boolean flag;// 如果日期强制回到1号，就置为true
    @SuppressWarnings("unused")
    private int[] schDateTagFlag = null; // 存储当月所有的日程日期

    private String showYear = ""; // 用于在头部显示的年份
    private String showMonth = ""; // 用于在头部显示的月份
    private String animalsYear = "";
    private String leapMonth = ""; // 闰哪一个月
    private String cyclical = ""; // 天干地支
    // 系统当前时间
    private String sysDate = "";
    private String sys_year = "";
    private String sys_month = "";
    private String sys_day = "";

    public CalendarAdapter() {
        Date date = new Date();
        sysDate = sdf.format(date); // 当期日期
        sys_year = sysDate.split("-")[0];
        sys_month = sysDate.split("-")[1];
        sys_day = sysDate.split("-")[2];

    }

    public CalendarAdapter(Context context, int jumpMonth, int jumpYear,
                           int year_c, int month_c, int day_c) {
        this();
        this.context = context;
        sc = SpecialCalendar.getInstance();
        lc = LunarCalendar.getInstance();
        int stepYear = year_c + jumpYear;
        int stepMonth = month_c + jumpMonth;
        if (stepMonth > 0) {
            // 往下一个月滑动
            if (stepMonth % 12 == 0) {
                stepYear = year_c + stepMonth / 12 - 1;
                stepMonth = 12;
            } else {
                stepYear = year_c + stepMonth / 12;
                stepMonth = stepMonth % 12;
            }
        } else {
            // 往上一个月滑动
            stepYear = year_c - 1 + stepMonth / 12;
            stepMonth = stepMonth % 12 + 12;
            if (stepMonth % 12 == 0) {

            }
        }

        currentYear = String.valueOf(stepYear); // 得到当前的年份
        currentMonth = String.valueOf(stepMonth); // 得到本月
        // （jumpMonth为滑动的次数，每滑动一次就增加一月或减一月）
        currentDay = String.valueOf(day_c); // 得到当前日期是哪天

        getCalendar(Integer.parseInt(currentYear),
                Integer.parseInt(currentMonth));

    }

    public CalendarAdapter(Context context, Resources rs, int year, int month,
                           int day) {
        this();
        this.context = context;
        sc = SpecialCalendar.getInstance();
        lc = LunarCalendar.getInstance();
        currentMonth = String.valueOf(month); // 得到跳转到的月份
        currentDay = String.valueOf(day); // 得到跳转到的天
        getCalendar(Integer.parseInt(currentYear),
                Integer.parseInt(currentMonth));
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dayNumber.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.calendar_item, null);
            convertView.setBackgroundColor(0Xffffff);// 设置背景
            holder = new Holder();
            holder.yangli = (TextView) convertView.findViewById(R.id.tv_ri);
            holder.nongli = (MarqueeView) convertView.findViewById(R.id.tv_ni);
            holder.bg = convertView.findViewById(R.id.bg);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        String d = dayNumber[position].split("\\.")[0];
        String dv = dayNumber[position].split("\\.")[1];
        holder.yangli.setText(d);
        holder.nongli.setData(new ArrayList<String>(Arrays.asList(dv.split("\n"))));
        holder.yangli.setTextColor(Color.GRAY);
        holder.nongli.setTextColor(Color.GRAY);

        if (position < daysOfMonth + dayOfWeek && position >= dayOfWeek) {
            // 当前月信息显示
            holder.yangli.setTextColor(Color.BLACK);
            holder.nongli.setTextColor(Color.BLACK);
            //holder.textView.setTextColor(Color.BLACK);// 当月字体设黑
            if (position % 7 == 0 || position % 7 == 6) {
                holder.yangli.setTextColor(Color.rgb(238, 113, 113));
                holder.nongli.setTextColor(Color.rgb(238, 113, 113));
                //holder.textView.setTextColor(Color.rgb(238, 113, 113));// 周末字体设置为红色
            }
            if (currentFlag_ == position) {
                holder.bg.setBackgroundResource(R.drawable.select_bg);
            }
            if (currentFlag == position
                    && (currentFlag_ == -1 || currentFlag_ == currentFlag)) {
                // 设置当天的背景
                holder.bg.setBackgroundResource(R.drawable.current_bg);
            }
            // =================得到中国放假的日子=========================
            List<Integer> list = new ArrayList<Integer>();// 保存当月中国的传统节日
            for (int i = 0; i < Constant.FANGJIAHOLIDAY.length; i++) {
                for (int j = 0; j < dayNumber.length; j++) {
                    String dx = dayNumber[j].split("\\.")[1];
                    String lh = Constant.FANGJIAHOLIDAY[i];
                    if (dx.contains(lh)) {
                        list.add(j);
                        if (lh.contains("春节") || lh.contains("皇上生辰") || lh.contains("皇后生辰") || lh.contains("国庆")) {// 如果包含春节，则后面的两天跟着放假
                            // 如果包含皇上生辰，则后面的七天跟着放假
                            // 如果是国庆，则后面七天也放假
                            list.add(j + 1);
                            list.add(j + 2);
                            list.add(j + 3);
                            list.add(j + 4);
                            list.add(j + 5);
                            list.add(j + 6);
                        }
                        j = dayNumber.length;
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == position) {
                    // 设置中国传统节假日
                    holder.yangli.setTextColor(Color.rgb(238, 113, 113));
                    holder.nongli.setTextColor(Color.rgb(238, 113, 113));
                }
            }

            // ===========================================
        } else {

        }

        return convertView;
    }

    // 得到某年的某月的天数且这月的第一天是星期几
    public void getCalendar(int year, int month) {
        isLeapyear = sc.isLeapYear(year); // 是否为闰年
        daysOfMonth = sc.getDaysOfMonth(isLeapyear, month); // 某月的总天数
        dayOfWeek = sc.getWeekdayOfMonth(year, month); // 某月第一天为星期几
        lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month - 1); // 上一个月的总天数
        getweek(year, month);
    }

    // 将一个月中的每一天的值添加入数组dayNuber中
    private void getweek(int year, int month) {
        int j = 1;
        String lunarDay = "";
        // 得到当前月的所有日程日期(这些日期需要标记)
        for (int i = 0; i < dayNumber.length; i++) {
            if (i < dayOfWeek) { // 前一个月
                int temp = lastDaysOfMonth - dayOfWeek + 1;
                lunarDay = lc.getLunarDate(year, month - 1, temp + i, false);
                dayNumber[i] = (temp + i) + "." + lunarDay;

            } else if (i < daysOfMonth + dayOfWeek) { // 本月
                String day = String.valueOf(i - dayOfWeek + 1); // 得到的日期
                lunarDay = lc.getLunarDate(year, month, i - dayOfWeek + 1,
                        false);
                dayNumber[i] = i - dayOfWeek + 1 + "." + lunarDay;
                // 对于当前月才去标记当前日期
                if (sys_year.equals(String.valueOf(year))
                        && sys_month.equals(String.valueOf(month))
                        && sys_day.equals(day)) {
                    currentFlag = i;
                }
                if (currentDay.equals(day)) {
                    // 标记当前日期
                    currentFlag_ = i;
                }
                setShowYear(String.valueOf(year));
                setShowMonth(String.valueOf(month));
                setAnimalsYear(lc.animalsYear(year));
                setLeapMonth(lc.leapMonth == 0 ? "" : String
                        .valueOf(lc.leapMonth));
                setCyclical(lc.cyclical(year));
            } else { // 下一个月
                lunarDay = lc.getLunarDate(year, month + 1, j, false);
                dayNumber[i] = j + "." + lunarDay;
                j++;
            }
        }
        if (currentFlag_ == -1) {
            currentFlag_ = dayOfWeek;
            flag = true;
        }

        String abc = "";
        for (int i = 0; i < dayNumber.length; i++) {
            abc = abc + dayNumber[i] + ":";
        }
    }

    public void matchScheduleDate(int year, int month, int day) {

    }

    /**
     * 点击每一个item时返回item中的日期
     *
     * @param position
     * @return
     */
    public String getDateByClickItem(int position) {
        return dayNumber[position];
    }

    /**
     * 在点击gridView时，得到这个月中第一天的位置
     *
     * @return
     */
    public int getStartPositon() {
        return dayOfWeek + 7;
    }

    /**
     * 在点击gridView时，得到这个月中最后一天的位置
     *
     * @return
     */
    public int getEndPosition() {
        return (dayOfWeek + daysOfMonth + 7) - 1;
    }

    /**
     * @return 返回用于在头部显示的年份
     */
    public String getShowYear() {
        return showYear;
    }

    public void setShowYear(String showYear) {
        this.showYear = showYear;
    }

    /**
     * @return 返回在头部显示的月份
     */
    public String getShowMonth() {
        return showMonth;
    }

    public void setShowMonth(String showMonth) {
        this.showMonth = showMonth;
    }

    public String getAnimalsYear() {
        return animalsYear;
    }

    public void setAnimalsYear(String animalsYear) {
        this.animalsYear = animalsYear;
    }

    public String getLeapMonth() {
        return leapMonth;
    }

    public void setLeapMonth(String leapMonth) {
        this.leapMonth = leapMonth;
    }

    public String getCyclical() {
        return cyclical;
    }

    public void setCyclical(String cyclical) {
        this.cyclical = cyclical;
    }

    class Holder {
        //TextView textView;
        TextView yangli;
        MarqueeView nongli;
        View bg;
    }
}
