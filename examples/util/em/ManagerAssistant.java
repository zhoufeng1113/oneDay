package com.homevip.util.em;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 共用逻辑封装类
 * 
 * @author 钟智军
 * @version 2.0
 */
public class ManagerAssistant {
	/**
	 * 当前查询condition条件字符串
	 */
	public static ThreadLocal<String> conditionStringTL = new ThreadLocal<String>();
	/**
	 * 当前查询HQL参数设置列表
	 */
	public static ThreadLocal<List<HQLParameterSetter>> paramSetterListTL = new ThreadLocal<List<HQLParameterSetter>>();

	/**
	 * 拼凑要查询的字段
	 * @param className 类名
	 * @param attributeList 字段类表
	 * @return String 拼凑字符串
	 */
	public static String scrabbleAttribute(String className,
			List<String> attributeList) {
		if (attributeList == null || attributeList.size() == 0
				|| className == null || "".equals(className)) {
			return " model";
		}
		StringBuilder attributeString = new StringBuilder();
		attributeString.append(" new ");
		attributeString.append(className);
		attributeString.append("(");
		int listSize = attributeList.size();
		for (int i = 0; i < listSize; i++) {
			String attributeName = attributeList.get(i);
			attributeString.append("model.");
			attributeString.append(attributeName);
			attributeString.append(",");
		}
		attributeString.deleteCharAt(attributeString.length() - 1);
		attributeString.append(")");
		String attStr = attributeString.toString();
		return attStr;
	}

	/**
	 * 拼凑所有condition条件
	 * @param conditions 所有封装条件类
	 * @return String 拼凑字符串
	 */
	public static String scrabbleCondition(C conditions) {
		paramSetterListTL.remove();
		conditionStringTL.remove();
		if (conditions == null) {
			return null;
		}
		
		paramSetterListTL.set(new ArrayList<HQLParameterSetter>());

		String conditionString = " and ";
		
		String scrabblePerConditionStr = scrabblePerCondition(conditions);
		if(scrabblePerConditionStr != null && !"".equals(scrabblePerConditionStr)){
			conditionString += scrabblePerConditionStr;
		}else{
			conditionString = "";
		}
		
		conditionStringTL.set(conditionString);

		return conditionString;
	}

	/**
	 * 拼凑单个condition条件（包括子condition，递归调用）
	 * @param condition 单个封装条件类
	 * @return String 拼凑字符串
	 */
	private static String scrabblePerCondition(C condition) {
		if (condition == null) {
			return null;
		}

		StringBuilder conditionString = new StringBuilder();

		if (condition.isUnit()) {
			conditionString.append("(");
			C[] subConditions = condition.getConditions();
			UnitType unitType = condition.getUnitType();
			String unitTypeStr = unitType.toString();
			String scrabblePerConditionStr = null;
			int ct = 0;
			for (C cdn : subConditions) {
				scrabblePerConditionStr = scrabblePerCondition(cdn);
				if (scrabblePerConditionStr != null
						&& !"".equals(scrabblePerConditionStr)) {
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
			}else{
				conditionString.delete(conditionString.length() - 1,
						conditionString.length());
			}
		} else {
			if(condition.isOriginal()){
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
			
			if(nullable != null){
				conditionString.append("model.");
				conditionString.append(entityAttribute.trim());
				conditionString.append(" ");
				conditionString.append(nullable.toString());
			}else{
				String paramName = null;
				if (compareType == CompareType.IN
						|| compareType == CompareType.NOT_IN) {
					conditionString.append("model.");
					conditionString.append(entityAttribute.trim());
					conditionString.append(" ");
					conditionString.append(compareType.toString());
					conditionString.append(" (");
					conditionString.append(context);
					conditionString.append(")");
					return conditionString.toString();
				} else if(compareType == CompareType.IN4STRING
						|| compareType == CompareType.NOT_IN4STRING){
					conditionString.append("model.");
					conditionString.append(entityAttribute.trim());
					conditionString.append(" ");
					conditionString.append(compareType.toString());
					conditionString.append(" ('");
					conditionString.append(context.toString().replace(",", "','"));
					conditionString.append("')");
					return conditionString.toString();
				}else if(compareType == CompareType.INSTR){
					conditionString.append("instr(model.");
					conditionString.append(entityAttribute.trim());
					conditionString.append(", :");
					paramName = entityAttribute.trim().replace(".", "_");
					paramName = optParamName(paramName);
					conditionString.append(paramName);
					conditionString.append(") > 0");
				}else{
					conditionString.append("model.");
					conditionString.append(entityAttribute.trim());
					conditionString.append(" ");
					conditionString.append(compareType.toString());
					conditionString.append(" :");
					paramName = entityAttribute.trim().replace(".", "_");
					paramName = optParamName(paramName);
					conditionString.append(paramName);
				}

				HQLParameterSetter setter = null;
				if (compareType == CompareType.LIKE
						|| compareType == CompareType.NOT_LIKE) {
					setter = new HQLParameterSetter(paramName, "%" + context + "%");
				} else {
					setter = new HQLParameterSetter(paramName, context);
				}

				paramSetterListTL.get().add(setter);
			}
		}

		String cStr = conditionString.toString();
		return cStr;
	}
	
	/**
	 * 处理参数名，去重（递归调用）
	 * @param paramName 参数名
	 * @return String 处理后字符串
	 */
	private static String optParamName(String paramName){
		for(HQLParameterSetter item : paramSetterListTL.get()){
			if(paramName.equals(item.getParamName())){
				paramName += "1";
				paramName = optParamName(paramName);
			}
		}
		return paramName;
	}

	/**
	 * 拼凑分组条件
	 * @param groupCondition 分组条件封装类
	 * @return String 拼凑字符串
	 */
//	public static String scrabbleGroup(GroupCondition groupCondition) {
//		if (groupCondition == null || groupCondition.getAttributes() == null
//				|| groupCondition.getAttributes().length == 0) {
//			return null;
//		}
//
//		StringBuilder groupString = new StringBuilder(" group by ");
//
//		String[] attributes = groupCondition.getAttributes();
//		for (int i = 0; i < attributes.length; i++) {
//			String attribute = attributes[i];
//			if(attribute != null){
//				groupString.append("model.");
//				groupString.append(attribute);
//				groupString.append(",");
//			}
//		}
//		groupString.deleteCharAt(groupString.length() - 1);
//		String rs = groupString.toString();
//		if(rs.equals(" group by")){
//			rs = null;
//		}
//		return rs;
//	}

	/**
	 * 拼凑排序条件
	 * @param orderCondition 排序条件封装类
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
			if(order != null && order.getEntityAttribute() != null 
					&& (order.isOriginal() || order.getOrderType() != null)){
				if(order.isOriginal()){
					orderString.append(order.getEntityAttribute());
				}else{
					orderString.append("model.");
					orderString.append(order.getEntityAttribute());
					orderString.append(" ");
					orderString.append(order.getOrderType().toString());
				}
				orderString.append(",");
			}
		}
		orderString.deleteCharAt(orderString.length() - 1);
		String rs = orderString.toString();
		if(rs.equals(" order by")){
			rs = null;
		}
		return rs;
	}

}
