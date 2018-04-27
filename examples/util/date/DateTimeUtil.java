package com.homevip.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import com.homevip.util.NumberUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.chainsaw.Main;

/**
 * 时间处理工具
 *
 * @author mojs
 */
public class DateTimeUtil {
    public static final long DAY_IN_MILLISECOND = 24 * 60 * 60 * 1000;

    public static String getPeriodUnitCN(String periodUnit) {
        String temp[] = {"day", "week", "month", "year"};
        for (int i = 0; i < temp.length; i++)
            if (periodUnit.equalsIgnoreCase(temp[i]))
                return PERIODUNIT_CN[i];

        return periodUnit;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String GetWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 当前日期
     *
     * @return
     */
    public static String getCurDate() {
        return formatDate(new Date());
    }

    /**
     * 当前时间
     *
     * @return
     */
    public static String getCurDateTime() {
        return formatDateTime(new Date());
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (null == date) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        }
    }

    public static String formatShortDate(Date date) {
        if (null == date) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat("yy-MM-dd");
            return df.format(date);
        }
    }

    /***
     * 格式化为中文日期
     */
    public static String formatChineseDate(Date date) {
        if (null == date) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
            return df.format(date);
        }
    }


    /**
     * 格式化时间按日期
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间按日期
     *
     * @param date
     * @return
     */
    public static String formatDateTime2Minute(Date date) {
        return formatDateTime(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 按格式得到日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDateTime(Date date, String format) {
        if (null == date) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
    }

    public static int getHour(Date date) {
        if (null == date)
            return 0;
        else
            return date.getHours();
    }

    public static int getMinute(Date date) {
        if (null == date)
            return 0;
        else
            return date.getMinutes();
    }

    public static Date getDate(String dateTime) {
        if (null == dateTime || dateTime.length() == 0)
            return null;
        DateFormat df = null;
        int len = dateTime.length();
        if (dateTime.length() == "yyyy-MM-dd HH:mm:ss".length())
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        else if (dateTime.length() == "yyyy-MM-dd HH:mm".length())
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        else if (dateTime.length() == "yyyy-MM-dd".length())
            df = new SimpleDateFormat("yyyy-MM-dd");
        else if (dateTime.length() == "yyyy-MM-dd HH".length())
            df = new SimpleDateFormat("yyyy-MM-dd HH");

            // add
        else if (len == "HH:mm:ss".length())
            df = new SimpleDateFormat("HH:mm:ss");
        else if (len == "EEE, dd MMM yyyy HH:mm:ss".length())
            df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US);
        else if (len == "EEE, dd MMM yyyy HH:mm:ss zzz".length())
            df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz",
                    Locale.US);
        else if (len == "yyyy-MM-ddTHH:mm:ss.SSSZ".length())
            df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        else if (len == "Fri, 06 Jan 2012 06:17:03 GMT".length()
                || len == "Fri, 6 Jan 2012 06:17:03 GMT".length())
            df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        else if (len == "Tue, 28 Jul 2009 04:08:00 +0800".length()
                || len == "Tue, 6 Jul 2009 04:08:00 +0800".length())
            df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        else if (len == "yyyy-MM".length())
            df = new SimpleDateFormat("yyyy-MM");
        else
            return null;
        try {
            return df.parse(dateTime);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }

    public static String buildDateTime(String date, String hour, String minute) {
        if (null == date || date.length() == 0)
            return "";
        if (null == hour)
            return date;
        String hm = hour.length() != 1 ? hour : "0" + hour;
        if (null != minute) {
            hm = hm + ":";
            hm = hm + (minute.length() != 1 ? minute : "0" + minute);
        }
        return date + " " + hm;
    }

    public static Date getStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static Date getEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int day) {
        return DateUtils.addDays(date, day);
    }

    public static Date addWeek(Date date, int week) {
        return DateUtils.addWeeks(date, week);
    }

    public static Date addMonth(Date date, int month) {
        return DateUtils.addMonths(date, month);
    }

    public static Date addTime(Date date, int hours, int mins, int seconds) {
        Date d = DateUtils.addHours(date, hours);
        d = DateUtils.addMinutes(d, mins);
        return DateUtils.addSeconds(d, seconds);
    }

    public static Date getPreMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getTime();
    }

    public static Date getPreYearDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, year - 1);
        return calendar.getTime();
    }

    public static int get(Date date, int field) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(field);
    }

    public static Date parseDate(String dateString, String pattern) {
        try {
            return DateUtils.parseDate(
                    dateString, new String[]{pattern});
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDate(String dateString, String[] patterns) {
        try {
            return DateUtils.parseDate(
                    dateString, patterns);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date newDate(int year, int month, int date) {
        return parseDate("" + year + month + date, month >= 10 ? "yyyyMMdd"
                : "yyyyMdd");
    }

    /**
     * 判断年份是否是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        }
        return false;
    }

    public static int getYearDays(int year) {
        if (isLeapYear(year)) {
            return 366;
        }
        return 365;
    }

    /**
     * 判断是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    public static Date truncate(Date d, int field) {
        return DateUtils.truncate(d, field);
    }

    /**
     * 判断是否是指定日期所在月份的最后一天
     */
    public static boolean isLastDayOfMonth(Date date) {
        return isFirstDayOfMonth(addDay(date, 1));
    }

    /**
     * 判断是否是指定日期所在月份的第一天
     */
    public static boolean isFirstDayOfMonth(Date date) {
        return get(date, Calendar.DAY_OF_MONTH) == 1;
    }

    /**
     * 得到指定月份的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastMonthDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 得到指定日期所在月份的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 得到当前日期所在月份的最后一天
     *
     * @return date
     */
    public static Date getLastMonthDay() {
        return getLastMonthDay(new Date());
    }

    /**
     * 得到指定日期所在月份的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 得到指定月份的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstMonthDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 得到指当前日期所在月份的第一天
     *
     * @return date
     */
    public static Date getFirstMonthDay() {
        return getFirstMonthDay(new Date());
    }

    /**
     * 得到指定周第一天的日期
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstWeekDay(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    /**
     * 得到指定日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //周日的要减1
        if (1 == calendar.get(Calendar.DAY_OF_WEEK)) {
            calendar.add(Calendar.DATE, -1);
        }
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 得到指定日期所在周的第一天
     * 忽略跨月
     *
     * @param date
     * @return
     */
    public static Date getFirstWeekDay(Date date, boolean ignoreovermonth) {
        Date sd = getFirstWeekDay(date);
        if (isOverMonth(formatDate(sd), formatDate(date))) {
            return getFirstMonthDay(date);
        } else {
            return sd;
        }
    }

    /**
     * 得到当前日期上周第一天
     *
     * @return
     */
    public static Date getBeforeWeekFirstWeekDay() {
        String datestr = DateTimeUtil.formatDate(DateTimeUtil.getFirstWeekDay());
        return DateTimeUtil.addDay(DateTimeUtil.getDate(datestr), -7);
    }

    /**
     * 得到当前日期上周最后一天
     *
     * @return
     */
    public static Date getBeforeWeekLastWeekDay() {
        String datestr = DateTimeUtil.formatDate(DateTimeUtil.getLastWeekDay());
        return DateTimeUtil.addDay(DateTimeUtil.getDate(datestr), -7);
    }

    /**
     * 得到当前日期上月第一天
     *
     * @return
     */
    public static Date getBeforeMonthFirstMonthDay() {
        String datestr = DateTimeUtil.formatDate(DateTimeUtil.getFirstMonthDay());
        return DateTimeUtil.addMonth(DateTimeUtil.getDate(datestr), -1);
    }

    /**
     * 得到当前日期上月最后一天
     *
     * @return
     */
    public static Date getBeforeMonthLastMonthDay() {
        String datestr = DateTimeUtil.formatDate(DateTimeUtil.getLastMonthDay());
        return DateTimeUtil.addMonth(DateTimeUtil.getDate(datestr), -1);
    }

    /**
     * 获取昨天
     *
     * @return
     */
    public static Date getBeforeDay() {
        String curDate = DateTimeUtil.getCurDate();
        return DateTimeUtil.addDay(DateTimeUtil.getDate(curDate), -1);
    }

    /**
     * 得到当前日期所在周的第一天
     *
     * @return date
     */
    public static Date getFirstWeekDay() {
        return getFirstWeekDay(new Date());
    }

    /**
     * 得到指定周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastWeekDay(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 得到指定日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //周日的要减1
        if (1 == calendar.get(Calendar.DAY_OF_WEEK)) {
            calendar.add(Calendar.DATE, -1);
        }
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date getLastWeekDay(Date date, boolean ignoreOverMonth) {
        Date ed = getLastWeekDay(date);
        if (isOverMonth(formatDate(ed), formatDate(date))) {
            return getLastMonthDay(date);
        } else {
            return ed;
        }
    }

    /**
     * 得到当前日期所在周的最后一天
     *
     * @return date
     */
    public static Date getLastWeekDay() {
        return getLastWeekDay(new Date());
    }

    /**
     * 指定日期所在周
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 指定日期所在月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 指定日期所在年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 日期间隔 ，负数表是
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getInterval(Date d1, Date d2) {
        int betweenDays = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR)
                - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }

        return betweenDays;
    }

    /****
     * 下面为得到两个日期的相隔的天数，正数endDay大过startDay，0为当天，负数endDay小于startDay
     *
     * @param startDay
     * @param endDay
     * @return
     */
    public static long getInterval(String startDay, String endDay) {

        Date date1;
        Date date2;
        long day = 0;
        try {

            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDay);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDay);

            // 日期相减得到相差的日期
            day = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return day;
    }

    /**
     * 时间相减,格式自定 如：yyyy-MM-dd HH:mm:ss
     * 返回毫秒
     * s-e
     */
    public static long lessDate(String s, String e, String fm) {
        long l = 0;
        try {
            if (null == fm) {
                fm = "yyyy-MM-dd HH:mm:ss";
            }
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(fm);
            long sd = dateFormat.parse(s).getTime();
            long ed = dateFormat.parse(e).getTime();
            l = (sd - ed);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return l;
    }

    /*****
     * 两日期相减(最好用)
     *
     * @param begin_date
     * @param end_date
     * @return
     */
    public static long getTwoDay(Date begin_date, Date end_date) {
        long day = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String sdate = format.format(Calendar.getInstance().getTime());

            if (begin_date == null) {
                begin_date = format.parse(sdate);
            }
            if (end_date == null) {
                end_date = format.parse(sdate);
            }
            day = (end_date.getTime() - begin_date.getTime())
                    / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return -1;
        }
        return day;
    }

    /**
     * 日期月份间隔
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getMonthInterval(Date startDate, Date endDate) {
        int betweenMonths = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(startDate);
        c2.setTime(endDate);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(startDate);
        }

        int y1 = c1.get(Calendar.YEAR);
        int y2 = c2.get(Calendar.YEAR);

        int m1 = c1.get(Calendar.MONTH);
        int m2 = c2.get(Calendar.MONTH);

        if (y2 > y1) {
            betweenMonths += (y2 - y1) * 12;
        }
        betweenMonths += (m2 - m1);

        return betweenMonths;
    }

    /**
     * 日期周间隔
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getWeekInterval(Date startDate, Date endDate) {
        int betweenWeeks = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(startDate);
        c2.setTime(endDate);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(startDate);
        }

        int y1 = c1.get(Calendar.YEAR);
        int y2 = c2.get(Calendar.YEAR);

        int w1 = c1.get(Calendar.WEEK_OF_YEAR);
        int w2 = c2.get(Calendar.WEEK_OF_YEAR);

        betweenWeeks += (w2 - w1);
        int betweenYears = y2 - y1;
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenWeeks += c1.getMaximum(Calendar.WEEK_OF_YEAR);
        }

        return betweenWeeks;
    }

    /**
     * 时间间隔,单位分
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDateTimeInterval(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        Date end = new Date();
        try {
            start = format.parse(startDate); // 将字符型转换成日期型
            end = format.parse(endDate); // 将字符型转换成日期型
        } catch (Exception e) {
            e.printStackTrace();
        }
        long time = end.getTime() - start.getTime(); // 返回毫秒数
        return (int) time / 1000 / 60;
    }

    /**
     * 日历时间加减操作
     *
     * @param resDate  原时间
     * @param field    Y-年,M-月,W-周,D-日,H-时,m-分,S-秒
     * @param interval 增加为正数，减少为负数
     * @return
     */
    public static Date dateAdd(final Date resDate, String field, int interval) {
        Date datReturn = null;
        int intField = GregorianCalendar.DAY_OF_YEAR;
        if (field.equals("Y")) {
            intField = GregorianCalendar.YEAR;
        } else if (field.equals("M")) {
            intField = GregorianCalendar.MONTH;
        } else if (field.equals("W")) {
            intField = GregorianCalendar.WEEK_OF_MONTH;
        } else if (field.equals("D")) {
            intField = GregorianCalendar.DAY_OF_YEAR;
        } else if (field.equals("H")) {
            intField = GregorianCalendar.HOUR_OF_DAY;
        } else if (field.equals("m")) {
            intField = GregorianCalendar.MINUTE;
        } else if (field.equals("S")) {
            intField = GregorianCalendar.SECOND;
        }

        GregorianCalendar cal = new GregorianCalendar();
        try {
            cal.setTime(resDate);
            cal.add(intField, interval);
            datReturn = cal.getTime();
        } catch (Exception ex) {
        }
        return datReturn;
    }

    /**
     * 把秒格式为时分秒格式输出
     *
     * @param duration
     * @return
     */
    public static String formatStatDate(int duration) {
        StringBuilder sb = new StringBuilder();
        if (duration > 3600) {
            sb.append(duration / 3600).append("小时");
        }
        if (duration > 60) {
            duration = duration % 3600;
            sb.append(duration / 60).append("分钟");
            duration = duration % 60;
        }
        if (0 != duration) {
            if (String.valueOf(duration).length() == 1) {
                sb.append("0");
            }
            sb.append(duration).append("秒");
        }
        return sb.toString();
    }

    /**
     * 获取日期中的天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回开始时间 到结束时间 期间的 日期间隔（精确到天）
     *
     * @param sdate
     * @param edate
     * @return <p>
     * return List
     * </p>
     */
    public static List<String> getDatesBetween(Date sdate, Date edate) {
        if (null == sdate || null == edate) {
            return null;
        }
        Calendar calendars = Calendar.getInstance();
        calendars.setTime(sdate);
        Calendar calendare = Calendar.getInstance();
        calendare.setTime(edate);
        if (calendare.before(calendars)) {
            Calendar temp = calendare;
            calendare = calendars;
            calendars = temp;
        }
        List<String> list = new ArrayList<String>();
        while (!calendars.after(calendare)) {
            list.add(DateTimeUtil.formatDateTime(calendars.getTime(), "MM-dd"));
            calendars.add(Calendar.DATE, 1);
        }
        return list;
    }

    /***
     * 验证日期真假
     *
     * @param str
     * @return
     */
    public static boolean checkDate(String str) {
        boolean ret = false;
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);

        if (str == null)
            return ret;
        try {
            // SimpleDateFormat类中的parse（）方法解析字符串的文本，生成 Date。format（） 将给定的
            // Date格式化为字符串

            Date date = format.parse(str);
            ret = true;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return ret;
    }

    /**
     * 日期比较
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2, String str) {


        DateFormat df = new SimpleDateFormat(str);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
//                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
//                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static final int JANUARY = 0;

    public static final int FEBRUARY = 1;

    public static final int MARCH = 2;

    public static final int APRIL = 3;

    public static final int MAY = 4;

    public static final int JUNE = 5;

    public static final int JULY = 6;

    public static final int AUGUST = 7;

    public static final int SEPTEMBER = 8;

    public static final int OCTOBER = 9;

    public static final int NOVEMBER = 10;

    public static final int DECEMBER = 11;

    private static final String MONTH_CN[] = {"\u4E00\u6708", "\u4E8C\u6708",
            "\u4E09\u6708", "\u56DB\u6708", "\u4E94\u6708", "\u516D\u6708",
            "\u4E03\u6708", "\u516B\u6708", "\u4E5D\u6708", "\u5341\u6708",
            "\u5341\u4E00\u6708", "\u5341\u4E8C\u6708"};

    public static final int SUNDAY = 0;

    public static final int MONDAY = 1;

    public static final int TUESDAY = 2;

    public static final int WEDNESDAY = 3;

    public static final int THURSDAY = 4;

    public static final int FRIDAY = 5;

    public static final int SATURDAY = 6;

    private static final String WEEK_CN[] = {"\u661F\u671F\u5929",
            "\u661F\u671F\u4E00", "\u661F\u671F\u4E8C", "\u661F\u671F\u4E09",
            "\u661F\u671F\u56DB", "\u661F\u671F\u4E94", "\u661F\u671F\u516D"};

    public static final String TIMEUNIT_MINUTE = "M";

    public static final String TIMEUNIT_HOUR = "H";

    public static final String TIMEUNIT_DAY = "D";

    private static final String TIMEUNIT_CN[] = {"\u5206\u949F",
            "\u5C0F\u65F6", "\u5929"};

    public static final String PERIODUNIT_DAY = "day";

    public static final String PERIODUNIT_WEEK = "week";

    public static final String PERIODUNIT_MONTH = "month";

    public static final String PERIODUNIT_YEAR = "year";

    private static final String PERIODUNIT_CN[] = {"\u5929", "\u5468",
            "\u6708", "\u5E74"};

    /**
     * 是否是今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(final Date date) {
        return isTheDay(date, new Date());
    }

    /**
     * 是否是指定日期
     *
     * @param date
     * @param day
     * @return
     */
    public static boolean isTheDay(final Date date, final Date day) {
        return date.getTime() >= DateTimeUtil.dayBegin(day).getTime()
                && date.getTime() <= DateTimeUtil.dayEnd(day).getTime();
    }

    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date dayBegin(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date dayEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 字符串的日期格式的计算(计算相差的天数)
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /***
     * 格式化为中文日期
     */
    public static String formatChineseDate(String date, boolean isShort) {
        DateFormat df = null;
        if (null == date) {
            return "";
        } else {
            if (isShort) {
                df = new SimpleDateFormat("MM月dd日");
            } else {
                df = new SimpleDateFormat("yyyy年MM月dd日");
            }
            return df.format(getDate(date));
        }
    }

    /**
     * 获取日期时间数值
     *
     * @return
     */
    public static String getCurDateTimeNum() {
        String curDateTime = getCurDateTime();
        return curDateTime.replace(" ", "").replace("-", "").replace(":", "");
    }

    /**
     * 判断某一时间是否在一个区间内
     *
     * @param sourceTime 时间区间,半闭合,如[10:00-20:00)
     * @param curTime    需要判断的时间 如10:00
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean isInTime(String sourceTime, String curTime) {
        if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }
        if (curTime == null || !curTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
        }
        String[] args = sourceTime.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            long now = sdf.parse(curTime).getTime();
            long start = sdf.parse(args[0]).getTime();
            long end = sdf.parse(args[1]).getTime();
            if (args[1].equals("00:00")) {
                args[1] = "24:00";
            }
            if (end < start) {
                if (now >= end && now < start) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (now >= start && now < end) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }

    }

    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        System.out.println(formatShortDate(cal.getTime()));
//        cal.add(Calendar.DATE, -1);
//        Date firstDate = DateTimeUtil.getFirstWeekDay(cal.getTime());
//        Date endDate = DateTimeUtil.getLastWeekDay(cal.getTime());
//        System.out.println(formatShortDate(firstDate));
//        System.out.println(formatShortDate(endDate));
   /*     System.out.println( DateTimeUtil.isInTime("08:30-12:30","13:30"));
        System.out.println(DateTimeUtil.formatDate(DateTimeUtil.addDay(new Date(),1)));*/

        /*List<String> slist=getWeekRangs("2017-02-27","2017-02-28");
        for(String s :slist){
            System.out.println( s);
        }*/


        try {
//            System.out.println(getSumSundayOfMonth("2017-07-03"));
            System.out.println(getSumWeekdayBetweenDate("2017-07-23", "2017-07-29", 1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证是否跨月
     * 跨月 true  没跨月  false
     *
     * @param datefrom
     * @param dateto
     * @return
     */
    public static boolean isOverMonth(String datefrom, String dateto) {
        Date dateStart = DateTimeUtil.getDate(datefrom);
        Date dateEnd = DateTimeUtil.getDate(dateto);
        return DateTimeUtil.getMonth(dateStart) != DateTimeUtil.getMonth(dateEnd);
    }

    /**
     * 获取某时间段的每周开始就结束时间
     *
     * @param start
     * @param enddate
     * @return
     */
    public static List<String> getWeekRangs(String start, String enddate) {
        String day = start;
        Date date = getDate(start);
        int i = 1;
        List<String> sddayList = new ArrayList();

        String sday = "";
        String eday = "";
        while (DateTimeUtil.getInterval(day, enddate) >= 0) {
            Date sdate = getFirstWeekDay(date, true);
            Date edate = getLastWeekDay(date, true);
            if (isOverMonth(formatDate(sdate), formatDate(date))) {
                sday = formatDate(date);
            } else {
                sday = formatDate(sdate);
            }

            if (isOverMonth(formatDate(date), formatDate(edate))) {
                eday = formatDate(date);
            } else {
                eday = formatDate(edate);
            }
            boolean isadd = true;
            for (String s : sddayList) {
                String[] sdarr = s.split(",");
                if (sdarr[1].contains(eday)) {
                    isadd = false;
                }

            }
            if (isadd) {
                sddayList.add(sday + "," + eday);
            }
            date = addDay(date, 1);
            day = formatDate(date);
        }
        return sddayList;
    }

    /**
     * 返回一段时间内的日期
     *
     * @param startdate
     * @param enddate
     * @return
     */
    public static List<String> getBetweenDays(String startdate, String enddate, boolean isIncludeStartdate) {
        List<String> dayList = new ArrayList();
        Date date = getDate(startdate);
        if (getInterval(startdate, enddate) >= 0) {
            if (isIncludeStartdate) {
                dayList.add(startdate);
            }
            while (!formatDate(date).equals(enddate)) {
                date = addDay(date, 1);
                dayList.add(formatDate(date));
            }
        }
        return dayList;
    }

    /**
     * 根据日期获取该日期在该周第几周
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static int getWeekendOfMonth(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
        Date enddate = DateTimeUtil.getLastWeekDay(date);
        Date startdate = DateTimeUtil.getFirstMonthDay(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        //周日减1（java默认周日为一周的第一天）
        if (DateTimeUtil.formatDate(enddate).equals(dateString) && !startdate.equals(dateString)) {
            weekOfMonth = weekOfMonth - 1;
        }
        return weekOfMonth;
    }

    /**
     * 返回两个日期间的星期X的数量
     *
     * @param startdate
     * @param enddate
     * @param weekday   JAVA日历，1星期日，2星期一，3星期二……
     * @return
     */
    public static long getSumWeekdayBetweenDate(String startdate, String enddate, int weekday) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startdate);
        Date end = sdf.parse(enddate);
        long whole = getInterval(start, end);
        long sum = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        for (int i = 0; i <= whole; i++) {
            if (weekday == calendar.get(Calendar.DAY_OF_WEEK))
                sum += 1;
            calendar.add(Calendar.DATE, 1);
        }

        return sum;
    }

}
