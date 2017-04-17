package com.zenchn.mlibrary.utils;

/**
 * 作者：wangr on 2017/1/4 9:38
 * 描述：字符串相关工具类
 */

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断一个字符串有内容(非空且非空字符串)
     *
     * @param charSequence
     * @return
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return null == charSequence || charSequence.length() == 0;
    }

    /**
     * 判断一列字符串有内容(非空且非空字符串)
     *
     * @param charSequences
     * @return
     */
    public static boolean isEmpty(CharSequence... charSequences) {
        for (CharSequence charSequence : charSequences) {
            if (isEmpty(charSequence))
                return true;
        }
        return false;
    }

    /**
     * 判断一个字符串有内容(非空且非空字符串)
     *
     * @param charSequence
     * @return
     */
    public static boolean isNonNull(CharSequence charSequence) {
        return null != charSequence && charSequence.length() > 0;
    }

    /**
     * 判断一列字符串有内容(非空且非空字符串)
     *
     * @param charSequences
     * @return
     */
    public static boolean isNonNull(CharSequence... charSequences) {
        for (CharSequence charSequence : charSequences) {
            if (isEmpty(charSequence))
                return false;
        }
        return true;
    }

    /**
     * 对字符串进行非空处理
     *
     * @param charSequences
     * @return
     */
    public static CharSequence getNonNull(CharSequence charSequences) {
        return isNonNull(charSequences) ? charSequences : "";
    }

    /**
     * 对字符串进行非空处理
     *
     * @param charSequences
     * @param defaultCharSequences
     * @return
     */
    public static CharSequence getNonNull(CharSequence charSequences, CharSequence defaultCharSequences) {
        return isNonNull(charSequences) ? charSequences : defaultCharSequences;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param character 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String character) {
        return (character == null || character.trim().length() == 0);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param characterA 待校验字符串a
     * @param characterB 待校验字符串b
     */
    public static boolean equalsIgnoreCase(String characterA, String characterB) {
        return (characterA == characterB) || (characterA != null) && (characterA.length() == characterB.length()) && characterA.regionMatches(true, 0, characterB, 0, characterB.length());
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int getNonNullLength(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param character 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String character) {
        if (isEmpty(character) || !Character.isLowerCase(character.charAt(0))) return character;
        return String.valueOf((char) (character.charAt(0) - 32)) + character.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param character 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String character) {
        if (isEmpty(character) || !Character.isUpperCase(character.charAt(0))) {
            return character;
        }
        return String.valueOf((char) (character.charAt(0) + 32)) + character.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param character 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String character) {
        int len = getNonNullLength(character);
        if (len <= 1) return character;
        int mid = len >> 1;
        char[] chars = character.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }
}