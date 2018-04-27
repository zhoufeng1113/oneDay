package com.homevip.util.em;

public class LimitCondition {
	private int startPosition;
	private int endPosition;
	
	public LimitCondition(int startPosition, int endPosition) {
		super();
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	public int getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
	public int getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
	public String toString(){
		String rs = "";
		endPosition = endPosition - startPosition;
		rs = " limit " + startPosition + "," + endPosition;
		return rs;
	}
}
