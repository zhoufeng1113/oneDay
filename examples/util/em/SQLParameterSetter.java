package com.homevip.util.em;
/**
 * 
 * SQL参数设置类
 * 
 * @author 钟智军
 * @version 1.0
 */
public class SQLParameterSetter {
	/**
	 * SQL中的参数索引
	 */
	private int paramIndex;
	/**
	 * 设置值
	 */
	private Object value;
	
	public SQLParameterSetter(int paramIndex, Object value) {
		super();
		this.paramIndex = paramIndex;
		this.value = value;
	}
	
	public int getParamIndex() {
		return paramIndex;
	}
	public void setParamIndex(int paramIndex) {
		this.paramIndex = paramIndex;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}