package com.homevip.util.em;
/**
 * 
 * 联合类型
 * 
 * @author 钟智军
 * @version 1.0
 */
public enum UnitType {
	/**
	 * and类型
	 */
	AND,
	/**
	 * or类型
	 */
	OR;
	
	public String toString(){
		String str = null;
		if(this == AND){
			str = "and";
		}else if(this == OR){
			str = "or";
		}
		return str; 
	}
}
