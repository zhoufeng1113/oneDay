package com.homevip.util.http;

import com.homevip.core.util.Global;
import com.homevip.util.StringUtil;
import com.homevip.util.gson.GsonUtil;
import com.homevip.util.system.CommArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http协议工具类
 * 
 * @author mojs
 * 
 */
public class HttpUtil {

	public static final String SCHEME_HTTP = "http";
	public static final String SCHEME_HTTPS = "https";

	/**
	 * 获取经常代理后的真实ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (null != ip && ip.equals("0:0:0:0:0:0:0:1%0")) ip = "127.0.0.1"; // localhost
		}
		if(ip.indexOf(",") > -1){
			ip = ip.split(",")[0];
		}
		return ip;
	}

	/**
	 * encodeURL中的中文
	 */
	public static String encodeUrl(String url) throws Exception {
		if (url.indexOf("/") != -1) {
			url = url.replace("\\", "/");
			String path = url.substring(0, url.lastIndexOf("/"));
			String filename = url.substring(url.lastIndexOf("/") + 1,
					url.length());
			filename = java.net.URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+", "%20");
			return path + "/" + filename;
		} else {
			return url;
		}
	}

	/**
	 * 接收request流
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestAsString(HttpServletRequest request)
			throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int read;
		InputStream in = request.getInputStream();
		do {
			read = in.read(buf);
			if (read > 0)
				out.write(buf, 0, read);
		} while (read > 0);
		String ret = out.toString("utf-8");
		out.close();
		out = null;
		return ret;
	}
	
	public static String getUa(HttpServletRequest request){
		String ua = request.getHeader("User-Agent");
		return ua;
	}
/*	public static void  main(String[] args){
		try {
			System.out.print(URLEncoder.encode("{"id":"6","num":"1","time":"4.0"}","utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 获取请求参数中的字符串,非null
	 *
	 * @param name
	 * @param request
	 * @return
	 */
	public static String getString(String name, HttpServletRequest request) {
		String method = request.getMethod();
		name = StringUtil.toString(request.getParameter(name));
		if ("GET".equalsIgnoreCase(method)) {
			try {
				name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {

			}
		}
		return name;
	}
	/**
	 * 获取不到使用默认值
	 *
	 * @param name
	 * @param value
	 * @param request
	 * @return
	 */
	public static int getInteger(String name, int value, HttpServletRequest request) {
		String temp = StringUtil.toString(request.getParameter(name)).trim();
		if ("".equals(temp)) {
			temp = "0";
		}
		int result = 0;
		try {
			result = Integer.parseInt(temp);
		} catch (NumberFormatException e) {
		}
		if (result == 0 && result != value) {
			result = value;
		}
		return result;
	}

	/**
	 * 跳转，支持https
	 * @param request
	 * @param response
	 * @param url
	 * @param ignoreMobile
	 * @throws IOException
     */
	public static void sendHttpRedirect(HttpServletRequest request, HttpServletResponse response, String url, String ignoreMobile) throws IOException {
		boolean isAbsolute = false; //是否绝对路径
		String mobileType = getMobileType(request);
		String scheme = getScheme(request);
		if(null == mobileType){
			mobileType = CommArray.EnumMobileType._iphone.getCode();
		}
		if(url.indexOf("http://") > -1 || url.indexOf("https://") > -1){
			isAbsolute = true;
		}
		if(!isAbsolute){
			if(SCHEME_HTTPS.equals(scheme) && !mobileType.equals(ignoreMobile)){
				response.sendRedirect(Global.HttpsPath + url);
			}else{
				response.sendRedirect(Global.WebPath + url);
			}
		}else{
			if(SCHEME_HTTPS.equals(scheme)){
				url = url.replace("http://", "https://");
			}else{
				url = url.replace("https://", "http://");
			}
			response.sendRedirect(url);
		}
	}

	/**
	 * 跳转 script
	 * @param url
	 * @param response
	 * @throws IOException
     */
	public static void sendRedirectScript(String url, HttpServletResponse response) throws IOException{
		String str = "<script>location.href=\"" + url + "\";</script>";
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}

	public static String toHttps(String content, HttpServletRequest request, String ignoreMobile){
		if(null == content) return null;
		String scheme = getScheme(request);
		String mobileType = getMobileType(request);
		if(null == mobileType){
			mobileType = CommArray.EnumMobileType._iphone.getCode();
		}
		if(SCHEME_HTTPS.equals(scheme) && !mobileType.equals(ignoreMobile)){
			content = content.replace("http://","https://");
			return content;
		}else{
			return content;
		}
	}

	public static String getScheme(HttpServletRequest request){
		String scheme = request.getHeader("x-forwarded-proto");
		if(null == scheme){
			scheme = request.getScheme();
		}
		return scheme.toLowerCase();
	}

	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	public static String getBrowserType(HttpServletRequest request){
		String ua = getUa(request);
		ua = StringUtil.toString(ua).toLowerCase();
		if(ua.indexOf("micromessenger") > -1){
			return CommArray.EnumBrowserType._weixin.getCode();
		}
		return null;
	}

	public static String getMobileType(HttpServletRequest request){
		String ua = getUa(request);
		ua = StringUtil.toString(ua).toLowerCase();
		if(ua.indexOf("android") > -1){
			return CommArray.EnumMobileType._android.getCode();
		}else if(ua.indexOf("iphone") > -1){
			return CommArray.EnumMobileType._iphone.getCode();
		}else{
			return null;
		}
	}
}
