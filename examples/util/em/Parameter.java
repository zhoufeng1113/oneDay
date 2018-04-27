package com.homevip.util.em;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Parameter {
	private String paramName;
	private Object value;
	
	public Parameter(String paramName, Object value) {
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
	public String toString(){
		String rs = null;
		if(value == null || "".equals(value.toString().trim())){
			rs = "";
		}else if(paramName == null || "".equals(paramName.trim())){
			rs = "";
		}else{
			try {
				rs = "&" + paramName + "=" + URLEncoder.encode(value.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}
	
}
