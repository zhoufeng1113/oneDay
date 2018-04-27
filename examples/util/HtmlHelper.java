package com.homevip.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * html相关方法。
 */
public class HtmlHelper {
	
	private static final Log _Log = LogFactory.getLog(HtmlHelper.class);

	// 转特殊字符为HTML可视代码
	public static String txt2HTML(String szText) {
		if (szText == null)
			return "&nbsp;";
		String tmp = "";
		int nLength = szText.length();
		char ch;
		for (int i = 0; i < nLength; i++) {
			ch = szText.charAt(i);
			switch (ch) {
			case '\r':
				if (i + 1 < nLength) {
					ch = szText.charAt(i + 1);
					if (ch == '\n')
						break;
				}
			case '\n':
				tmp += "<br>";
				break;
			case '<':
				tmp += "&lt;";
				break;
			case '>':
				tmp += "&gt;";
				break;
			default:
				tmp += ch;
			}
		}
		return tmp;
	}
	
	/** 转换所有JS为普通文本 */
	public static String delAllJavaScriptTag(String text) {
		String newStr = text;

		Pattern p;
		Matcher m;
		String patrn = "";
		patrn = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		p = Pattern.compile(patrn, Pattern.CASE_INSENSITIVE);
		m = p.matcher(newStr);
		newStr = m.replaceAll("");

		patrn = " href=[\"|'|]+javascript[^>]+>";
		p = Pattern.compile(patrn, Pattern.CASE_INSENSITIVE);
		m = p.matcher(newStr);
		newStr = m.replaceAll(">");

		patrn = " on[a-z]+=\"[^\"]+\"";
		p = Pattern.compile(patrn, Pattern.CASE_INSENSITIVE);
		m = p.matcher(newStr);
		newStr = m.replaceAll("");

		patrn = "<iframe(.|\n)+</iframe *>";
		p = Pattern.compile(patrn, Pattern.CASE_INSENSITIVE);
		m = p.matcher(newStr);
		newStr = m.replaceAll("");

		patrn = "<frameset(.|\n)+</frameset *>";
		p = Pattern.compile(patrn, Pattern.CASE_INSENSITIVE);
		m = p.matcher(newStr);
		newStr = m.replaceAll("");
	    
		return newStr;
	}

	/**
	 * html转为txt
	 * @param html
	 * @return
	 */
	public static String html2Txt(String html) {
		return html2Txt(html, false);
	}
	
	/**
	 * html转为txt
	 * @param html
	 * @param isSingleline
	 * @return
	 */
	public static String html2Txt(String html, boolean isSingleline) {
		if (null == html || html.length() < 2) {
			return html;
		}
		html = delAllJavaScriptTag(html);
		StringBuilder text = new StringBuilder(html.length());
		StringBuilder ret = new StringBuilder();

		int escapeIdx = -1;
		// int escapeLen;
		char ch;
		for (int i = 0; i < html.length(); i++) {
			ch = html.charAt(i);
			if (-1 != escapeIdx) {
				// 判断是否有分号
				ret.setLength(0);
				ret.append(html.substring(escapeIdx + 1, html.length()));
				if (ret.indexOf("&") > -1) {
					ret.setLength(ret.indexOf("&"));
				}

				if (-1 == ret.indexOf(";")) {
					text.append('&');
					i = escapeIdx;
					escapeIdx = -1;
					ret.setLength(0);
					continue;
				}

				if (';' == ch) {
					// int escapeLen = i - escapeIdx - 1;
					String esc = html.substring(escapeIdx + 1, i).toLowerCase();
					if (("lt").equals(esc)) {
						text.append('<');
					} else if (("gt").equals(esc)) {
						text.append('>');
					} else if (("apos").equals(esc)) {
						text.append('\'');
					} else if (("quot").equals(esc)) {
						text.append('"');
					} else if (("amp").equals(esc)) {
						text.append('&');
					} else if (("nbsp").equals(esc)) {
						text.append(' ');
					} else if (esc.length() > 1 && esc.charAt(0) == '#') {
						try {
							if (esc.length() > 2 && esc.length() <= 4 + 2
									&& esc.charAt(1) == 'x') {
								// &#x....;
								esc = esc.substring(2);
								ch = (char) Integer.parseInt(esc, 16);
								text.append(ch);
							} else if (esc.length() <= 5 + 1) {
								// &#.....;
								esc = esc.substring(1);
								ch = (char) Integer.parseInt(esc, 10);
								text.append(ch);
							}
						} catch (NumberFormatException e) {
							// 转换出错，忽略它
						}
					}
					escapeIdx = -1;
					continue;
				}
				continue;
			}
			if ('&' == ch) {
				escapeIdx = i;
				continue;
			}
			text.append(ch);
		}
		String textstr = text.toString();
		if (isSingleline) {
			textstr = StringUtil.replace(textstr, "\n", "");
			textstr = StringUtil.replace(textstr, "\r", "");
		}
		return textstr;
	}

	// 过滤怪字符
	public static String filterBadChar(String szText) {
		String tmp = szText;

		String result = "";
		for (int i = 0; i < tmp.length(); i++) {
			char ch = tmp.charAt(i);
			if (ch < ' ' || (ch > '~' && ch < 255))
				continue;
			result += ch;
		}
		return result;
	}

	public static String delHTMLTag(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}



	/**
	 * 输出到input替换"等特殊符号
	 * 
	 * @param str
	 * @return
	 */
	public static String formInputFormat(String str, boolean isLine) {
		if(null == str) return "";
		str = str.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;")
				.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		if(isLine)
			str = str.replaceAll("\r", "").replaceAll("\n", "");
		return str;
	}
	
	/**
	 * 还原特殊字符
	 * @param str
	 * @return
	 */
	public static String xmlDecode(String str) {
		if(null == str) return "";
		return str.replaceAll("&amp;", "&").replaceAll("&quot;", "\"").replaceAll("&apos;", "'")
				.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}
	
	/**
	 * 替换回车
	 * 
	 * @param str
	 * @return
	 */
	public static String formToLine(String str) {
		if(null == str) return "";
		return str.replaceAll("\r", "").replaceAll("\n", "");
	}

}