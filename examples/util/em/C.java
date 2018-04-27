package com.homevip.util.em;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 对比条件封装类
 * 
 * @author 钟智军
 * @version 1.0
 */
public class C {
	/**
	 * 实体字段名
	 */
	private String entityAttribute;
	/**
	 * 对比内容
	 */
	private Object context;
	/**
	 * 是否是原始字符串
	 */
	private boolean original = false;
	/**
	 * 对比类型
	 */
	private CompareType compareType;
	/**
	 * 是否为空
	 */
	private Nullable nullable;
	/**
	 * 子条件
	 */
	private C[] conditions;
	/**
	 * 联合类型
	 */
	private UnitType unitType;
	/**
	 * 是否是联合封装类
	 */
	private boolean unit = false;
	
	public C(UnitType unitType, C...conditions){
		unit = true;
		this.unitType = unitType;
		this.conditions = conditions;
	}
	
	public C(String entityAttribute, Object context, CompareType compareType){
		this.entityAttribute = entityAttribute;
		this.context = context;
		this.compareType = compareType;
	}
	
	public C(String conditionStr){
		this.entityAttribute = conditionStr;
		this.original = true;
	}
	
	public C(String entityAttribute, Nullable nullable){
		this.entityAttribute = entityAttribute;
		this.nullable = nullable;
	}
	
	public String getEntityAttribute() {
		return entityAttribute;
	}
	public void setEntityAttribute(String entityAttribute) {
		this.entityAttribute = entityAttribute;
	}

	public CompareType getCompareType() {
		return compareType;
	}
	public void setCompareType(CompareType compareType) {
		this.compareType = compareType;
	}

	public Object getContext() {
		return context;
	}

	public void setContext(Object context) {
		this.context = context;
	}

	public boolean isUnit() {
		return unit;
	}

	public void setUnit(boolean unit) {
		this.unit = unit;
	}

	public C[] getConditions() {
		return conditions;
	}

	public void setConditions(C[] conditions) {
		this.conditions = conditions;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	public Nullable getNullable() {
		return nullable;
	}

	public void setNullable(Nullable nullable) {
		this.nullable = nullable;
	}

	public boolean isOriginal() {
		return original;
	}

	public void setOriginal(boolean original) {
		this.original = original;
	}
	
	public C[] getChildrenAndI(){
		C[] rs = null;
		if(conditions != null && conditions.length != 0){
			List<C> cs = new ArrayList<C>();
			for(C item : conditions){
				if(item == null){
					continue;
				}
				C[] itemC = item.getChildrenAndI();
				for(C itemIn : itemC){
					cs.add(itemIn);
				}
			}
			cs.add(this);
			rs = new C[cs.size()];
			rs = cs.toArray(rs);
		}else{
			rs = new C[1];
			rs[0] = this;
		}
		return rs;
	}
	
	public void reset(){
		entityAttribute = null;
		context = null;
		original = false;
		compareType = null;
		nullable = null;
		conditions = null;
		unitType = null;
		unit = false;
	}
}
