package com.zenchn.library.event;

/**
 * 作    者：wangr on 2017/2/28 22:45
 * 描    述：
 * 修订记录：
 */
public class EventFactory<T> {

    private int tag;
    private T data;

    public EventFactory(int tag) {
        this.tag = tag;
    }

    public EventFactory(int tag, T data) {
        this.tag = tag;
        this.data = data;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EventFactory{" +
                "tag=" + tag +
                ", data=" + data.toString() +
                '}';
    }
}
