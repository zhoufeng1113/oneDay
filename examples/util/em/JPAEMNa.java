package com.homevip.util.em;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.homevip.core.page.Page;

/**
 * 
 * 原生查询工具类
 * 
 * @author 钟智军
 * @version 2.0
 */
public class JPAEMNa {

	/**
	 * 获取分页对象
	 * 
	 * @param pageSize
	 *            分页大小
	 * @param pageNO
	 *            页码
	 * @param conn
	 *            连接对象
	 * @param tableNames
	 *            表名
	 * @param attributeList
	 *            查询字段类表
	 * @param conditions
	 *            查询条件
	 * @param groupCondition
	 *            分组条件
	 * @param orderCondition
	 *            排序条件
	 * @param dataBaseType
	 *            数据库类型
	 * @param printSql
	 *            是否打印sql语句
	 * @return page 分页对象
	 */
	public static Page<Map<String, Object>> getMultiTablePage(int pageSize, int pageNO,
			EntityManager entityManager, String[] tableNames, List<String> attributeList,
			C conditions, GC groupCondition,
			C havingConditions, OC orderCondition) {
		List<Map<String, Object>> l = null;
		int totalPage = 0;
		long totalRows = 0;
		if (pageSize == 0) {
			l = findByCondition(entityManager, tableNames, attributeList, conditions,
					groupCondition, havingConditions, orderCondition);
			if (l == null) {
				return null;
			} else {
				pageNO = 1;
				totalPage = 1;
				totalRows = l.size();
			}
		} else {
			if (entityManager == null || tableNames == null || tableNames.length == 0) {
				return null;
			} else {
				for (String tableName : tableNames) {
					if (tableName == null || "".equals(tableName.trim())) {
						return null;
					}
				}
			}

			totalRows = count(entityManager, tableNames, conditions, groupCondition,
					havingConditions);
			if (totalRows == 0) {
				return null;
			}
			if (totalRows % pageSize == 0) {
				totalPage = (int) (totalRows / pageSize);
			} else {
				totalPage = (int) (totalRows / pageSize + 1);
			}
			if (pageNO < 1) {
				pageNO = 1;
			}
			if (pageNO > totalPage) {
				pageNO = totalPage;
			}
			int startPosition = pageSize * (pageNO - 1);
			int endPosition = pageSize * pageNO;
			LimitCondition limitCondition = new LimitCondition(startPosition,
					endPosition);

			Query query = createSQLQuery(entityManager, tableNames, attributeList,
					conditions, groupCondition, havingConditions,
					orderCondition, limitCondition);

			l = query.getResultList();
			ManagerAssistantNa.conditionStringTL.remove();
			ManagerAssistantNa.havingConditionStringTL.remove();
			ManagerAssistantNa.paramSetterListTL.remove();
		}

		// to page
		Page<Map<String, Object>> page = null;
		if (l != null && l.size() != 0) {
			l = ManagerAssistantNa.dateTryOpt2Map(l, attributeList);
			page = new Page<Map<String, Object>>(pageNO, totalPage, (int)totalRows, l);
			page.setPageSize(pageSize);
		}

		return page;
	}

	/**
	 * 根据条件获取数据
	 * 
	 * @param session
	 *            连接对象session
	 * @param tableNames
	 *            表名
	 * @param attributeList
	 *            查询字段类表
	 * @param conditions
	 *            查询条件
	 * @param groupCondition
	 *            分组条件
	 * @param orderCondition
	 *            排序条件
	 * @return list 分页列表
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> findByCondition(EntityManager entityManager,
			String[] tableNames, List<String> attributeList,
			C conditions, GC groupCondition,
			C havingConditions, OC orderCondition) {
		if (entityManager == null || tableNames == null || tableNames.length == 0) {
			return null;
		} else {
			for (String tableName : tableNames) {
				if (tableName == null || "".equals(tableName.trim())) {
					return null;
				}
			}
		}

		ManagerAssistantNa.conditionStringTL.remove();
		Query query = createSQLQuery(entityManager, tableNames, attributeList,
				conditions, groupCondition, havingConditions, orderCondition,
				null);

		List<Map<String, Object>> l = query.getResultList();
		l = ManagerAssistantNa.dateTryOpt2Map(l, attributeList);
		return l;
	}
	
	/**
	 * 根据条件获取数据
	 * 
	 * @param session
	 *            连接对象session
	 * @param tableNames
	 *            表名
	 * @param attributeList
	 *            查询字段类表
	 * @param conditions
	 *            查询条件
	 * @param groupCondition
	 *            分组条件
	 * @param orderCondition
	 *            排序条件
	 * @return list 分页列表
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> findOneByCondition(EntityManager entityManager,
			String[] tableNames, List<String> attributeList,
			C conditions, GC groupCondition,
			C havingConditions, OC orderCondition) {
		if (entityManager == null || tableNames == null || tableNames.length == 0) {
			return null;
		} else {
			for (String tableName : tableNames) {
				if (tableName == null || "".equals(tableName.trim())) {
					return null;
				}
			}
		}

		ManagerAssistantNa.conditionStringTL.remove();
		Query query = createSQLQuery(entityManager, tableNames, attributeList,
				conditions, groupCondition, havingConditions, orderCondition,
				null);

		Object rs = query.getSingleResult();
		Map<String, Object> item = null;
		if(rs != null){
			item = ManagerAssistantNa.dateTryOpt2Map(rs, attributeList);;
		}
		return item;
	}

	/**
	 * 根据条件统计有多少条记录
	 * 
	 * @param conn
	 *            连接对象
	 * @param tableNames
	 *            表名
	 * @param conditions
	 *            查询条件
	 * @return long 统计条数
	 */
	public static long count(EntityManager entityManager, String[] tableNames,
			C conditions, GC groupCondition,
			C havingConditions) {
		if (entityManager == null || tableNames == null || tableNames.length == 0) {
			for (String tableName : tableNames) {
				if (tableName == null || "".equals(tableName.trim())) {
					return 0;
				}
			}
		}
		ManagerAssistantNa.conditionStringTL.remove();
		ManagerAssistantNa.havingConditionStringTL.remove();
		ManagerAssistantNa.paramSetterListTL.remove();
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(*) from ");
		for (int i = 0; i < tableNames.length; i++) {
			if (i > 0) {
				queryString.append(",");
			}
			queryString.append(tableNames[i]);
		}
		queryString.append(" where 1=1");
		String conditionString = ManagerAssistantNa
				.scrabbleCondition(conditions, "where");
		if (conditionString != null) {
			queryString.append(conditionString);
		}

		String groupString = ManagerAssistantNa.scrabbleGroup(groupCondition);
		if (groupString != null) {
			String aa = "aa" + System.currentTimeMillis();
			String oneGItem = groupCondition.getAttributes()[0];
			String[] arr = oneGItem.split("\\.");
			queryString.replace(7, 15, oneGItem);
			queryString.append(groupString);
			//having部分
			String havingConditionString = ManagerAssistantNa
					.scrabbleCondition(havingConditions, "having");
			if (havingConditionString != null) {
				queryString.append(" having 1=1");
				queryString.append(havingConditionString);
			}
			queryString.insert(0, "select count(" + aa + "."
					+ arr[arr.length - 1] + ") from (");
			queryString.append(") ");
			queryString.append(aa);
		}

		Query query = null;
		Object obj = null;
		List<Object> l = null;
		query = entityManager.createNativeQuery(queryString.toString());
		setParameters(query);
		l = query.getResultList();
		if (l != null && l.size() != 0) {
			obj = l.get(0);
		}
		if (obj != null) {
			return Long.parseLong(obj.toString());
		} else {
			return 0;
		}
	}

