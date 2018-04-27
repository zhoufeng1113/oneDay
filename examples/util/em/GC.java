package com.homevip.util.em;

/**
 * 
 * 分组条件封装类
 * 
 * @author 钟智军
 * @version 1.0
 */
public class GC {
	/**
	 * 分组字段名
	 */
	private String[] attributes;

	public GC(String...attributes) {
		super();
		this.attributes = attributes;
	}

	public String[] getAttributes() {
		return attributes;
	}

	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}
	
	
	
}
