package com.homevip.util.em;

public class TransformInfo {
	private String codeFieldNamne;
	private String targetFieldName;
	private Object getDataObj;
	private String getDataMethodName;
	public TransformInfo(String codeFieldNamne, String targetFieldName,
			Object getDataObj, String getDataMethodName) {
		super();
		this.codeFieldNamne = codeFieldNamne;
		this.targetFieldName = targetFieldName;
		this.getDataObj = getDataObj;
		this.getDataMethodName = getDataMethodName;
	}
	
	public String getCodeFieldNamne() {
		return codeFieldNamne;
	}

	public void setCodeFieldNamne(String codeFieldNamne) {
		this.codeFieldNamne = codeFieldNamne;
	}

	public String getTargetFieldName() {
		return targetFieldName;
	}
	public void setTargetFieldName(String targetFieldName) {
		this.targetFieldName = targetFieldName;
	}
	public Object getGetDataObj() {
		return getDataObj;
	}
	public void setGetDataObj(Object getDataObj) {
		this.getDataObj = getDataObj;
	}
	public String getGetDataMethodName() {
		return getDataMethodName;
	}
	public void setGetDataMethodName(String getDataMethodName) {
		this.getDataMethodName = getDataMethodName;
	}
	
}
