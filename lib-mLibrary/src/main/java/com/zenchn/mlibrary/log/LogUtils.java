package com.zenchn.mlibrary.log;

import android.util.Log;

import com.zenchn.mlibrary.MLibraryConf;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    private static boolean isDebug = MLibraryConf.isDebug;
    public static final String simple = MLibraryConf.LOG_TIME_FORMAT;

    private static final String ERROR = MLibraryConf.LogTags.ERROR_TAG;
    private static final String TEST = MLibraryConf.LogTags.TEST_TAG;

    public static final int LOG_LEVEL_D = MLibraryConf.LogLevels.D;
    public static final int LOG_LEVEL_I = MLibraryConf.LogLevels.I;
    public static final int LOG_LEVEL_W = MLibraryConf.LogLevels.W;
    public static final int LOG_LEVEL_E = MLibraryConf.LogLevels.E;


    /**
     * 获取调用的类名及方法名
     *
     * @return
     */
    private static String getMethodInfo(String desc) {

//        String threadName = Thread.currentThread().getName();// 获取线程名
//        long threadID = Thread.currentThread().getId();// 获取线程ID

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
//        String fileName = stackTraceElement.getFileName();// 获取文件名.即xxx.java
        String className = stackTraceElement.getClassName();// 获取类名.即包名+类名
//        String methodName = stackTraceElement.getMethodName();// 获取方法名称
        int lineNumber = stackTraceElement.getLineNumber();// 获取输出行数

        return new StringBuffer()
                .append("--------------------------------------------------------------------" + "\n")
                .append("Class name : " + className + "\n")
                .append("line Number: " + lineNumber + "\n")
                .append("User Desc  : " + desc + "\n")
                .append("--------------------------------------------------------------------" + "\n")
                .toString();
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    private static String getDateFormatter() {
        return new SimpleDateFormat(simple).format(new Date());
    }

    /**
     * 打印错误日志
     *
     * @param e
     */
    public static void printErrorLog(Exception e) {
        printErrorLog(getMethodInfo(e.toString()));
    }

    /**
     * 打印错误日志
     *
     * @param rspDesc
     */
    public static void printErrorLog(String rspDesc) {
        if (isDebug)
            Log.e(ERROR, getDateFormatter() + "\n" + getMethodInfo(rspDesc));
    }

    /**
     * 打印日志(内容)
     *
     * @param rspDesc
     */
    protected static void printTestLog(String rspDesc) {
        if (isDebug)
            Log.d(TEST, rspDesc);
    }

    /**
     * 打印日志(标题)
     *
     * @param rspTitle
     */
    protected static void printTestCutoffRule(String rspTitle) {
        if (isDebug)
            Log.d(TEST, "=================================== " + rspTitle + "=================================== ");
    }

    /**
     * 打印日志(标题+内容),默认为log_info
     *
     * @param rspTitle
     * @param rspDesc
     */
    protected static void printTestLog(String rspTitle, String rspDesc) {
        printTestLog(rspTitle, rspDesc, LOG_LEVEL_I);
    }

    /**
     * 打印日志(标题+内容)
     *
     * @param rspTitle
     * @param rspDesc
     */
    public static void printTestLog(String rspTitle, String rspDesc, int logLevel) {
        if (isDebug) {
            if (logLevel == LOG_LEVEL_D) {
                Log.d(TEST, "============================" + rspTitle + "========================================");
                Log.d(TEST, getDateFormatter() + "\n" + getMethodInfo(rspDesc));
                Log.d(TEST, "==================================================================================");
            } else if (logLevel == LOG_LEVEL_W) {
                Log.w(TEST, "============================" + rspTitle + "========================================");
                Log.w(TEST, getDateFormatter() + "\n" + getMethodInfo(rspDesc));
                Log.w(TEST, "==================================================================================");
            } else if (logLevel == LOG_LEVEL_E) {
                Log.e(TEST, "============================" + rspTitle + "========================================");
                Log.e(TEST, getDateFormatter() + "\n" + getMethodInfo(rspDesc));
                Log.e(TEST, "==================================================================================");
            } else {
                Log.i(TEST, "============================" + rspTitle + "========================================");
                Log.i(TEST, getDateFormatter() + "\n" + getMethodInfo(rspDesc));
                Log.i(TEST, "==================================================================================");
            }
        }
    }

    /**
     * 自定义打印日志
     *
     * @param customTag
     * @param rspDesc
     */
    public static void printCustomLog(String customTag, String rspDesc) {
        printCustomLog(customTag, rspDesc, null);
    }

    /**
     * 自定义打印日志
     *
     * @param customTag
     * @param rspDesc
     * @param logLevel
     */
    public static void printCustomLog(String customTag, String rspDesc, int logLevel) {
        printCustomLog(customTag, null, rspDesc, logLevel, false);
    }

    /**
     * 自定义打印日志
     *
     * @param customTag
     * @param rspTitle
     * @param rspDesc
     */
    public static void printCustomLog(String customTag, String rspTitle, String rspDesc) {
        printCustomLog(customTag, rspTitle, rspDesc, LOG_LEVEL_D, false);
    }

    /**
     * 自定义打印日志(默认为正常日志)
     *
     * @param customTag
     * @param rspDesc
     * @param logLevel
     */
    public static void printCustomLog(String customTag, String rspTitle, String rspDesc, int logLevel) {
        printCustomLog(customTag, rspTitle, rspDesc, logLevel, false);
    }

    /**
     * 自定义打印日志(默认为正常日志)
     *
     * @param customTag
     * @param rspDesc
     * @param logLevel
     */
    public static void printCustomLog(String customTag, String rspTitle, String rspDesc, int logLevel, boolean isCompact) {
        if (isDebug) {
            if (logLevel == LOG_LEVEL_D) {
                if (rspTitle != null)
                    Log.d(customTag, "============================" + rspTitle + "========================================");
                else
                    Log.d(customTag, "====================================================================================");
                if (rspDesc != null)
                    Log.d(customTag, (isCompact ? (getDateFormatter() + "\n" + getMethodInfo(rspDesc) + "\n") : "") + rspDesc);
                Log.d(customTag, "==================================================================================");
            } else if (logLevel == LOG_LEVEL_W) {
                if (rspTitle != null)
                    Log.w(customTag, "============================" + rspTitle + "========================================");
                else
                    Log.w(customTag, "====================================================================================");
                if (rspDesc != null)
                    Log.w(customTag, (isCompact ? (getDateFormatter() + "\n" + getMethodInfo(rspDesc) + "\n") : "") + rspDesc);
                Log.w(customTag, "==================================================================================");
            } else if (logLevel == LOG_LEVEL_E) {
                if (rspTitle != null)
                    Log.e(customTag, "============================" + rspTitle + "========================================");
                else
                    Log.e(customTag, "====================================================================================");
                if (rspDesc != null)
                    Log.e(customTag, (isCompact ? (getDateFormatter() + "\n" + getMethodInfo(rspDesc) + "\n") : "") + rspDesc);
                Log.e(customTag, "==================================================================================");
            } else {
                if (rspTitle != null)
                    Log.i(customTag, "============================" + rspTitle + "========================================");
                else
                    Log.i(customTag, "====================================================================================");
                if (rspDesc != null)
                    Log.i(customTag, (isCompact ? (getDateFormatter() + "\n" + getMethodInfo(rspDesc) + "\n") : "") + rspDesc);
                Log.i(customTag, "==================================================================================");
            }
        }
    }

    /**
     * 自定义打印日志
     *
     * @param customTag
     * @param rspDesc
     */
    public static void printSimpleLog(String customTag, String rspDesc) {
        printSimpleLog(customTag, rspDesc, null);
    }

    /**
     * 自定义打印日志
     *
     * @param customTag
     * @param rspDesc
     * @param logLevel
     */
    public static void printSimpleLog(String customTag, String rspDesc, int logLevel) {
        printSimpleLog(customTag, null, rspDesc, logLevel, false);
    }

    /**
     * 自定义打印日志
     *
     * @param customTag
     * @param rspTitle
     * @param rspDesc
     */
    public static void printSimpleLog(String customTag, String rspTitle, String rspDesc) {
        printSimpleLog(customTag, rspTitle, rspDesc, LOG_LEVEL_D, false);
    }

    /**
     * 自定义打印日志(默认为正常日志)
     *
     * @param customTag
     * @param rspDesc
     * @param logLevel
     */
    public static void printSimpleLog(String customTag, String rspTitle, String rspDesc, int logLevel) {
        printCustomLog(rspDesc, rspDesc, rspDesc, logLevel, false);
    }

    /**
     * 自定义打印日志(默认为正常日志)
     *
     * @param customTag
     * @param rspDesc
     * @param logLevel
     */
    public static void printSimpleLog(String customTag, String rspTitle, String rspDesc, int logLevel, boolean isCompact) {
        if (isDebug) {
            if (logLevel == LOG_LEVEL_D) {
                if (rspTitle != null)
                    Log.d(customTag, rspTitle);
                if (rspDesc != null)
                    Log.d(customTag, (isCompact ? (getDateFormatter() + "\n" + getMethodInfo(rspDesc) + "\n") : "") + rspDesc);
            } else if (logLevel == LOG_LEVEL_W) {
                if (rspTitle != null)
                    Log.w(customTag, rspTitle);
                if (rspDesc != null)
                    Log.w(customTag, (isCompact ? (getDateFormatter() + "\n" + getMethodInfo(rspDesc) + "\n") : "") + rspDesc);
            } else if (logLevel == LOG_LEVEL_E) {
                if (rspTitle != null)
                    Log.e(customTag, rspTitle);
                if (rspDesc != null)
                    Log.e(customTag, (isCompact ? (getDateFormatter() + "\n" + getMethodInfo(rspDesc) + "\n") : "") + rspDesc);
            } else {
                if (rspTitle != null)
                    Log.i(customTag, rspTitle);
                if (rspDesc != null)
                    Log.i(customTag, (isCompact ? (getDateFormatter() + "\n" + getMethodInfo(rspDesc) + "\n") : "") + rspDesc);
            }
        }
    }
}
