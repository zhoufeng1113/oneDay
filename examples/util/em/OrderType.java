package com.homevip.util.em;
/**
 * 
 * 排序类型
 * 
 * @author 钟智军
 * @version 1.0
 */
public enum OrderType {
	/**
	 * 降序
	 */
	DESC,
	/**
	 * 升序
	 */
	ASC;
	public String toString(){
		String str = null;
		if(this == DESC){
			str = "desc";
		}else{
			str = "asc";
		}
		return str;
	}
}
