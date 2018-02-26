package cn.ltwc.cft.rxbus;

/**
 * Event
 * Created by Dino on 2016/11/21.
 */

public class Event {
    public int id;
    public Object data;

    public Event(int id) {
        this.id = id;
    }

    public Event(int id, Object data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BusEvent{" +
                "id='" + id + '\'' +
                "data='" + data +
                '}';
    }
}
