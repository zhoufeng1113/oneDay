package com.homevip.util.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 * 封装HttpRequest.getParameter对参数进行HTML转义编码，预防参数输入型的XXS
 * 
 */
public class XxsGuardRequest extends HttpServletRequestWrapper {

	public XxsGuardRequest(HttpServletRequest request) {
		super(request);
	}


	@Override
	public String getParameter(String name) {
		if(name==null || name.length()==0) return null;
		return intercept(super.getParameter(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (null == values || 0 == values.length) {
			return values;
		}
		if (1 == values.length) {
			String ret = intercept(values[0]);
			if (ret == values[0]) {
				return values;
			}
			return new String[] { ret };
		}
		String[] result = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = intercept(values[i]);
		}
		return result;
	}

	/**
	 * 拦截XXS输入参数
	 * 
	 * @param content
	 *            参数内容
	 * @return 拦截处理后的内容
	 */
	protected String intercept(String content) {
		return rigid(content);
	}

	/**
	 * 严格过滤HTML标签
	 * 
	 * @param content
	 *            内容
	 * @return 过滤后的内容
	 */
	public static String rigid(String content) {
		if (null == content || content.length() < 1) {
			return content;
		}
		StringBuilder sb = new StringBuilder(content.length() + ((2 * 3) - 2));
		for (int i = 0; i < content.length(); i++) {
			int ch = content.charAt(i);
			if (0x3e == ch) { // >
				sb.append("&gt;");
			} else if (0x3c == ch) { // <
				sb.append("&lt;");
			} else if (0x22 == ch) { //"
				sb.append("&#34;");	
			} else if (0x27 == ch) { //'
				sb.append("&#39;");		
			} else if ('&' == ch) { //&
				sb.append("&amp;");
			} else {
				sb.append(content.charAt(i));
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 只是宽松过滤包含javascript的HTML标签，有漏掉的可能
	 * 
	 * TODO 未实现
	 * 
	 * @param content
	 *            内容
	 * @return 过滤后的内容
	 */
	public static String loose(String content) {
		return content;
	}

}


