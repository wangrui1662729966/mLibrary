package com.zenchn.library;

import android.os.Environment;

import com.zenchn.library.router.Router;

/**
 * 作者：wangr on 2016/12/30 11:03
 * 描述：配置管理类
 */
public class MLibraryConf {

    // #log 是否收集报错日志
    public static final boolean isDebug = true;
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/zenchn/mLibrary/log/";
    public static final String FILE_NAME = "crash";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FILE_NAME_SUFFIX = ".text";


//    // # app缓存根目录
//    public static String appFolder = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "zenchn" + File.separator + "mLibrary";
//    public static final String FILE_DIR_LOGS = "logs";

    // #router
    public static final int ROUTER_ANIM_ENTER = Router.RES_NONE;
    public static final int ROUTER_ANIM_EXIT = Router.RES_NONE;

}