	/**
	 * 创建查询
	 * 
	 * @param session
	 *            连接对象session
	 * @param tableNames
	 *            表名
	 * @param attributeList
	 *            查询字段类表
	 * @param conditions
	 *            查询条件
	 * @param groupCondition
	 *            分组条件
	 * @param orderCondition
	 *            排序条件
	 * @return query 查找类实例
	 */
	public static Query createSQLQuery(EntityManager entityManager, String[] tableNames,
			List<String> attributeList, C conditions,
			GC groupCondition, C havingConditions,
			OC orderCondition, LimitCondition limitCondition) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("select");
		queryString.append(ManagerAssistantNa
				.scrabbleAttribute(attributeList));
		queryString.append(" from ");
		for (int i = 0; i < tableNames.length; i++) {
			if (i > 0) {
				queryString.append(",");
			}
			queryString.append(tableNames[i]);
		}
		queryString.append(" where 1=1");

		String conditionString = ManagerAssistantNa.conditionStringTL.get();
		if (conditionString == null) {
			conditionString = ManagerAssistantNa
					.scrabbleCondition(conditions, "where");
		}
		if (conditionString != null) {
			queryString.append(conditionString);
		}

		String groupString = ManagerAssistantNa.scrabbleGroup(groupCondition);
		if (groupString != null) {
			queryString.append(groupString);

			String havingConditionString = ManagerAssistantNa.havingConditionStringTL
					.get();
			if (havingConditionString == null) {
				havingConditionString = ManagerAssistantNa
						.scrabbleCondition(havingConditions, "having");
			}
			if (havingConditionString != null) {
				queryString.append(" having 1=1");
				queryString.append(havingConditionString);
			}
		}

		String orderString = ManagerAssistantNa.scrabbleOrder(orderCondition);
		if (orderString != null) {
			queryString.append(orderString);
		}

		String sql = ManagerAssistantNa.optLimit(queryString, limitCondition);
		Query query = null;
		if (sql != null) {
			query = entityManager.createNativeQuery(sql);
			setParameters(query);
		}

		return query;
	}

	/**
	 * 设置参数
	 * 
	 * @param query
	 *            当次所创建的查询
	 */
	public static void setParameters(Query query) {
		List<SQLParameterSetter> list = ManagerAssistantNa.paramSetterListTL
				.get();
		if (list == null || list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			SQLParameterSetter setter = list.get(i);
			if(setter.getValue() instanceof String){
				try {
					setter.setValue(URLDecoder.decode(setter.getValue().toString(), "UTF-8"));
				} catch (Exception e) {
				}
			}
			query.setParameter(setter.getParamIndex(), setter.getValue());
		}
	}
	
	
	public List executeSearchByOrigSQL(EntityManager entityManager, String sql, Object[] obj) {
		Query q = entityManager.createQuery(sql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				q.setParameter(i, obj[i]);
			}
		}
		List ret = q.getResultList();
		return ret;
	}

	public int executeUpdateByOrigSQL(EntityManager entityManager,final String sql, final Object[] obj) {
		Query q = entityManager.createQuery(sql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				q.setParameter(i, obj[i]);
			}
		}
		int ret = q.executeUpdate();
		return ret;
	}
	
}
