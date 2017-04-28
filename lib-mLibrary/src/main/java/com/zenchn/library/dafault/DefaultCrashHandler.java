package com.zenchn.library.dafault;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.NonNull;

import com.zenchn.library.MLibraryConf;
import com.zenchn.library.base.ICrashHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultCrashHandler implements UncaughtExceptionHandler {

    private UncaughtExceptionHandler mDefaultCrashHandler;    //系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
    private Context mContext;
    protected ICrashHandler mCrashHandler;

    private DefaultCrashHandler() {
    }

    private static class SingletonInstance {
        private static final DefaultCrashHandler INSTANCE = new DefaultCrashHandler();
    }

    public static DefaultCrashHandler getInstance() {
        return SingletonInstance.INSTANCE;
    }

    //这里主要完成初始化工作
    public void init(@NonNull Context context) {
        init(context, null);
    }

    public void init(@NonNull Context context, ICrashHandler crashHandler) {

        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();//获取系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);//将当前实例设为系统默认的异常处理器
        mContext = context.getApplicationContext();

        if (crashHandler == null)
            crashHandler = new ICrashHandler() {
                @Override
                public boolean getDebugMode() {
                    return MLibraryConf.isDebug;
                }

                @Override
                public String getFilePath() {
                    return MLibraryConf.FILE_PATH;
                }

                @Override
                public String getFileName() {
                    return MLibraryConf.FILE_NAME;
                }

                @Override
                public String getDateFormat() {
                    return MLibraryConf.DATE_FORMAT;
                }

                @Override
                public String getFileNameSuffix() {
                    return MLibraryConf.FILE_NAME_SUFFIX;
                }

                @Override
                public void uploadExceptionToServer() {
                    //TODO Upload Exception Message To Your Web Server

                }

                @Override
                public void exitApp() {
                    //TODO Kill App And Free The Memory
                }
            };
        mCrashHandler = crashHandler;
    }

    public DefaultCrashHandler setCrashHandler(ICrashHandler mCrashHandler) {
        this.mCrashHandler = mCrashHandler;
        return this;
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (mCrashHandler != null && mContext != null) {
            try {
                //导出异常信息到SD卡中
                dumpExceptionToSDCard(ex);
                //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
                mCrashHandler.uploadExceptionToServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //打印出当前调用栈信息
        ex.printStackTrace();

        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, ex);
        } else {
            mCrashHandler.exitApp();
            Process.killProcess(Process.myPid());
        }

    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (mCrashHandler.getDebugMode())
                return;
        }

        File dir = new File(mCrashHandler.getFilePath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String crashTime = new SimpleDateFormat(mCrashHandler.getDateFormat()).format(new Date(current));

        //以当前时间创建log文件
        File file = new File(mCrashHandler.getFilePath() + mCrashHandler.getFileName() + crashTime + mCrashHandler.getFileNameSuffix());

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            //导出发生异常的时间
            pw.println(crashTime);

            //导出手机信息
            dumpPhoneInfo(pw);

            pw.println();
            //导出异常的调用栈信息
            ex.printStackTrace(pw);

            pw.close();
        } catch (Exception e) {

        }
    }

    private void dumpPhoneInfo(PrintWriter printWriter) throws NameNotFoundException {

        printWriter.append("================Build================\n");
        printWriter.append(String.format("BOARD\t%s\n", Build.BOARD));
        printWriter.append(String.format("BOOTLOADER\t%s\n", Build.BOOTLOADER));
        printWriter.append(String.format("BRAND\t%s\n", Build.BRAND));
        printWriter.append(String.format("CPU_ABI\t%s\n", Build.CPU_ABI));
        printWriter.append(String.format("CPU_ABI2\t%s\n", Build.CPU_ABI2));
        printWriter.append(String.format("DEVICE\t%s\n", Build.DEVICE));
        printWriter.append(String.format("DISPLAY\t%s\n", Build.DISPLAY));
        printWriter.append(String.format("FINGERPRINT\t%s\n", Build.FINGERPRINT));
        printWriter.append(String.format("HARDWARE\t%s\n", Build.HARDWARE));
        printWriter.append(String.format("HOST\t%s\n", Build.HOST));
        printWriter.append(String.format("ID\t%s\n", Build.ID));
        printWriter.append(String.format("MANUFACTURER\t%s\n", Build.MANUFACTURER));
        printWriter.append(String.format("MODEL\t%s\n", Build.MODEL));
        printWriter.append(String.format("SERIAL\t%s\n", Build.SERIAL));
        printWriter.append(String.format("PRODUCT\t%s\n", Build.PRODUCT));
        printWriter.append("================APP================\n");

        //应用的版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo packageInfo = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        int versionCode = packageInfo.versionCode;
        String versionName = packageInfo.versionName;
        printWriter.append(String.format("versionCode\t%s\n", versionCode));
        printWriter.append(String.format("versionName\t%s\n", versionName));
        printWriter.append("================Exception================\n");
    }

}
