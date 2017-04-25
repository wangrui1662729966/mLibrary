package com.zenchn.library.event;

/**
 * 作    者：wangr on 2017/2/28 22:45
 * 描    述： 
 * 修订记录：
 */

public class BusFactory {
    
    private static IBus bus;

    public static IBus getBus() {
        if (bus == null) {
            synchronized (BusFactory.class) {
                if (bus == null) {
                    bus = new EventBusImpl();
                }
            }
        }
        return bus;
    }
}
