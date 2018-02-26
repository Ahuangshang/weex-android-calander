package cn.ltwc.cft.beans;

public class XiaomiZhishuList {
    private String image;
    private String summary;
    private String title;
    private HeadData headData;
    private Link link;

    public XiaomiZhishuList() {
    }

    public XiaomiZhishuList(String image, String summary, String title, HeadData headData, Link link) {
        this.image = image;
        this.summary = summary;
        this.title = title;
        this.headData = headData;
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HeadData getHeadData() {
        return headData;
    }

    public void setHeadData(HeadData headData) {
        this.headData = headData;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
