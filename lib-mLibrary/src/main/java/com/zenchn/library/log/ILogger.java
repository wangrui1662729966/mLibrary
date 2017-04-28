package com.zenchn.library.log;

/**
 * 作    者：wangr on 2017/4/27 11:24
 * 描    述：
 * 修订记录：
 */

public interface ILogger {

    boolean isDebugMode(boolean isDebug);

    void logError(String error);


    void printTestLog(String rspTitle);

    void printTestLog(String rspTitle, String rspDesc);


    void printCustomLog(String customTag, String rspDesc);

    void printCustomLog(String customTag, String rspDesc, int logLevel);

    void printCustomLog(String customTag, String rspTitle, String rspDesc);

    void printCustomLog(String customTag, String rspTitle, String rspDesc, int logLevel);

    void printCustomLog(String customTag, String rspTitle, String rspDesc, int logLevel, boolean isCompact);


}
