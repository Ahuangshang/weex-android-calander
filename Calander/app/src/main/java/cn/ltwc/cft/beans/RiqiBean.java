package cn.ltwc.cft.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 日期的bean TODO:
 *
 * @author huangshang 2016-7-2 下午9:56:54
 * @Modified_By:
 */

public class RiqiBean implements Parcelable {
    private int year, yMonth, yDay, week;// 阳历的年月日和星期几（0代表星期日）
    private String nYear, nMonth, nDay, nAnimal, nHolidayDay;// 农历的年月日和生肖,农历日期带节假日返回格式
    private String yi, ji;

    public RiqiBean() {
        // TODO Auto-generated constructor stub
    }

    public RiqiBean(int year, int yMonth, int yDay, int week, String nYear,
                    String nMonth, String nDay, String nAnimal, String nHolidayDay,
                    String yi, String ji) {
        super();
        this.year = year;
        this.yMonth = yMonth;
        this.yDay = yDay;
        this.week = week;
        this.nYear = nYear;
        this.nMonth = nMonth;
        this.nDay = nDay;
        this.nAnimal = nAnimal;
        this.nHolidayDay = nHolidayDay;
        this.yi = yi;
        this.ji = ji;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getyMonth() {
        return yMonth;
    }

    public void setyMonth(int yMonth) {
        this.yMonth = yMonth;
    }

    public int getyDay() {
        return yDay;
    }

    public void setyDay(int yDay) {
        this.yDay = yDay;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getnYear() {
        return nYear;
    }

    public void setnYear(String nYear) {
        this.nYear = nYear;
    }

    public String getnMonth() {
        return nMonth;
    }

    public void setnMonth(String nMonth) {
        this.nMonth = nMonth;
    }

    public String getnDay() {
        return nDay;
    }

    public void setnDay(String nDay) {
        this.nDay = nDay;
    }

    public String getnAnimal() {
        return nAnimal;
    }

    public void setnAnimal(String nAnimal) {
        this.nAnimal = nAnimal;
    }

    public String getYi() {
        return yi;
    }

    public void setYi(String yi) {
        this.yi = yi;
    }

    public String getJi() {
        return ji;
    }

    public void setJi(String ji) {
        this.ji = ji;
    }

    public String getnHolidayDay() {
        return nHolidayDay;
    }

    public void setnHolidayDay(String nHolidayDay) {
        this.nHolidayDay = nHolidayDay;
    }

    @Override
    public String toString() {
        return "RiqiBean [year=" + year + ", yMonth=" + yMonth + ", yDay="
                + yDay + ", week=" + week + ", nYear=" + nYear + ", nMonth="
                + nMonth + ", nDay=" + nDay + ", nAnimal=" + nAnimal
                + ", nHolidayDay=" + nHolidayDay + ", yi=" + yi + ", ji=" + ji
                + "]";
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeInt(year);
        dest.writeInt(yMonth);
        dest.writeInt(yDay);
        dest.writeInt(week);
        dest.writeString(nYear);
        dest.writeString(nMonth);
        dest.writeString(nDay);
        dest.writeString(nAnimal);
        dest.writeString(nHolidayDay);
        dest.writeString(yi);
        dest.writeString(ji);
    }

    public static final Creator<RiqiBean> CREATOR = new Creator<RiqiBean>() {

        @Override
        public RiqiBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new RiqiBean(source);
        }

        @Override
        public RiqiBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new RiqiBean[size];
        }

    };

    public RiqiBean(Parcel in) {
        year = in.readInt();
        yMonth = in.readInt();
        yDay = in.readInt();
        week = in.readInt();
        nYear = in.readString();
        nMonth = in.readString();
        nDay = in.readString();
        nAnimal = in.readString();
        nHolidayDay = in.readString();
        yi = in.readString();
        ji = in.readString();
    }

}
