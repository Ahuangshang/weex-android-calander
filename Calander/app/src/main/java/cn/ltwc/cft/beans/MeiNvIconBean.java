package cn.ltwc.cft.beans;

public class MeiNvIconBean {

    private String title;

    private String picUrl;

    public MeiNvIconBean(String title, String picUrl) {
        super();
        this.title = title;

        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "IconBean [title=" + title + ", picUrl=" + picUrl + "]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}
