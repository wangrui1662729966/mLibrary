package com.zenchn.library.base;

import java.io.File;

/**
 * 作    者：wangr on 2017/4/27 16:11
 * 描    述：
 * 修订记录：
 */
public interface ICrashHandler {

    boolean getReportMode();

    String getFilePath();

    String getFileName();

    String getDateFormat();

    String getFileNameSuffix();

    void uploadExceptionToServer(File logFile);

}
