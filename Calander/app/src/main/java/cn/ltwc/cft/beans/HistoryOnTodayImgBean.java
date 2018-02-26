package cn.ltwc.cft.beans;

public class HistoryOnTodayImgBean {

    private String imgTitle;
    private String imgUrl;

    public HistoryOnTodayImgBean(String imgTitle, String imgUrl) {
        super();
        this.imgTitle = imgTitle;
        this.imgUrl = imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "HistoryOnTodayImgBean [imgTitle=" + imgTitle + ", imgUrl="
                + imgUrl + "]";
    }

}
