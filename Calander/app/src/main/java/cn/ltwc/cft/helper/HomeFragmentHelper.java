package cn.ltwc.cft.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.CalendarAdapter;
import cn.ltwc.cft.adapter.XiaoMIWeatherAdapter;
import cn.ltwc.cft.beans.HeadData;
import cn.ltwc.cft.beans.Link;
import cn.ltwc.cft.beans.RiqiBean;
import cn.ltwc.cft.beans.XiaomiWeather;
import cn.ltwc.cft.beans.XiaomiZhishuList;
import cn.ltwc.cft.beans.YiJiBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.data.LunarCalendar;
import cn.ltwc.cft.datapick.PickUtils;
import cn.ltwc.cft.db.HuangLi;
import cn.ltwc.cft.entiy.Kaijiang;
import cn.ltwc.cft.entiy.LocationInfo;
import cn.ltwc.cft.net.APIService;
import cn.ltwc.cft.net.ProgressSubscriber;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.cft.view.ContainerLayout;
import cn.ltwc.cft.view.MyGridView;
import cn.ltwc.cft.view.MyListView;
import rx.functions.Action1;

/**
 * HomeFragment的帮助类
 * Created by LZL on 2017/2/21.
 */

public class HomeFragmentHelper {
    private TextView ssqi, red1, red2, red3, red4, red5, red6, blue;// 双色球期号、红色球1~6、蓝色球
    public ImageView jumptoToday;
    private GestureDetector gestureDetector = null;
    private CalendarAdapter calV = null;
    private ViewFlipper flipper = null;
    private MyGridView gridView = null;
    private MyListView myListView = null;
    public int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
    public int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    public int chooseday;
    public int year_c = 0;
    public int month_c = 0;
    public int day_c = 0;
    /**
     * 当前的年月，现在日历顶端
     */
    private TextView currentMonth;
    public RiqiBean rbean;// 要跳转到下一个界面的日期信息
    private TextView nongli, yi, ji;// 选中日期的农历信息
    private ContainerLayout myscrollview;
    private Context context;

    private HomeFragmentHelper() {
    }

    @SuppressLint("StaticFieldLeak")
    private static class HelperHolder {
        private static HomeFragmentHelper instance = new HomeFragmentHelper();
    }

    public static HomeFragmentHelper getInstance() {
        return HelperHolder.instance;
    }

    public void init(View view, Context context) {
        this.context = context;
        ssqi = view.findViewById(R.id.ssq_qi);
        red1 = view.findViewById(R.id.red_1);
        red2 = view.findViewById(R.id.red_2);
        red3 = view.findViewById(R.id.red_3);
        red4 = view.findViewById(R.id.red_4);
        red5 = view.findViewById(R.id.red_5);
        red6 = view.findViewById(R.id.red_6);
        blue = view.findViewById(R.id.blue);
        jumptoToday = view.findViewById(R.id.weather);
        gestureDetector = new GestureDetector(context, new MyGestureListener());
        flipper = view.findViewById(R.id.flipper);
        currentMonth = view.findViewById(R.id.month);
        nongli = view.findViewById(R.id.nongli);
        yi = view.findViewById(R.id.yi);
        ji = view.findViewById(R.id.ji);
        myscrollview = view.findViewById(R.id.scrollview);
        myListView = view.findViewById(R.id.my_list_view);
        //========================================================================
        bindView();
    }

    private void bindView() {
        getCurrentDay();
        flipper.removeAllViews();
        calV = new CalendarAdapter(context, jumpMonth, jumpYear, year_c, month_c,
                day_c);
        addGridView();
        gridView.setAdapter(calV);
        flipper.addView(gridView, 0);
        addTextToTopTextView(currentMonth);
        showNongLi(
                LunarCalendar.getInstance().getCalendarInfoByChooseDay(
                        Integer.parseInt(calV.getShowYear()),
                        Integer.parseInt(calV.getShowMonth()), day_c),
                calV.getShowYear(), calV.getShowMonth(), day_c + "");
        int position = ((CalendarAdapter) gridView.getAdapter()).currentFlag_;
        myscrollview.setRowNum(position / 7);
        // 标题栏里的日期的点击事件(实现轮子选择日期控件)
        currentMonthClickListenner();
        jumptoToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpTodata(true, year_c, month_c, day_c, -jumpYear,
                        -jumpMonth, year_c, month_c, day_c);
                chooseday = day_c;// 选中的日期为今天
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    private void getCurrentDay() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        String currentDate = sdf.format(date); // 当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
        chooseday = day_c;
    }

