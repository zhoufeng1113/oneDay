package com.homevip.util;

import static org.springframework.beans.BeanUtils.getPropertyDescriptor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.homevip.base.entity.Base_workstation;
import com.homevip.core.page.Sort;
import com.homevip.util.date.DateTimeUtil;

/**
 * sql工具  (未完成)
 * Created by pigy  on 2016/7/7.
 */
public class SqlUtil {
    public static final Integer NO_WHERE = 1;
    //select
    private StringBuffer _select = null;
    //from
    private StringBuffer _from = null;
    //condition
    private StringBuffer _condition = null;
    //sort
    private Sort _sort = null;
    //attributes (select 的属性列表)
    private List<String> _attributes = null;
    private String limit = null;
    private String groupBy = null;
    private String having = null;
    //初始化




    public SqlUtil() {
        _select = new StringBuffer();
        _from = new StringBuffer();
        _condition = new StringBuffer();
        _attributes = new ArrayList<String>();
    }

    //设置排序
    public void setSort(Sort sort) {
        _sort = sort;
    }

    //添加select

    /**
     * 添加查询字段
     *
     * @param filedName 字段名  eg:  d.memberid
     * @param alias     别名    eg:  memberId
     * @param hasBreak  是否拼接分割符    eg:  ,
     */
    public SqlUtil select(String filedName, String alias, boolean hasBreak) {
        if(_select.toString().length() == 0 && _select.indexOf("select") <= -1){
            _select.append(" select ");
        }
        _select.append(" " + filedName + " ");
        if (null != alias && alias.length() > 0) {
            _select.append(" as ").append(alias);
            _attributes.add(alias);
        }else{
            _attributes.add(filedName);
        }
        if (hasBreak) {
            _select.append(" , ");
        }
        return this;
    }


    /**
     * 主表
     *
     * @param tableName
     * @param alias     别名
     */
    public SqlUtil from(String tableName, String alias) {
        _from.append(" from " + tableName);
        if (alias != null && alias.length() > 0) {
            _from.append(" " + alias + " ");
        }else{
            _from.append(" d ");
        }
        return this;
    }

    /**
     * 关联表
     *
     * @param tableName 表名
     * @param alias     别名
     * @param condition 关联条件
     */
    public SqlUtil leftJoin(String tableName, String alias, String condition) {
        _from.append(" left join ").append(tableName);
        if (alias != null && alias.length() > 0) {
            _from.append(" " + alias + " ");
        }
        _from.append(" on " + condition + " ");

        return this;
    }

    /**
     * 条件拼接  and
     * 备注：属性值自动判断空，字符串值自动添加单引号
     *
     * @param attrName
     * @param value
     * @return
     */
    public SqlUtil and(String attrName, Object value) {
        if (StringUtil.isEmpty(attrName) || null == value) {
            return this;
        }
        if (value instanceof String) {
            _condition.append(" and " + attrName + " = ");
            String val = (String) value;
            //已经存在单引号的不需要添加单引号，否则自动添加单引号
            if (val.indexOf("\'") > -1) {
                _condition.append(val + " ");
            }
            //带#号的字符串不需要进行单引号添加  场景应用 and exists( select id from member where 1= 1 and id = d.memberid)
            else if(val.indexOf("#") > -1){
                val = val.substring(val.indexOf("#")+1);
                _condition.append(val + " ");
            }
            else {
                _condition.append(SqlHelper.toSql(val) + " ");
            }
        } else if (value instanceof Integer) {
            _condition.append(" and " + attrName + " = ");
            _condition.append(value + " ");
        }
        return this;
    }

    /**
     * 条件拼接  and  (可控操作符)
     * 备注：属性值自动判断空，字符串值自动添加单引号
     *
     * @param attrName
     * @param value
     * @return
     */
    public SqlUtil and(String attrName, String op, Object value) {
        if (StringUtil.isEmpty(attrName) || StringUtil.isEmpty(op) || null == value) {
            return this;
        }
        if (value instanceof String) {
            _condition.append(" and " + attrName + " " + op + " ");
            String val = (String) value;
            //已经存在单引号的不需要添加单引号，否则自动添加单引号
            if (val.indexOf("\'") > -1) {
                _condition.append(value + " ");
            } else {
                _condition.append(SqlHelper.toSql((String) value) + " ");
            }
        } else if (value instanceof Integer) {
            _condition.append(" and " + attrName + " " + op + " ");
            _condition.append(value + " ");
        }
        return this;
    }

