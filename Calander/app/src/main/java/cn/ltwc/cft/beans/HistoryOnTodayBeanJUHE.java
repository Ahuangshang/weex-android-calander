package cn.ltwc.cft.beans;

import java.io.Serializable;

/**
 * 历史上的今天聚合数据模型
 *
 * @author LZL
 */
@SuppressWarnings("serial")
public class HistoryOnTodayBeanJUHE implements Serializable {
    private String year;
    private String title;
    private String e_id;

    public HistoryOnTodayBeanJUHE() {
        // TODO Auto-generated constructor stub
    }

    public HistoryOnTodayBeanJUHE(String year, String title, String e_id) {
        super();
        this.year = year;
        this.title = title;
        this.e_id = e_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    @Override
    public String toString() {
        return "HistoryOnTodayBeanJUHE [year=" + year + ", title=" + title
                + ", e_id=" + e_id + "]";
    }

}
