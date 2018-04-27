package com.homevip.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.homevip.util.system.Const;

/**
 * 公共方法。
 */
public class StringUtil {

	//判断是否空值
	public static boolean isEmpty(String str){
		if(null != str && !"".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	
	/** 最大长度字符显示 */
	public static String leftString(String str, int len, boolean showdot) {
		if (str == null)
			return "";
		if (showdot)
			return StringUtil.cutstring(str, len, "...");
		else
			return StringUtil.cutstring(str, len, "");
	}

	/**
	 * 按字节长度截取字符串
	 * 
	 * @param str
	 *            将要截取的字符串参数
	 * @param toCount
	 *            截取的字节长度
	 * @param more
	 *            字符串末尾补上的字符串
	 * @return 返回截取后的字符串
	 */
	public static String cutstring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";

		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		byte[] CharLen = str.getBytes();
		byte[] moreLen = more.getBytes();

		if (CharLen.length > toCount) {
			toCount = toCount - moreLen.length;
		}

		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}

		if (CharLen.length > toCount)
			reStr += more;

		return reStr;
	}

	// 把字符串按照一定字符进行分割
	public static String[] splitString(String szSource, String token) {
		if ((szSource == null) || (token == null))
			return null;
		java.util.StringTokenizer st1 = new java.util.StringTokenizer(szSource,
				token);
		String[] d1 = new String[st1.countTokens()];
		for (int x = 0; x < d1.length; x++)
			if (st1.hasMoreTokens())
				d1[x] = st1.nextToken();
		return d1;
	}

	// 把字符串按照一定字符进行分割 第二种方法
	public static String[] split(String str, String splitsign) {
		int index;
		if (str == null || splitsign == null)
			return null;
		ArrayList<String> al = new ArrayList<String>();
		while ((index = str.indexOf(splitsign)) != -1) {
			al.add(str.substring(0, index));
			str = str.substring(index + splitsign.length());
		}
		al.add(str);
		return (String[]) al.toArray(new String[0]);
	}

	public static byte[] toByte(String szStr) {
		if (szStr == null)
			return null;
		byte[] tmp = szStr.getBytes();
		return tmp;
	}

	public static String toSpace(Object obj) {
		if (obj == null || obj.equals(" "))
			return "&nbsp;";
		return obj.toString();
	}

	public static String toSpace(Date date) {
		if (date == null)
			return "&nbsp;";
		return toString(date);
	}

	public static String toSpace(Date date, String format) {
		if (date == null)
			return "&nbsp;";
		return toString(date, format);
	}

	// 数字数组转换成字符串
	public static String arrayInttoString(int[] ids, String separator) {
		String ret = "";
		for (int i = 0; i < ids.length; i++) {
			if (i < ids.length - 1)
				ret += String.valueOf(ids[i]) + separator;
			else
				ret += String.valueOf(ids[i]);
		}
		return ret;
	}

	// 字符数组转换成字符串
	public static String arrayStrtoString(String[] ids, String separator) {
		String ret = "";
		for (int i = 0; i < ids.length; i++) {
			if (i < ids.length - 1)
				ret += String.valueOf(ids[i]) + separator;
			else
				ret += String.valueOf(ids[i]);
		}
		return ret;
	}

	public static String toString(byte[] byBuf) {
		if (byBuf == null)
			return null;
		return new String(byBuf);
	}

	public static String toString(Object obj) {
		if (obj == null)
			return "";
		return obj.toString().trim();
	}

	public static String toString(String obj) {
		if (obj == null)
			return "";
		return obj;
	}

	public static String toString(Date obj) {
		return toString(obj, "yyyy-MM-dd HH:mm:ss");
	}

	public static String toString(Date obj, String format) {
		if (obj == null)
			return "";
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
		return df.format(obj);
	}

	/** 最大长度字符显示 */
	public static String subString(String str, int len) {
		if (str == null)
			return "";
		if (str.length() <= len)
			return str;
		return str.substring(0, len);
	}

	public static String subString(String str, int len, String expandstr) {
		if (str == null)
			return "";
		if (str.length() <= len)
			return str;
		return str.substring(0, len) + expandstr;
	}

