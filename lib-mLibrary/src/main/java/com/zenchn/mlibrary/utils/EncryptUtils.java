package com.zenchn.mlibrary.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 作    者：wangr on 2017/3/15 11:34
 * 描    述：加密解密相关的工具类
 * 修订记录：
 */
public class EncryptUtils {

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String MD5Encrypt(String data) {
        return encrypt(data, "MD5");
    }

    /**
     * SHA1加密
     *
     * @param data
     * @return
     */
    public static String SHA1Encrypt(String data) {
        return encrypt(data, "SHA-1");
    }

    /**
     * SHA256加密
     *
     * @param data
     * @return
     */
    public static String SHA256Encrypt(String data) {
        return encrypt(data, "SHA-256");
    }

    /**
     * SHA384加密
     *
     * @param data
     * @return
     */
    public static String SHA384Encrypt(String data) {
        return encrypt(data, "SHA-384");
    }

    /**
     * 加密
     *
     * @param data
     * @param encName
     * @return
     */
    private static String encrypt(String data, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = data.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * @param bts
     * @return
     */
    private static String bytes2Hex(byte[] bts) {
        StringBuffer sb = new StringBuffer();
        int length = bts.length;
        for (int i = 0; i < length; i++) {
            int val = ((int) bts[i]) & 0xff;
            if (val < 16)
                sb.append("0");
            sb.append(Integer.toHexString(val));
        }
        return sb.toString();
    }

    /**
     * 计算文件的MD5值
     *
     * @param file
     * @return
     */
    public static String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}