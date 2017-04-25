package com.zenchn.library.base;

import android.app.Service;
import android.os.Looper;
import android.os.Message;


/**
 * 作    者：wangr on 2017/4/17 14:25
 * 描    述： 
 * 修订记录：
 */
public abstract class DefaultService extends Service {

    protected UiHandler handler = new UiHandler(Looper.getMainLooper());

    public DefaultService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        setHandler();
    }

    private void setHandler() {
        handler.setHandler(new IHandler() {
            public void handleMessage(Message msg) {
                handler(msg);// 有消息就提交给子类实现的方法
            }
        });
    }

    // 让子类处理消息
    protected abstract void handler(Message msg);

}
