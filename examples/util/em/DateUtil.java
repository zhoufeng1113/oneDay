package com.homevip.util.em;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public final static String FORMATTIME = "yyyyMMddHHmmssSSS";
	public final static String CN_FORMATTIME = "yyyy年MM月dd日";
	public final static String YEAR_FORMAT = "yyyy";
	
	/**
	 * 日期转化成字符串。
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToStr(Date date, String pattern) {
		String dateStr = null;
		if(date == null){
			return dateStr;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			dateStr = format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 日期转化成字符串。 格式： yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStr_yyyy_MM_dd_HH_mm_ss(Date date) {
		return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 日期转化成字符串。 格式： yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStr_yyyy_MM_dd(Date date) {
		return dateToStr(date, "yyyy-MM-dd");
	}

	/**
	 * 字符串转化成日期。
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date strToDate(String str, String pattern) {
		Date date = null;
		if(DataUtil.isBlank(str) || DataUtil.isBlank(pattern)){
			return date;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转化成日期。
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date strToDateOfMonth_yyyy_MM_dd(String str, int num) {
		if(DataUtil.isBlank(str)){
			return null;
		}
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.parse(str);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DAY_OF_MONTH, num);
			date = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转化成日期。格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static Date strToDate_yyyy_MM_dd_HH_mm_ss(String str) {
		return strToDate(str, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 字符串转化成日期。格式：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static Date strToDate_yyyy_MM_dd(String str) {
		return strToDate(str, "yyyy-MM-dd");
	}
	
	/**
	 * 得到当前时间
	 * @param pattern 
	 * @return
	 */
	public static String getCurrDate(String pattern){
		Date date = new Date(); 
	    SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
	    String nowTime = sDateFormat.format(date);
	    return nowTime;
	}
	/**
	 * 得到当前时间
	 * @param pattern yyyy-MM-dd
	 * @return
	 */
	public static String getCurrDate_yyyy_MM_dd(){
	    return getCurrDate("yyyy-MM-dd");
	}
	/**
	 * 得到当前时间
	 * @param pattern yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrDate_yyyy_MM_dd_HH_mm_ss(){
	    return getCurrDate("yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getDateOfMonth_yyyy_MM_dd(int num){
		Date date = getDateOfMonth(num);
	    return dateToStr_yyyy_MM_dd(date);
	}
	/**
	 * 获取当月第一天
	 * @param num
	 * @return
	 */
	public static String getFirstDateOfMonth_yyyy_MM_dd(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.MONTH, 0);
		calendar.set(GregorianCalendar.DAY_OF_MONTH,1);
		Date time = calendar.getTime();
	    return dateToStr_yyyy_MM_dd(time);
	}
	public static Date getDateOfMonth(int num){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DAY_OF_MONTH, num);
		Date date = new Date(calendar.getTimeInMillis());
	    return date;
	}
	public static Date getDateOfMonth_yyyy_MM_dd_00_00_00(int num){
		String str = getDateOfMonth_yyyy_MM_dd(num);
	    return strToDate_yyyy_MM_dd(str);
	}
	public static int getMonth(int num){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.MONTH, num);
		Date date = new Date(calendar.getTimeInMillis());
		int rs = DataUtil.parseInt(dateToStr(date, "yyyyMM"));
	    return rs;
	}
	public static int getDay(int num){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DAY_OF_MONTH, num);
		Date date = new Date(calendar.getTimeInMillis());
		int rs = DataUtil.parseInt(dateToStr(date, "yyyyMMdd"));
	    return rs;
	}
	public static int monthAdd(int month, int num){
		Date dateTemp = strToDate(month + "01", "yyyyMMdd");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(dateTemp.getTime());
		calendar.add(GregorianCalendar.MONTH, num);
		Date date = new Date(calendar.getTimeInMillis());
		int rs = DataUtil.parseInt(dateToStr(date, "yyyyMM"));
	    return rs;
	}
	
	public static void main(String[] args){
//		System.out.println(getDay(0));
		System.out.println(getFirstDateOfMonth_yyyy_MM_dd());
	}
}
