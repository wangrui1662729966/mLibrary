package com.zenchn.library.log;

import com.orhanobut.logger.Logger;
import com.zenchn.library.BuildConfig;
import com.zenchn.library.MLibraryConf;
import com.zenchn.library.utils.StringUtils;

/**
 * 作    者：wangr on 2017/5/5 17:00
 * 描    述：
 * 修订记录：
 */
public class EasyLogger {

    private static final boolean isDebug = BuildConfig.DEBUG;

    public EasyLogger() {
        if (isDebug)
            Logger.init(MLibraryConf.DEFAULT_TAG);
    }

    public static void setLogController(ILogController mLogController) {
        if (isDebug)
            if (mLogController != null) {
                Logger.init(StringUtils.getNonNull(mLogController.getLoggerTag(), MLibraryConf.DEFAULT_TAG).toString());
            } else {
                Logger.init(MLibraryConf.DEFAULT_TAG);
            }
    }

    public static void resetSettings() {
        if (isDebug)
            Logger.resetSettings();
    }

    public static void t(String tag) {
        if (isDebug)
            Logger.t(tag);
    }

    public static void t(int methodCount) {
        if (isDebug)
            Logger.t(null, methodCount);
    }

    public static void t(String tag, int methodCount) {
        if (isDebug)
            Logger.t(tag, methodCount);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if (isDebug)
            Logger.log(priority, tag, message, throwable);
    }

    public static void d(String message, Object... args) {
        if (isDebug)
            Logger.d(message, args);
    }

    public static void d(Object object) {
        if (isDebug)
            Logger.d(object);
    }

    public static void e(String message, Object... args) {
        if (isDebug)
            Logger.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (isDebug)
            Logger.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        if (isDebug)
            Logger.i(message, args);
    }

    public static void v(String message, Object... args) {
        if (isDebug)
            Logger.v(message, args);
    }

    public static void w(String message, Object... args) {
        if (isDebug)
            Logger.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        if (isDebug)
            Logger.wtf(message, args);
    }

    public static void json(String json) {
        if (isDebug)
            Logger.json(json);
    }

    public static void xml(String xml) {
        if (isDebug)
            Logger.xml(xml);
    }


}
