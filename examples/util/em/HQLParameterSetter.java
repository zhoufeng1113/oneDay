package com.homevip.util.em;
/**
 * 
 * HQL参数设置类
 * 
 * @author 钟智军
 * @version 1.0
 */
public class HQLParameterSetter {
	/**
	 * HQL中的参数名
	 */
	private String paramName;
	/**
	 * 设置值
	 */
	private Object value;
	
	public HQLParameterSetter(String paramName, Object value) {
		super();
		this.paramName = paramName;
		this.value = value;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	
}