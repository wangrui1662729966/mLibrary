package com.zenchn.librarydemo.app;

import com.zenchn.library.base.ActivityLifecycleCallback;
import com.zenchn.library.dafault.DefaultActivityLifecycle;
import com.zenchn.library.dafault.DefaultApplicationKit;
import com.zenchn.library.base.IApplicationKit;

/**
 * 作    者：wangr on 2017/3/18 0018 13:58
 * 描    述：
 * 修订记录：
 */
public class ApplicationKit extends DefaultApplicationKit implements IApplicationKit, ActivityLifecycleCallback {

    private ApplicationKit() {
    }

    private static class SingletonInstance {
        private static final ApplicationKit INSTANCE = new ApplicationKit();
    }

    public static ApplicationKit getInstance() {
        return SingletonInstance.INSTANCE;
    }

    @Override
    public void initSetting() {
        super.initSetting();
        initActivityLifecycle();
    }

    private void initActivityLifecycle() {
        DefaultActivityLifecycle.getInstance().addCallback(this);
    }

    @Override
    public void onBackground() {

    }

    @Override
    public void onForeground() {

    }

    @Override
    public void onDestroyedSelf() {

    }
}
