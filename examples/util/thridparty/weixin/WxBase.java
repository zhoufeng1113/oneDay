package com.homevip.util.thridparty.weixin;

import com.homevip.util.StringUtil;
import com.homevip.util.XmlHelper;
import com.homevip.util.gson.JsonUtil;
import com.homevip.util.http.URLConnectionHelper;
import com.homevip.util.security.MD5Util;
import com.homevip.util.system.Const;
import com.homevip.util.thridparty.weixin.vo.WxAccessTokenVo;
import org.apache.commons.lang.RandomStringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

/**
 * 微信工具基础方法
 * @author panpan
 */
public class WxBase {

	/**
	 * 访问接口公用方法
	 * @throws IOException
	 */
	public static String apiPost(String url, String postData) throws IOException {
		URLConnectionHelper uh = new URLConnectionHelper(url);
		String ret = null;

		ret = uh.doPost(postData);
		if (!StringUtil.isEmpty(ret)) {
			ret = uh.getContent();
		}
		return ret;
	}

	protected static String createNonceStr() {
		return RandomStringUtils.randomNumeric(16);
	}

	protected static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	protected static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 创建参数字符串,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	protected static String formatBizQueryParaMap(Map<String, String> params) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		if (sb.length() > 0) {
			// 删除最后的&
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public static String createSign(Map<String, String> params, String key) {
		String sign = formatBizQueryParaMap(params);
		sign = sign + "&key=" + key;
		System.out.println("sign签名:" + sign);
		sign = MD5Util.MD5Encode(sign, Const.UTF8).toUpperCase();
		System.out.println("md5签名:" + sign);
		return sign;
	}

	/**
	 * 格式化支付返回xml为Map。
	 */
	public static Map<String, String> formatPayResultXML(String xml) {
		System.out.println("pay return xml->" + xml);
		Map<String, String> params = new TreeMap<String, String>();
		XmlHelper xh = new XmlHelper();
		xh.load(xml);
		Node root = xh.selectChildElement("xml");
		NodeList nl = root.getChildNodes();
		for(int i = 0; i < nl.getLength(); i++){
			Node n = nl.item(i);
			//过滤#text
			if(3 == n.getNodeType()){
				continue;
			}
			String k = n.getNodeName();
			String v = n.getTextContent();
			if (null != v && !"".equals(v)) {
				params.put(k, v);
			}
		}
		return params;
	}

	/**
	 * 创建xml数据
	 *
	 * @throws UnsupportedEncodingException
	 */
	public static String createXml(Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		String sign = "";
		sb.append("<xml>");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("sign".equals(k)) {
				sign = v;
				continue;
			}
			if (null != v && !"".equals(v)) {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		if (!"".equals(sign)) {
			sb.append("<sign>" + sign + "</sign>");
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 访问接口公用方法
	 * @throws IOException
	 */
	public static WxAccessTokenVo getTokenResult(String url) throws IOException {

		WxAccessTokenVo token = null;
		String ret = null;

		URLConnectionHelper uh = new URLConnectionHelper(url);
		ret = uh.doGet();

		if (!StringUtil.isEmpty(ret)) {
			token = (WxAccessTokenVo) JsonUtil.fromJson(ret, WxAccessTokenVo.class);
		}
		return token;
	}

}
