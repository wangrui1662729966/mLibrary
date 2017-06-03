package com.zenchn.library.log;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 作 者：wangr on 2017/5/23 13:49 描 述： 修订记录：
 */
public class Kits {

	private static boolean isEmpty(CharSequence... charSequences) {
		for (CharSequence charSequence : charSequences) {
			if (isEmpty(charSequence))
				return true;
		}
		return false;
	}

	private static boolean isEmpty(CharSequence charSequence) {
		return charSequence == null || charSequence.length() == 0;
	}

	public static class Random {

		public static final String NUMBERS = "0123456789";
		public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
		public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		public static final String LETTERS = CAPITAL_LETTERS + LOWER_CASE_LETTERS;
		public static final String NUMBERS_AND_LETTERS = NUMBERS + LETTERS;

		public static String getRandomNumbersAndLetters(int length) {
			return getRandom(NUMBERS_AND_LETTERS, length);
		}

		public static String getRandomNumbers(int length) {
			return getRandom(NUMBERS, length);
		}

		public static String getRandomLetters(int length) {
			return getRandom(LETTERS, length);
		}

		public static String getRandomCapitalLetters(int length) {
			return getRandom(CAPITAL_LETTERS, length);
		}

		public static String getRandomLowerCaseLetters(int length) {
			return getRandom(LOWER_CASE_LETTERS, length);
		}

		public static String getRandom(String source, int length) {
			return isEmpty(source) ? null : getRandom(source.toCharArray(), length);
		}

		public static String getRandom(char[] sourceChar, int length) {
			if (isEmpty(sourceChar.toString()) || length < 0) {
				return null;
			}

			StringBuilder str = new StringBuilder(length);
			java.util.Random random = new java.util.Random();
			for (int i = 0; i < length; i++) {
				str.append(sourceChar[random.nextInt(sourceChar.length)]);
			}
			return str.toString();
		}

		public static int getRandom(int max) {
			return getRandom(0, max);
		}

		public static int getRandom(int lower, int upper) {
			int min = Math.min(lower, upper);
			int scope = Math.abs(upper - lower);
			return new java.util.Random().nextInt(scope) + min;
		}
	}

	public static class File {

		public final static String DEFAULT_CHARSET_NAME = "UTF-8";
		public final static String FILE_EXTENSION_SEPARATOR = ".";

