package com.homevip.util.em;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.homevip.core.page.Page;

/**
 * 
 * JPA查询工具类
 * 
 * @author 钟智军
 * @version 2.0
 */
public class JPAEM {

	/**
	 * 获取分页对象
	 * 
	 * @param pageSize
	 *            分页大小
	 * @param pageNO
	 *            页码
	 * @param entityManager
	 *            EJB实体管理器对象
	 * @param entityClass
	 *            操作的实体的类
	 * @param attributeList
	 *            查询字段类表
	 * @param conditions
	 *            查询条件
	 * @param groupCondition
	 *            分组条件
	 * @param orderCondition
	 *            排序条件
	 * @return page 分页对象
	 */
	@SuppressWarnings("unchecked")
	public static <E> Page<E> getPage(int pageSize, int pageNO,
			EntityManager entityManager, Class<E> entityClass,
			List<String> attributeList, C conditions,
			OC orderCondition) {
		String className = entityClass.getName();
		String[] nameArr = className.split("\\.");
		String entityName = nameArr[nameArr.length - 1];

		List l = null;
		int totalPage = 0;
		long totalRows = 0;
		if (pageSize == 0) {
			l = findByCondition(entityManager, entityClass, attributeList,
					conditions, orderCondition);
			if (l == null) {
				return null;
			} else {
				pageNO = 1;
				totalPage = 1;
				totalRows = l.size();
			}
		} else {
			if (pageSize == 0 || entityManager == null
					|| entityName == null || "".equals(entityName)) {
				return null;
			}

			totalRows = count(entityManager, entityClass, conditions);
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
			int maxResult = pageSize;
			
			Query query = createQuery(entityManager, entityClass,
					attributeList, conditions, orderCondition);

			query.setFirstResult(startPosition);
			query.setMaxResults(maxResult);

			l = query.getResultList();
			ManagerAssistantNa.conditionStringTL.remove();
			ManagerAssistantNa.havingConditionStringTL.remove();
			ManagerAssistantNa.paramSetterListTL.remove();
		}

		// to page
		Page<E> page = null;
		if (l != null && l.size() != 0) {
			page = new Page<E>(pageNO, totalPage, (int)totalRows, l);
			page.setPageSize(pageSize);
		}

		return page;
	}

	/**
	 * 根据条件获取数据
	 * 
	 * @param entityManager
	 *            EJB实体管理器对象
	 * @param entityClass
	 *            操作的实体的类
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
	public static <E> List<E> findByCondition(EntityManager entityManager,
			Class<E> entityClass, List<String> attributeList,
			C conditions, OC orderCondition) {
		String className = entityClass.getName();
		String[] nameArr = className.split("\\.");
		String entityName = nameArr[nameArr.length - 1];

		if (entityManager == null || entityName == null || "".equals(entityName)) {
			return null;
		}

		List<E> l = null;
		
		ManagerAssistant.conditionStringTL.remove();
		Query query = createQuery(entityManager, entityClass, attributeList,
				conditions, orderCondition);

		l = query.getResultList();
		return l;
	}
	
	/**
	 * 根据条件获取数据
	 * 
	 * @param entityManager
	 *            EJB实体管理器对象
	 * @param entityClass
	 *            操作的实体的类
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
	public static <E> E findOneByCondition(EntityManager entityManager,
			Class<E> entityClass, List<String> attributeList,
			C conditions, OC orderCondition) {
		String className = entityClass.getName();
		String[] nameArr = className.split("\\.");
		String entityName = nameArr[nameArr.length - 1];

		if (entityManager == null || entityName == null || "".equals(entityName)) {
			return null;
		}

		List<E> l = null;
		
		ManagerAssistant.conditionStringTL.remove();
		Query query = createQuery(entityManager, entityClass, attributeList,
				conditions, orderCondition);

		query.setFirstResult(0);
		query.setMaxResults(1);
		
		l = query.getResultList();
		E obj = null;
		if(l != null && l.size() != 0){
			obj = (E)l.get(0);
		}
		return obj;
	}

	/**
	 * 根据条件统计有多少条记录
	 * 
	 * @param entityManager
	 *            EJB实体管理器对象
	 * @param entityClass
	 *            操作的实体的类
	 * @param conditions
	 *            查询条件
	 * @return long 统计条数
	 */
	@SuppressWarnings("unchecked")
	public static long count(EntityManager entityManager, Class entityClass,
			C conditions) {
		String className = entityClass.getName();
		String[] nameArr = className.split("\\.");
		String entityName = nameArr[nameArr.length - 1];

		if (entityManager == null || entityName == null || "".equals(entityName)) {
			return 0;
		}
		ManagerAssistantNa.conditionStringTL.remove();
		ManagerAssistantNa.havingConditionStringTL.remove();
		ManagerAssistantNa.paramSetterListTL.remove();
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(model) from ");
		queryString.append(entityName);
		queryString.append(" model where 1=1");
		String conditionString = ManagerAssistant.scrabbleCondition(conditions);
		if (conditionString != null) {
			queryString.append(conditionString);
		}
		Query query = entityManager.createQuery(queryString.toString());
		if(conditions != null){
			setParameters(query);
		}
		Object o = query.getSingleResult();
		long row = 0;
		if(o != null){
			row = Long.parseLong(o.toString());
		}
		return row;
	}
	
