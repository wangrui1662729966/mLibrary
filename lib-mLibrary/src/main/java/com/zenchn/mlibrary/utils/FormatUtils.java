package com.zenchn.mlibrary.utils;

/**
 * 作者：wangr on 2016/10/25 0025 11:02
 * 描述：
 */
public class FormatUtils {

    /**
     * 格式化为float类型
     *
     * @param sourceValue
     * @return
     */
    public static float formatToFloat(String sourceValue) {
        return formatToFloat(sourceValue, 0f);
    }

    public static float formatToFloat(String sourceValue, float defaultValue) {
        float value = defaultValue;
        try {
            value = Float.valueOf(sourceValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            return value;
        }
    }

    /**
     * 格式化为int类型
     *
     * @param sourceValue
     * @return
     */
    public static int formatToInt(String sourceValue) {
        return formatToInt(sourceValue, 0);
    }

    public static int formatToInt(String sourceValue, int defaultValue) {
        int value = defaultValue;
        try {
            value = Integer.valueOf(sourceValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            return value;
        }
    }

}
