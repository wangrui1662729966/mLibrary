package com.zenchn.library.utils;

import com.zenchn.library.kit.JavaKits;
import com.zenchn.library.kit.LoggerKit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 作    者：wangr on 2017/4/24 11:09
 * 描    述：ZIP压缩工具类
 * 修订记录：
 */
public class GZIPUtils {

    private GZIPUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * GZIP解压
     *
     * @param sourcePath
     * @param targetPath
     * @return
     */
    public static boolean unZip(String sourcePath, String targetPath) {
        return unZip(new File(sourcePath), new File(targetPath));
    }

    /**
     * GZIP解压
     *
     * @param sourceFile
     * @param targetFile
     * @return
     */
    public static boolean unZip(File sourceFile, File targetFile) {
        boolean result = false;
        InputStream in = null;
        OutputStream os = null;
        try {
            in = new FileInputStream(sourceFile);
            os = new FileOutputStream(targetFile);
            result = unZip(in, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LoggerKit.e(e);
        } finally {
            JavaKits.close(in, os);
        }
        return result;
    }

    /**
     * GZIP解压
     *
     * @param in
     * @param os
     */
    public static boolean unZip(InputStream in, OutputStream os) {
        boolean result = false;
        InputStream is = null;
        try {
            is = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            LoggerKit.e(e);
        } finally {
            JavaKits.close(is, os);
        }
        return result;
    }

    /**
     * GZIP压缩
     *
     * @param sourcePath
     * @param targetPath
     * @return
     */
    public static boolean zip(String sourcePath, String targetPath) {
        return zip(new File(sourcePath), new File(targetPath));
    }


    /**
     * GZIP压缩
     *
     * @param sourceFile
     * @param targetFile
     * @return
     */
    public static boolean zip(File sourceFile, File targetFile) {
        boolean result = false;
        InputStream in = null;
        OutputStream os = null;
        try {
            in = new FileInputStream(sourceFile);
            os = new FileOutputStream(targetFile);
            result = zip(in, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LoggerKit.e(e);
        } finally {
            JavaKits.close(in, os);
        }
        return result;
    }

    /**
     * GZIP压缩
     *
     * @param in 要压缩的文件输入流
     * @param os 压缩后的文件的输出流
     * @throws Exception
     */
    public static boolean zip(InputStream in, OutputStream os) {
        boolean result = false;
        OutputStream out = null;
        try {
            out = new GZIPOutputStream(os);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            LoggerKit.e(e);
        } finally {
            JavaKits.close(out, in);
        }
        return result;
    }
}
