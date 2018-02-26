package cn.ltwc.cft.beans;

/**
 * Created by Administrator on 2017/12/3 0003.
 */

public class Link {
    private String channelId;
    private String type;
    private String url;

    public Link() {
    }

    public Link(String channelId, String type, String url) {
        this.channelId = channelId;
        this.type = type;
        this.url = url;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
