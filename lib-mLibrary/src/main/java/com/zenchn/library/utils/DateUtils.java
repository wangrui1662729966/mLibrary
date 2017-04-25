package com.zenchn.library.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 日期工具类
 */
/**
 * 作    者：wangr on 2017/4/24 11:13
 * 描    述：日期工具类 
 * 修订记录：
 */
public class DateUtils {

    private static Calendar calendar;
    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final Map<String, DateFormat> DATE_FORMAT_MAP = new HashMap<String, DateFormat>();
    private static final String TIME_STANDARD_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

    static {
        DATE_FORMAT_MAP.put("yyyy-MM-dd", DEFAULT_DATEFORMAT);
        DATE_FORMAT_MAP.put("yyyy-MM-dd HH:mm:ss", DATETIME_FORMAT);
    }

    /**
     * 获取当前月的第一天日期字符串
     *
     * @return
     */
    public static String getCurrentFirstDayOfMonth() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat("yyyy-MM-dd 00:00").format(calendar.getTime());
    }

    /**
     * 获取当前月的最后一天日期字符串
     *
     * @return
     */
    public static String getCurrentLastDayOfMonth() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        return new SimpleDateFormat("yyyy-MM-dd 23:59").format(calendar.getTime());
    }

    /**
     * 把日期形式转换成字符串形式，格式为指定格式
     *
     * @param aDate
     * @return 返回格式为指定格式的字符串
     */
    public static final String convertDateToString(Date aDate, String aMask) {
        if (aDate == null)
            return null;
        if (aMask == null || "".equals(aMask))
            return null;
        return getDateTime(aDate, aMask);
    }

    /**
     * 把时间戳转换成字符串形式，格式为指定格式
     *
     * @param time
     * @return 返回格式为指定格式的字符串
     */
    public static final String convertDateToString(long time, String aMask) {
        return convertDateToString(new Date(time), aMask);
    }

    /**
     * 取得特定时间对应的字符串,格式化为指定格式
     *
     * @param aMask 包含日期格式的字符串
     * @param aDate
     * @return 返回格式为指定格式的字符串
     * @see SimpleDateFormat
     */
    public static final String getDateTime(Date aDate, String aMask) {
        if (aDate == null)
            return null;

        DateFormat format = DATE_FORMAT_MAP.get(aMask);
        if (format == null) {
            format = new SimpleDateFormat(aMask);
            DATE_FORMAT_MAP.put(aMask, format);
        }
        return format.format(aDate);
    }

    /**
     * 获取差值日期
     *
     * @param field  日历字段
     * @param amount 为字段添加的日期或时间量
     * @return
     */
    public static String amountDateStr(int field, int amount) {
        calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return new SimpleDateFormat("yyyy-MM-dd 00:00").format(calendar.getTime());
    }

    /**
     * 日期加一天
     *
     * @param dateStr 日期
     * @return
     * @throws ParseException
     */
    public static String andOneDay(String dateStr) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 获取当天时间
     *
     * @return
     * @throws ParseException
     */
    public static String getNowDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 格式化成指定格式
     *
     * @param dateStr   时间
     * @param oldFormat 指定格式
     * @param newFormat 指定格式
     * @return
     * @throws ParseException
     */
    public static String dateFormat(String dateStr, String oldFormat, String newFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(oldFormat);
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat(newFormat).format(date);
    }

    /**
     * 获取某月最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 分钟转换小时
     *
     * @param minutePar
     * @return
     */
    public static String minuteToHour(int minutePar) {
        int hour;
        int minute;
        hour = minutePar / 60;
        minute = minutePar % 60;
        return String.valueOf(hour) + "小时" + String.valueOf(minute) + "分";
    }

    /**
     * 根据时间添加小时返回计算后时间
     *
     * @param day
     * @param x
     * @return
     */
    public static String addDateHour(String day, int x) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 24小时制
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, x);// 24小时制
        date = cal.getTime();
        cal = null;
        return format.format(date);
    }

    /**
     * 获取时间差
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getSecondsSpace(long startTime, long endTime) {
        long time = endTime - startTime;
        int totalS = Math.abs(new Long(time / 1000).intValue());
        return totalS;
    }

    /**
     * 获取与当前时间的时间差
     *
     * @param startTime
     * @return
     */
    public static int getSecondsSpace(long startTime) {
        return getSecondsSpace(startTime, getCurrentTime());
    }

    /**
     * 比较日期大小
     *
     * @param beginDate  开始日期
     * @param endDate    结束日期
     * @param dateFormat 格式化
     * @return
     * @throws ParseException
     */
    public static boolean compareDate(String beginDate, String endDate, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(beginDate));
            c2.setTime(df.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        int result = c1.compareTo(c2);
        Log.d("TAG", c1 + "," + c2 + ",result:" + result);
        if (result > 0)// c1大于c2
            return true;
        return false;// c1小于等于c2
    }

    /**
     * 比较两个时间大小
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean compareTime(long beginTime, long endTime) {
        return endTime >= beginTime;
    }

    /**
     * 读取两个日期之间的天数
     *
     * @param beginDate yyyy-mm-dd
     * @param endDate   yyyymmdd
     * @return
     */
    public static int daysBetween(String beginDate, String endDate) {
        Date now = null;
        Date returnDate = null;
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            now = simpledateformat.parse(beginDate);
            returnDate = simpledateformat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(now);
        cReturnDate.setTime(returnDate);
        setTimeToMidnight(cNow);
        setTimeToMidnight(cReturnDate);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = returnMs - todayMs;
        return millisecondsToDays(intervalMs);
    }

    /**
     * 读取两个时间点之间的天数
     *
     * @param beginTime
     * @param endTime
     * @param isCeil
     * @return
     */
    public static int daysBetween(long beginTime, long endTime, boolean isCeil) {
        long intervalMs = Math.abs(endTime - beginTime);
        return millisecondsToDays(intervalMs, isCeil);
    }

    /**
     * 毫秒值转天数
     *
     * @param milliseconds
     * @return
     */
    public static int millisecondsToDays(long milliseconds) {
        return millisecondsToDays(milliseconds, false);
    }

    /**
     * 毫秒值转天数(进一法)
     *
     * @param milliseconds
     * @return
     */
    public static int millisecondsToDays(long milliseconds, boolean isCeil) {
        float rawValue = millisecondsToHours(milliseconds) / 24;
        return (int) (isCeil ? Math.ceil(rawValue) : rawValue);
    }

    /**
     * 毫秒值转小时数
     *
     * @param milliseconds
     * @return
     */
    public static float millisecondsToHours(long milliseconds) {
        return millisecondsToMinutes(milliseconds) / 60;
    }

    public static float millisecondsToMinutes(long milliseconds) {
        return millisecondsToSeconds(milliseconds) / 60;
    }

    public static float millisecondsToSeconds(long milliseconds) {
        return milliseconds / 1000;
    }

    private static void setTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    /**
     * 日期转时间戳(yyyy-MM-dd HH:mm:ss)
     */
    public static long getTimeFromDate(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeTemp = 0;
        try {
            Date date = simpleDateFormat.parse(dateStr);
            timeTemp = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeTemp;
    }

    /**
     * 获取当前时间(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getStandardCurrentTime() {
        return new SimpleDateFormat(TIME_STANDARD_TEMPLATE, Locale.CHINA).format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime(String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(new Date());
    }

    /**
     * 获取当天0点0分0秒
     *
     * @return
     */
    public static Long getStartTimeOfDay(Date date) {
        long time = date.getTime();
        return time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
    }

    /**
     * 获取当天23点59分59秒
     *
     * @return
     */
    public static Long getEndTimeOfDay(Date date) {
        return getStartTimeOfDay(date) + 24 * 60 * 60 * 1000 - 1;
    }

    /**
     * 获取当前时间(ACache类专用)
     *
     * @return
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

}