    /**
     * 条件拼接  and
     * 备注：属性值自动判断空，字符串值自动添加单引号
     *
     * @param attrName
     * @param value
     * @param and  false 不拼接and    true 拼接and
     * @return
     */
    public SqlUtil and(String attrName, Object value,boolean and) {
        if (StringUtil.isEmpty(attrName) || null == value) {
            return this;
        }
        if (value instanceof String) {
            _condition.append((and?" and ":"") + attrName + " = ");
            String val = (String) value;
            //已经存在单引号的不需要添加单引号，否则自动添加单引号
            if (val.indexOf("\'") > -1) {
                _condition.append(val + " ");
            }
            //带#号的字符串不需要进行单引号添加  场景应用 and exists( select id from member where 1= 1 and id = d.memberid)
            else if(val.indexOf("#") > -1){
                val = val.substring(val.indexOf("#")+1);
                _condition.append(val + " ");
            }
            else {
                _condition.append(SqlHelper.toSql(val) + " ");
            }
        } else if (value instanceof Integer) {
            _condition.append((and?" and ":"")  + attrName + " = ");
            _condition.append(value + " ");
        }
        return this;
    }

    /**
     * 条件拼接  and  (可控操作符)
     * 备注：属性值自动判断空，字符串值自动添加单引号
     *
     * @param attrName
     * @param value
     * @param and  false 不拼接and    true 拼接and
     * @return
     */
    public SqlUtil and(String attrName, String op, Object value,boolean and) {
        if (StringUtil.isEmpty(attrName) || StringUtil.isEmpty(op) || null == value) {
            return this;
        }
        if (value instanceof String) {
            _condition.append((and?" and ":"")  + attrName + " " + op + " ");
            String val = (String) value;
            //已经存在单引号的不需要添加单引号，否则自动添加单引号
            if (val.indexOf("\'") > -1) {
                _condition.append(value + " ");
            } else {
                _condition.append(SqlHelper.toSql((String) value) + " ");
            }
        } else if (value instanceof Integer) {
            _condition.append((and?" and ":"")  + attrName + " " + op + " ");
            _condition.append(value + " ");
        }
        return this;
    }



    /**
     * 直接拼接sql
     *
     * @param sql
     * @return
     */
    public SqlUtil and(String sql) {
        if (StringUtil.isEmpty(sql)) {
            return this;
        }
        _condition.append(" and ").append(sql);
        return this;
    }


    public SqlUtil andLike(String attrName,Object value){
        return and(attrName,"like",SqlHelper.toSqlLike((String) value));
    }
    public SqlUtil andNotLike(String attrName,Object value){
        return and(attrName," not like",SqlHelper.toSqlLike((String) value));
    }



    public SqlUtil andIn(String attrName, Object... values) {
        if (null == attrName || attrName.length() < 1 || null == values || values.length < 1) return this;
        _condition.append(" and " + attrName + " in (");
        for (int i = 0, len = values.length; i < len; i++) {
            Object item = values[i];
            if(item instanceof String){
                if(((String) item).indexOf("\'") > -1){
                    _condition.append(item);
                }else{
                    _condition.append(SqlHelper.toSql((String) item));
                }
            }else if(item instanceof Integer){
                _condition.append(item);
            }
            //传入了int数组而不是Integer数组的时候
            else if(item.getClass().getTypeName().equals("int[]")){
                int[] tempItem = (int[])item;
                for (int j = 0, lens = tempItem.length; j < lens; j++) {
                    _condition.append(tempItem[j]);
                    if(j+1<lens){
                        _condition.append(" , ");
                    }
                }
            }

            if(i+1<len){
                _condition.append(" , ");
            }
        }
        _condition.append(")");
        return this;
    }

    public SqlUtil andIn(String attrName, List<Integer> listId) {
        if (null == attrName || attrName.length() < 1 || null == listId || listId.size() < 1) return this;
        _condition.append(" and " + attrName + " in (");
        for (int i = 0, len = listId.size(); i < len; i++) {
            Object item = listId.get(i);
            if(item instanceof Integer){
                _condition.append(item);
            }
            if(i+1<len){
                _condition.append(" , ");
            }
        }
        _condition.append(")");
        return this;
    }

    public SqlUtil andIn(String attrName,SqlUtil sqlUtil){
        if (null == attrName || attrName.length() < 1 || null == sqlUtil ) return this;
        _condition.append(" and " + attrName + " in (").append(sqlUtil.toSql()).append(" ) ");
        return this;
    }

