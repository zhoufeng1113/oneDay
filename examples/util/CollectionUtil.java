package com.homevip.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * 集合工具
 * @author ll&pan
 */
public class CollectionUtil {

	/**
	 * 取实体编号集
	 * @param entitys 实体集
	 * @return
	 */
	public static <T> List<Integer> getEntityIds(Collection<T> entitys) {
		
		if (null == entitys || entitys.size() < 1) return null;

		PropertyDescriptor pd = null;
		List<Integer> ids = new ArrayList<Integer>(entitys.size());
		for (T t : entitys) {
			
			if (null == pd) pd = BeanUtils.getPropertyDescriptor(t.getClass(), "id");
			try {
				ids.add((Integer) pd.getReadMethod().invoke(t));
			} 
			catch (Exception e) {
				return null;
			}
		}
		return ids;
	}

	/**
	 * 取实体编号集
	 * @param entitys 实体集
	 * @param property 编号属性名
	 * @return
	 */
	public static <T> List<Integer> getEntityIds(Collection<T> entitys, String property) {
		
		if (null == entitys || entitys.size() < 1) return null;

		PropertyDescriptor pd = null;
		List<Integer> ids = new ArrayList<Integer>(entitys.size());
		for (T t : entitys) {
			
			if (null == pd) pd = BeanUtils.getPropertyDescriptor(t.getClass(), property);
			try {
				ids.add((Integer) pd.getReadMethod().invoke(t));
			} 
			catch (Exception e) {
				return null;
			}
		}
		return ids;
	}
	
	/**
	 * 取实体编号串
	 * @param entitys 实体集
	 * @return 编号串，以逗号分隔
	 */
	public static <T> String getEntityIdString(Collection<T> entitys) {
		
		if (null == entitys || entitys.size() < 1) return null;

		PropertyDescriptor pd = null;
		StringBuilder sb = new StringBuilder(entitys.size() * 8);
		for (T t : entitys) {
			
			if (null == pd) pd = BeanUtils.getPropertyDescriptor(t.getClass(), "id");
			try {
				
				if (sb.length() > 0) sb.append(',');
				sb.append(pd.getReadMethod().invoke(t).toString());
			} 
			catch (Exception e) {
				return null;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 取实体编号串
	 * @param entitys 实体集
	 * @param property 编号属性名
	 * @return 编号串，以逗号分隔
	 */
	public static <T> String getEntityIdString(Collection<T> entitys, String property) {
		
		if (null == entitys || entitys.size() < 1) return null;

		PropertyDescriptor pd = null;
		StringBuilder sb = new StringBuilder(entitys.size() * 8);
		for (T t : entitys) {
			
			if (null == pd) pd = BeanUtils.getPropertyDescriptor(t.getClass(), property);
			try {
				
				if (sb.length() > 0) sb.append(',');
				sb.append(pd.getReadMethod().invoke(t).toString());
			} 
			catch (Exception e) {
				return null;
			}
		}
		return sb.toString();
	}

	/**
	 * 判断是否空值
	 * @param ls 列表对象
     * @return
     */
	public static <T> boolean isEmpty(List<T> ls){
		if(null != ls && ls.size() > 0){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 合拼列表，过滤重复
	 * @param source 合拼源列表
	 * @param target 合拼后目标列表
	 */
	public static <T> List<T> merge(List<T> source, List<T> target){
		if(null== target) return null;
		if(!CollectionUtil.isEmpty(source)){
			for(T e : source){
				if(!target.contains(e)){
					target.add(e);
				}
			}
		}

		return target;
	}

	/**
	 * 合拼列表
	 * @param args 合拼源列表
	 */
	public static <T> List<T> mergeTo(List<T> ...args){
		List<T> target = new ArrayList<T>();
		for(List<T> arg : args){
			if(!isEmpty(arg)){
				target.addAll(arg);
			}
		}

		return target;
	}

	/**
	 * 转换为列表
	 * @param args
	 * @param <T>
     * @return
     */
	public static <T> List<T> asList(T ...args) {
		List<T> ols = new ArrayList<T>();
		for(T arg : args){
			ols.add(arg);
		}
		return ols;
	}
}