    /**
     * 添加头部的年份 闰哪月等信息
     *
     * @param view view
     */
    private void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        textDate.append(calV.getShowYear()).append("年")
                .append(calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
    }

    /**
     * 显示选中日期信息
     *
     * @param nongliStr 农历信息
     * @param year      阳历的年
     * @param month     阳历的月
     * @param day       阳历的日
     */
    private void showNongLi(String nongliStr, String year, String month,
                            String day) {
        nongli.setText(nongliStr);
        YiJiBean bean = HuangLi.getInstance().quearHuangli(year, month, day);
        yi.setText(TextUtils.isEmpty(bean.getYi()) ? "诸事不宜" : bean.getYi());
        ji.setText(TextUtils.isEmpty(bean.getJi()) ? "黄道吉日，诸事大吉" : bean.getJi());
        rbean = LunarCalendar.getInstance().getRiqiBeanInfo(
                Integer.parseInt(year), Integer.parseInt(month),
                Integer.parseInt(day));
        rbean.setYi(TextUtils.isEmpty(bean.getYi()) ? "诸事不宜" : bean.getYi());
        rbean.setJi(TextUtils.isEmpty(bean.getJi()) ? "黄道吉日，诸事大吉" : bean
                .getJi());
    }

    /**
     * 跳转到指定的月份的某一天
     *
     * @param istotoday   是否是点击了回到今天(true为点击了回到今天,false为其他的事件响应)
     * @param year        标题栏里的年份
     * @param month       标题栏里的月份
     * @param day         标题栏里的日期
     * @param jumpYear_c  需要跳转的年份
     * @param jumpMonth_c 需要跳转的月份
     * @param chooseYear  当前选择的年份
     * @param chooseMonth 当前选择的月份
     * @param chooseDay   当前选择的日期
     */
    public void JumpTodata(boolean istotoday, int year, int month, int day,
                           int jumpYear_c, int jumpMonth_c, int chooseYear, int chooseMonth,
                           int chooseDay) {
        if (jumpYear_c == 0 && jumpMonth_c == 0) {// 如果当前界面在本年本月
            // 得到当前日期在GridView里的下标
            int pos = ((CalendarAdapter) gridView.getAdapter()).currentFlag;
            if (pos != -1 && istotoday) {
                setChooseBg(pos);
                showNongLi(
                        LunarCalendar.getInstance().getCalendarInfoByChooseDay(
                                chooseYear, chooseMonth, chooseDay), chooseYear
                                + "", chooseMonth + "", chooseDay + "");
                return;
            }
        } else {// 如果当前界面不在本年本月
            if (jumpMonth_c == -1) {
                flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_out));
            } else if (jumpMonth_c == 1) {
                flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_out));

            } else {
                flipper.setInAnimation(null);
                flipper.setOutAnimation(null);
            }
            if (istotoday) {// 如果点击的是回到今天
                jumpMonth = 0;
                jumpYear = 0;
                jumpMonth_c = 0;
                jumpYear_c = 0;
            }
        }
        calV = new CalendarAdapter(context, jumpMonth_c, jumpYear_c, year, month, day);
        HomeFragmentHelper.getInstance().addGridView();
        gridView.setAdapter(calV);
        flipper.addView(gridView, 1);
        flipper.showNext();
        flipper.removeViewAt(0);
        addTextToTopTextView(currentMonth);
        showNongLi(
                LunarCalendar.getInstance().getCalendarInfoByChooseDay(
                        chooseYear, chooseMonth, chooseDay), chooseYear + "",
                chooseMonth + "", chooseDay + "");
        int position = ((CalendarAdapter) gridView.getAdapter()).currentFlag_;
        myscrollview.setRowNum((position / 7));
        myscrollview.collapse2();
    }

    /**
     * 设置选择日期的背景
     */
    private void setChooseBg(int position) {
        try {
            // 循环遍历GridView里面所有的子项，将背景设为默认状态
            for (int i = 0; i < gridView.getChildCount(); i++) {
                gridView.getChildAt(i).setBackgroundColor(0Xffffff);// 设置背景
                gridView.getChildAt(i).findViewById(R.id.bg)
                        .setBackgroundColor(0Xffffff);
            }
            int resid;
            if (position == calV.currentFlag) {
                resid = R.drawable.current_bg;
            } else {
                // 设置选中日期的背景
                resid = R.drawable.select_bg;
            }
            gridView.getChildAt(position).findViewById(R.id.bg)
                    .setBackgroundResource(resid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        myscrollview.setRowNum((position / 7));
        myscrollview.collapse2();
    }

    /**
     * 移动到下一个月
     *
     * @param gvFlag gvFlag
     */
    public void enterNextMonth(int gvFlag) {
        HomeFragmentHelper.getInstance().addGridView(); // 添加一个gridView
        jumpMonth++; // 下一个月
        flushView(gvFlag, AnimationUtils.loadAnimation(context, R.anim.push_left_in),
                AnimationUtils.loadAnimation(context, R.anim.push_left_out));
    }

    /**
     * 移动到上一个月和下一个月刷新视图
     *
     * @param gvFlag       gvFlag
     * @param inAnimation  画面进入动画
     * @param outAnimation 画面退出动画
     */
    private void flushView(int gvFlag, Animation inAnimation,
                           Animation outAnimation) {
        calV = new CalendarAdapter(context, jumpMonth, jumpYear, year_c, month_c,
                chooseday);
        gridView.setAdapter(calV);
        int position = calV.currentFlag_;
        myscrollview.setRowNum((position / 7));
        if (calV.flag) {
            chooseday = 1;
            myscrollview.setRowNum(0);
        }
        myscrollview.collapse2();
        addTextToTopTextView(currentMonth); // 移动到下一月后，将当月显示在头标题中
        gvFlag++;
        flipper.addView(gridView, gvFlag);
        flipper.setInAnimation(inAnimation);
        flipper.setOutAnimation(outAnimation);
        flipper.showNext();
        flipper.removeViewAt(0);
        showNongLi(
                LunarCalendar.getInstance().getCalendarInfoByChooseDay(
                        Integer.parseInt(calV.getShowYear()),
                        Integer.parseInt(calV.getShowMonth()), chooseday),
                calV.getShowYear(), calV.getShowMonth(), chooseday + "");
    }

    /**
     * 移动到上一个月
     *
     * @param gvFlag gvFlag
     */
    public void enterPrevMonth(int gvFlag) {
        HomeFragmentHelper.getInstance().addGridView(); // 添加一个gridView
        jumpMonth--; // 上一个月
        flushView(gvFlag,
                AnimationUtils.loadAnimation(context, R.anim.push_right_in),
                AnimationUtils.loadAnimation(context, R.anim.push_right_out));

    }

    /**
     * 标题栏里的日期的点击事件(实现轮子选择日期控件)
     */
    private void currentMonthClickListenner() {
        currentMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                LayoutInflater inflater = LayoutInflater.from(context);// 得到视图转换器
                PickUtils.getInstance().setInflater(inflater);// 设置视图转换器
                String ym = currentMonth.getText().toString();// 得到标题里的年月信息
                final int cyear = Integer.parseInt(ym.substring(0,
                        ym.indexOf("年")));// 得到标题里的年份
                final int cmonth = Integer.parseInt(ym.substring(
                        ym.indexOf("年") + 1, ym.indexOf("月")));// 得到标题里的月份
                PickUtils.getInstance().showPopwindow(
                        PickUtils.getInstance().getDataPick(cyear, cmonth,
                                chooseday));// 弹出日期选择器
                // 设置回调接口，返回数据
                PickUtils.getInstance().setCallback(new PickUtils.CallBack() {

                    @Override
                    public void SetStr(String str) {
                        currentMonth.setText(str.substring(0,
                                str.indexOf("月") + 1));
                        int year = Integer.parseInt(str.substring(0,
                                str.indexOf("年")));// 得到选择的年份
                        int month = Integer.parseInt(str.substring(
                                str.indexOf("年") + 1, str.indexOf("月")));// 得到选择的月份
                        int day = Integer.parseInt(str.substring(
                                str.indexOf("月") + 1, str.length()));// 得到选中的日期
                        chooseday = day;// 轮子选择器得到的时间为选择时间
                        // =========设置当前日历界面到选择的界面===============
                        int jumpYear_c = year - cyear;
                        int jumpMonth_c = month - cmonth;
                        jumpMonth_c = jumpYear_c * 12 + jumpMonth_c;
                        jumpYear += jumpYear_c;
                        jumpMonth += jumpMonth_c;
                        JumpTodata(false, cyear, cmonth, day, jumpYear_c,
                                jumpMonth_c, year, month, day);
                        // =========================
                    }
                });
            }
        });
    }

    /**
     * 通过日历视图的下标得到日期农历信息
     *
     * @param position 日历视图的下标
     * @return 农历信息(包含农历年份月份日期以及周几)
     */
    private String getCalendarInfo(int position) {
        String scheduleYear = calV.getShowYear();// 得到当前的阳历的年份
        String scheduleMonth = calV.getShowMonth();// 得到当前的阳历的月份
        String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 得到当前的阳历的日期
        int year_long = Integer.parseInt(scheduleYear);// 当前的阳历的年份
        int month_long = Integer.parseInt(scheduleMonth);// 当前的阳历的月份
        int day_long = Integer.parseInt(scheduleDay);// 当前的阳历的日期
        return (LunarCalendar.getInstance().getCalendarInfoByChooseDay(
                year_long, month_long, day_long));
    }

    /**
     * 添加一个网格视图
     */
    @SuppressLint("ClickableViewAccessibility")
    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);

        gridView = new MyGridView(context);
        gridView.setNumColumns(7);
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        // 去除gridView边框
        gridView.setVerticalSpacing(0);
        gridView.setHorizontalSpacing(0);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 去除点击效果
        gridView.setOnTouchListener(new View.OnTouchListener() {
            // 将GridView中的触摸事件回传给gestureDetector
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long arg3) {
                setChooseBg(position);// 设置选中的背景
                // =====================================
                // TODO Auto-generated method stub
                // 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
                // myscrollview.setRowNum((position / 7));
                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();

                String scheduleDay = calV.getDateByClickItem(position).split(
                        "\\.")[0]; // 这一天的阳历
                chooseday = Integer.parseInt(scheduleDay);// 点击日历的哪一天时为选择日期
                if (startPosition <= position + 7
                        && position <= endPosition - 7) {
                    showNongLi(HomeFragmentHelper.getInstance().getCalendarInfo(position), calV.getShowYear(),
                            calV.getShowMonth(),
                            calV.getDateByClickItem(position).split("\\.")[0]);

                }
                if (position < startPosition - 7) {
                    // 如果点击的下标比当月开始的下标小，则跳转到上一个月，并显示那一天
                    enterPrevMonth(0);
                }
                if (position > endPosition - 7) {
                    enterNextMonth(0);
                }
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                // TODO Auto-generated method stub
                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();
                if (startPosition <= position + 7
                        && position <= endPosition - 7) {
                    // 只有在当前月份时才可以有长按的响应事件
                    getCalendarInfo(position);
                }
                return true;
            }

        });
        gridView.setLayoutParams(params);

    }

    public void getLot() {
        APIService.getInstance(Constant.LOT_URL).getLot(new ProgressSubscriber<>(new Action1<Kaijiang>() {
            @Override
            public void call(Kaijiang kaijiang) {
                setLot(kaijiang);
            }
        }));
    }

    /**
     * 设置开奖信息
     *
     * @param kaijiang 开奖信息
     */
    private void setLot(Kaijiang kaijiang) {
        ssqi.setText(String.format("-第%s期", kaijiang.getBack().get(0).getQh()));
        String blueq = kaijiang.getBack().get(0).getLot().split("[#]")[1];// "+"是正则表达式的符号，所以需要完全匹配"[+]"
        String red[] = (kaijiang.getBack().get(0).getLot().split("[#]")[0]).split("[,]");
        red1.setText(red[0]);
        red2.setText(red[1]);
        red3.setText(red[2]);
        red4.setText(red[3]);
        red5.setText(red[4]);
        red6.setText(red[5]);
        blue.setText(blueq);
    }

    public void getLayout(String cityCode) {
        APIService.getInstance(Constant.GET_XIAO_MI_LAYOUT2, 1).getLayout(cityCode, new ProgressSubscriber<>(new Action1<Object>() {
            @Override
            public void call(Object layout) {
                try {
                    JSONObject object = new JSONObject((String) layout);
                    setXiaoMILayout(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void getWeather(final LocationInfo locationInfo) {
        APIService.getInstance(Constant.GET_WEATHER, APIService.backStringData).anyGet(new HashMap<String, Object>() {{
            put("city", locationInfo.getDistrict());
        }}, null, new ProgressSubscriber<>(new Action1<Object>() {
            @Override
            public void call(Object layout) {
                try {
                    JSONObject object = new JSONObject((String) layout);
                    setWeather(locationInfo, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));

    }

    /**
     * 设置天气信息
     *
     * @param object 天气信息
     */
    private void setWeather(LocationInfo locationInfo, JSONObject object) {
        try {
            JSONObject data = object.optJSONObject("data");
            String province = data.optJSONObject("city").optString("pname");
            JSONObject condition = data.optJSONObject("condition");
            String info = condition.optString("condition");
            String temperature = condition.optString("temp");
            StringBuilder buffer = new StringBuilder();
            String street = locationInfo.getStreet();
            if (TextUtils.isEmpty(street)) {
                street = locationInfo.getRoad();
            }
            buffer.append(locationInfo.getAddress()).append(" ").append(locationInfo.getCityName()).append(" ").append(locationInfo.getDistrict()).append(" ").append(street).append(" ").append(info).append(" ").append(temperature).append("℃");
            //jumptoToday.setText(buffer.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置小米的指数布局
     *
     * @param object 布局信息
     */
    private void setXiaoMILayout(JSONObject object) {
        // TODO Auto-generated method stub
        JSONArray array = object.optJSONArray("cards");
        if (array != null) {
            List<XiaomiWeather> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.optJSONObject(i);
                String title = obj.optString("title");
                List<XiaomiZhishuList> listZhishu = new ArrayList<>();
                JSONArray arr = obj.optJSONArray("list");
                if (arr != null) {
                    for (int j = 0; j < arr.length(); j++) {
                        JSONObject o = arr.optJSONObject(j);
                        if (Utils.isNull(o)) {
                            continue;
                        }
                        JSONObject data = o.optJSONObject("data");
                        if (Utils.isNull(data)) {
                            continue;
                        }
                        String image = data.optString("wtrImges").replace("[", "").replace("]", "").split(",")[0].replace("\"", "").replace("\\", "");
                        String summary = data.optString("wtrSummary");
                        String t = data.optString("wtrTitle");
                        JSONObject wtrHeadData = data.optJSONObject("wtrHeadData");
                        JSONObject wtrLink = data.optJSONObject("wtrLink");
                        if (Utils.isNull(wtrHeadData) || Utils.isNull(wtrLink)) {
                            continue;
                        }
                        HeadData headData = new HeadData(wtrHeadData.optString("summary"), wtrHeadData.optString("imgUrl"), wtrHeadData.optString("title"), wtrHeadData.optString("iconImgUrl"));
                        Link link = new Link(wtrLink.optString("channelId"), wtrLink.optString("type"), wtrLink.optString("url"));
                        XiaomiZhishuList bean = new XiaomiZhishuList(image, summary, title, headData, link);
                        listZhishu.add(bean);
                    }
                }
                if (Utils.isNull(listZhishu)) {
                    continue;
                }
                XiaomiWeather weather = new XiaomiWeather(title, listZhishu);
                datas.add(weather);
            }
            XiaoMIWeatherAdapter adapter = new XiaoMIWeatherAdapter(context, datas);
            myListView.setAdapter(adapter);
        }
    }
}
