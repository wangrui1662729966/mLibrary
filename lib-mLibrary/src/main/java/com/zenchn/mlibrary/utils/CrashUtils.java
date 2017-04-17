package com.zenchn.mlibrary.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * 作    者：wangr on 2017/2/20 19:03
 * 描    述：
 * 修订记录：
 */
public class CrashUtils {

    /**
     * 保存异常信息到本地
     *
     * @param savePath
     * @param throwable
     * @param context
     */
    public static void printException(String savePath, final Throwable throwable, final Context context) {

        FileUtils.deleteDirectory(savePath);
        File file = new File(savePath);
        file.mkdirs();
        File saveFile = new File(file, System.currentTimeMillis() + ".txt");

        PrintWriter printWriter = null;
        try {

            FileWriter wr = new FileWriter(saveFile, false);
            printWriter = new PrintWriter(wr);
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
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            printWriter.append(String.format("versionCode\t%s\n", versionCode));
            printWriter.append(String.format("versionName\t%s\n", versionName));
            printWriter.append("================Exception================\n");
            throwable.printStackTrace(printWriter);
            Throwable cause = throwable.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null)
                CloseUtils.closeIO(printWriter);
        }
    }
}

