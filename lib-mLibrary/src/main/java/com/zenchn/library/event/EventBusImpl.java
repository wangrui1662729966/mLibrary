package com.zenchn.library.event;

import org.greenrobot.eventbus.EventBus;

/**
 * 作    者：wangr on 2017/2/28 22:45
 * 描    述： 
 * 修订记录：
 */

public class EventBusImpl implements IBus {

    @Override
    public void register(Object object) {
        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }
    }


    @Override
    public void unregister(Object object) {
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }
    }

    @Override
    public void post(EventFactory event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void post(IEvent event) {
        EventBus.getDefault().post(event);
    }


    @Override
    public void postSticky(IEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
