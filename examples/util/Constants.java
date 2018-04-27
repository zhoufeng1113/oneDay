package com.homevip.util;

import org.apache.commons.lang.StringUtils;

public class Constants {

	public final static String TRAIN_PASS = "通过";
	
	public final static String TRAIN_UN_PASS = "不通过";
	
	public final static String TRAIN_UN_EXAMINE = "未考核";
	
	public final static String TRAIN_LOSS = "已流失";
	
	public static String getTrainTypeForInt(int type) {
		
		if(type == 1) {
			return TRAIN_UN_PASS;
		}else if(type == 2) {
			return TRAIN_PASS;
		}else if(type == 3) {
			return TRAIN_UN_EXAMINE;
		}else if(type == 4) {
			return TRAIN_LOSS;
		}else {
			return null;
		}
	}
	
	public static Integer getTrainTypeForStr(String type) {
		
		if(StringUtils.isNotBlank(type) && TRAIN_UN_PASS.equals(type)) {
			return 1;
		}else if(StringUtils.isNotBlank(type) && TRAIN_PASS.equals(type)) {
			return 2;
		}else if(StringUtils.isNotBlank(type) && TRAIN_UN_EXAMINE.equals(type)) {
			return 3;
		}else if(StringUtils.isNotBlank(type) && TRAIN_LOSS.equals(type)) {
			return 4;
		}else {
			return null;
		}
	}
}
