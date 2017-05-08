package com.zenchn.librarydemo;

import android.os.Environment;

/**
 * 作    者：wangr on 2017/5/5 0005 21:23
 * 描    述：配置管理类
 * 修订记录：
 */
public class GlobalConfig {

    public static final boolean isDebug = true;
    public static final String DEFAULT_TAG = "wangr";

    // #crash 是否收集报错日志
    public static final boolean isReport = true;
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/zenchn/mLibrary/log/";
    public static final String FILE_NAME_PREFIX = "crash";
    public static final String FILE_DATE_FORMAT = "yyyy-MM-dd HH-mm-ss";
    public static final String FILE_NAME_SUFFIX = ".text";


}
