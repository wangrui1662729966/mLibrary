package com.zenchn.library.dafault;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.zenchn.library.base.IHandler;
import com.zenchn.library.base.UiHandler;


/**
 * 作    者：wangr on 2017/4/17 14:25
 * 描    述： 
 * 修订记录：
 */
public abstract class AbstractService extends Service implements IHandler {

    protected UiHandler mHandler = new UiHandler(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void handleMessage(Message msg) {

    }
}