	/**
	 * 字符串转为list
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> str2List(String str) {
		final String URL_SPLIT_PATTERN = "[, ;\r\n]";// 逗号 空格 分号 换行
		if (str == null) {
			return null;
		}
		String[] arr = str.split(URL_SPLIT_PATTERN);
		List<String> list = new ArrayList<String>();
		for (String a : arr) {
			a = a.trim();
			if (a.length() == 0) {
				continue;
			}
			list.add(a);
		}
		return list;
	}
	
	/**
	 * 字符串转为list
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Double> str2NumberList(String str) {
		List<String> list = str2List(str);
		List<Double> ret = new ArrayList<Double>();
		for(String s : list){
			ret.add(Double.valueOf(s));
		}
		return ret;
	}

	/**
	 * 字符串转为list
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> str2IntList(String str) {
		List<String> list = str2List(str);
		List<Integer> ret = new ArrayList<Integer>();
		for(String s : list){
			ret.add(Integer.valueOf(s));
		}
		return ret;
	}

	//list转字符","
	public static <T> String list2Str(List<T> args) {
		return list2Str(args, ",");
	}
	
	//list转字符","
	public static <T> String list2Str(List<T> args,String split) {
	    String str = "";
		if(null == split) split = ",";
	    if (args != null && args.size() > 0) {  
	        for (T i : args) {  
	            str += i + split;
	        }  
	        str = str.substring(0, str.length() - split.length());
	        return str;  
	    }else{
	    	return "";
	    }
	}

	//list转字符","
	public static <T> String array2Str(Integer[] arr,String split) {
		String str = "";
		if(null == split) split = ",";
		if (arr != null && arr.length> 0) {
			for (int i=0;i<arr.length; i++) {
				str += arr[i] + split;
			}
			str = str.substring(0, str.length() - split.length());
			return str;
		}else{
			return "";
		}
	}

	// 数字显示格式的处理
	public static String toPrice(double d) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(d);
	}

	public static String toPrice(float d) {
		DecimalFormat df = new DecimalFormat("##0.00");
		return df.format(d);
	}

	// 数字显示格式的处理
	public static String toPrice_Fin(double d) {
		DecimalFormat df = new DecimalFormat("###0.00");
		return df.format(d);
	}
	
	/**
	 * <p>方法名: defaultString	</p>
	 * <p>描述:	null 或者 "" 返回默认字符串</p>
	 * <p>参数:	</p>
	 * @return		
	 * <p>return	String</p>
	 */
	public static String defaultString(String originalString,String defaultString){
		if(null == originalString || "".equals(originalString.trim())){
			return defaultString;
		}
		return originalString;
	}
	
	/**
	 * 
	 * 方法名: findDiff <br />  
	 * 描述:返回oriStr中有的，但str上没有的词 <br /> 
	 * 参数：<br /> 
	 * @param oriStr
	 * @param str
	 * @return <br />    
	 * @return String <br />    
	 * @throws
	 */
	public static String findDiff(String oriStr, String str, String token){
		if(null == token) token = ",";
			String ret = "";
			if(null == oriStr || 0 == oriStr.length()){
				return "";
			}else if(null == str || 0 == str.length()){
				return oriStr;
			}else{
				String[] arr = oriStr.split(token);
				str += token;
				for(String val : arr){
					if(str.indexOf(val + token) == -1){
						ret += val + ",";
					}
				}
				if(ret.length() > 0)
					ret = ret.substring(0, ret.length() - 1);
				return ret;
			}
	}

	// 过滤特殊字符  
    public static  String StringFilter(String str)throws PatternSyntaxException   {     
          // 只允许字母和数字       
          // String   regEx  =  "[^a-zA-Z0-9]";                     
          // 清除掉所有特殊字符  
          String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
          Pattern   p   =   Pattern.compile(regEx);     
          Matcher   m   =   p.matcher(str);     
          return   m.replaceAll("").trim();     
   } 
	
	/**
	 * 替换字符串
	 * @param text
	 * @param repl
	 * @param with
	 * @return
	 */
	public static String replace(String text, String repl, String with) {
		
		return replace(text, repl, with, -1);
	}