    public SqlUtil andNotIn(String attrName, Object... values) {
        if (null == attrName || attrName.length() < 1 || null == values || values.length < 1) return this;
        _condition.append(" and " + attrName + " not in (");
        for (int i = 0, len = values.length; i < len; i++) {
            Object item = values[i];
            if(item instanceof String){
                if(((String) item).indexOf("\'") > -1){
                    _condition.append(item);
                }else{
                    _condition.append(SqlHelper.toSql((String) item));
                }
            }else if(item instanceof Integer){
                _condition.append(item);
            }
            if(i+1<len){
                _condition.append(" , ");
            }
        }
        _condition.append(")");
        return this;
    }

    /**
     * 条件拼接  or  (可控操作符)
     * 备注：属性值自动判断空，字符串值自动添加单引号
     *
     * @param attrName
     * @param value
     * @return
     */
    public SqlUtil or(String attrName, String op, Object value) {
        if (StringUtil.isEmpty(attrName) || StringUtil.isEmpty(op) || null == value) {
            return this;
        }
        if (value instanceof String) {
            _condition.append(" or " + attrName + " " + op + " ");
            String val = (String) value;
            //已经存在单引号的不需要添加单引号，否则自动添加单引号
            if (val.indexOf("\'") > -1) {
                _condition.append(value + " ");
            } else {
                _condition.append(SqlHelper.toSql((String) value) + " ");
            }
        } else if (value instanceof Integer) {
            _condition.append(" or " + attrName + " " + op + " ");
            _condition.append(value + " ");
        }
        return this;
    }


    /**
     * 条件拼接  or
     * 备注：属性值自动判断空，字符串值自动添加单引号
     *
     * @param attrName
     * @param value
     * @return
     */
    public SqlUtil or(String attrName, Object value) {
        if (StringUtil.isEmpty(attrName) || null == value) {
            return this;
        }
        if (value instanceof String) {
            _condition.append(" or " + attrName + " = ");
            String val = (String) value;
            //已经存在单引号的不需要添加单引号，否则自动添加单引号
            if (val.indexOf("\'") > -1) {
                _condition.append(value + " ");
            } else {
                _condition.append(SqlHelper.toSql((String) value) + " ");
            }
        } else if (value instanceof Integer) {
            _condition.append(" or " + attrName + " = ");
            _condition.append(value + " ");
        }
        return this;
    }

    public SqlUtil orNotIn(String attrName, Object... values) {
        if (null == attrName || attrName.length() < 1 || null == values || values.length < 1) return this;
        _condition.append(" or " + attrName + " not in (");
        for (int i = 0, len = values.length; i < len; i++) {
            Object item = values[i];
            if(item instanceof String){
                if(((String) item).indexOf("\'") > -1){
                    _condition.append(item);
                }else{
                    _condition.append(SqlHelper.toSql((String) item));
                }
            }else if(item instanceof Integer){
                _condition.append(item);
            }
            if(i+1<len){
                _condition.append(" , ");
            }
        }
        _condition.append(")");
        return this;
    }

    public SqlUtil orIn(String attrName, Object... values) {
        if (null == attrName || attrName.length() < 1 || null == values || values.length < 1) return this;
        _condition.append(" or " + attrName + " in (");
        for (int i = 0, len = values.length; i < len; i++) {
            Object item = values[i];
            if(item instanceof String){
                if(((String) item).indexOf("\'") > -1){
                    _condition.append(item);
                }else{
                    _condition.append(SqlHelper.toSql((String) item));
                }
            }else if(item instanceof Integer){
                _condition.append(item);
            }
            if(i+1<len){
                _condition.append(" , ");
            }
        }
        _condition.append(")");
        return this;
    }

    public SqlUtil orLike(String attrName,Object value){
        return or(attrName,"like",SqlHelper.toSqlLike((String) value));
    }
    public SqlUtil orNotLike(String attrName,Object value){
        return or(attrName," not like",SqlHelper.toSqlLike((String) value));
    }

    /**
     * and exists ( select * from xxxx  where memberid = d.id)
     * @param sqlUtil
     * @return
     */
    public SqlUtil andExists(SqlUtil sqlUtil){
        if(null == sqlUtil) return this;
        _condition.append(" and exists( ").append(sqlUtil.toSql()).append(" ) ");
        return  this;
    }
    /**
     * and exists ( select * from xxxx  where memberid = d.id)
     * @param sql
     * @return
     */
    public SqlUtil andExists(String sql){
        if(StringUtil.isEmpty(sql)) return this;
        _condition.append(" and exists( ").append(sql).append(" ) ");
        return  this;
    }

    /**
     * and ( xxxxxxx )   eg:  and (status = 2 or status =3 or status = 7)
     * @param sqlUtil
     * @return
     */
    public SqlUtil and(SqlUtil sqlUtil){
        if(null == sqlUtil) return this;
        _condition.append(" and ( ").append(sqlUtil.toSql()).append(" ) ");
        return  this;
    }

