package com.zenchn.library.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class UiHandler extends Handler {

    private IHandler mHandler;

    public UiHandler() {
        super(Looper.getMainLooper());
    }

    public UiHandler(IHandler mHandler) {
        super(Looper.getMainLooper());
        this.mHandler = mHandler;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (mHandler != null)
            mHandler.handleMessage(msg);//有消息，就传递
    }

}

