package com.zenchn.mlibrary.utils;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

/**
 * 手机震动工具类
 *
 * @author Administrator
 */
public class VibratorUtil {

    public static long[] defaultPattern = {300, 200, 300, 150};

    /**
     * final Activity activity  ：调用该方法的Activity实例
     * long milliseconds ：震动的时长，单位是毫秒
     * long[] pattern  ：自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * boolean isRepeat ： 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     */

    public static Vibrator vibrate(final Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
        return vib;
    }

    public static Vibrator vibrate(final Activity activity, long[] pattern, boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
        return vib;
    }

    public static Vibrator defaultAlarmVibrate(final Activity activity, boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(defaultPattern, isRepeat ? 1 : -1);
        return vib;
    }
}


