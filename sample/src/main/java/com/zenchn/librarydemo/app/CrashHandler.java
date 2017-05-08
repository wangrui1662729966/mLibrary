package com.zenchn.librarydemo.app;

import com.zenchn.library.dafault.DefaultCrashHandler;
import com.zenchn.librarydemo.GlobalConfig;

/**
 * 作    者：wangr on 2017/5/5 0005 21:22
 * 描    述：
 * 修订记录：
 */
public class CrashHandler extends DefaultCrashHandler {

    @Override
    public boolean getReportMode() {
        return GlobalConfig.isReport;
    }

    @Override
    public String getFilePath() {
        return GlobalConfig.FILE_PATH;
    }

    @Override
    public String getFileNamePrefix() {
        return GlobalConfig.FILE_NAME_PREFIX;
    }

    @Override
    public String getDateFormat() {
        return GlobalConfig.FILE_DATE_FORMAT;
    }

    @Override
    public String getFileNameSuffix() {
        return GlobalConfig.FILE_NAME_SUFFIX;
    }

}
