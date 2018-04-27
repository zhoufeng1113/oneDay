package com.homevip.util.date;

import java.util.Date;

import com.homevip.core.util.DateRange;


/**
 * 时间范围
 * 
 * @author panpan
 * 
 */
public class DateRangeUtil {
	//时间范围
	private DateRange dr;
	
	public DateRange getDr() {
		return dr;
	}

	public void setDr(DateRange dr) {
		this.dr = dr;
	}

	/**
	 * 指定日期范围
	 * @param r 日期范围
	 */
	public DateRangeUtil(String r){
		if("1w".equals(r)){ //本周
			dr = new DateRange(DateTimeUtil.getFirstWeekDay(), r);
		}else if("1m".equals(r)){ //本月
			dr = new DateRange(DateTimeUtil.getFirstMonthDay(), r);
		}else if("nm".equals(r)){ //下月
			Date d = DateTimeUtil.getFirstMonthDay();
			d = DateTimeUtil.addMonth(d, 1);
			dr = new DateRange(d, "1m");
		}else if("-1m".equals(r)){ //上月
			Date d = DateTimeUtil.getFirstMonthDay();
			d = DateTimeUtil.addMonth(d, -1);
			dr = new DateRange(d, "1m");
		}else if("-3m".equals(r)){ //最近三月
			Date d = DateTimeUtil.getFirstMonthDay();
			Date s = DateTimeUtil.getFirstMonthDay(DateTimeUtil.addMonth(d, -3));
			Date e = DateTimeUtil.getLastMonthDay(DateTimeUtil.addMonth(d, -1));
			e = DateTimeUtil.addDay(e, 1);
			dr = new DateRange(s, e);
		}else{
			r = "1d";
			dr = new DateRange(r);
		}
	}
	
	public static void main(String[] args) {
		Date rdate = new Date();	//当月
		Date sdate = DateTimeUtil.dateAdd(rdate,"M",-1);	//上月
		String curr_dateStr = DateTimeUtil.formatDateTime(new Date(),"yyyy-MM")+"-26";
		String bef_dateStr = DateTimeUtil.formatDateTime(sdate,"yyyy-MM")+"-26";
		System.out.println(curr_dateStr);
		System.out.println(bef_dateStr);
	}
}
