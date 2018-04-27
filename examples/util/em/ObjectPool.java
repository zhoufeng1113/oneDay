package com.homevip.util.em;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ObjectPool {
	private static ObjectPool objectPool = null;
	
	//=====EM对象=====
	//C
	private ConcurrentLinkedQueue<C> cLinkedQueue = null;
	
	//=====常用对象=====
	//StringBuilder
	private ConcurrentLinkedQueue<StringBuilder> stringBuilderLinkedQueue = null;
	//ArrayList<String>
	private ConcurrentLinkedQueue<List<String>> stringListLinkedQueue = null;
	//HashMap<String, Object>
	private ConcurrentLinkedQueue<HashMap<String, Object>> hashMapStringObjectLinkedQueue = null;
	

	private ObjectPool() {
		//C
		cLinkedQueue = new ConcurrentLinkedQueue<C>();
		
		//StringBuilder
		stringBuilderLinkedQueue = new ConcurrentLinkedQueue<StringBuilder>();
		//ArrayList<String>
		stringListLinkedQueue = new ConcurrentLinkedQueue<List<String>>();
		//HashMap<String, Object>
		hashMapStringObjectLinkedQueue = new ConcurrentLinkedQueue<HashMap<String, Object>>();
	}

	public static ObjectPool getInstance() {
		if (objectPool == null) {
			objectPool = new ObjectPool();
		}
		return objectPool;
	}
	
	private C getAndRemoveC() {
		C getC = null;
		try {
			if (cLinkedQueue.isEmpty()) {
				return null;
			}
			getC = cLinkedQueue.poll();
		} catch (Exception e) {
			getC = null;
		}
		return getC;
	}

	public C getC(UnitType unitType, C... conditions) {
		C c = getAndRemoveC();
		if (c == null) {
			c = new C(unitType, conditions);
		} else {
			c.setUnitType(unitType);
			c.setConditions(conditions);
			c.setUnit(true);
		}
		return c;
	}

	public C getC(String entityAttribute, Object context,
			CompareType compareType) {
		C c = getAndRemoveC();
		if (c == null) {
			c = new C(entityAttribute, context, compareType);
		} else {
			c.setEntityAttribute(entityAttribute);
			c.setContext(context);
			c.setCompareType(compareType);
		}
		return c;
	}

	public C getC(String conditionStr) {
		C c = getAndRemoveC();
		if (c == null) {
			c = new C(conditionStr);
		} else {
			c.setEntityAttribute(conditionStr);
			c.setOriginal(true);
		}
		return c;
	}

	public C getC(String entityAttribute, Nullable nullable) {
		C c = getAndRemoveC();
		if (c == null) {
			c = new C(entityAttribute, nullable);
		} else {
			c.setEntityAttribute(entityAttribute);
			c.setNullable(nullable);
		}
		return c;
	}
	
	public void ccC(C... cs) {
		if (cs == null || cs.length == 0) {
			return;
		}
		for(C topItem : cs){
			C[] thisCs = topItem.getChildrenAndI();
			if(thisCs == null || thisCs.length == 0){
				continue;
			}
			for (C item : thisCs) {
				if(item == null){
					continue;
				}
				item.reset();
				try {
					cLinkedQueue.offer(item);
				} catch (Exception e) {
				}
			}
		}
	}
	
	private StringBuilder getAndRemoveStringBuilder() {
		StringBuilder getStringBuilder = null;
		try {
			if (stringBuilderLinkedQueue.isEmpty()) {
				return null;
			}
			getStringBuilder = stringBuilderLinkedQueue.poll();
		} catch (Exception e) {
			getStringBuilder = null;
		}
		return getStringBuilder;
	}
	
	public StringBuilder getStringBuilder(){
		StringBuilder obj = getAndRemoveStringBuilder();
		if (obj == null) {
			obj = new StringBuilder();
		}
		return obj;
	}
	
	public void ccStringBuilder(StringBuilder... stringBuilders) {
		if (stringBuilders == null || stringBuilders.length == 0) {
			return;
		}
		
		for(StringBuilder item : stringBuilders){
			if(item == null){
				continue;
			}
			item.setLength(0);
			try {
				stringBuilderLinkedQueue.offer(item);
			} catch (Exception e) {
			}
		}
	}
	
	private List<String> getAndRemoveStringList() {
		List<String> getStringList = null;
		try {
			if (stringListLinkedQueue.isEmpty()) {
				return null;
			}
			getStringList = stringListLinkedQueue.poll();
		} catch (Exception e) {
			getStringList = null;
		}
		return getStringList;
	}
	
	public List<String> getStringList(String... attrs){
		List<String> attrList = getStringList();
		if(attrs == null || attrs.length == 0){
			return attrList;
		}
		for(String item : attrs){
			attrList.add(item);
		}
		return attrList;
	}
	
	public List<String> getStringList(){
		List<String> obj = getAndRemoveStringList();
		if (obj == null) {
			obj = new ArrayList<String>();
		}
		return obj;
	}
	
	public void ccStringList(List<String>... stringLists) {
		if (stringLists == null || stringLists.length == 0) {
			return;
		}
		
		for(List<String> item : stringLists){
			if(item == null){
				continue;
			}
			item.clear();
			try {
				stringListLinkedQueue.offer(item);
			} catch (Exception e) {
			}
		}
	}
	
	private HashMap<String, Object> getAndRemoveHashMapStringObject() {
		HashMap<String, Object> map = null;
		try {
			if (hashMapStringObjectLinkedQueue.isEmpty()) {
				return null;
			}
			map = hashMapStringObjectLinkedQueue.poll();
		} catch (Exception e) {
			map = null;
		}
		return map;
	}
	
	public HashMap<String, Object> getHashMapStringObject(){
		HashMap<String, Object> obj = getAndRemoveHashMapStringObject();
		if (obj == null) {
			obj = new HashMap<String, Object>();
		}
		return obj;
	}
	
	public void ccMapStringObject(Map<String, Object>... hashMaps) {
		if (hashMaps == null || hashMaps.length == 0) {
			return;
		}
		
		for(Map<String, Object> item : hashMaps){
			if(item == null){
				continue;
			}
			if (item instanceof HashMap) {
				item.clear();
				try {
					hashMapStringObjectLinkedQueue.offer((HashMap<String, Object>)item);
				} catch (Exception e) {
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ObjectPool op = ObjectPool.getInstance();
		Map<String, Object> map = op.getHashMapStringObject();
		
		//使用。。。。。
		
		op.ccMapStringObject(map);
	}
}
