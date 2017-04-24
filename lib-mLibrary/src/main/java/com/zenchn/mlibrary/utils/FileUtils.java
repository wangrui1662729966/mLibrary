package com.zenchn.mlibrary.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作    者：wangr on 2017/4/24 11:12
 * 描    述：文件工具类
 * 修订记录：
 */
public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String appFilesDir = "";

    public static String getSDPATH() {
        String sde = Environment.getExternalStorageState();
        String path = null;
        if (sde.equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().toString();
        } else {
            path = appFilesDir;
        }
        return path;
    }

    /**
     * 安装APK包
     *
     * @param apkPath
     * @return
     */
    public static void installApk(String apkPath, Activity activity) {
        String type = null;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String end = (apkPath.substring(apkPath.lastIndexOf(".") + 1, apkPath.length())).toLowerCase();
        if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        }
        if (type != null) {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)), type);
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "未找到更新文件", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateFormatterName() {
        Date date = new Date();
        String simple = "yyyy_MMdd_HHmm_ss";
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    /**
     * 判断文件或者目录是否存在
     *
     * @param path
     * @return
     */
    public static boolean isFolderExist(String path) {
        if (null == path) {
            return false;
        }
        File file = new File(path);
        return file.isDirectory() && file.exists();
    }

    /**
     * 判断文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean isFileExist(String path) {
        if (null == path) {
            return false;
        }
        File file = new File(path);
        return file.isFile() && file.exists();
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean isFileExist(File file) {
        return file != null && file.isFile() && file.exists();
    }


    /**
     * 判断文件是否不存在
     *
     * @param file
     * @return
     */
    public static boolean isFileNonExist(File file) {
        return file == null || !file.isFile() || !file.exists();
    }

    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    public static File createFolder(String path) {
        File dir = new File(path);
        dir.mkdirs();
        return dir;
    }

    /**
     * 生成文本文件
     */
    public static void createFile(File file, String fileContext) {
        try {
            if (!file.exists()) {
                if (file.createNewFile() == false) {
                    Log.e("FileUtil", "文件创建失败：" + file.getAbsolutePath());
                    return;
                }
            }
            FileWriter resultFile = new FileWriter(file);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.print(fileContext);
            resultFile.close();
            myFile.close();
        } catch (IOException e) {
            Log.e("FileUtil.createFile", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 生成文本文件
     */
    public static void createFile(File file) {
        try {
            if (!file.exists()) {
                if (file.createNewFile() == false) {
                    Log.e("FileUtil", "文件创建失败：" + file.getAbsolutePath());
                    return;
                }
            }
            FileWriter resultFile = new FileWriter(file);
            PrintWriter myFile = new PrintWriter(resultFile);
            resultFile.close();
            myFile.close();
        } catch (IOException e) {
            Log.e("FileUtil.createFile", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 文件内容读取
     *
     * @param fileName 文件名
     * @return
     */
    public static String read(String fileName) {
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        // 创建字符流
        try {
            fr = new FileReader(fileName);// 字符文件读取
            br = new BufferedReader(fr);// 创建字符流
            String line = br.readLine();
            sb = new StringBuffer();
            // 开始读取
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            Log.e("FileUtil.read", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                Log.e("FileUtil.read clolse", e.getMessage());
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 写入文本文件内容
     *
     * @param fileName 文件名称
     * @param content  文件内容
     */
    public static void write(String fileName, String content) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(fileName, true); // 构建 文件写流
            bw = new BufferedWriter(fw);// 字符输出流
            bw.write(content);
        } catch (Exception e) {
            Log.e("FileUtil.write", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                Log.e("FileUtil.write close", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void writeBeforeClearContent(String fileName, String content) {
        clearConent(fileName);
        write(fileName, content);
    }

    public static void clearConent(String fileName) {
        FileWriter fw = null;
        try {
            File f = new File(fileName);
            fw = new FileWriter(f);
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public File write2SDFromInput(String path, String fileName, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            creatSDDir(path);
            file = creatFileInSDCard(path + fileName);
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            int temp;
            while ((temp = input.read(buffer)) != -1) {
                output.write(buffer, 0, temp);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     */
    public File creatSDDir(String dirName) {
        return createFolder(getSDPATH() + dirName);
    }

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    public File creatFileInSDCard(String fileName) throws IOException {
        File file = new File(getSDPATH() + fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件完整路径名
     * @return
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        boolean flag = false;
        if (file.isFile() && file.exists()) {
            flag = file.delete();
        }
        return flag;
    }

    /**
     * 删除SDCard上文件
     *
     * @param fileName 文件完整路径名
     * @return
     */
    public static boolean deleteFileInSDCard(String fileName) {
        return deleteFile(getSDPATH() + fileName);
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else { // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    File file = null;
    boolean flag = false;

    /**
     * 写入txt文件，可以在原文件内容的基础上追加内容(并判断目录是否存在，不存在则生成目录)
     *
     * @param value      写入文件内容
     * @param fileCatage 文件父目录；
     * @param fileName   文件名字；
     * @param code       文件的编码；
     * @throws IOException
     */
    public void WriteFile(String value, String fileCatage, String fileName, String code) {
        File file = null;
        try {
            file = new File(fileCatage);
            if (!file.isDirectory())
                file.mkdir();
            else {
                file = new File(fileCatage + fileName);
                if (!file.exists())
                    file.createNewFile();
                FileOutputStream out = new FileOutputStream(file, true);
                out.write(value.getBytes(code));
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 覆盖原来的内容；
     *
     * @param filePath 文件的路径
     * @param content  保存的内容；
     * @return
     */
    public boolean saveFile(String filePath, String content) {
        boolean successful = true;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(new File(filePath), false);
            fout.write(content.getBytes());
        } catch (IOException e) {
            successful = false;
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {

                }
            }
        }
        return successful;
    }

    /**
     * 删除文件的综合操作( 根据路径删除指定的目录或文件，无论存在与否)
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public boolean DeleteFolder(String sPath) {
        flag = false;
        file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else { // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 将流中的文本读入一个 BufferedReader 中
     *
     * @param filePath 文件路径
     * @param code     编码格式
     * @return
     * @throws IOException
     */

    public BufferedReader readToBufferedReader(String filePath, String code) throws IOException {
        BufferedReader bufferedReader = null;
        File file = new File(filePath);
        if (file.isFile() && file.exists()) { // 判断文件是否存在
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), code);// 考虑到编码格式
            bufferedReader = new BufferedReader(read);
        }
        return bufferedReader;
    }

    /**
     * 将流中的文本读入一个 StringBuffer 中
     *
     * @param filePath 文件路径
     * @throws IOException
     */
    public StringBuffer readToBuffer(String filePath, String code) {
        StringBuffer buffer = new StringBuffer();
        InputStream is;
        try {
            File file = new File(filePath);
            if (!file.exists())
                return null;
            is = new FileInputStream(filePath);
            String line; // 用来保存每行读取的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), code));
            line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                buffer.append(line); // 将读到的内容添加到 buffer 中
                // buffer.append("\n"); // 添加换行符
                line = reader.readLine(); // 读取下一行
            }
            reader.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public String loadFile(String filePath, String charset) {
        FileInputStream fin = null;
        StringBuffer sb = new StringBuffer();
        try {
            fin = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[Integer.MAX_VALUE];
            int start = -1;
            while ((start = fin.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, start, charset));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                }
            }
        }
        return sb.toString();
    }

    /**
     * 获取某个目录下所有文件或者获取某个文件的大小； 单位：MB
     *
     * @param file
     * @return
     */
    public static double getDirSize(File file) {
        // 判断文件是否存在
        if (file.exists()) {
            // 如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {// 如果是文件则直接返回其大小,以“兆”为单位
                double size = (double) file.length() / 1024 / 1024;
                return size;
            }
        } else {
            System.out.println("获取文件大小错误！！文件或者文件夹不存在，请检查路径是否正确！");
            return 0.0;
        }
    }

    /**
     * 获取某个目录下所有的文件的全路径和文件名的集合；
     *
     * @return
     */
    public List<List<String>> getAllFile(String mulu) {
        File file = new File(mulu);
        File[] files = file.listFiles();
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> allFilePath = new ArrayList<String>();
        List<String> allFileName = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                allFilePath.add(files[i].toString());
                allFileName.add(files[i].getName());
            }
        }
        ret.add(allFilePath);
        ret.add(allFileName);
        return ret;
    }

    /**
     * //取得文件夹大小
     *
     * @param folder
     * @return
     * @throws Exception
     */
    public static long getFileSize(File folder) {
        long size = 0;
        try {
            File fileList[] = folder.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFileSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {

        } finally {
            return size;
        }
    }

    /**
     * //转换文件大小
     *
     * @param fileSize
     * @return
     */
    public static String formatFileSize(long fileSize) {
        if (fileSize <= 0)
            return "";

        DecimalFormat df = new DecimalFormat("#.00");
        if (fileSize < 1024) {
            return df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            return df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            return df.format((double) fileSize / 1048576) + "M";
        } else {
            return df.format((double) fileSize / 1073741824) + "G";
        }
    }

}
