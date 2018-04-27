package com.homevip.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RegexUtil {

	// 匹配URL地址
	public final static boolean isUrl(String str) {
		return match(str, "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
	}

	// 匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。
	public final static boolean isPwd(String str) {
		return match(str, "^[a-zA-Z]\\w{6,12}$");
	}

	// 验证字符，只能包含中文、英文、数字、下划线等字符。
	public final static boolean stringCheck(String str) {
		return match(str, "^[a-zA-Z0-9\u4e00-\u9fa5-_]+$");
	}

	// 匹配Email地址
	public final static boolean isEmail(String str) {
		return match(str,
				"^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
	}

	// 匹配非负整数（正整数+0）
	public final static boolean isInteger(String str) {
		return match(str, "^[+]?\\d+$");
	}

	// 判断数值类型，包括整数和浮点数
	public final static boolean isNumeric(String str) {
		if (isFloat(str) || isInteger(str))
			return true;
		return false;
	}

	// 只能输入数字
	public final static boolean isDigits(String str) {
		return match(str, "^[0-9]*$");
	}

	// 匹配正浮点数

	public final static boolean isFloat(String str) {
		return match(str, "^[-\\+]?\\d+(\\.\\d+)?$");
	}

	// 手机号码验证
	public final static boolean isMobile(String text) {
		if (text.length() != 11)
			return false;
		return match(text,
				"^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\\d{8})$");
	}

	// 身份证号码验证
	public final static boolean isIdCardNo(String text) {
		return match(text, "^(\\d{6})()?(\\d{4})(\\d{2})(\\d{2})(\\d{3})(\\w)$");
	}

	// 邮政编码验证

	public final static boolean isZipCode(String text) {
		return match(text, "^[0-9]{6}$");
	}

	// 判断整数num是否等于0
	public final static boolean isIntEqZero(int num) {
		return num == 0;
	}

	// 判断整数num是否大于0
	public final static boolean isIntGtZero(int num) {
		return num > 0;
	}

	// 判断整数num是否大于或等于0
	public final static boolean isIntGteZero(int num) {
		return num >= 0;
	}

	// 判断浮点数num是否等于0
	public final static boolean isFloatEqZero(float num) {
		return num == 0f;
	}

	// 判断浮点数num是否大于0
	public final static boolean isFloatGtZero(float num) {
		return num > 0f;
	}

	// 判断浮点数num是否大于或等于0
	public final static boolean isFloatGteZero(float num) {
		return num >= 0f;
	}

	// 判断是否为合法字符(a-zA-Z0-9-_)
	public final static boolean isRightfulString(String text) {
		return match(text, "^[A-Za-z0-9_-]+$");
	}

	// 判断英文字符(a-zA-Z)
	public final static boolean isEnglish(String text) {
		return match(text, "^[A-Za-z]+$");
	}

	// 判断中文字符(包括汉字和符号)
	public final static boolean isChineseChar(String text) {
		return match(text, "^[\u0391-\uFFE5]+$");
	}

	// 匹配汉字
	public final static boolean isChinese(String text) {
		return match(text, "^[\u4e00-\u9fa5]+$");
	}

	// 是否包含中英文特殊字符，除英文"-_"字符外
	public static boolean isContainsSpecialChar(String text) {
		if (StringUtils.isBlank(text))
			return false;
		String[] chars = { "[", "`", "~", "!", "@", "#", "$", "%", "^", "&",
				"*", "(", ")", "+", "=", "|", "{", "}", "'", ":", ";", "'",
				",", "[", "]", ".", "<", ">", "/", "?", "~", "！", "@", "#",
				"￥", "%", "…", "&", "*", "（", "）", "—", "+", "|", "{", "}",
				"【", "】", "‘", "；", "：", "”", "“", "’", "。", "，", "、", "？", "]" };
		for (String ch : chars) {
			if (text.contains(ch))
				return true;
		}
		return false;
	}

	// 过滤中英文特殊字符，除英文"-_"字符外
	public static String stringFilter(String text) {
		String regExpr = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regExpr);
		Matcher m = p.matcher(text);
		return m.replaceAll("").trim();
	}

	// 正则表达式匹配
	private final static boolean match(String text, String reg) {
		if (StringUtils.isBlank(text) || StringUtils.isBlank(reg))
			return false;
		return Pattern.compile(reg).matcher(text).matches();
	}
}