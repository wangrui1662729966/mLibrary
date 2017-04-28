package com.zenchn.library.base;

/**
 * 作    者：wangr on 2017/4/27 16:11
 * 描    述：
 * 修订记录：
 */
public interface ICrashHandler {

    boolean getDebugMode();

    String getFilePath();

    String getFileName();

    String getDateFormat();

    String getFileNameSuffix();

    void uploadExceptionToServer();

    void exitApp();
}
