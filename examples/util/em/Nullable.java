package com.homevip.util.em;

/**
 * 
 * 对比条件是否为空的枚举
 * 
 * @author 钟智军
 * @version 1.0
 */
public enum Nullable {
	/**
	 * 为空
	 */
	NULL,
	/**
	 * 不为空
	 */
	NOTNULL;
	
	public String toString(){
		String str = null;
		if(this == NULL){
			str = "IS NULL";
		}else if(this == NOTNULL){
			str = "IS NOT NULL";
		}
		return str; 
	}
}
