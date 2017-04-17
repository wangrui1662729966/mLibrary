package com.zenchn.mlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 作者：wangr on 2017/1/4 9:33
 * 描述：apk工具类
 */
public class ApkUtils {

    private ApkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 安装一个apk文件
     *
     * @param context
     * @param uriFile
     */
    public static void install(Context context, File uriFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(uriFile), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 卸载一个app
     *
     * @param context
     * @param packageName
     */
    public static void unInstall(Context context, String packageName) {
        Uri packageURI = Uri.parse("package:" + packageName);// 通过程序的包名创建URI
        Intent intent = new Intent(Intent.ACTION_DELETE, packageURI);// 创建Intent意图
        context.startActivity(intent);// 执行卸载程序
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAvailable(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packageManager
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0); // 获取所有已安装程序的包信息
        List<String> packageNames = new ArrayList<>();// 用于存储所有已安装程序的包名
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean isAvailable(Context context, File file) {
        return isAvailable(context, getPackageName(context, file.getAbsolutePath()));
    }

    /**
     * 根据文件路径获取包名
     *
     * @param context
     * @param filePath
     * @return
     */
    public static String getPackageName(Context context, String filePath) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            return appInfo.packageName; // 得到安装包名称
        }
        return null;
    }

    /**
     * 从apk中获取版本信息
     *
     * @param context
     * @param channelPrefix
     * @return
     */
    public static String getChannelFromApk(Context context, String channelPrefix) {
        // 从apk包中获取
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        // 默认放在meta-inf/里， 所以需要再拼接一下
        String key = "META-INF/" + channelPrefix;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith(key)) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] split = ret.split(channelPrefix);
        String channel = "";
        if (split.length >= 2) {
            channel = ret.substring(key.length());
        }
        return channel;
    }

    /**
     * app版本名
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    /**
     * app版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    /**
     * app信息
     *
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {

        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }
}
