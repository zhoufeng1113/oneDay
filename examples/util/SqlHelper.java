package com.homevip.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * sql方法。
 */
public class SqlHelper {
	private static final Log _Log = LogFactory.getLog(SqlHelper.class);

	/**
	 * 返回合法SQL输入,带引号。 创建日期：(2004-1-8 17:12:02)
	 * 
	 * @return java.lang.String
	 */
	public static String toSql(String str) {
		StringBuffer szTemp = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
			case '\'':
				szTemp.append("''");
				break;
			case '\"':
				szTemp.append("\"\"");
				break;
			default:
				szTemp.append(str.charAt(i));
			}
		}
		return "'" + szTemp.toString() + "'";
	}

	/**
	 * 返回合法SQL输入。 创建日期：(2004-1-8 17:12:02)
	 * 
	 * @return java.lang.String
	 */
	public static String toSqlLike(String str) {
		return toSql("%"+str+"%");
	}
	
	/**
	 * 返回合法SQL输入--Dataset类专用。 创建日期：(2004-1-8 17:12:02)
	 * 
	 * @return java.lang.String
	 */
	public static String toDataset(String str) {
		StringBuffer szTemp = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
			case '\'':
				szTemp.append("''");
				break;
			default:
				szTemp.append(str.charAt(i));
			}
		}
		return szTemp.toString();
	}

	/**
	 * 返回合法sqllike，
	 * 
	 * @return java.lang.String
	 */
	public static String toJpqlParamLike(String str) {
		return "%"+str+"%";
	}

	/**
	 * 替换查询类型（sql方法）
	 * @param sql
	 * @param repalcestr
	 * @return
	 */
	public static String replaceQL(String sql ,String repalcestr){
		int stIndex=sql.lastIndexOf("from");
		if(stIndex < 0) stIndex=sql.lastIndexOf("FROM");
		if(stIndex>=0) {
			String endstr = sql.substring(stIndex, sql.length());
			String ststr = "select ";
			String resultstr = "";
			resultstr=ststr+repalcestr+" "+endstr;
			return resultstr;
		}else{
			return "";
		}

	}

	/**
	 * 返回count语句，暂支持简单的
	 * @param sql
	 * @param col
     * @return
     */
	public static String createCountQL(String sql, String col){
		return replaceQL(sql, "count(" + col + ")");
	}

	/**
	 * 返回sum语句，暂支持简单的
	 * @param sql
	 * @param col
	 * @return
	 */
	public static String createSumQL(String sql, String col){
		return replaceQL(sql, "sum(" + col + ")");
	}

}