package com.homevip.util.em;
/**
 * 
 * 排序条件联合类
 * 
 * @author 钟智军
 * @version 1.0
 */
public class OC {
	/**
	 * 排序条件封装实体
	 */
	private O[] orders;
	
	public OC(O...orders){
		this.orders = orders;
	}

	public O[] getOrders() {
		return orders;
	}

	public void setOrders(O[] orders) {
		this.orders = orders;
	}
	
	
}
