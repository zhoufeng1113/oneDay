package com.homevip.util.em;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {
	public static String[] chars = new String[]{
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
			};
	
	public static String buildKey(int length){
		if(length == 0){
			return null;
		}
		String rs = "";
		Random r = new Random();
		for(int i = 0; i < length; i++){
			int rInnt = r.nextInt(chars.length);
			rs += chars[rInnt];
		}
		return rs;
	}
	
	public static int parseInt(Object data){
		if(isBlank(data)){
			return 0;
		}
		try{
			String[] dataArr = data.toString().split("\\.");
			if(dataArr == null || dataArr.length == 0){
				dataArr = data.toString().split(".");
			}
			if(dataArr.length > 0){
				return Integer.parseInt(dataArr[0]);
			}else{
				return 0;
			}
		}catch(Exception e){
			return 0;
		}
	}
	public static long parseLong(Object data){
		if(isBlank(data)){
			return 0;
		}
		try{
			String[] dataArr = data.toString().split("\\.");
			if(dataArr == null || dataArr.length == 0){
				dataArr = data.toString().split(".");
			}
			if(dataArr.length > 0){
				return Long.parseLong(dataArr[0]);
			}else{
				return 0;
			}
		}catch(Exception e){
			return 0;
		}
	}
	public static float parseFloat(Object data){
		if(isBlank(data)){
			return 0;
		}
		try{
			return Float.parseFloat(data.toString());
		}catch(Exception e){
			return 0;
		}
	}
	public static double parseDouble(Object data){
		if(isBlank(data)){
			return 0;
		}
		try{
			return Double.parseDouble(data.toString());
		}catch(Exception e){
			return 0;
		}
	}
	
	public static String obj2Str(Object o) {
		String result = (o == null || "null".equalsIgnoreCase(o.toString())) ? null : String.valueOf(o);
		return result;
	}
	
	public static String arr2Str(Object[] data, String split){
		if(data == null || data.length == 0){
			return null;
		}
		ObjectPool op = ObjectPool.getInstance();
		StringBuilder rs = op.getStringBuilder();
		for(Object item : data){
			rs.append(obj2Str(item));
			rs.append(split);
		}
		if(rs.length() != 0){
			rs.delete(rs.length() - split.length(), rs.length());
		}
		String rsStr = rs.toString();
		op.ccStringBuilder(rs);
		return rsStr;
	}
	
	public static String list2Str(List data, String split){
		if(data == null || data.size() == 0){
			return null;
		}
		ObjectPool op = ObjectPool.getInstance();
		StringBuilder rs = op.getStringBuilder();
		for(Object item : data){
			rs.append(obj2Str(item));
			rs.append(split);
		}
		if(rs.length() != 0){
			rs.delete(rs.length() - split.length(), rs.length());
		}
		String rsStr = rs.toString();
		op.ccStringBuilder(rs);
		return rsStr;
	}
	
	public static String[] str2Arr(String source, String split){
		if(source == null){
			return null;
		}
		if("".equals(source.trim())){
			return new String[]{source};
		}
		String[] arr = source.split(split);
		ObjectPool op = ObjectPool.getInstance();
		List<String> strList = op.getStringList();
		for(String item : arr){
			if("".equals(item.trim())){
				continue;
			}
			if("null".equalsIgnoreCase(item.trim())){
				continue;
			}
			strList.add(item);
		}
		String[] rs = new String[strList.size()];
		strList.toArray(rs);
		op.ccStringList(strList);
		return rs;
	}
	
	public static String limitStr(String source, int limit){
		if(source == null || "".equals(source.trim())){
			return "";
		}
		if(source.length() <= limit){
			return source;
		}
		return source.substring(0, limit) + "...";
	}
	/**
	 * 首字母大写
	 * 
	 * @param src
	 * @return 处理后的字符串
	 */
	public static String fu(String src) {
		if(src == null || src.length()== 0){
			return "";
		}
		return src.substring(0, 1).toUpperCase()
				+ src.substring(1, src.length());
	}

	/**
	 * 全部大写
	 * 
	 * @param src
	 * @return 处理后的字符串
	 */
	public static String au(String src) {
		if(src == null || src.length() == 0){
			return "";
		}
		return src.toUpperCase();
	}
	
	/**
	 * 全部小写
	 * 
	 * @param src
	 * @return 处理后的字符串
	 */
	public static String al(String src) {
		if(src == null || src.length() == 0){
			return "";
		}
		return src.toLowerCase();
	}

	/**
	 * 去除下划线并下一个字母大写
	 * 
	 * @param src
	 * @return
	 */
	public static String rul(String src) {
		if(src == null || src.length() == 0){
			return "";
		}
		int index = src.indexOf("_");
		while(index != -1){
			src = src.substring(0, index)
				+ fu(src.substring(++index, src.length()));
			index = src.indexOf("_");
		}
		return src;
	}

	/**
	 * 大写字母前加下划线，字母变为小写
	 * 
	 * @param src
	 * @return
	 */
	public static String aul(String src) {
		if(src == null || src.length() == 0){
			return "";
		}
		int c = 0;
		char tempC;
		String tempStr;
		ObjectPool op = ObjectPool.getInstance();
		StringBuilder rs = op.getStringBuilder();
		for(int  i = 0; i < src.length(); i++){
			tempC = src.charAt(i);
			c = (int)tempC;
			if(c >= 65 && c <= 90){
				tempStr = String.valueOf(tempC);
				tempStr = tempStr.toLowerCase();
				rs.append("_");
				rs.append(tempStr);
			}else{
				rs.append(tempC);
			}
		}
		String rsStr = rs.toString();
		op.ccStringBuilder(rs);
		return rsStr;
	}

	/**
	 * 去除横线并下一个字母大写
	 * 
	 * @param src
	 * @return
	 */
	public static String rml(String src) {
		if(src == null || src.length() == 0){
			return "";
		}
		int index = src.indexOf("-");
		while(index != -1){
			src = src.substring(0, index)
				+ fu(src.substring(++index, src.length()));
			index = src.indexOf("-");
		}
		return src;
	}

	/**
	 * 大写字母前加横线，字母变为小写
	 * 
	 * @param src
	 * @return
	 */
	public static String aml(String src) {
		if(src == null || src.length() == 0){
			return "";
		}
		int c = 0;
		char tempC;
		String tempStr;
		ObjectPool op = ObjectPool.getInstance();
		StringBuilder rs = op.getStringBuilder();
		for(int  i = 0; i < src.length(); i++){
			tempC = src.charAt(i);
			c = (int)tempC;
			if(c >= 65 && c <= 90){
				tempStr = String.valueOf(tempC);
				tempStr = tempStr.toLowerCase();
				rs.append("-");
				rs.append(tempStr);
			}else{
				rs.append(tempC);
			}
		}
		String rsStr = rs.toString();
		op.ccStringBuilder(rs);
		return rsStr;
	}
	
	public static String getEncoding(String source){
		String encode = null;
		String rs = null;
		try {
			encode = "ISO-8859-1";
			if (source.equals(new String(source.getBytes(encode), encode))) {
				rs = encode;
				return rs;
			}
			encode = "GB2312";
			if (source.equals(new String(source.getBytes(encode), encode))) {
				rs = encode;
				return rs;
			}
			encode = "UTF-8";
			if (source.equals(new String(source.getBytes(encode), encode))) {
				rs = encode;
				return rs;
			}
			encode = "GBK";
			if (source.equals(new String(source.getBytes(encode), encode))) {
				rs = encode;
				return rs;
			}
		} catch (Exception exception) {
		}
		return rs;
	}
	
	public static String encoding2UTF8(String source){
		if(DataUtil.isBlank(source)){
			return source;
		}
		String toEncoding = "UTF-8";
		String encoding = DataUtil.getEncoding(source);
		if(toEncoding.equals(encoding)){
			return source;
		}
		try {
			source = new String(source.getBytes(encoding), toEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return source;
	}
	
	public static boolean isBlank(Object str) {
		if(str == null){
			return true;
		}
		String thisStr = str.toString();
		int strLen = thisStr.length();
		if (strLen == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(thisStr.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isDigit(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("[+-]?[0-9]+");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isNumber(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("[+-]?[0-9]+(\\.)?[0-9]+");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isCharOrDigit(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-Z0-9]+");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isEmail(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isMobile(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("((\\+)?86-)?(0)?[0-9]{11}");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	/**
	 * 是否家庭号码
	 * @param source
	 * @return
	 */
	public static boolean isPhone(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("(((\\+)?86-)?[0-9]{3,4}-)?[0-9]{1,8}");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isLoginName(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-Z0-9_-]{4,20}");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isHtml(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("<(\\S+)(\\s[^>]*)?>[\\s\\S]*<\\/\\1\\s*>");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isIp(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])(\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){3}");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isDate(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\\d|3[0-1])");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isTime(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean isUrl(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("(\\w+):\\/\\/([^/:]+)(?::(\\d*))?(\\/[^#]*)*([#]\\w+)?");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	/**
	 * 是否邮编
	 * @param source
	 * @return
	 */
	public static boolean isPostNO(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("[0-9]{6}");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	/**
	 * 是否人民币或美元
	 * @param source
	 * @return
	 */
	public static boolean isMoney(String source){
		if(DataUtil.isBlank(source)){
			return false;
		}
		Pattern p = Pattern.compile("(￥|\\$)?[0-9]+(\\.[0-9]{1,2})?");
		Matcher m = p.matcher(source);
		return m.matches();
	}
	
	public static boolean strCompare(String source1, String source2){
		if(source1 == null && source2 == null){
			return true;
		}
		if(source1 == null || source2 == null){
			return false;
		}
		if(source1.equals(source2)){
			return true;
		}
		return false;
	}
	
	public static boolean longCompare(Long source1, Long source2){
		if(source1 == null && source2 == null){
			return true;
		}
		if(source1 == null || source2 == null){
			return false;
		}
		if(source1.longValue() == source2.longValue()){
			return true;
		}
		return false;
	}
	
	public static boolean intCompare(Integer source1, Integer source2){
		if(source1 == null && source2 == null){
			return true;
		}
		if(source1 == null || source2 == null){
			return false;
		}
		if(source1.intValue() == source2.intValue()){
			return true;
		}
		return false;
	}
	
	public static boolean doubleCompare(Double source1, Double source2){
		if(source1 == null && source2 == null){
			return true;
		}
		if(source1 == null || source2 == null){
			return false;
		}
		if(source1.doubleValue() == source2.doubleValue()){
			return true;
		}
		return false;
	}
	
	public static boolean dateCompare(Date source1, Date source2){
		if(source1 == null && source2 == null){
			return true;
		}
		if(source1 == null || source2 == null){
			return false;
		}
		if(source1.getTime() == source2.getTime()){
			return true;
		}
		return false;
	}
	
	public static void objectConvertToObject(Object source, Object target, String[][] attrs){
		if(source == null || target == null || attrs == null || attrs.length == 0){
			return;
		}
		
		for(String[] item : attrs){
			if(item == null || item.length == 0){
				continue;
			}
			String sourceAttr = item[0];
			String targetAttr = null;
			if(item.length < 2){
				targetAttr = item[0];
			}else{
				targetAttr = item[1];
			}
			String sourceMethodName = "get" + String.valueOf(sourceAttr.charAt(0)).toUpperCase() + sourceAttr.substring(1);
			String targetMethodName = "set" + String.valueOf(targetAttr.charAt(0)).toUpperCase() + targetAttr.substring(1);
			
			Object sourceValue = null;
			try {
				Method sourceMethod = source.getClass().getMethod(sourceMethodName);
				if(sourceMethod != null){
					sourceValue = sourceMethod.invoke(source);
				}
			} catch (Exception e) {
			}
			
			if(sourceValue == null){
				continue;
			}
			
			Class valueClass = sourceValue.getClass();
			Method targetMethod = null;
			try {
				targetMethod = target.getClass().getMethod(targetMethodName, valueClass);
			} catch (Exception e) {
			}
			
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
			
			if(targetMethod == null){
				continue;
			}
			
			try {
				targetMethod.invoke(target, sourceValue);
			} catch (Exception e) {
			}
		}
	}
	
	public static void objectConvertToMap(Object source, Map<String, Object> target, String[][] attrs){
		if(source == null || target == null){
			return;
		}
		
		if(attrs == null || attrs.length == 0){
			Field[] fields = source.getClass().getDeclaredFields();
			if(fields != null && fields.length != 0){
				attrs = new String[fields.length][2];
				for(int i = 0; i < fields.length; i++){
					Field fieldItem = fields[i];
					attrs[i][0] = fieldItem.getName();
					attrs[i][1] = fieldItem.getName();
				}
			}
		}
		
		if(attrs == null || attrs.length == 0){
			return;
		}
		
		for(String[] item : attrs){
			if(item == null || item.length == 0){
				continue;
			}
			String sourceAttr = item[0];
			String targetAttr = null;
			if(item.length < 2){
				targetAttr = item[0];
			}else{
				targetAttr = item[1];
			}
			String sourceMethodName = "get" + String.valueOf(sourceAttr.charAt(0)).toUpperCase() + sourceAttr.substring(1);
			
			Object sourceValue = null;
			try {
				Method sourceMethod = source.getClass().getMethod(sourceMethodName);
				if(sourceMethod != null){
					sourceValue = sourceMethod.invoke(source);
				}
			} catch (Exception e) {
			}
			
			target.put(targetAttr, sourceValue);
		}
		
	}
	
	public static void main(String[] args){
//		String data = "中文中文中文中文中文中文中文";
//		System.out.println(limitStr(data, 3));
		
		/*TestEntity e1 = new TestEntity();
		e1.setName("zzj");
		e1.setAge(32);
		e1.setDate(new Date());
		
		TestEntity e2 = new TestEntity();
		
		String[][] attrs = {{"name"},{"age"},{"date"}};
		DataUtil.objectConvertToObject(e1, e2, attrs);
		
		System.out.println(e2.getName()+ "==" + e2.getAge() + "===" + e2.getDate());
		*/
	}
}
