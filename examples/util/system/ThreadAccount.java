package com.homevip.util.system;

/**
 * 线程帐号
 * @author ll
 *
 */
public class ThreadAccount {
	
	static class Item {
		
		int id;
		String code;
	}
	
	private static ThreadAccount gInstance = null;
	// 线程变量
	private ThreadLocal<Item> items;

	public static ThreadAccount getInstance() {
		
		if (null == gInstance) gInstance = new ThreadAccount();
		return gInstance;
	}
	
	private ThreadAccount() {
		
		items = new ThreadLocal<Item>();
	}
	
	public Item get() {

		return items.get();
	}
	
	public void set(Item item) {

		if (null == item)
			items.remove();
		else
			items.set(item);
	}
	
	/**
	 * ID getter
	 * @return
	 */
	public static int getId() {

		Item item = getInstance().get();
		return null == item ? 0 : item.id;
	}
	
	/**
	 * 编码getter
	 * @return
	 */
	public static String getCode() {
		
		Item item = getInstance().get();
		return null == item ? null: item.code;
	}
	
	/**
	 * 帐号ID与编码setter
	 * @param id
	 * @param code
	 */
	public static void setAccount(int id, String code) {

		Item item = getInstance().get();
		if (null == item) item = new Item();
		item.id = id;
		item.code = code;
		getInstance().set(item);
	}
}
