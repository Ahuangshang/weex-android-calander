package cn.ltwc.cft.beans;

import java.util.List;

public class ZhishuDetailBean {
    private String title;
    private String summary;
    private String source;
    private List<String> images;
    private Link link;

    public ZhishuDetailBean() {
    }

    public ZhishuDetailBean(String title, String summary, String source, List<String> images, Link link) {
        this.title = title;
        this.summary = summary;
        this.source = source;
        this.images = images;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
