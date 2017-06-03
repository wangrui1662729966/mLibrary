package com.zenchn.library.utils;

import android.content.res.Resources;

/**
 * 作    者：wangr on 2017/4/24 11:02
 * 描    述：
 * 修订记录：
 */

public class DensityUtils {

    private DensityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;
    private static final float SCALED_DENSITY = Resources.getSystem().getDisplayMetrics().scaledDensity;

    /**
     * dp转px，保证尺寸大小不变
     *
     * @param dpValue
     * @return
     */
    public static int dp2px(int dpValue) {
        return Math.round(dpValue * DENSITY);
    }

    /**
     * px转dp，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dp(int pxValue) {
        return Math.round(pxValue / DENSITY);
    }

    /**
     * px转sp，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue) {
        return Math.round(pxValue / SCALED_DENSITY);
    }

    /**
     * sp转px，保证尺寸大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        return Math.round(spValue * SCALED_DENSITY);
    }
}
