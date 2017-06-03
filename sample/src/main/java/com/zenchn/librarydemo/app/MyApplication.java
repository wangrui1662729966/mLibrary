package com.zenchn.librarydemo.app;

import android.app.Application;

/**
 * 作    者：wangr on 2017/4/28 16:43
 * 描    述：
 * 修订记录：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationKit.getInstance().initKit(this);
    }

}
