package com.zenchn.library.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class UiHandler extends Handler {

    private IHandler handler;//回调接口，消息传递给注册者

    public UiHandler(Looper looper) {
        super(looper);
    }

    public UiHandler(Looper looper, IHandler handler) {
        super(looper);
        this.handler = handler;
    }

    public void setHandler(IHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (handler != null) {
            handler.handleMessage(msg);//有消息，就传递
        }
    }
}

