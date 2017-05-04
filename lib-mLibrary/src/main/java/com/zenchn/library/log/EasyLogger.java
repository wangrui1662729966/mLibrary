package com.zenchn.library.log;

import com.orhanobut.logger.Logger;
import com.zenchn.library.MLibraryConf;

public abstract class EasyLogger implements ILogger {

    private ILogger mLogger;

    @Override
    public void initLogger() {
        if (getDebugMode()) {
            Logger.init(MLibraryConf.DEFAULT_TAG);
        }
    }

}