		/**
		 * read file
		 *
		 * @param filePath
		 * @param charsetName
		 *            The name of a supported {@link java.nio.charset.Charset
		 *            </code>charset<code>}
		 * @return if file not exist, return null, else return content of file
		 * @throws RuntimeException
		 *             if an error occurs while operator BufferedReader
		 */
		public static StringBuilder readFile(String filePath, String charsetName) {

			java.io.File file = new java.io.File(filePath);
			StringBuilder fileContent = new StringBuilder("");

			if (file == null || !file.isFile()) {
				return null;
			}

			BufferedReader bufferedReader = null;
			try {

				InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), isEmpty(charsetName) ? DEFAULT_CHARSET_NAME : charsetName);
				bufferedReader = new BufferedReader(inputStreamReader);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					if (!fileContent.toString().equals("")) {
						fileContent.append("\r\n");
					}
					fileContent.append(line);
				}
				return fileContent;

			} catch (IOException e) {
				e.printStackTrace();
				return fileContent;
			} finally {
				IO.closeQuietly(bufferedReader);
			}

		}

		/**
		 * write file
		 *
		 * @param filePath
		 * @param content
		 * @param append
		 *            is append, if true, write to the end of file, else clear
		 *            content of file and write into it
		 * @return return false if content is empty, true otherwise
		 * @throws RuntimeException
		 *             if an error occurs while operator FileWriter
		 */
		public static boolean writeFile(String filePath, String content, boolean append) {

			if (isEmpty(content)) {
				return false;
			}

			FileWriter fileWriter = null;
			try {
				makeDirs(filePath);
				fileWriter = new FileWriter(filePath, append);
				fileWriter.write(content);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				IO.close(fileWriter);
			}
		}

		/**
		 * write file
		 *
		 * @param filePath
		 * @param contentList
		 * @param append
		 *            is append, if true, write to the end of file, else clear
		 *            content of file and write into it
		 * @return return false if contentList is empty, true otherwise
		 * @throws RuntimeException
		 *             if an error occurs while operator FileWriter
		 */
		public static boolean writeFile(String filePath, List<String> contentList, boolean append) {
			if (contentList == null || contentList.isEmpty()) {
				return false;
			}

			FileWriter fileWriter = null;
			try {
				makeDirs(filePath);
				fileWriter = new FileWriter(filePath, append);
				int i = 0;
				for (String line : contentList) {
					if (i++ > 0) {
						fileWriter.write("\r\n");
					}
					fileWriter.write(line);
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				IO.close(fileWriter);
			}
		}

		/**
		 * write file, the string will be written to the begin of the file
		 *
		 * @param filePath
		 * @param content
		 * @return
		 */
		public static boolean writeFile(String filePath, String content) {
			return writeFile(filePath, content, false);
		}

		/**
		 * write file, the string list will be written to the begin of the file
		 *
		 * @param filePath
		 * @param contentList
		 * @return
		 */
		public static boolean writeFile(String filePath, List<String> contentList) {
			return writeFile(filePath, contentList, false);
		}

		/**
		 * write file, the bytes will be written to the begin of the file
		 *
		 * @param filePath
		 * @param stream
		 * @return
		 * @see {@link #writeFile(String, InputStream, boolean)}
		 */
		public static boolean writeFile(String filePath, InputStream inputStream) {
			return writeFile(filePath, inputStream, false);
		}

		/**
		 * write file
		 *
		 * @param stream
		 *            the input stream
		 * @param append
		 *            if <code>true</code>, then bytes will be written to the
		 *            end of the file rather than the beginning
		 * @return return true
		 * @throws RuntimeException
		 *             if an error occurs while operator FileOutputStream
		 */
		public static boolean writeFile(String filePath, InputStream inputStream, boolean append) {
			return writeFile(filePath != null ? new java.io.File(filePath) : null, inputStream, append);
		}

		/**
		 * write file, the bytes will be written to the begin of the file
		 *
		 * @param file
		 * @param stream
		 * @return
		 * @see {@link #writeFile(java.io.File, InputStream, boolean)}
		 */
		public static boolean writeFile(java.io.File file, InputStream inputStream) {
			return writeFile(file, inputStream, false);
		}

		/**
		 * write file
		 *
		 * @param file
		 *            the file to be opened for writing.
		 * @param stream
		 *            the input stream
		 * @param append
		 *            if <code>true</code>, then bytes will be written to the
		 *            end of the file rather than the beginning
		 * @return return true
		 * @throws RuntimeException
		 *             if an error occurs while operator FileOutputStream
		 */
		public static boolean writeFile(java.io.File file, InputStream inputStream, boolean append) {
			OutputStream outputStream = null;
			try {
				makeDirs(file.getAbsolutePath());
				outputStream = new FileOutputStream(file, append);
				byte data[] = new byte[1024];
				int length = -1;
				while ((length = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, length);
				}
				outputStream.flush();
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				IO.close(outputStream, inputStream);
			}
		}

		/**
		 * move file
		 *
		 * @param sourceFilePath
		 * @param destFilePath
		 */
		public static void moveFile(String sourceFilePath, String destFilePath) {
			if (isEmpty(sourceFilePath, destFilePath)) {
				throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
			}
			moveFile(new java.io.File(sourceFilePath), new java.io.File(destFilePath));
		}

		/**
		 * move file
		 *
		 * @param srcFile
		 * @param destFile
		 */
		public static void moveFile(java.io.File srcFile, java.io.File destFile) {
			boolean rename = srcFile.renameTo(destFile);
			if (!rename) {
				copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
				deleteFile(srcFile.getAbsolutePath());
			}
		}

		/**
		 * copy file
		 *
		 * @param sourceFilePath
		 * @param destFilePath
		 * @return
		 * @throws RuntimeException
		 *             if an error occurs while operator FileOutputStream
		 */
		public static boolean copyFile(String sourceFilePath, String destFilePath) {
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(sourceFilePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("FileNotFoundException occurred. ", e);
			}
			return writeFile(destFilePath, inputStream);
		}

		/**
		 * read file to string list, a element of list is a line
		 *
		 * @param filePath
		 * @param charsetName
		 *            The name of a supported {@link java.nio.charset.Charset
		 *            </code>charset<code>}
		 * @return if file not exist, return null, else return content of file
		 * @throws RuntimeException
		 *             if an error occurs while operator BufferedReader
		 */
		public static List<String> readFileToList(String filePath, String charsetName) {
			java.io.File file = new java.io.File(filePath);
			List<String> fileContent = new ArrayList<String>();
			if (file == null || !file.isFile()) {
				return null;
			}

			BufferedReader reader = null;
			try {
				InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
				reader = new BufferedReader(is);
				String line = null;
				while ((line = reader.readLine()) != null) {
					fileContent.add(line);
				}
				return fileContent;
			} catch (IOException e) {
				e.printStackTrace();
				return fileContent;
			} finally {
				IO.close(reader);
			}
	
		}

		/**
		 * get file name from path, not include suffix
		 * <p/>
		 * 
		 * <pre>
		 *      getFileNameWithoutExtension(null)               =   null
		 *      getFileNameWithoutExtension("")                 =   ""
		 *      getFileNameWithoutExtension("   ")              =   "   "
		 *      getFileNameWithoutExtension("abc")              =   "abc"
		 *      getFileNameWithoutExtension("a.mp3")            =   "a"
		 *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
		 *      getFileNameWithoutExtension("c:\\")              =   ""
		 *      getFileNameWithoutExtension("c:\\a")             =   "a"
		 *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
		 *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
		 *      getFileNameWithoutExtension("/home/admin")      =   "admin"
		 *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
		 * </pre>
		 *
		 * @param filePath
		 * @return file name from path, not include suffix
		 * @see
		 */
		public static String getFileNameWithoutExtension(String filePath) {
			if (isEmpty(filePath)) {
				return filePath;
			}

			int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
			int filePosi = filePath.lastIndexOf(java.io.File.separator);
			if (filePosi == -1) {
				return (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi));
			}
			if (extenPosi == -1) {
				return filePath.substring(filePosi + 1);
			}
			return (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1));
		}

		/**
		 * get file name from path, include suffix
		 * <p/>
		 * 
		 * <pre>
		 *      getFileName(null)               =   null
		 *      getFileName("")                 =   ""
		 *      getFileName("   ")              =   "   "
		 *      getFileName("a.mp3")            =   "a.mp3"
		 *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
		 *      getFileName("abc")              =   "abc"
		 *      getFileName("c:\\")              =   ""
		 *      getFileName("c:\\a")             =   "a"
		 *      getFileName("c:\\a.b")           =   "a.b"
		 *      getFileName("c:a.txt\\a")        =   "a"
		 *      getFileName("/home/admin")      =   "admin"
		 *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
		 * </pre>
		 *
		 * @param filePath
		 * @return file name from path, include suffix
		 */
		public static String getFileName(String filePath) {
			if (isEmpty(filePath)) {
				return filePath;
			}

			int filePosi = filePath.lastIndexOf(java.io.File.separator);
			return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
		}

		/**
		 * get folder name from path
		 * <p/>
		 * 
		 * <pre>
		 *      getFolderName(null)               =   null
		 *      getFolderName("")                 =   ""
		 *      getFolderName("   ")              =   ""
		 *      getFolderName("a.mp3")            =   ""
		 *      getFolderName("a.b.rmvb")         =   ""
		 *      getFolderName("abc")              =   ""
		 *      getFolderName("c:\\")              =   "c:"
		 *      getFolderName("c:\\a")             =   "c:"
		 *      getFolderName("c:\\a.b")           =   "c:"
		 *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
		 *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
		 *      getFolderName("/home/admin")      =   "/home"
		 *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
		 * </pre>
		 *
		 * @param filePath
		 * @return
		 */
		public static String getFolderName(String filePath) {

			if (isEmpty(filePath)) {
				return filePath;
			}

			int filePosi = filePath.lastIndexOf(java.io.File.separator);
			return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
		}

		/**
		 * get suffix of file from path
		 * <p/>
		 * 
		 * <pre>
		 *      getFileExtension(null)               =   ""
		 *      getFileExtension("")                 =   ""
		 *      getFileExtension("   ")              =   "   "
		 *      getFileExtension("a.mp3")            =   "mp3"
		 *      getFileExtension("a.b.rmvb")         =   "rmvb"
		 *      getFileExtension("abc")              =   ""
		 *      getFileExtension("c:\\")              =   ""
		 *      getFileExtension("c:\\a")             =   ""
		 *      getFileExtension("c:\\a.b")           =   "b"
		 *      getFileExtension("c:a.txt\\a")        =   ""
		 *      getFileExtension("/home/admin")      =   ""
		 *      getFileExtension("/home/admin/a.txt/b")  =   ""
		 *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
		 * </pre>
		 *
		 * @param filePath
		 * @return
		 */
		public static String getFileExtension(String filePath) {
			if (isEmpty(filePath)) {
				return filePath;
			}

			int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
			int filePosi = filePath.lastIndexOf(java.io.File.separator);
			if (extenPosi == -1) {
				return "";
			}
			return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
		}

		/**
		 * Creates the directory named by the trailing filename of this file,
		 * including the complete directory path required to create this
		 * directory. <br/>
		 * <br/>
		 * <ul>
		 * <strong>Attentions:</strong>
		 * <li>makeDirs("C:\\Users\\Trinea") can only create users folder</li>
		 * <li>makeFolder("C:\\Users\\Trinea\\") can create Trinea folder</li>
		 * </ul>
		 *
		 * @param filePath
		 * @return true if the necessary directories have been created or the
		 *         target directory already exists, false one of the directories
		 *         can not be created.
		 *         <ul>
		 *         <li>if {@link File#getFolderName(String)} return null, return
		 *         false</li>
		 *         <li>if target directory already exists, return true</li>
		 *         </ul>
		 */
		public static boolean makeDirs(String filePath) {
			String folderName = getFolderName(filePath);
			if (isEmpty(folderName)) {
				return false;
			}

			java.io.File folder = new java.io.File(folderName);
			return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
		}

		/**
		 * @param filePath
		 * @return
		 * @see #makeDirs(String)
		 */
		public static boolean makeFolders(String filePath) {
			return makeDirs(filePath);
		}

		/**
		 * Indicates if this file represents a file on the underlying file
		 * system.
		 *
		 * @param filePath
		 * @return
		 */
		public static boolean isFileExist(String filePath) {
			if (isEmpty(filePath)) {
				return false;
			}

			java.io.File file = new java.io.File(filePath);
			return (file.exists() && file.isFile());
		}

		/**
		 * Indicates if this file represents a directory on the underlying file
		 * system.
		 *
		 * @param directoryPath
		 * @return
		 */
		public static boolean isFolderExist(String directoryPath) {
			if (isEmpty(directoryPath)) {
				return false;
			}

			java.io.File dire = new java.io.File(directoryPath);
			return (dire.exists() && dire.isDirectory());
		}

		/**
		 * delete file or directory
		 * <ul>
		 * <li>if path is null or empty, return true</li>
		 * <li>if path not exist, return true</li>
		 * <li>if path exist, delete recursion. return true</li>
		 * <ul>
		 *
		 * @param path
		 * @return
		 */
		public static boolean deleteFile(String path) {
			if (isEmpty(path)) {
				return true;
			}

			java.io.File file = new java.io.File(path);
			if (!file.exists()) {
				return true;
			}
			if (file.isFile()) {
				return file.delete();
			}
			if (!file.isDirectory()) {
				return false;
			}
			for (java.io.File f : file.listFiles()) {
				if (f.isFile()) {
					f.delete();
				} else if (f.isDirectory()) {
					deleteFile(f.getAbsolutePath());
				}
			}
			return file.delete();
		}

		/**
		 * get file size
		 * <ul>
		 * <li>if path is null or empty, return -1</li>
		 * <li>if path exist and it is a file, return file size, else return -1</li>
		 * <ul>
		 *
		 * @param path
		 * @return returns the length of this file in bytes. returns -1 if the
		 *         file does not exist.
		 */
		public static long getFileSize(String path) {
			if (isEmpty(path)) {
				return -1;
			}

			java.io.File file = new java.io.File(path);
			return (file.exists() && file.isFile() ? file.length() : -1);
		}

	}

	public static class IO {

		/**
		 * 关闭IO
		 *
		 * @param closeables
		 *            closeables
		 */
		public static void close(Closeable... closeables) {
			if (closeables == null)
				return;
			for (Closeable closeable : closeables) {
				if (closeable != null) {
					try {
						closeable.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		/**
		 * 安静关闭IO
		 *
		 * @param closeables
		 *            closeables
		 */
		public static void closeQuietly(Closeable... closeables) {
			if (closeables == null)
				return;
			for (Closeable closeable : closeables) {
				if (closeable != null) {
					try {
						closeable.close();
					} catch (IOException ignored) {
					}
				}
			}
		}
	}

	public static class Date {

		private static SimpleDateFormat m = new SimpleDateFormat("MM", Locale.CANADA);
		private static SimpleDateFormat d = new SimpleDateFormat("dd", Locale.CANADA);
		private static SimpleDateFormat md = new SimpleDateFormat("MM-dd", Locale.CANADA);
		private static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
		private static SimpleDateFormat ymdDot = new SimpleDateFormat("yyyy.MM.dd", Locale.CANADA);
		private static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
		private static SimpleDateFormat ymdhmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CANADA);
		private static SimpleDateFormat ymdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
		private static SimpleDateFormat hm = new SimpleDateFormat("HH:mm", Locale.CANADA);
		private static SimpleDateFormat mdhm = new SimpleDateFormat("MM月dd日 HH:mm", Locale.CANADA);
		private static SimpleDateFormat mdhmLink = new SimpleDateFormat("MM-dd HH:mm", Locale.CANADA);

		/**
		 * 年月日[2015-07-28]
		 *
		 * @param timeInMills
		 * @return
		 */
		public static String getYmd(long timeInMills) {
			return ymd.format(new java.util.Date(timeInMills));
		}

		/**
		 * 年月日[2015.07.28]
		 *
		 * @param timeInMills
		 * @return
		 */
		public static String getYmdDot(long timeInMills) {
			return ymdDot.format(new java.util.Date(timeInMills));
		}

		public static String getYmdhms(long timeInMills) {
			return ymdhms.format(new java.util.Date(timeInMills));
		}

		public static String getYmdhmsS(long timeInMills) {
			return ymdhmss.format(new java.util.Date(timeInMills));
		}

		public static String getYmdhm(long timeInMills) {
			return ymdhm.format(new java.util.Date(timeInMills));
		}

		public static String getHm(long timeInMills) {
			return hm.format(new java.util.Date(timeInMills));
		}

		public static String getMd(long timeInMills) {
			return md.format(new java.util.Date(timeInMills));
		}

		public static String getMdhm(long timeInMills) {
			return mdhm.format(new java.util.Date(timeInMills));
		}

		public static String getMdhmLink(long timeInMills) {
			return mdhmLink.format(new java.util.Date(timeInMills));
		}

		public static String getM(long timeInMills) {
			return m.format(new java.util.Date(timeInMills));
		}

		public static String getD(long timeInMills) {
			return d.format(new java.util.Date(timeInMills));
		}

		/**
		 * 是否是今天
		 *
		 * @param timeInMills
		 * @return
		 */
		public static boolean isToday(long timeInMills) {
			String dest = getYmd(timeInMills);
			String now = getYmd(Calendar.getInstance().getTimeInMillis());
			return dest.equals(now);
		}

		/**
		 * 是否是同一天
		 *
		 * @param aMills
		 * @param bMills
		 * @return
		 */
		public static boolean isSameDay(long aMills, long bMills) {
			String aDay = getYmd(aMills);
			String bDay = getYmd(bMills);
			return aDay.equals(bDay);
		}

		/**
		 * 获取年份
		 *
		 * @param mills
		 * @return
		 */
		public static int getYear(long mills) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(mills);
			return calendar.get(Calendar.YEAR);
		}

		/**
		 * 获取月份
		 *
		 * @param mills
		 * @return
		 */
		public static int getMonth(long mills) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(mills);
			return calendar.get(Calendar.MONTH) + 1;
		}

		/**
		 * 获取月份的天数
		 *
		 * @param mills
		 * @return
		 */
		public static int getDaysInMonth(long mills) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(mills);

			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);

			switch (month) {
			case Calendar.JANUARY:
			case Calendar.MARCH:
			case Calendar.MAY:
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.OCTOBER:
			case Calendar.DECEMBER:
				return 31;
			case Calendar.APRIL:
			case Calendar.JUNE:
			case Calendar.SEPTEMBER:
			case Calendar.NOVEMBER:
				return 30;
			case Calendar.FEBRUARY:
				return (year % 4 == 0) ? 29 : 28;
			default:
				throw new IllegalArgumentException("Invalid Month");
			}
		}

		/**
		 * 获取星期,0-周日,1-周一，2-周二，3-周三，4-周四，5-周五，6-周六
		 *
		 * @param mills
		 * @return
		 */
		public static int getWeek(long mills) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(mills);

			return calendar.get(Calendar.DAY_OF_WEEK) - 1;
		}

		/**
		 * 获取当月第一天的时间（毫秒值）
		 *
		 * @param mills
		 * @return
		 */
		public static long getFirstOfMonth(long mills) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(mills);
			calendar.set(Calendar.DAY_OF_MONTH, 1);

			return calendar.getTimeInMillis();
		}

	}

}
