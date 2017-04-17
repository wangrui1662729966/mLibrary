package com.zenchn.mlibrary.app;

import com.zenchn.mlibrary.base.DefaultApplicationKit;
import com.zenchn.mlibrary.base.IApplicationKit;

/**
 * 作    者：wangr on 2017/3/18 0018 13:58
 * 描    述：
 * 修订记录：
 */
public class ApplicationKit extends DefaultApplicationKit implements IApplicationKit {

    private ApplicationKit() {
    }

    private static class SingletonInstance {
        private static final ApplicationKit INSTANCE = new ApplicationKit();
    }

    public static ApplicationKit getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
