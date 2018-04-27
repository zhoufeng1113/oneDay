package com.homevip.util.http;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.homevip.util.em.ObjectPool;
import com.homevip.util.file.FileLogic;

public class NetworkUtil {
	/**
	 * 
	 * @param url
	 * @param params
	 * @param method
	 * @param encode
	 * @param timeout
	 * @param JSESSIONID
	 * @return new String[]{status, rs, sessionId};
	 */
	public static String[] query(String url, String params, String method, String encode, 
			int timeout, String JSESSIONID) {
		String[] rst = null;
		String status = "0";
		String rs = null;

		String sCurrentLine = null;
		ObjectPool op = ObjectPool.getInstance();
		StringBuilder sTotalString = op.getStringBuilder();

		URL urlObj = null;
		HttpURLConnection conn = null;
		InputStream inStream = null;
		InputStreamReader inReader = null;
		BufferedReader buffReader = null;
		OutputStream os = null;
		try {
			if ("GET".equalsIgnoreCase(method)) {
				String paramStr = (params==null||"".equals(params.trim()))?"":("?"+params.trim());
				urlObj = new URL(url + paramStr);
				conn = (HttpURLConnection) urlObj.openConnection();
				conn.setConnectTimeout(timeout);
				conn.setReadTimeout(timeout);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//				if(JSESSIONID != null && !"".equals(JSESSIONID.trim())){
				conn.setRequestProperty("Cookie","JSESSIONID=" + (JSESSIONID==null?"":JSESSIONID));
//				}
				conn.connect();
				int code = conn.getResponseCode();
				if (code == 200) {
					inStream = conn.getInputStream();
					inReader = new InputStreamReader(inStream, encode);
					buffReader = new BufferedReader(inReader);
					while ((sCurrentLine = buffReader.readLine()) != null) {
						sTotalString.append(sCurrentLine);
					}
					rs = sTotalString.toString();
					status = "1";
				} else {
					rs = "访问失败，错误代码：" + code;
				}
			} else if ("POST".equalsIgnoreCase(method)) {
				urlObj = new URL(url);
				conn = (HttpURLConnection) urlObj.openConnection();
				conn.setConnectTimeout(timeout);
				conn.setReadTimeout(timeout);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//post方法必须使用这种类型
//				conn.setRequestProperty("Content-Type", "text/xml");
//				if(JSESSIONID != null && !"".equals(JSESSIONID.trim())){
					conn.setRequestProperty("Cookie","JSESSIONID=" + (JSESSIONID==null?"":JSESSIONID));
//				}
				os = conn.getOutputStream();
				String paramStr = (params==null)?"":(params.trim());
				os.write(paramStr.getBytes(encode));
				os.flush();
				int code = conn.getResponseCode();
				if (code == 200) {
					inStream = conn.getInputStream();
					inReader = new InputStreamReader(inStream, encode);
					buffReader = new BufferedReader(inReader);
					while ((sCurrentLine = buffReader.readLine()) != null) {
						sTotalString.append(sCurrentLine);
					}
					rs = sTotalString.toString();
					status = "1";
				} else {
					rs = "访问失败，错误代码：" + code;
				}
			}
			String cookieValue = conn.getHeaderField("Set-Cookie");
			String sessionId = null;
			if(cookieValue != null && cookieValue.toUpperCase().contains("JSESSIONID")){
				sessionId = cookieValue.substring(0, cookieValue.indexOf(";"));
				sessionId = sessionId.substring(sessionId.indexOf("=")+1, sessionId.length());
			}else{
				sessionId = JSESSIONID;
			}
	        rst = new String[]{status, rs, sessionId};
		} catch (Exception e) {
			rst = new String[]{"0", "网络访问失败！", null};
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (buffReader != null) {
				try {
					buffReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inReader != null) {
				try {
					inReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		op.ccStringBuilder(sTotalString);
		return rst;
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @param method
	 * @param encode
	 * @param timeout
	 * @param JSESSIONID
	 * @return new String[]{status, rs, sessionId};
	 */
	public static String[] query4Stream(String url, String streamStr, String method, String encode, 
			int timeout, String JSESSIONID) {
		String[] rst = null;
		String status = "0";
		String rs = null;

		String sCurrentLine = null;
		ObjectPool op = ObjectPool.getInstance();
		StringBuilder sTotalString = op.getStringBuilder();

		URL urlObj = null;
		HttpURLConnection conn = null;
		InputStream inStream = null;
		InputStreamReader inReader = null;
		BufferedReader buffReader = null;
		OutputStream os = null;
		try {
			if ("GET".equalsIgnoreCase(method)) {
				urlObj = new URL(url);
				conn = (HttpURLConnection) urlObj.openConnection();
				conn.setConnectTimeout(timeout);
				conn.setReadTimeout(timeout);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-Type", "text/xml; charset=" + encode);
//				if(JSESSIONID != null && !"".equals(JSESSIONID.trim())){
				conn.setRequestProperty("Cookie","JSESSIONID=" + (JSESSIONID==null?"":JSESSIONID));
//				}
				os = conn.getOutputStream();
				String paramStr = (streamStr==null)?"":(streamStr.trim());
				os.write(paramStr.getBytes(encode));
				os.flush();
				conn.connect();
				int code = conn.getResponseCode();
				if (code == 200) {
					inStream = conn.getInputStream();
					inReader = new InputStreamReader(inStream, encode);
					buffReader = new BufferedReader(inReader);
					while ((sCurrentLine = buffReader.readLine()) != null) {
						sTotalString.append(sCurrentLine);
					}
					rs = sTotalString.toString();
					status = "1";
				} else {
					rs = "访问失败，错误代码：" + code;
				}
			} else if ("POST".equalsIgnoreCase(method)) {
				urlObj = new URL(url);
				conn = (HttpURLConnection) urlObj.openConnection();
				conn.setConnectTimeout(timeout);
				conn.setReadTimeout(timeout);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
//				conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//post方法必须使用这种类型
				conn.setRequestProperty("Content-Type", "text/xml; charset=" + encode);
//				if(JSESSIONID != null && !"".equals(JSESSIONID.trim())){
					conn.setRequestProperty("Cookie","JSESSIONID=" + (JSESSIONID==null?"":JSESSIONID));
//				}
				os = conn.getOutputStream();
				String paramStr = (streamStr==null)?"":(streamStr.trim());
				os.write(paramStr.getBytes(encode));
				os.flush();
				int code = conn.getResponseCode();
				if (code == 200) {
					inStream = conn.getInputStream();
					inReader = new InputStreamReader(inStream, encode);
					buffReader = new BufferedReader(inReader);
					while ((sCurrentLine = buffReader.readLine()) != null) {
						sTotalString.append(sCurrentLine);
					}
					rs = sTotalString.toString();
					status = "1";
				} else {
					rs = "访问失败，错误代码：" + code;
				}
			}
			String cookieValue = conn.getHeaderField("Set-Cookie");
			String sessionId = null;
			if(cookieValue != null && cookieValue.toUpperCase().contains("JSESSIONID")){
				sessionId = cookieValue.substring(0, cookieValue.indexOf(";"));
				sessionId = sessionId.substring(sessionId.indexOf("=")+1, sessionId.length());
			}else{
				sessionId = JSESSIONID;
			}
	        rst = new String[]{status, rs, sessionId};
		} catch (Exception e) {
			rst = new String[]{"0", "网络访问失败！", null};
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (buffReader != null) {
				try {
					buffReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inReader != null) {
				try {
					inReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		op.ccStringBuilder(sTotalString);
		return rst;
	}
	
	public static boolean downloadImage(String url, String saveFilePath){
		HttpURLConnection conn = null;
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL urls = new URL(url);
            conn = (HttpURLConnection) urls.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setReadTimeout(10000);
            conn.connect();
            in = conn.getInputStream();
            out = new FileOutputStream(saveFilePath);
            byte[] buf = new byte[1024];
            int pos;
            while ((pos = in.read(buf, 0, 1024)) > 0) {
            	out.write(buf, 0, pos);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }finally{
        	if(out != null){
        		try {
    				out.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
        	}
        	if(in != null){
        		 try {
     				in.close();
     			} catch (IOException e) {
     				e.printStackTrace();
     			}
        	}
        }
		
		return true;
	}
	
	public static void main(String[] args){
//		String url = "http://localhost:8080/SparkOA/comm/authCode.jsp";
//		String url = "http://localhost:8080/SparkOA/pub/login!login.zx";
//		String url = "http://localhost:8080/SparkOA/pub/register!compRegister.zx";
		/*String url = "http://localhost:8080/SparkOA/wxs/main.zx";
		String params = "<xml>"
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[this is a test]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>";
		String[] rss = NetworkUtil.query4Stream(url,
				params, "POST", "UTF-8", 5000, null);
		System.out.println(rss[1]);*/
		String path = FileLogic.createDateSplitDirYYYYMMDDHH("d:/test");
		NetworkUtil.downloadImage("http://www.51homevip.com/portal/web/images/home-top.png", path + "/gg.png");
	}
}
