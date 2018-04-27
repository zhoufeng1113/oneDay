package com.homevip.util.em;

/**
 * 
 * 条件对比类型
 * 
 * @author 钟智军
 * @version 1.0
 */
public enum CompareType {
	/**
	 * 等于
	 */
	EQUATION,
	/**
	 * 相似
	 */
	LIKE,
	/**
	 * 相似
	 */
	LIKE4EXP,
	/**
	 * 不相似
	 */
	NOT_LIKE,
	/**
	 * 不相似
	 */
	NOT_LIKE4EXP,
	/**
	 * 大于
	 */
	MORE,
	/**
	 * 小于
	 */
	LESS,
	/**
	 * 大于等于
	 */
	MORE_EQUATION,
	/**
	 * 小于等于
	 */
	LESS_EQUATION,
	/**
	 * 不等于
	 */
	UNEQUAL,
	/**
	 * 在内
	 */
	IN,
	/**
	 * 在内
	 */
	IN4STRING,
	/**
	 * 不在内
	 */
	NOT_IN,
	/**
	 * 不在内
	 */
	NOT_IN4STRING,
	/**
	 * instr(code, '001') > 0
	 * 等同于code LIKE '%001%'
	 */
	INSTR
	;
	
	public String toString(){
		String str = null;
		if(this == EQUATION){
			str = "=";
		}else if(this == LIKE){
			str = "like";
		}else if(this == LIKE4EXP){
			str = "like";
		}else if(this == NOT_LIKE){
			str = "not like";
		}else if(this == NOT_LIKE4EXP){
			str = "not like";
		}else if(this == MORE){
			str = ">";
		}else if(this == LESS){
			str = "<";
		}else if(this == MORE_EQUATION){
			str = ">=";
		}else if(this == LESS_EQUATION){
			str = "<=";
		}else if(this == UNEQUAL){
			str = "<>";
		}else if(this == IN){
			str = "in";
		}else if(this == IN4STRING){
			str = "in";
		}else if(this == NOT_IN){
			str = "not in";
		}else if(this == NOT_IN4STRING){
			str = "not in";
		}else{
			str = "";
		}
		return str;
	}
}
