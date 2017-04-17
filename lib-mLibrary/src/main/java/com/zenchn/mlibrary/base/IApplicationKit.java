package com.zenchn.mlibrary.base;

import android.app.Activity;
import android.app.Application;

/**
 * 作    者：wangr on 2017/3/9 16:24
 * 描    述：
 * 修订记录：
 */
public interface IApplicationKit {

    void initKit(Application application);

    Application getApplication();

    void initCustomSetting();

    Activity getTopActivity();

    void addActivity(Activity activity);

    void removeActivity(Activity activity);

    void finishAllActivity();

    void exitApp();
}
