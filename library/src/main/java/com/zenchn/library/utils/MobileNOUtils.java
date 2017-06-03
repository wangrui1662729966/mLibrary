package com.zenchn.library.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zenchn.library.kit.RegexKit;

/**
 * 作    者：wangr on 2017/5/31 9:57
 * 描    述：手机号工具类
 * 修订记录：
 */
public class MobileNOUtils {

    /**
     * 判断是否是手机号码(简单模式)
     *
     * @param mobile
     * @return
     */
    public static boolean isMobileNoSimple(@NonNull String mobile) {
        String rawMobileNO = getRawMobileNO(mobile);
        return StringUtils.isNonNull(rawMobileNO) && rawMobileNO.matches(RegexKit.REGEX_MOBILE_SIMPLE);
    }

    /**
     * 判断是否是手机号码(严格模式)
     *
     * @param mobile
     * @return
     */
    public static boolean isMobileNoExact(@NonNull String mobile) {
        String rawMobileNO = getRawMobileNO(mobile);
        return StringUtils.isNonNull(rawMobileNO) && rawMobileNO.matches(RegexKit.REGEX_MOBILE_EXACT);
    }

    /**
     * 隐藏手机号的中间4位
     *
     * @param mobile
     * @return
     */
    public static String getEncryptMobileNO(@NonNull String mobile) {
        if (isMobileNoSimple(mobile))
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return null;
    }

    /**
     * 除去空格回车换行 去除+86
     *
     * @param mobile
     * @return
     */
    public static String getRawMobileNO(@Nullable String mobile) {
        if (StringUtils.isEmpty(mobile))
            return null;
        return mobile.replaceAll(RegexKit.REGEX_BLANK_LINE, "").replaceAll(RegexKit.REGEX_MOBILE_ZONE, "");
    }

}
