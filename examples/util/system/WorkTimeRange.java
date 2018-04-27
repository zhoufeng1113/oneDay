package com.homevip.util.system;

import java.util.ArrayList;
import java.util.List;

import com.homevip.util.NumberUtil;

/**
 * 工作班制
 * @author panpan
 *
 */
public class WorkTimeRange {

	public static String[] arrJzTimeName = {"上午", "", "中午", "",  "晚上", "" };
	public static List<String> jzs = new ArrayList<String>(); //三班
	
	static{
		jzs.add("08:30");
		jzs.add("12:30");
		jzs.add("14:00");
		jzs.add("18:30");
		jzs.add("19:00");
		jzs.add("21:00");
	}
	
	/**
	 * 获取三班接着的下一班开始时间
	 */
	public static int getJzNextStartIndex(int hour){
		int startHour = 0;
		Boolean isIn;
		//先判断是否在三班内
		isIn = false;
		for(int i = 0; i < jzs.size(); i+=2){
			int start = NumberUtil.toInt(jzs.get(i).split(":")[0]);
			int end = NumberUtil.toInt(jzs.get(i + 1).split(":")[0]);
			if(start <= hour && hour <= end){
				isIn = true;
				startHour = i;
				break;
			}
		}
		if(isIn){
			if(jzs.size() - 2 == startHour){
				startHour = 0;
			}else{
				startHour += 2;
			}
		}else{
			isIn = false;
			startHour = 0;
			for(int i = 0; i < jzs.size(); i+=2){
				int next = 0;
				int start = NumberUtil.toInt(jzs.get(i).split(":")[0]);
				if(i == jzs.size() - 2){
					next = NumberUtil.toInt(jzs.get(i + 1).split(":")[0]);
				}else{
					next = NumberUtil.toInt(jzs.get(i + 2).split(":")[0]);
				}
				if(start <= hour && hour <= next){
					isIn = true;
					startHour = i;
					break;
				}
			}
			if(isIn){
				startHour += 2;
			}
		}
		if(startHour > jzs.size() - 1)
			startHour = 0;
		return startHour;
	}
	
	public static void main(String[] args) {
		System.out.println(jzs.get(getJzNextStartIndex(11)));
	}
}