    public String getCountSql(String selectStr){
        String ret = StringUtil.isEmpty(selectStr)?" select count(d.id) ":selectStr;
        String condition = this._condition.toString().trim();
        if(condition.length() > 0){
            condition = "where " + condition.substring(3, condition.length());
        }
        ret += this._from.toString()+condition;
        if(!StringUtil.isEmpty(groupBy)){
            ret+= groupBy;
        }
        if(!StringUtil.isEmpty(having)){
            ret+= having;
        }
        if (null != _sort) {
            ret += _sort.toQL();
        }
        if(!StringUtil.isEmpty(limit)){
            ret+= limit;
        }
        return ret;
    }


    /**
     * 转sql
     *
     * @return
     */
    public String toSql() {
        String condition = this._condition.toString().trim();
        if(condition.length() > 0){
            condition = "where " + condition.substring(3, condition.length());
        }
        String ret = _select.toString() + _from + condition;
        if(!StringUtil.isEmpty(groupBy)){
            ret+= groupBy;
        }
        if(!StringUtil.isEmpty(having)){
            ret+= having;
        }
        if (null != _sort) {
            ret += _sort.toQL();
        }
        if(!StringUtil.isEmpty(limit)){
            ret+= limit;
        }
        return ret;
    }
    public String getSelectSql(){
        return _select!= null ?_select.toString():null;
    }

    /**
     * 获取select属性字符串 (以逗号分隔)
     * @return
     */
    public String getAttributesStr(){
        return _attributes!= null ?StringUtil.list2Str(_attributes,","):null;
    }

    public void setLimit(Integer offset,int rows){
        limit = " limit "+((null== offset|| offset ==0)?"":offset)+" "+rows;
    }

