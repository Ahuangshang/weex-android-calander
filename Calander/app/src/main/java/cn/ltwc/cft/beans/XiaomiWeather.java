package cn.ltwc.cft.beans;

import java.util.List;

public class XiaomiWeather {
    private String title;
    private List<XiaomiZhishuList> listZhishu;

    public XiaomiWeather() {
        super();
    }

    public XiaomiWeather(String title, List<XiaomiZhishuList> listZhishu) {
        super();
        this.title = title;
        this.listZhishu = listZhishu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<XiaomiZhishuList> getListZhishu() {
        return listZhishu;
    }

    public void setListZhishu(List<XiaomiZhishuList> listZhishu) {
        this.listZhishu = listZhishu;
    }

}
