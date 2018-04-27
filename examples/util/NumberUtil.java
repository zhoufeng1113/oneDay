package com.homevip.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * 数字类方法。
 */
public class NumberUtil {
	// 字符型字段精准2位小数加
	public static String aAdd(Double value1, String value2) {
		try{
			BigDecimal v1 = new BigDecimal(value1);
			BigDecimal v2 = new BigDecimal(value2);
			BigDecimal vret = v1.add(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
			return vret.toString();
		}catch (Exception e){
			return null;
		}
	}

	// 字符型字段精准2位小数减
	public static String aSubtract(Double value1, String value2) {
		try{
			BigDecimal v1 = new BigDecimal(value1);
			BigDecimal v2 = new BigDecimal(value2);
			BigDecimal vret = v1.subtract(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
			return vret.toString();
		}catch (Exception e){
			return null;
		}
	}

	// 字符型字段精准2位小数乘法
	public static String aMultiply(Double value1, String value2) {
		try{
			BigDecimal v1 = new BigDecimal(value1);
			BigDecimal v2 = new BigDecimal(value2);
			BigDecimal vret = v1.multiply(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
			return vret.toString();
		}catch (Exception e){
			return null;
		}
	}

	// 字符型字段精准2位小数乘法
	public static String aMultiply(String value1, String value2) {
		try{
			BigDecimal v1 = new BigDecimal(value1);
			BigDecimal v2 = new BigDecimal(value2);
			BigDecimal vret = v1.multiply(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
			return vret.toString();
		}catch (Exception e){
			return null;
		}
	}

	// 字符型字段精准2位小数除法
	public static String aDivide(Double value1, String value2) {
		try{
			BigDecimal v1 = new BigDecimal(value1);
			BigDecimal v2 = new BigDecimal(value2);
			BigDecimal vret = v1.divide(v2, 2, BigDecimal.ROUND_HALF_UP);
			return vret.toString();
		}catch (Exception e){
			return null;
		}

	}
	
	public static Double aDivide(Double value1, Double value2, int scale) {
		return aDivide(value1, value2, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static Double aDivide(Double value1, Double value2, int scale, int roundType) {
		try{
			BigDecimal v1 = new BigDecimal(value1);
			BigDecimal v2 = new BigDecimal(value2);
			BigDecimal vret = v1.divide(v2, scale, roundType);
			return vret.doubleValue();
		}catch (Exception e){
			return 0.0;
		}

	}

	/**
	 * 按最大来计算
	 *
	 * @param v
	 * @param scale
	 * @return
	 */
	public static String aCeiling(String v, int scale) {
		try{
			BigDecimal b = new BigDecimal(v);
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, BigDecimal.ROUND_CEILING).toString();
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 按四舍五入
	 *
	 * @param v
	 * @param scale
	 * @return
	 */
	public static String aRound(String v, int scale) {
		try{
			BigDecimal b = new BigDecimal(v);
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, BigDecimal.ROUND_UP).toString();
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 比较大小（BigDecimal）
	 * 结果是:
	 * -1 a<b
	 * 0 a=b
	 * 1 a>b
	 * @param a
	 * @param b
     * @return
     */
	public static int aCompareTo(String a, String b){
		try {
			BigDecimal ab = new BigDecimal(a);
			BigDecimal bb = new BigDecimal(b);
			return ab.compareTo(bb);
		}catch (Exception e){
			return -1;
		}
	}

	public static double ToDouble(String value) {
		if (value == null)
			return 0.00F;
		// 处理"，"
		String szTemp = "";
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',')
				szTemp = szTemp + value.charAt(i);
		try {
			return Double.parseDouble(szTemp);
		} catch (NumberFormatException e) {
			return 0.00;
		}
	}

	// 将长整型转换成浮点类型
	public static float toFloat(long value) {
		return (float) value;
	}

	// 将字符串转换成浮点类型，在Float.parseFloat方法上加上了对","的处理
	public static float toFloat(String value) {
		if (value == null)
			return 0.00F;
		// 处理"，"
		String szTemp = "";
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',')
				szTemp = szTemp + value.charAt(i);
		try {
			return Float.parseFloat(szTemp);
		} catch (NumberFormatException e) {
			return 0.00F;
		}
	}

	/**
	 * 四舍五入
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static float round(float v, int scale) {
		BigDecimal b = new BigDecimal(Float.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 四舍五入
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double round(double v, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 按最大来计算
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static float ceiling(float v, int scale) {
		BigDecimal b = new BigDecimal(Float.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_CEILING).floatValue();
	}

	/**
	 * 按最大来计算
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double ceiling(double v, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_CEILING).doubleValue();
	}

	public static int toInt(float value) {
		return (int) value;
	}

	public static int toInt(double value) {
		return (int) value;
	}
	
	public static int toInt(Integer value) {
		if(null == value){
			return 0;
		}else{
			return (int) value;
		}
	}

	// 将字符串转换成长整类型，在Long.parseLong方法上加上了对","的处理
	public static int toInt(String value) {
		if (value == null)
			return 0;
		// 处理"，"
		value = value.trim();
		String szTemp = "";
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',')
				szTemp = szTemp + value.charAt(i);
		try {
			double d = ToDouble(szTemp);
			return (int)d;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static int toInt(Object value){
		try{
			Integer ret = Integer.parseInt(value.toString());
			return ret.intValue();
		}
		catch (NumberFormatException e){
			return 0;
		}catch (Exception e){
			return 0;
		}
	}

	public static long toLong(float value) {
		return (long) value;
	}

	// 将字符串转换成长整类型，在Long.parseLong方法上加上了对","的处理
	public static long toLong(String value) {
		if (value == null)
			return 0L;
		// 处理"，"
		value = value.trim();
		String szTemp = "";
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',')
				szTemp = szTemp + value.charAt(i);
		try {
			return Long.parseLong(szTemp);
		} catch (NumberFormatException e) {
			return 0L;
		}
	}

	//double值保留2位小数
	public static String doubleFormat(Double d) {
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(d);
	}

	public static double doubleRemainder(double dividend,double divisor){
		return new BigDecimal(dividend%divisor).floatValue();
	}

	public static BigInteger toBigInteger(Object o) {
		if(null == o){
			return new BigInteger("0");
		}
		try{
			BigInteger ret = (BigInteger)o;
			return ret;
		}
		catch (NumberFormatException e){
			return new BigInteger("0");
		}catch (Exception e){
			return new BigInteger("0");
		}
	}

	public static Double toDouble(Object value) {
		if (value == null)
			return 0.00d;
		try {
			return (Double)value;
		} catch (NumberFormatException e) {
			return 0.00d;
		} catch (Exception e) {
			return 0.00d;
		}
	}

	/**
	 * 返回数值字符串
	 * 没有小数取整，有小数保留2位
	 * @param value
	 * @return
     */
	public static String toDecimalString(Object value){
		try {
			if(value instanceof Integer){
				return "" + ((Integer) value).intValue();
			}
			if(value instanceof Long){
				return "" + ((Long) value).intValue();
			}
			if(value instanceof Double){
				Double e = (Double) value;
				e = round(e, 2);
				String str = String.valueOf(e);
				int idx = str.indexOf(".");
				if(idx  > -1){
					String strPoint = str.substring(idx + 1, str.length());
					int point = toInt(strPoint);
					if(0 == point){
						return str.substring(0, idx);
					}else{
						return "" + round(e, 2);
					}
				}
			}
			if(value instanceof BigDecimal){
				BigDecimal e = (BigDecimal) value;
				String str = aRound(e.toString(), 2);
				int idx = str.indexOf(".");
				if(idx  > -1){
					String strPoint = str.substring(idx + 1, str.length());
					int point = toInt(strPoint);
					if(0 == point){
						return str.substring(0, idx);
					}else{
						return aRound(e.toString(), 2);
					}
				}
			}

		}catch (NumberFormatException e){
			return null;
		}catch (Exception e){
			return null;
		}
		return null;
	}

	public static void main(String[] args) {
		BigDecimal e = new BigDecimal("290.999");
		System.out.println(toDecimalString(e));
	}
}