	/**
	 * 替换字符串
	 * @param text
	 * @param repl
	 * @param with
	 * @param max
	 * @return
	 */
	public static String replace(String text, String repl, String with, int max) {
		
		if ((text == null) || (repl == null) || (with == null)
				|| (repl.length() == 0) || (max == 0)) {
			return text;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			max--;
			if (max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
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

	public static String fixZero(int num){
		if(num < 10){
			return "0" + num;
		}else{
			return "" + num;
		}
	}

	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为2位
	 * @param value 指定的字符串
	 * @return 字符串的长度
	 */
	public static int getLength(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
			String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
				valueLength += 2;
			} else {
                /* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	// 过滤特殊字符
	public static  String filterEmoji(String str)throws PatternSyntaxException   {
		if(str.trim().isEmpty()){
			return str;
		}
		/*String pattern="[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
		String reStr="";
		Pattern emoji=Pattern.compile(pattern);
		Matcher  emojiMatcher=emoji.matcher(str);
		str=emojiMatcher.replaceAll(reStr);*/
		return str.replaceAll("[^\\u0000-\\uFFFF]", "");
	}

	/**
	 * 合拼字符串
	 * @param split
	 * @param args
     * @return
     */
	public static String concat(String split, Object... args) {
		StringBuilder sb = new StringBuilder();
		for(Object arg : args){
			sb.append("" + arg).append(split);
		}
		return sb.toString().substring(0, sb.toString().length() - split.length());
	}

	public static void main(String[] args) {
//		String eventJson = "{\"id\":\"evt_400170511183836456412503\",\"created\":1494499116,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_anLu9GCGKuf9DGSqDSqPuH08\",\"object\":\"charge\",\"created\":1494499106,\"livemode\":false,\"paid\":true,\"refunded\":false,\"app\":\"app_PWzvzHyv9OGOWfD0\",\"channel\":\"alipay\",\"order_no\":\"alipay7761952223847236\",\"client_ip\":\"172.16.1.236\",\"amount\":18200,\"amount_settle\":18200,\"currency\":\"cny\",\"subject\":\"标准保洁\",\"body\":\"购买51家庭管家标准保洁\",\"extra\":{\"buyer_account\":\"alipay_account\"},\"time_paid\":1494499116,\"time_expire\":1494585506,\"time_settle\":null,\"transaction_no\":\"2017051185022407\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_anLu9GCGKuf9DGSqDSqPuH08/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{\"uid\":\"407d0fb6fc5c0d5d08ab3b2e795eaa4910712\",\"orderno\":\"1036528675\",\"catalog_type\":\"service\"},\"credential\":{},\"description\":null}},\"object\":\"event\",\"request\":\"iar_8Wn9yDOS8u98rfrnTGTiXnnP\",\"pending_webhooks\":0}";
//		JsonObject event= new JsonParser().parse(eventJson).getAsJsonObject();
//		JsonObject eventdata =   event.get("data").getAsJsonObject();
//		JsonObject object =   eventdata.get("object").getAsJsonObject();
//		JsonObject metaobject= object.get("metadata").getAsJsonObject();
//		System.out.println(metaobject.get("orderno").getAsString());
//		String a = "\\\\xF0\\\\x9F\\\\x98\\\\x80 U+1F600";
//		boolean b = StringUtil.hasEmoji(a);
//		System.out.println(b);
//
//		String str = String.format("%04d", 12345);
//        System.out.println(str); // 0000000001
		TreeSet<Integer> treeSet = new TreeSet<>();
		treeSet.add(3);
		treeSet.add(4);
		treeSet.add(2);
		System.out.println(StringUtil.list2Str(new ArrayList<Integer>(treeSet),"-"));
	}

	/**
	 * 数字转化为中文大写
	 * @param num
	 * @return
	 */
	public static String numReturnBig(int num){
		String bignum="";
		switch (num){
			case 1:bignum="一";
				break;
			case 2:bignum="二";
				break;
			case 3:bignum="三";
				break;
			case 4:bignum="四";
				break;
			case 5:bignum="五";
				break;
			case 6:bignum="六";
				break;
			case 7:bignum="七";
				break;
			case 8:bignum="八";
				break;
			case 9:bignum="九";
				break;
		}
		return bignum;
	}

	public static boolean isChinese(String str) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find())
			flg = true;

		return flg;
	}
	
	/**
	 * 简化字符串
	 * @param s
	 * @return
	 */
	public static String getSimpleString(String s){
		String simpleString=s;
		if(!StringUtil.isEmpty(s)) {
			if (s.length() > 15) {
				simpleString = s.substring(0, 15) + "...";
			}
		}
		return simpleString;
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	//判断是否含有emoji表情
    public static boolean hasEmoji(String content){
        if(StringUtil.isEmpty(content)){
            return  false;
        }
        String temStr =  content.replaceAll("[^\\u0000-\\uFFFF]", "");
        return temStr.length() != content.length();
    }

	public static String cutString(String fee, String s) {
		try{
			String[] arr = split(fee, s);
			return arr[0];
		}catch (Exception e){
			return fee;
		}
	}

	//解码
	public static String decode(String arg, boolean isCh) {
		String str = "";
		try {
			str = URLDecoder.decode(arg, Const.UTF8);
			if(isCh){
				str = new String(arg.getBytes("ISO-8859-1"), Const.UTF8);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}