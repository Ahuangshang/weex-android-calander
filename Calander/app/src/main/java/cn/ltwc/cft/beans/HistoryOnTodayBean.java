package cn.ltwc.cft.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HistoryOnTodayBean implements Serializable {
    private String title;
    private String event;
    String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public HistoryOnTodayBean() {
        super();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public HistoryOnTodayBean(String title, String event, String year) {
        super();
        this.title = title;
        this.event = event;
        this.year = year;
    }

    @Override
    public String toString() {
        return "HistoryOnTodayBean [title=" + title + ", event=" + event
                + ", year=" + year + "]";
    }

}
