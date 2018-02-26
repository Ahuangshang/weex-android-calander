package cn.ltwc.cft.beans;

/**
 * xiaomi Layout
 * Created by Administrator on 2017/12/3 0003.
 */

public class HeadData {
    private String summary;
    private String imgUrl;
    private String title;
    private String iconImgUrl;

    public HeadData() {
    }

    public HeadData(String summary, String imgUrl, String title, String iconImgUrl) {
        this.summary = summary;
        this.imgUrl = imgUrl;
        this.title = title;
        this.iconImgUrl = iconImgUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconImgUrl() {
        return iconImgUrl;
    }

    public void setIconImgUrl(String iconImgUrl) {
        this.iconImgUrl = iconImgUrl;
    }
}
