package com.zenchn.library.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 作    者：wangr on 2017/4/24 10:48
 * 描    述：屏幕工具类
 * 修订记录：
 */
public class ScreenUtils {

    private ScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取DisplayMetrics对象
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisPlayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        if (null != context) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        }
        return metric;
    }

    /**
     * 获取屏幕的宽度（像素）
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return getDisPlayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕的高（像素）
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return getDisPlayMetrics(context).heightPixels;
    }

    /**
     * 屏幕密度(0.75 / 1.0 / 1.5)
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        return getDisPlayMetrics(context).density;
    }

    /**
     * 屏幕密度DPI(120 / 160 / 240)
     *
     * @param context
     * @return
     */
    public static int getDensityDpi(Context context) {
        return getDisPlayMetrics(context).densityDpi;
    }

}
