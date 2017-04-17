package com.zenchn.mlibrary.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileUtils {

    /**
     * 简单验证手机格式
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、176、187、188
     * 联通：130、131、132、152、155、156、176、185、186
     * 电信：133、153、180、181、177、189、（1349卫通） 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     *
     * @param mobile
     * @return
     */
    public static boolean isMobileNO(String mobile) {
        String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobile))
            return false;
        else
            return mobile.matches(telRegex);
    }

    /**
     * 隐藏手机号的中间4位
     *
     * @param mobile
     * @return
     */
    public static String getEncryptMobileNO(String mobile) {
        String encryptMobile = "";
        try {
            encryptMobile = new StringBuilder().append(mobile, 0, 3).append("****").append(mobile, 7, mobile.length()).toString();
        } catch (Exception e) {
            return encryptMobile;
        }
        return encryptMobile;
    }

    /**
     * 除去空格回车换行 去除+86
     *
     * @param mobile
     * @return
     */
    public static String replaceBlank(String mobile) {

        String dest = "";

        if (mobile != null) {

            //去除空格
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(mobile);
            dest = m.replaceAll("");

            //去除86前缀
            if (dest.startsWith("86")) {
                dest = dest.replace("86", "");
            }

            //去除+86前缀
            if (dest.startsWith("+86")) {
                dest = dest.replace("+86", "");
            }

        }
        return dest;
    }

}
