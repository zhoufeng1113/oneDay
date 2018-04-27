package com.homevip.util.em;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * 
 * 对象修改设值工具类
 * 
 * @author zzj
 *
 */
public class EntityModifyUtil {
	public static <T> void dataConvert(T source, T target, String[] forceUpdateFields, TransformInfo[] transformInfos){
		//没对象，不执行了
		if(source == null || target == null){
			return;
		}
		
		//获取对象类型和字段
		Field[] fields = source.getClass().getDeclaredFields();
		
		//没找到字段，不执行了
		if(fields.length == 0){
			return;
		}
		
		//遍历字段
		for(Field item : fields){
			String fieldName = item.getName();
			String sourceMethodName = "get" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
			String targetMethodName = "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
			
			//获取源对象数据（get）
			Object sourceValue = null;
			try {
				Method sourceMethod = source.getClass().getMethod(sourceMethodName);
				if(sourceMethod != null){
					sourceValue = sourceMethod.invoke(source);
				}
			} catch (Exception e) {
			}
			
			//判断是否空数据
			boolean isSourceValueEmpty = false;
			if(sourceValue == null || (sourceValue instanceof Number && DataUtil.parseDouble(sourceValue.toString()) == 0d)){
				isSourceValueEmpty = true;
			}
			
			//判断是否是强制更新字段
			boolean isForceUpdateField = false;
			if(forceUpdateFields != null && forceUpdateFields.length != 0){
				for(String forceUpdateFieldItem : forceUpdateFields){
					if(DataUtil.strCompare(forceUpdateFieldItem, fieldName)){
						isForceUpdateField = true;
						break;
					}
				}
			}
			
			//看是不是空数据又不是强制更新字段，如果是就忽略这个字段的操作
			if(isSourceValueEmpty && !isForceUpdateField){
				continue;
			}
			
			//获取目标方法（set）
			Class valueClass = sourceValue.getClass();
			Method targetMethod = null;
			try {
				targetMethod = target.getClass().getMethod(targetMethodName, valueClass);
			} catch (Exception e) {
			}
			
			//如果没拿到目标方法，则判断是否是原始数据类型的set方法，再尝试获取
			if(targetMethod == null){
				if(valueClass == Boolean.class){
					valueClass = boolean.class;
				}else if(valueClass == Integer.class){
					valueClass = int.class;
				}else if(valueClass == Long.class){
					valueClass = long.class;
				}else if(valueClass == Double.class){
					valueClass = double.class;
				}else if(valueClass == Short.class){
					valueClass = short.class;
				}else if(valueClass == Float.class){
					valueClass = float.class;
				}else if(valueClass == Byte.class){
					valueClass = byte.class;
				}
				try {
					targetMethod = target.getClass().getMethod(targetMethodName, valueClass);
				} catch (Exception e) {
				}
			}
			
			//还是没拿到目标方法就忽略这个字段的操作
			if(targetMethod == null){
				continue;
			}
			
			//执行目标方法
			try {
				targetMethod.invoke(target, sourceValue);
			} catch (Exception e) {
			}
		}
		
		//判断是否有关联关系的对象字段配置，如果没有任务到此为止
		if(transformInfos == null || transformInfos.length == 0){
			return;
		}
		
		//遍历配置并执行
		for(TransformInfo transformInfoItem : transformInfos){
			transformFieldData(target, transformInfoItem);
		}
	}
	
	public static <T> void transformFieldData(T entity, TransformInfo transformInfo){
		//没对象，不执行了
		if(entity == null || transformInfo == null){
			return;
		}
		
		//获取配置参数
		String codeFieldNamne = transformInfo.getCodeFieldNamne();
		String targetFieldName = transformInfo.getTargetFieldName();
		Object getDataObj = transformInfo.getGetDataObj();
		String getDataMethodName = transformInfo.getGetDataMethodName();
		
		//判断配置项有效性
		if(DataUtil.isBlank(codeFieldNamne) || DataUtil.isBlank(targetFieldName) 
				|| getDataObj == null || DataUtil.isBlank(getDataMethodName)){
			return;
		}
		
		//获取关联对象的id值
		Object codeValue = null;
		String getCodeMethodName = "get" + String.valueOf(codeFieldNamne.charAt(0)).toUpperCase() + codeFieldNamne.substring(1);
		try {
			Method getCodeMethod = entity.getClass().getMethod(getCodeMethodName);
			if(getCodeMethod != null){
				codeValue = getCodeMethod.invoke(entity);
			}
		} catch (Exception e) {
		}
		
		//判断是否能获取得到id，获取不到则忽略
		if(DataUtil.isBlank(codeValue) || (codeValue instanceof Number && DataUtil.parseLong(codeValue.toString()) == 0l)){
			return;
		}
		
		//获取加载关联对象的方法
		Class codeClass = codeValue.getClass();
		Method getDataMethod = null;
		try {
			getDataMethod = getDataObj.getClass().getMethod(getDataMethodName, codeClass);
		} catch (Exception e) {
		}
		//如果没获取到则尝试获取使用原始对象类型的方法
		if(getDataMethod == null){
			if(codeClass == Boolean.class){
				codeClass = boolean.class;
			}else if(codeClass == Integer.class){
				codeClass = int.class;
			}else if(codeClass == Long.class){
				codeClass = long.class;
			}else if(codeClass == Double.class){
				codeClass = double.class;
			}else if(codeClass == Short.class){
				codeClass = short.class;
			}else if(codeClass == Float.class){
				codeClass = float.class;
			}else if(codeClass == Byte.class){
				codeClass = byte.class;
			}
			try {
				getDataMethod = getDataObj.getClass().getMethod(getDataMethodName, codeClass);
			} catch (Exception e) {
			}
		}
		
		//还是没获取到，忽略
		if(getDataMethod == null){
			return;
		}
		
		//执行获取关联对象的方法
		Object findObj = null;
		try {
			findObj = getDataMethod.invoke(getDataObj, codeValue);
		} catch (Exception e) {
		}
		//没拿到对象，忽略
		if(findObj == null){
			return;
		}
		//获取设置对象值的方法
		String setTargetFieldMethodName = "set" + String.valueOf(targetFieldName.charAt(0)).toUpperCase() + targetFieldName.substring(1);
		Class clazz = null;
		Field targetField = null;
		Method setTargetFieldMethod = null;
		try {
			targetField = entity.getClass().getDeclaredField(targetFieldName);
			clazz = targetField==null?null:targetField.getType();
			if(clazz != null){
				setTargetFieldMethod = entity.getClass().getMethod(setTargetFieldMethodName, clazz);
			}
		} catch (Exception e) {
		}
		//没获取到，忽略
		if(setTargetFieldMethod == null){
			return;
		}
		//设置对象值
		try {
			setTargetFieldMethod.invoke(entity, findObj);
		} catch (Exception e) {
		}
	}
}