    /**
     * group by
     * @param s
     */
    public void groupBy(String s) {
        if(StringUtil.isEmpty(s)){
            return;
        }
        groupBy = " group by "+s;
    }
    /**
     * having
     * @param s
     */
    public void having(String s) {
        if(StringUtil.isEmpty(s)){
            return;
        }
        having = " having "+s;
    }
    //原生结果集转换到bean
    public static <T> List<T> convertToBean(List resultList, Class<T> mClass) {
        if(CollectionUtil.isEmpty(resultList)){
            return null;
        }
        List<Map<String, Object>> items = resultList;
        T entity = null;

        ArrayList<T> datas = new ArrayList<T>(items.size());
        for (Map<String, Object> item : items) {
            Set<String> keysName = item.keySet();
            PropertyDescriptor pd;
            Method m;
            try {
                entity = mClass.newInstance();
                for(String key : keysName){
                    pd = getPropertyDescriptorWithRule(entity.getClass(), key);
                    if(pd == null){
                        continue;
                    }
                    boolean targetIsLong = (pd.getPropertyType() == Long.class || pd.getPropertyType() == long.class);
                    boolean targetIsInteger = (pd.getPropertyType() == Integer.class || pd.getPropertyType() == int.class);
                    boolean targetIsString = (pd.getPropertyType() == String.class);
                    boolean targetIsDouble = (pd.getPropertyType() == Double.class || pd.getPropertyType() == double.class);

                    Object value = null;
                    if(item.get(key) == null ){
                        if(targetIsInteger){
                            value = 0;
                        }
                        else if(targetIsDouble){
                            value=0d;
                        }
                        else if(targetIsString){
                            value = null;
                        }
                        if(targetIsLong) {
                        	value = 0L;
                        }
                    }else{
                        if(item.get(key) instanceof BigInteger && targetIsLong){
                            value = ((BigInteger) item.get(key)).longValue();
                        }
                        else if(item.get(key) instanceof BigInteger && targetIsInteger){
                            value = ((BigInteger) item.get(key)).intValue();
                        }
                        else if(item.get(key) instanceof Double && targetIsInteger){
                            value = ((Double) item.get(key)).intValue();
                        }
                        else if(item.get(key) instanceof BigInteger && targetIsDouble){
                            value = ((BigInteger) item.get(key)).doubleValue();
                        }else{
                            value = item.get(key);
                        }
                    }
                    m = pd.getWriteMethod();
                    m.invoke(entity, value);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            datas.add(entity);
        }
        return datas;
    }

    //原生结果集转换到bean
    public static <T> T convertToSingleBean(Object result, Class<T> mClass) {
        if(null == result){
            return null;
        }
        Map<String,Object> item = (Map<String,Object>)result;
        T entity = null;
        Set<String> keysName = item.keySet();
        PropertyDescriptor pd;
        Method m;
        try {
            entity = mClass.newInstance();
            for(String key : keysName){

                pd = getPropertyDescriptorWithRule(entity.getClass(), key);

                if(pd == null){
                    continue;
                }
                boolean targetIsLong = (pd.getPropertyType() == Long.class || pd.getPropertyType() == long.class);
                boolean targetIsInteger = (pd.getPropertyType() == Integer.class || pd.getPropertyType() == int.class);
                boolean targetIsString = (pd.getPropertyType() == String.class);
                boolean targetIsDouble = (pd.getPropertyType() == Double.class || pd.getPropertyType() == double.class);


                Object value = null;
                if(item.get(key) == null ){
                	if(targetIsInteger){
                        value = 0;
                    }
                    else if(targetIsDouble){
                        value=0d;
                    }
                    else if(targetIsString){
                        value = null;
                    }
                    if(targetIsLong) {
                    	value = 0L;
                    }
                }else{
                    if(item.get(key) instanceof BigInteger && targetIsLong){
                        value = ((BigInteger) item.get(key)).longValue();
                    }
                    else if(item.get(key) instanceof BigInteger && targetIsInteger){
                        value = ((BigInteger) item.get(key)).intValue();
                    }
                    else if(item.get(key) instanceof BigInteger && targetIsDouble){
                        value = ((BigInteger) item.get(key)).doubleValue();
                    }else{
                        value = item.get(key);
                    }
                }
                m = pd.getWriteMethod();
                m.invoke(entity, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    private static PropertyDescriptor getPropertyDescriptorWithRule(Class clazz, String propertyName) {
        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
        if(pd == null){
            String[] pStr = propertyName.split("_");
            String retStr = "";
            //如果长度大于1说明存在下划线，根据规则将下划线后面的英文字母变成大写，同时删除下划线
            if(pStr.length > 1){
                for(int i = 1;i<pStr.length;i++){
                    String temp = pStr[i];
                    String prefix = temp.substring(0,1);
                    prefix = prefix.toUpperCase();
                    retStr+= prefix+temp.substring(1);
                }
                return getPropertyDescriptor(clazz, pStr[0]+retStr);
            }
        }
        return  pd;
    }


    public static void main(String[] as) {
        SqlUtil sqlUtil = new SqlUtil();
        //select
        sqlUtil.select("d.memberid", null, true)
                .select("d.cname", null, true)
                .select("d.phone", null, false);
        System.out.println("select语句: "+sqlUtil.toSql());
        //表
        sqlUtil = new SqlUtil();
        sqlUtil.from("member","d")
                .leftJoin("base_memberlevel", "bl", "d.levelid = bl.id")
                .leftJoin("base_workstation", "bw", "d.workstationid = bw.id");

        System.out.println("from部分: " + sqlUtil.toSql());

        //count
        System.out.println("count用法："+sqlUtil.getCountSql(null));

        sqlUtil = new SqlUtil();
        //where    eg: and d.memberid = 23 and d.createdate = '2016-07-07'
        //自动判断空,如果属性值为空,自动忽略
        sqlUtil.and("d.memberid", 23)
                .and("d.createdate", ">=", DateTimeUtil.getCurDate())
                .andNotLike("bw.debug ",Base_workstation.ISDEBUG)
                .andIn("d.status",new String[]{"1","2"})
                .andNotIn("d.status",new Integer[]{1,2})
                .or("d.cname","admin")
                .orLike("d.cname","admin");
        System.out.println("基本条件查询用法: "+sqlUtil.toSql());

        sqlUtil = new SqlUtil();
        //特殊用法
        //第一种 and existes （select xxxxx from  xxxx where xxxx）
        SqlUtil innerSql = new SqlUtil();
        innerSql.select("bl.id",null,false).from("base_memberlevel","bl").and("bl.id", "#d.levelid");
        sqlUtil.andExists(innerSql);
        System.out.println("特殊用法1："+sqlUtil.toSql());

        //第二种  and d.levelid in (select xxxxx from  xxxx where xxxx)
        sqlUtil = new SqlUtil();
        innerSql = new SqlUtil();
        innerSql.select("bl.id",null,false).from("base_memberlevel","bl").and("bl.id", "#d.levelid");
        sqlUtil.andIn("d.levelid",innerSql);
        System.out.println("特殊用法2："+sqlUtil.toSql());

        //设置排序
        sqlUtil.setSort(new Sort("d.cratedate", false));
        System.out.println("特殊用法3(带排序)：" + sqlUtil.toSql());

    }


}