	/**
	 * 根据条件统计有多少条记录
	 * @param session Hibernate会话对象
	 * @param entityClass 操作的实体的类
	 * @param conditions 查询条件
	 * @return long 统计条数
	 */
	@SuppressWarnings("unchecked")
	public static <E> double sum(EntityManager entityManager, Class<E> entityClass,
			C conditions, String attribute) {
		String className = entityClass.getName();
		String[] nameArr = className.split("\\.");
		String entityName = nameArr[nameArr.length - 1];

		if (entityManager == null || entityName == null || "".equals(entityName)
				|| attribute == null || "".equals(attribute)) {
			return 0;
		}
		StringBuilder queryString = new StringBuilder();
		queryString.append("select ");
		queryString.append("sum(model.");
		queryString.append(attribute);
		queryString.append(")");
		queryString.append(" from ");
		queryString.append(entityName);
		queryString.append(" model where 1=1");
		String conditionString = ManagerAssistant.scrabbleCondition(conditions);
		if (conditionString != null) {
			queryString.append(conditionString);
		}
		Query query = entityManager.createQuery(queryString.toString());
		if(conditions != null){
			setParameters(query);
		}

		List list = query.getResultList();
		double totles = 0;
		if(list != null && list.size() != 0){
			totles = Double.parseDouble(list.get(0).toString());
		}
		return totles;
	}

	/**
	 * 创建查询
	 * 
	 * @param entityManager
	 *            EJB实体管理器对象
	 * @param entityClass
	 *            操作的实体的类
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
	@SuppressWarnings("unchecked")
	public static Query createQuery(EntityManager entityManager,
			Class entityClass, List<String> attributeList,
			C conditions, OC orderCondition) {
		String className = entityClass.getName();
		String[] nameArr = className.split("\\.");
		String entityName = nameArr[nameArr.length - 1];

		StringBuilder queryString = new StringBuilder();
		queryString.append("select");
		queryString.append(ManagerAssistant.scrabbleAttribute(className,
				attributeList));
		queryString.append(" from ");
		queryString.append(entityName);
		queryString.append(" model where 1=1");

		String conditionString = ManagerAssistant.conditionStringTL.get();
		if (conditionString == null) {
			conditionString = ManagerAssistant.scrabbleCondition(conditions);
		}
		if (conditionString != null) {
			queryString.append(conditionString);
		}

		String orderString = ManagerAssistant.scrabbleOrder(orderCondition);
		if (orderString != null) {
			queryString.append(orderString);
		}

		Query query = entityManager.createQuery(queryString.toString());
		if(conditions != null){
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

		List<HQLParameterSetter> list = ManagerAssistant.paramSetterListTL
				.get();

		if (list == null || list.size() == 0) {
			return;
		}

		for (HQLParameterSetter setter : list) {
			if(setter.getValue() instanceof String){
				try {
					setter.setValue(URLDecoder.decode(setter.getValue().toString(), "UTF-8"));
				} catch (Exception e) {
				}
			}
			query.setParameter(setter.getParamName(), setter.getValue());
		}

	}
	
	public static <E> int deleteByCondition(EntityManager entityManager, Class<E> entityClass, 
			C conditions){
		if(entityClass == null){
			return 0;
		}
		if(conditions == null){
			return 0;
		}
		String conditionString = ManagerAssistant.scrabbleCondition(conditions);
		if(conditionString == null || "".equals(conditionString)){
			return 0;
		}
		
		String className = entityClass.getName();
		String[] nameArr = className.split("\\.");
		String entityName = nameArr[nameArr.length - 1];
		
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(entityName).append(" model");
		hql.append(" where 1=1");
		hql.append(conditionString);
		Query q = entityManager.createQuery(hql.toString());
		setParameters(q);
		int ret = q.executeUpdate();
		return ret;
	}
	
	public static <E> int updateByCondition(EntityManager entityManager, Class<E> entityClass, 
			Map<String, Object> params, C conditions){
		if(entityClass == null){
			return 0;
		}
		if(params == null || params.size() == 0){
			return 0;
		}
		
		String className = entityClass.getName();
		String[] nameArr = className.split("\\.");
		String entityName = nameArr[nameArr.length - 1];
		
		StringBuilder hql = new StringBuilder();
		hql.append("update ");
		hql.append(entityName);
		hql.append(" model set").append(" ");
		for(Map.Entry<String,Object> entry : params.entrySet()){
			hql.append("model.").append(entry.getKey()).append("=:p_").append(entry.getKey()).append(",");
		}
		hql.deleteCharAt(hql.length() - 1);
		if(conditions != null){
			hql.append(" where 1=1");
			String conditionString = ManagerAssistant.scrabbleCondition(conditions);
			if (conditionString != null) {
				hql.append(conditionString);
			}
		}
		Query q = entityManager.createQuery(hql.toString());
		for(Map.Entry<String,Object> entry : params.entrySet()){
			q.setParameter("p_" + entry.getKey(), entry.getValue());
		}
		setParameters(q);
		int ret = q.executeUpdate();
		return ret;
	}
	
	public List executeSearchByHQL(EntityManager entityManager, String hql, Object[] obj) {
		Query q = entityManager.createQuery(hql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				q.setParameter(i, obj[i]);
			}
		}
		List ret = q.getResultList();
		return ret;
	}

	public int executeUpdateByHQL(EntityManager entityManager, String hql, Object[] obj) {
		Query q = entityManager.createQuery(hql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				q.setParameter(i, obj[i]);
			}
		}
		int ret = q.executeUpdate();
		return ret;
	}

}
