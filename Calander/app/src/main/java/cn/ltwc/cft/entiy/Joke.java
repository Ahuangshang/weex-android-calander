package cn.ltwc.cft.entiy;

public class Joke {
    private String content;
    private long publishTime;

    public Joke() {
    }

    public Joke(String content, long publishTime) {
        this.content = content;
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }
}
