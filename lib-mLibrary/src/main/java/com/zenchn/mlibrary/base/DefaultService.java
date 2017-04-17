package com.zenchn.mlibrary.base;

import android.app.Service;
import android.os.Looper;
import android.os.Message;


/**
 * service基础类
 *
 * @author xuxu
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
