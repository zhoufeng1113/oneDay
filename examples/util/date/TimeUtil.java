package com.homevip.util.date;

import java.util.Date;
import java.util.GregorianCalendar;

import com.homevip.util.em.DataUtil;
import com.homevip.util.em.DateUtil;
/**
 * 时间处理工具类
 * @author zzj
 *
 */
public class TimeUtil {
	
	//1970-01-01 08:00:00
	public static long hour8L = 8 * 60 * 60 * 1000;
	public static String long2DD_HH_mm_ss4CH(long timeL){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(timeL - hour8L);
		
		StringBuilder strTemp = new StringBuilder();
		
		long day = 0;
		Date zDate = DateUtil.strToDate_yyyy_MM_dd("1970-01-01");
		String thisDateStr = calendar.get(GregorianCalendar.YEAR) 
			+ "-" + (calendar.get(GregorianCalendar.MONTH) + 1) 
			+ "-" + calendar.get(GregorianCalendar.DAY_OF_MONTH);
		
		Date thisDate = DateUtil.strToDate_yyyy_MM_dd(thisDateStr);
		day = (thisDate.getTime() - zDate.getTime()) / 24 / 60 / 60 / 1000;
		if(day != 0){
			strTemp.append(day);
			strTemp.append("天");
		}
		int getH = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		if(day != 0 || getH != 0){
			strTemp.append(getH);
			strTemp.append("小时");
		}
		strTemp.append(calendar.get(GregorianCalendar.MINUTE));
		strTemp.append("分");
		strTemp.append(calendar.get(GregorianCalendar.SECOND));
		strTemp.append("秒");
		return strTemp.toString();
	}
	
	public static String long2HH_mm_ss4CH(long timeL){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(timeL - hour8L);
		
		StringBuilder strTemp = new StringBuilder();
		
		long day = 0;
		Date zDate = DateUtil.strToDate_yyyy_MM_dd("1970-01-01");
		String thisDateStr = calendar.get(GregorianCalendar.YEAR) 
			+ "-" + (calendar.get(GregorianCalendar.MONTH) + 1) 
			+ "-" + calendar.get(GregorianCalendar.DAY_OF_MONTH);
		
		Date thisDate = DateUtil.strToDate_yyyy_MM_dd(thisDateStr);
		day = (thisDate.getTime() - zDate.getTime()) / 24 / 60 / 60 / 1000;
//		if(day != 0){
//			strTemp.append(day);
//			strTemp.append("天");
//		}
		int getH = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		if(day != 0 || getH != 0){
			strTemp.append(getH);
			strTemp.append("小时");
		}
		strTemp.append(calendar.get(GregorianCalendar.MINUTE));
		strTemp.append("分");
		strTemp.append(calendar.get(GregorianCalendar.SECOND));
		strTemp.append("秒");
		return strTemp.toString();
	}
	
	public static String long2DD_HH_mm_ss(long timeL){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(timeL - hour8L);
		
		StringBuilder strTemp = new StringBuilder();
		
		long day = 0;
		Date zDate = DateUtil.strToDate_yyyy_MM_dd("1970-01-01");
		String thisDateStr = calendar.get(GregorianCalendar.YEAR) 
			+ "-" + (calendar.get(GregorianCalendar.MONTH) + 1) 
			+ "-" + calendar.get(GregorianCalendar.DAY_OF_MONTH);
		
		Date thisDate = DateUtil.strToDate_yyyy_MM_dd(thisDateStr);
		day = (thisDate.getTime() - zDate.getTime()) / 24 / 60 / 60 / 1000;
		if(day != 0){
			strTemp.append(day<10?("0"+day):day);
			strTemp.append(" ");
		}
		int getH = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		if(day != 0 || getH != 0){
			strTemp.append(getH<10?("0"+getH):getH);
			strTemp.append(":");
		}
		int min = calendar.get(GregorianCalendar.MINUTE);
		strTemp.append(min<10?("0"+min):min);
		strTemp.append(":");
		int sec = calendar.get(GregorianCalendar.SECOND);
		strTemp.append(sec<10?("0"+sec):sec);
		return strTemp.toString();
	}
	
	public static String long2HH_mm_ss(long timeL){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(timeL - hour8L);
		
		StringBuilder strTemp = new StringBuilder();
		
		long day = 0;
		Date zDate = DateUtil.strToDate_yyyy_MM_dd("1970-01-01");
		String thisDateStr = calendar.get(GregorianCalendar.YEAR) 
			+ "-" + (calendar.get(GregorianCalendar.MONTH) + 1) 
			+ "-" + calendar.get(GregorianCalendar.DAY_OF_MONTH);
		
		Date thisDate = DateUtil.strToDate_yyyy_MM_dd(thisDateStr);
		day = (thisDate.getTime() - zDate.getTime()) / 24 / 60 / 60 / 1000;
//		if(day != 0){
//			strTemp.append(day<10?("0"+day):day);
//			strTemp.append(" ");
//		}
		int getH = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		if(day != 0 || getH != 0){
			strTemp.append(getH<10?("0"+getH):getH);
			strTemp.append(":");
		}
		int min = calendar.get(GregorianCalendar.MINUTE);
		strTemp.append(min<10?("0"+min):min);
		strTemp.append(":");
		int sec = calendar.get(GregorianCalendar.SECOND);
		strTemp.append(sec<10?("0"+sec):sec);
		return strTemp.toString();
	}
	
	public static long str2Long(String timeStr){
		if(DataUtil.isBlank(timeStr)){
			return 0;
		}
		long dayLong = 0;
		String[] dayData = timeStr.split(" ");
		if(dayData.length > 1){
			dayLong = DataUtil.parseInt(dayData[0]) * 24 * 60 * 60 * 1000;
			timeStr = dayData[1];
		}
		String[] timeDataArr = timeStr.split(":");
		long rs = 0;
		if(timeDataArr.length == 3){
			rs = DataUtil.parseInt(timeDataArr[0]) * 60 * 60 * 1000 
					+ DataUtil.parseInt(timeDataArr[1]) * 60 * 1000 
					+ DataUtil.parseInt(timeDataArr[2]) * 1000;
		}else if(timeDataArr.length == 2){
			rs = DataUtil.parseInt(timeDataArr[0]) * 60 * 60 * 1000 
					+ DataUtil.parseInt(timeDataArr[1]) * 60 * 1000;
		}
		rs += dayLong;
		return rs;
	}
	
	public static void main(String[] args){
		String timeStr = long2DD_HH_mm_ss(2 * 24 * 60 * 60 * 1000 + 17 * 60 * 60 * 1000 + 8 * 60 * 60 * 1000 + 5 * 1000 + 10 * 60 * 1000);
		System.out.println(timeStr);
		
//		String timeStr = long2DD_HH_mm_ss(1 * 60 * 60 * 1000 + 8 * 60 * 60 * 1000 + 5 * 1000 + 10 * 60 * 1000);
//		System.out.println(timeStr);
		long timeL = str2Long(timeStr);
		System.out.println(timeL);
		String timeStr2 = long2HH_mm_ss(timeL);
		System.out.println(timeStr2);
	}
}
