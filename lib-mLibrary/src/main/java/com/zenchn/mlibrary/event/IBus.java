package com.zenchn.mlibrary.event;

/**
 * 作    者：wangr on 2017/2/28 22:45
 * 描    述：
 * 修订记录：
 */

public interface IBus {

    void register(Object object);

    void unregister(Object object);

    void post(EventFactory event);

    void post(IEvent event);

    void postSticky(IEvent event);


    interface IEvent {
        int getTag();
    }

}
