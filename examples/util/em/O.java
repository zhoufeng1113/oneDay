package com.homevip.util.em;

/**
 * 
 * 排序条件封装类
 * 
 * @author 钟智军
 * @version 1.0
 */
public class O {
	/**
	 * 实体字段名
	 */
	private String entityAttribute;
	/**
	 * 排序类型
	 */
	private OrderType orderType;
	/**
	 * 是否是原始字符串
	 */
	private boolean original = false;
	
	public O(String entityAttribute, OrderType orderType) {
		this.entityAttribute = entityAttribute;
		this.orderType = orderType;
	}
	
	public O(String orderStr){
		this.entityAttribute = orderStr;
		this.original = true;
	}
	
	public String getEntityAttribute() {
		return entityAttribute;
	}
	public void setEntityAttribute(String entityAttribute) {
		this.entityAttribute = entityAttribute;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	public boolean isOriginal() {
		return original;
	}
	public void setOriginal(boolean original) {
		this.original = original;
	}
	
}
