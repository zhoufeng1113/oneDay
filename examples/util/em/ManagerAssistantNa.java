package com.homevip.util.em;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 逻辑封装类
 * 
 * @author 钟智军
 * @version 2.0
 */
public class ManagerAssistantNa {
	/**
	 * 当前查询condition条件字符串
	 */
	public static ThreadLocal<String> conditionStringTL = new ThreadLocal<String>();
	/**
	 * 当前查询havingCondition条件字符串
	 */
	public static ThreadLocal<String> havingConditionStringTL = new ThreadLocal<String>();
	/**
	 * 当前查询HQL参数设置列表
	 */
	public static ThreadLocal<List<SQLParameterSetter>> paramSetterListTL = new ThreadLocal<List<SQLParameterSetter>>();

	/**
	 * 拼凑要查询的字段
	 * 
	 * @param className
	 *            类名
	 * @param attributeList
	 *            字段类表
	 * @return String 拼凑字符串
	 */
	public static String scrabbleAttribute(List<String> attributeList) {
		if (attributeList == null || attributeList.size() == 0) {
			return " *";
		}

		StringBuilder attributeString = new StringBuilder();
		int listSize = attributeList.size();
		int listSizeP = listSize - 1;
		for (int i = 0; i < listSize; i++) {
			String attributeName = attributeList.get(i);
			attributeString.append(" ");
			attributeString.append(attributeName);
			if (i != listSizeP) {
				attributeString.append(",");
			}
		}
		String attStr = attributeString.toString();
		return attStr;
	}

	/**
	 * 拼凑所有condition条件
	 * 
	 * @param conditions
	 *            所有封装条件类
	 * @return String 拼凑字符串
	 */
	public static String scrabbleCondition(C conditions, String whereOrHaving) {
		if (conditions == null) {
			return null;
		}
		if("where".equals(whereOrHaving)){
			paramSetterListTL.remove();
			paramSetterListTL.set(new ArrayList<SQLParameterSetter>());
		}

		String conditionString = " and ";

		String scrabblePerConditionStr = scrabblePerCondition(conditions);
		if (scrabblePerConditionStr != null && !"".equals(scrabblePerConditionStr)) {
			conditionString += scrabblePerConditionStr;
		} else {
			conditionString = "";
		}

		if("where".equals(whereOrHaving)){
			conditionStringTL.set(conditionString);
		}else if("having".equals(whereOrHaving)){
			havingConditionStringTL.set(conditionString);
		}

		return conditionString;
	}

	/**
	 * 拼凑单个condition条件（包括子condition，递归调用）
	 * 
	 * @param condition
	 *            单个封装条件类
	 * @return String 拼凑字符串
	 */
	private static String scrabblePerCondition(C condition) {
		if (condition == null) {
			return null;
		}

		StringBuilder conditionString = new StringBuilder();

		if (condition.isUnit()) {
			C[] subConditions = condition.getConditions();
			if(subConditions == null){
				return null;
			}
			conditionString.append("(");
			UnitType unitType = condition.getUnitType();
			String unitTypeStr = unitType.toString();
			String scrabblePerConditionStr = null;
			int ct = 0;
			for (int i = 0; i < subConditions.length; i++) {
				C cdn = subConditions[i];
				scrabblePerConditionStr = scrabblePerCondition(cdn);
				if (scrabblePerConditionStr != null && !"".equals(scrabblePerConditionStr)) {
					conditionString.append(scrabblePerConditionStr);
					conditionString.append(" ");
					conditionString.append(unitTypeStr);
					conditionString.append(" ");
					ct++;
				}
			}
			if (ct != 0) {
				int i = 0;
				if (unitType == UnitType.AND) {
					i = 5;
				} else if (unitType == UnitType.OR) {
					i = 4;
				}
				conditionString.delete(conditionString.length() - i,
						conditionString.length());
				conditionString.append(")");
			} else {
				conditionString.delete(conditionString.length() - 1,
						conditionString.length());
			}
		} else {
			if (condition.isOriginal()) {
				return condition.getEntityAttribute();
			}
			Object context = condition.getContext();
			Nullable nullable = condition.getNullable();
			if ((context == null || "".equals(context.toString().trim())) 
					&& nullable == null) {
				return conditionString.toString();
			}
			String entityAttribute = condition.getEntityAttribute();
			CompareType compareType = condition.getCompareType();
			
			if (nullable != null) {
				conditionString.append(entityAttribute.trim());
				conditionString.append(" ");
				conditionString.append(nullable.toString());
			} else {
				if (compareType == CompareType.IN
						|| compareType == CompareType.NOT_IN) {
					conditionString.append(entityAttribute.trim());
					conditionString.append(" ");
					conditionString.append(compareType.toString());
					conditionString.append(" (");
					conditionString.append(context);
					conditionString.append(")");
					return conditionString.toString();
				} else if(compareType == CompareType.IN4STRING
						|| compareType == CompareType.NOT_IN4STRING){
					conditionString.append(entityAttribute.trim());
					conditionString.append(" ");
					conditionString.append(compareType.toString());
					conditionString.append(" ('");
					conditionString.append(context.toString().replace(",", "','"));
					conditionString.append("')");
					return conditionString.toString();
				}else if(compareType == CompareType.INSTR){
					conditionString.append("instr(");
					conditionString.append(entityAttribute.trim());
					conditionString.append(", ?) > 0");
				}else{
					conditionString.append(entityAttribute.trim());
					conditionString.append(" ");
					conditionString.append(compareType.toString());
					conditionString.append(" ?");
				}

				SQLParameterSetter setter = null;
				if (compareType == CompareType.LIKE
						|| compareType == CompareType.NOT_LIKE) {
					setter = new SQLParameterSetter(paramSetterListTL.get()
							.size() + 1, "%" + context + "%");
				} else {
					setter = new SQLParameterSetter(paramSetterListTL.get()
							.size() + 1, context);
				}

				paramSetterListTL.get().add(setter);
			}
		}

		String cStr = conditionString.toString();
		return cStr;
	}

