package cn.ltwc.cft.data;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.ltwc.cft.beans.RiqiBean;

/**
 * TODO:农历算法
 *
 * @author huangshang 2015-11-18 下午7:53:32
 * @Modified_By:
 */
@SuppressLint("SimpleDateFormat")
public class LunarCalendar {
    private int year; // 农历的年份
    private int month;
    private int day;
    private String lunarMonth; // 农历的月份
    private boolean leap;
    public int leapMonth = 0; // 闰的是哪个月
    // 单例模式
    private static LunarCalendar instance = new LunarCalendar();

    private LunarCalendar() {

    }

    public static LunarCalendar getInstance() {
        return instance;
    }

    final static String chineseNumber[] = {"一", "二", "三", "四", "五", "六", "七",
            "八", "九", "十", "十一", "十二"};
    final static String chineseLNumber[] = {"正", "二", "三", "四", "五", "六", "七",
            "八", "九", "十", "冬", "腊"};
    static SimpleDateFormat chineseDateFormat = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm:ss:SSS");
    //lunarInfo是从1900年到2099年所有的农历天数和闰月信息
    //然后解释一下表中的数据，拿第一个0x04bd8来说吧，他是5个16进制数，共20bit。
    // 最后四位，即8，代表该年闰月的月份，为0则没有闰月。前四位，即0，在该年有闰月才有意义，为0表示闰月29天，为1表示闰月30天。
    // 中间12位，即4bd代表该年12个月每个月的天数，0表示29天，1表示30天。
    final static long[] lunarInfo = new long[]{ //
            0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
            0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
            0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
            0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,
            0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,
            0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
            0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,
            0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,
            0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
            0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
            0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0,
            0x14b63, 0x09370, 0x049f8, 0x14970, 0x064b0, 0x168a6, 0x0ea50, 0x06aa0, 0x1a6c4, 0x0aae0,
            0x092e0, 0x0d2e3, 0x0c960, 0x0d557, 0x0d4a0, 0x0da50, 0x05d55, 0x056a0, 0x0a6d0, 0x055d4,
            0x052d0, 0x0a9b8, 0x0a950, 0x0b4a0, 0x0b6a6, 0x0ad50, 0x055a0, 0x0aba4, 0x0a5b0, 0x052b0,
            0x0b273, 0x06930, 0x07337, 0x06aa0, 0x0ad50, 0x14b55, 0x04b60, 0x0a570, 0x054e4, 0x0d260,
            0x0e968, 0x0d520, 0x0daa0, 0x16aa6, 0x056d0, 0x04ae0, 0x0a9d4, 0x0a4d0, 0x0d150, 0x0f252,
    };

    // ====== 传回农历 y年的总天数
    final private static int yearDays(int y) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((lunarInfo[(y - 1900) % lunarInfo.length] & i) != 0)
                sum += 1;
        }
        return (sum + leapDays(y));
    }

    // ====== 传回农历 y年闰月的天数
    final private static int leapDays(int y) {
        if (leapMonth(y) != 0) {
            if ((lunarInfo[(y - 1900) % lunarInfo.length] & 0x10000) != 0)
                return 30;
            else
                return 29;
        } else
            return 0;
    }

    // ====== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
    final private static int leapMonth(int y) {
        return (int) (lunarInfo[(y - 1900) % lunarInfo.length] & 0xf);
    }

    // ====== 传回农历 y年m月的总天数
    final private static int monthDays(int y, int m) {
        if ((lunarInfo[(y - 1900) % lunarInfo.length] & (0x10000 >> m)) == 0)
            return 29;
        else
            return 30;
    }

    // ====== 传回农历 y年的生肖
    final public String animalsYear(int year) {
        final String[] Animals = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇",
                "马", "羊", "猴", "鸡", "狗", "猪"};
        return Animals[(year - 4) % 12];
    }

    // ====== 传入 月日的offset 传回干支, 0=甲子
    final private static String cyclicalm(int num) {
        final String[] Gan = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚",
                "辛", "壬", "癸"};
        final String[] Zhi = new String[]{"子", "丑", "寅", "卯", "辰", "巳", "午",
                "未", "申", "酉", "戌", "亥"};
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    // ====== 传入 offset 传回干支, 0=甲子
    final public String cyclical(int year) {
        int num = year - 1900 + 36;
        return (cyclicalm(num));
    }

    public static String getChinaDayString(int day) {
        String chineseTen[] = {"初", "十", "廿", "三"};
        int n = day % 10 == 0 ? 9 : day % 10 - 1;
        if (day > 30)
            return "";
        if (day == 10)
            return "初十";
        else
            return chineseTen[day / 10] + chineseNumber[n];
    }

