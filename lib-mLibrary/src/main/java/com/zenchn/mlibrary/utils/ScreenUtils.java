package com.zenchn.mlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @类名:ScreenUtils
 * @类描述:屏幕工具类
 * @作者:Administrator
 * @创建时间:2015年2月12日-下午4:46:00
 * @修改人:
 * @修改时间:
 * @修改备注:
 * @版本:
 */
public class ScreenUtils {

    private static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;

    /**
     * @param context
     * @return
     * @方法说明:获取DisplayMetrics对象
     * @方法名称:getDisPlayMetrics
     * @返回值:DisplayMetrics
     */
    public static DisplayMetrics getDisPlayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        if (null != context) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        }
        return metric;
    }

    /**
     * @param context
     * @return
     * @方法说明:获取屏幕的宽度（像素）
     * @方法名称:getScreenWidth
     * @返回值:int
     */
    public static int getScreenWidth(Context context) {
        int width = getDisPlayMetrics(context).widthPixels;
        return width;
    }

    /**
     * @param context
     * @return
     * @方法说明:获取屏幕的高（像素）
     * @方法名称:getScreenHeight
     * @返回值:int
     */
    public static int getScreenHeight(Context context) {
        int height = getDisPlayMetrics(context).heightPixels;
        return height;
    }

    /**
     * @param context
     * @return
     * @方法说明:屏幕密度(0.75 / 1.0 / 1.5)
     * @方法名称:getDensity
     * @返回 float
     */
    public static float getDensity(Context context) {
        float density = getDisPlayMetrics(context).density;
        return density;
    }

    /**
     * @param context
     * @return
     * @方法说明:屏幕密度DPI(120 / 160 / 240)
     * @方法名称:getDensityDpi
     * @返回 int
     */
    public static int getDensityDpi(Context context) {
        int densityDpi = getDisPlayMetrics(context).densityDpi;
        return densityDpi;
    }

    /**
     * @return
     * @方法说明:dp转px
     * @方法名称:dp2Px
     * @返回 int
     */
    public static int dp2Px(int dp) {
        return Math.round(dp * DENSITY);
    }

    public static int dp2px(Context ctx, float dpValue) {
        final float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * @return
     * @方法说明:px转dp
     * @方法名称:px2dp
     * @返回 int
     */
    public static int px2dp(int px) {
        return Math.round(px / DENSITY);
    }

}