	/**
	 * 拼凑分组条件
	 * 
	 * @param groupCondition
	 *            分组条件封装类
	 * @return String 拼凑字符串
	 */
	public static String scrabbleGroup(GC groupCondition) {
		if (groupCondition == null || groupCondition.getAttributes() == null
				|| groupCondition.getAttributes().length == 0) {
			return null;
		}

		StringBuilder groupString = new StringBuilder();
		groupString.append(" group by ");

		String[] attributes = groupCondition.getAttributes();
		for (int i = 0; i < attributes.length; i++) {
			String attribute = attributes[i];
			if (attribute != null) {
				groupString.append(attribute);
				groupString.append(",");
			}
		}
		groupString.deleteCharAt(groupString.length() - 1);
		String rs = groupString.toString();
		if (rs.equals(" group by")) {
			rs = null;
		}
		return rs;
	}

	/**
	 * 拼凑排序条件
	 * 
	 * @param orderCondition
	 *            排序条件封装类
	 * @return String 拼凑字符串
	 */
	public static String scrabbleOrder(OC orderCondition) {
		if (orderCondition == null || orderCondition.getOrders() == null
				|| orderCondition.getOrders().length == 0) {
			return null;
		}
		StringBuilder orderString = new StringBuilder();
		orderString.append(" order by ");

		O[] orders = orderCondition.getOrders();
		for (int i = 0; i < orders.length; i++) {
			O order = orders[i];
			if (order != null && order.getEntityAttribute() != null
					&& (order.isOriginal() || order.getOrderType() != null)) {
				if (order.isOriginal()) {
					orderString.append(order.getEntityAttribute());
				} else {
					orderString.append(order.getEntityAttribute());
					orderString.append(" ");
					orderString.append(order.getOrderType().toString());
				}
				orderString.append(",");
			}
		}
		orderString.deleteCharAt(orderString.length() - 1);
		String rs = orderString.toString();
		if (rs.equals(" order by")) {
			rs = null;
		}
		return rs;
	}

	public static String optLimit(StringBuilder sql,
			LimitCondition limitCondition) {
		String rs = null;
		if (limitCondition != null) {
			sql.append(limitCondition.toString());
		}

		rs = sql.toString();
		return rs;
	}
	
	public static List<Map<String, Object>> dateTryOpt2Map(List l, String attrStr){
		attrStr = attrStr.replace("select ", "");
		attrStr = attrStr.replace("SELECT ", "");
		String[] attrStrArr = attrStr.split(",");
		List<String> attrList = new ArrayList<String>();
		for(String item : attrStrArr){
			attrList.add(item);
		}
		return dateTryOpt2Map(l, attrList);
	}