/**
 * 传出y年m月d日对应的农历. yearCyl3:农历年与1864的相差数 ? monCyl4:从1900年1月31日以来,闰月数
 * dayCyl5:与1900年1月31日相差的天数,再加40 ?
 * <p>
 * isday: 这个参数为false---日期为节假日时，阴历日期就返回节假日 ，true---不管日期是否为节假日依然返回这天对应的阴历日期
 *
 * @param
 * @return 传出y年m月d日对应的农历. yearCyl3:农历年与1864的相差数 ? monCyl4:从1900年1月31日以来,闰月数
 * dayCyl5:与1900年1月31日相差的天数,再加40 ?
 * <p>
 * isday: 这个参数为false---日期为节假日时，阴历日期就返回节假日 ，true---不管日期是否为节假日依然返回这天对应的阴历日期
 * @param
 * @return
 */
    /**
     * 传出y年m月d日对应的农历. yearCyl3:农历年与1864的相差数 ? monCyl4:从1900年1月31日以来,闰月数
     * dayCyl5:与1900年1月31日相差的天数,再加40 ?
     * <p>
     * isday: 这个参数为false---日期为节假日时，阴历日期就返回节假日 ，true---不管日期是否为节假日依然返回这天对应的阴历日期
     *
     * @param
     * @return
     */

    public String getLunarDate(int year_log, int month_log, int day_log,
                               boolean isday) {
// @SuppressWarnings("unused")
        @SuppressWarnings("unused")
        int yearCyl, monCyl, dayCyl;
        // int leapMonth = 0;
        String nowadays;
        Date baseDate = null;
        Date nowaday = null;
        try {
            baseDate = chineseDateFormat.parse("1900年1月31日 00:00:00:000");
        } catch (ParseException e) {
            e.printStackTrace(); // To change body of catch statement use
            // Options | File Templates.
        }

        nowadays = year_log + "年" + month_log + "月" + day_log + "日 00:00:00:000";
        try {
            nowaday = chineseDateFormat.parse(nowadays);
        } catch (ParseException e) {
            e.printStackTrace(); // To change body of catch statement use
            // Options | File Templates.
        }

        // 求出和1900年1月31日相差的天数
        //TODO：特别说明：这里采用了四舍五入的方法来计算相隔天数，如不这样计算，计算出的时间存在误差
        int offset = (int) Math.round((nowaday.getTime() - baseDate.getTime()) * 1.0 / 86400000L);
        dayCyl = offset + 40;
        monCyl = 14;

        // 用offset减去每农历年的天数
        // 计算当天是农历第几天
        // i最终结果是农历的年份
        // offset是当年的第几天
        int iYear, daysOfYear = 0;
        for (iYear = 1900; iYear < 10000 && offset > 0; iYear++) {
            daysOfYear = yearDays(iYear);
            offset -= daysOfYear;
            monCyl += 12;
        }
        if (offset < 0) {
            offset += daysOfYear;
            iYear--;
            monCyl -= 12;
        }
        // 农历年份
        year = iYear;
        setYear(year); // 设置公历对应的农历年份

        yearCyl = iYear - 1864;
        leapMonth = leapMonth(iYear); // 闰哪个月,1-12
        leap = false;

        // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
        int iMonth, daysOfMonth = 0;
        for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
            // 闰月
            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
                --iMonth;
                leap = true;
                daysOfMonth = leapDays(year);
            } else
                daysOfMonth = monthDays(year, iMonth);

            offset -= daysOfMonth;
            // 解除闰月
            if (leap && iMonth == (leapMonth + 1))
                leap = false;
            if (!leap)
                monCyl++;
        }
        // offset为0时，并且刚才计算的月份是闰月，要校正
        if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
            if (leap) {
                leap = false;
            } else {
                leap = true;
                --iMonth;
                --monCyl;
            }
        }
        // offset小于0时，也要校正
        if (offset < 0) {
            offset += daysOfMonth;
            --iMonth;
            --monCyl;
        }
        month = iMonth;
        setLunarMonth(leap ? "闰" + chineseLNumber[month - 1] + "月"
                : chineseLNumber[month - 1] + "月"); // 设置对应的阴历月份
        day = offset + 1;
        StringBuffer sb = new StringBuffer();
        if (!isday) {
            // 如果日期为节假日则阴历日期则返回节假日
            // setLeapMonth(leapMonth);
            for (int i = 0; i < Constant.solarHoliday.length; i++) {
                // 返回公历节假日名称
                String sd = Constant.solarHoliday[i].split(" ")[0]; // 节假日的日期
                String sdv = Constant.solarHoliday[i].split(" ")[1]; // 节假日的名称
                String smonth_v = month_log + "";
                String sday_v = day_log + "";
                String smd = "";
                if (month_log < 10) {
                    smonth_v = "0" + month_log;
                }
                if (day_log < 10) {
                    sday_v = "0" + day_log;
                }
                smd = smonth_v + sday_v;
                if (sd.trim().equals(smd.trim())) {
                    //TODO:新增一些节日还未形成在日历上不显示
                    if (sdv.equals("国庆节")) {
                        if (year_log > 1949) {
                            sb = build(sb.toString(), sdv);
                        } else if (year_log == 1949) {
                            sb = build(sb.toString(), "新中国成立");
                        }
                    } else if (sdv.equals("澳门回归")) {
                        if (year_log >= 1999) {
                            sb = build(sb.toString(), sdv);
                        }
                    } else if (sdv.equals("香港回归")) {
                        if (year_log >= 1997) {
                            sb = build(sb.toString(), sdv);
                        }
                    } else if (sdv.equals("建党节")) {
                        if (year_log >= 1921) {
                            sb = build(sb.toString(), sdv);
                        }
                    } else if (sdv.equals("建军节")) {
                        if (year_log >= 1927) {
                            sb = build(sb.toString(), sdv);
                        }
                    } else if (sdv.equals("抗战胜利")) {
                        if (year_log >= 1945) {
                            sb = build(sb.toString(), sdv);
                        }
                    } else if (sdv.equals("九一八")) {
                        if (year_log >= 1931) {
                            sb = build(sb.toString(), sdv);
                        }
                    } else {
                        sb = build(sb.toString(), sdv);
                    }
                    if (!sdv.equals("建党节")) {
                        break;
                    }
                }
            }

            for (int i = 0; i < Constant.lunarHoliday.length; i++) {
                // 返回农历节假日名称
                String ld = Constant.lunarHoliday[i].split(" ")[0]; // 节假日的日期
                String ldv = Constant.lunarHoliday[i].split(" ")[1]; // 节假日的名称
                String lmonth_v = month + "";
                String lday_v = day + "";
                String lmd = "";
                if (month < 10) {
                    lmonth_v = "0" + month;
                }
                if (day < 10) {
                    lday_v = "0" + day;
                }

                lmd = lmonth_v + lday_v;
                if (!leap) {
                    if (month == 12 && monthDays(year, month) == 29 && day == 29) {
                        sb = build(sb.toString(), "除夕");
                        break;
                    } else if (ld.trim().equals(lmd.trim())) {
                        sb = build(sb.toString(), ldv);
                        break;
                    }
                }
            }
            if (month_log < 13 && month_log > 0) {
                String jieqi = new JieQi(year_log, month_log, day_log)
                        .getSolartermsMsg();
                if (!TextUtils.isEmpty(jieqi)) {
                    sb = build(sb.toString(), jieqi);
                }
            }

        }
        if (TextUtils.isEmpty(sb.toString())) {
            if (day == 1) {
                return leap ? "闰" + chineseLNumber[month - 1] + "月"
                        : chineseLNumber[month - 1] + "月";
            } else {
                return getChinaDayString(day);
            }
        } else {
            return sb.toString();
        }
    }

    private StringBuffer build(String before, String append) {
        StringBuffer sb = new StringBuffer();
        if (TextUtils.isEmpty(before)) {
            sb.append(append);
        } else {
            sb.append(before).append("\n").append(append);
        }
        return sb;
    }

    public String toString() {
        if (chineseLNumber[month - 1] == "正" && getChinaDayString(day) == "初一")
            return "农历" + year + "年";
        else if (getChinaDayString(day) == "初一")
            return chineseLNumber[month - 1] + "月";
        else
            return getChinaDayString(day);
        // return year + "年" + (leap ? "闰" : "") + chineseNumber[month - 1] +
        // "月" + getChinaDayString(day);
    }

// public static void main(String[] args) { System.out.println(new
// LunarCalendar().getLunarDate(2012, 1, 23,false)); }

    public int getLeapMonth() {
        return leapMonth;
    }

    public void setLeapMonth(int leapMonth) {
        this.leapMonth = leapMonth;
    }

    /**
     * 得到当前日期对应的阴历月份
     *
     * @return
     */
    public String getLunarMonth() {
        return lunarMonth;
    }

    public void setLunarMonth(String lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    /**
     * 得到当前年对应的农历年份
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * 通过选定的阳历日期得到日期农历信息
     *
     * @param chooseYear  选中的阳历年份
     * @param chooseMonth 选中的阳历月份
     * @param chooseDay   选中的阳历日期
     * @return 农历信息(包含农历年份月份日期以及周几)
     */
    @SuppressWarnings("unused")
    public String getCalendarInfoByChooseDay(int chooseYear, int chooseMonth,
                                             int chooseDay) {
        String lunarDate = getLunarDate(chooseYear, chooseMonth, chooseDay,
                true);// 得到当前的农历的日期(先执行此方法，通过传入阳历数据计算得到农历信息)
        String lunarMonth = getLunarMonth();// 得到当前的农历的月份
        if (lunarMonth.equals(lunarDate)) {
            // 如果当前的农历日期是月份，则返回初一
            lunarDate = "初一";
        }
        int year = getYear();// 得到当前的农历的年份
        String cyYeay = cyclical(year);// 天干地支纪年的年份
        String animal = animalsYear(year);// 生肖
        int temp = SpecialCalendar.getInstance().getWeekdayOfMonth(chooseYear,
                chooseMonth, chooseDay);// 得到该天星期几
        String week = "";
        switch (temp) {
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;

        }

        // return (cyYeay + animal + "年" + lunarMonth + lunarDate + week);
        return (cyYeay + animal + "年" + lunarMonth + lunarDate);

    }

    /**
     * 通过选定的阳历日期得到日期农历信息
     *
     * @param chooseYear  选中的阳历年份
     * @param chooseMonth 选中的阳历月份
     * @param chooseDay   选中的阳历日期
     * @return 农历信息(包含农历年份月份日期以及周几)
     */
    public RiqiBean getRiqiBeanInfo(int chooseYear, int chooseMonth,
                                    int chooseDay) {
        String lunarDate = getLunarDate(chooseYear, chooseMonth, chooseDay,
                true);// 得到当前的农历的日期(先执行此方法，通过传入阳历数据计算得到农历信息)
        String lunarMonth = getLunarMonth();// 得到当前的农历的月份
        if (lunarMonth.equals(lunarDate)) {
            // 如果当前的农历日期是月份，则返回初一
            lunarDate = "初一";
        }
        int year = getYear();// 得到当前的农历的年份
        String cyYeay = cyclical(year);// 天干地支纪年的年份
        String animal = animalsYear(year);// 生肖
        int temp = SpecialCalendar.getInstance().getWeekdayOfMonth(chooseYear,
                chooseMonth, chooseDay);// 得到该天星期几
        RiqiBean bean = new RiqiBean();
        bean.setYear(chooseYear);
        bean.setyMonth(chooseMonth);
        bean.setyDay(chooseDay);
        bean.setWeek(temp);
        bean.setnYear(cyYeay);
        bean.setnMonth(lunarMonth);
        bean.setnDay(lunarDate);
        bean.setnHolidayDay(getLunarDate(chooseYear, chooseMonth, chooseDay,
                false));
        bean.setnAnimal(animal);
        return bean;

    }
}