	public static List<Map<String, Object>> dateTryOpt2Map(List l,
			List<String> attributeList) {
		List<Map<String, Object>> rs = null;
		if (l != null && l.size() != 0 
				&& attributeList != null && attributeList.size() != 0) {
			if(l.get(0) instanceof Object[]){
				// 把数据转换成map格式
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = null;
				Object[] dateItem = null;
				for (int i = 0; i < l.size(); i++) {
					map = new HashMap<String, Object>();
					dateItem = (Object[])l.get(i);
					for (int j = 0; j < attributeList.size(); j++) {
						String key = attributeList.get(j);
						String[] keyArr = key.split("as ");
						if(keyArr.length == 2){
							key = keyArr[1].trim();
						}else if(keyArr.length > 2){
							key = keyArr[keyArr.length - 1].trim();
						}else{
							keyArr = key.split("\\.");
							key = keyArr[keyArr.length - 1].trim();
						}
						map.put(key.toUpperCase(), dateItem[j]);
					}
					mapList.add(map);
				}
				rs = mapList;
			}else if(l.get(0) instanceof Map){
				// 把数据转换成map格式
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = null;
				Map<String, Object> dateItem = null;
				for (int i = 0; i < l.size(); i++) {
					map = new HashMap<String, Object>();
					dateItem = (Map<String, Object>)l.get(i);
					for (String oldKey : dateItem.keySet()) {
						map.put(oldKey.toUpperCase(), dateItem.get(oldKey));
					}
					mapList.add(map);
				}
				rs = mapList;
			}else{
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = null;
				Object dateItem = null;
				for (int i = 0; i < l.size(); i++) {
					map = new HashMap<String, Object>();
					dateItem = l.get(i);
					for (int j = 0; j < attributeList.size(); j++) {
						String key = attributeList.get(j);
						String[] keyArr = key.split("as ");
						if(keyArr.length == 2){
							key = keyArr[1].trim();
						}else if(keyArr.length > 2){
							key = keyArr[keyArr.length - 1].trim();
						}else{
							keyArr = key.split("\\.");
							key = keyArr[keyArr.length - 1].trim();
						}
						map.put(key.toUpperCase(), dateItem);
					}
					mapList.add(map);
				}
				rs = mapList;
			}
		}
		return rs;
	}
	
	public static Map<String, Object> dateTryOpt2Map(Object srcObj, String attrStr){
		attrStr = attrStr.replace("select ", "");
		String[] attrStrArr = attrStr.split(",");
		List<String> attrList = new ArrayList<String>();
		for(String item : attrStrArr){
			attrList.add(item);
		}
		return dateTryOpt2Map(srcObj, attrList);
	}
	
	public static Map<String, Object> dateTryOpt2Map(Object srcObj,
			List<String> attributeList) {
		Map<String, Object> rs = null;
		if(srcObj instanceof Object[]){
			// 把数据转换成map格式
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] dateItem = (Object[])srcObj;
			for (int j = 0; j < attributeList.size(); j++) {
				String key = attributeList.get(j);
				String[] keyArr = key.split("as ");
				if(keyArr.length == 2){
					key = keyArr[1].trim();
				}else if(keyArr.length > 2){
					key = keyArr[keyArr.length - 1].trim();
				}else{
					keyArr = key.split("\\.");
					key = keyArr[keyArr.length - 1].trim();
				}
				map.put(key.toUpperCase(), dateItem[j]);
			}
			rs = map;
		}else if(srcObj instanceof Map){
			// 把数据转换成map格式
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> dateItem = (Map<String, Object>)srcObj;
			for (String oldKey : dateItem.keySet()) {
				map.put(oldKey.toUpperCase(), dateItem.get(oldKey));
			}
			rs = map;
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			for (int j = 0; j < attributeList.size(); j++) {
				String key = attributeList.get(j);
				String[] keyArr = key.split("as ");
				if(keyArr.length == 2){
					key = keyArr[1].trim();
				}else if(keyArr.length > 2){
					key = keyArr[keyArr.length - 1].trim();
				}else{
					keyArr = key.split("\\.");
					key = keyArr[keyArr.length - 1].trim();
				}
				map.put(key.toUpperCase(), srcObj);
			}
			rs = map;
		}
		return rs;
	}
}
