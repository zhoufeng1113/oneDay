package com.homevip.util.thridparty.weixin.mp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import com.homevip.util.thridparty.weixin.vo.WxAccessTokenVo;
import com.homevip.util.thridparty.weixin.vo.WxUserinfoVo;
import com.homevip.util.gson.JsonUtil;
import com.homevip.util.http.URLConnectionHelper;
import com.homevip.util.thridparty.weixin.mp.crypt.SHA1;

import com.homevip.core.util.ThirdPart;
import com.homevip.util.StringUtil;
import com.homevip.util.system.Const;

/**
 * 微信接口对应处理方法
 * @author panpan
 */
public class WxMpUtil extends WxMpBase {

	/**
	 * 微信公众平台验证 Token可由开发者可以任意填写，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
     * @return
     */
	public static String verifyURL(String signature, String timestamp, String nonce, String echostr) {
		String result = "";
		// 设置系统Token值
		String token = ThirdPart.wx_local_server_token;

		System.out.println("token->" + token);
		System.out.println("signature->" + signature);
		System.out.println("timestamp->" + timestamp);
		System.out.println("nonce->" + nonce);
		System.out.println("echostr->" + echostr);

		// 将获取到的参数放入数组
		String[] ArrTmp = { token, timestamp, nonce };
		// 按微信提供的方法，对数据内容进行排序
		Arrays.sort(ArrTmp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sb.append(ArrTmp[i]);
		}
		// 对排序后的字符串进行SHA-1加密
		String pwd = SHA1.Encrypt(sb.toString());

		System.out.println("pwd->" + pwd);

		if (pwd.equals(signature)) {
			try {
				result = echostr;
			} catch (Exception ex) {
				result = "微信平台签名消息验证失败！";
				ex.printStackTrace();
			}
		} else {
			result = "微信平台签名消息验证失败！";
		}
		return result;
	}

	/**
	 * jsapi配置验证
	 * @param jsapi_ticket
	 * @param url
     * @return
     */
	public static Map<String, String> configJsapiSignature(String jsapi_ticket, String url) {
		Map<String, String> params = new TreeMap<String, String>();
		String nonce_str = createNonceStr();
		String timestamp = createTimestamp();
		String sign;
		String signature = "";

		// 全部要小写
		params.put("jsapi_ticket", jsapi_ticket);
		params.put("noncestr", nonce_str);
		params.put("timestamp", timestamp);
		params.put("url", url);

		sign = formatBizQueryParaMap(params);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(sign.getBytes(Const.UTF8));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("configSignature:" + sign);
		System.out.println("configSignature crypt:" + signature);

		params.put("signature", signature);

		return params;
	}

	/**
	 * 获取access_token
	 * @return
	 * @throws IOException
     */
	public static WxAccessTokenVo getAccessToken() throws IOException {
		WxAccessTokenVo token = new WxAccessTokenVo();
		String url = WxMpUtil.formatWithAppId(ThirdPart.wx_api_token_url, ThirdPart.wx_fwh_appsecret);
		token = WxMpUtil.getTokenResult(url);
		return token;
	}

	/**
	 * 获取ticket
	 * @param aeescc_token
	 * @return
	 * @throws IOException
     */
	public static WxAccessTokenVo getTicket(String aeescc_token) throws IOException {
		WxAccessTokenVo token = new WxAccessTokenVo();
		String url = WxMpUtil.formatWithAppId(ThirdPart.wx_ticket_url, aeescc_token, "jsapi");
		token = WxMpUtil.getTokenResult(url);
		return token;
	}

	/**
	 * 通过code换取公众号网页授权access_token
	 * @param code
	 * @return
	 * @throws IOException
     */
	public static WxAccessTokenVo getWebAccessToken(String code) throws IOException {
		WxAccessTokenVo token = new WxAccessTokenVo();
		String url = WxMpUtil.formatWithAppId(ThirdPart.wx_access_token_url, ThirdPart.wx_fwh_appsecret, code, "authorization_code");
		token = WxMpUtil.getTokenResult(url);
		return token;
	}

	/**
	 * 刷新网页授权access_token
	 * @param refresh_token
	 * @return
	 * @throws IOException
     */
	public static WxAccessTokenVo doRefreshToken(String refresh_token) throws IOException {
		WxAccessTokenVo token = new WxAccessTokenVo();
		String url = WxMpUtil.formatWithAppId(ThirdPart.wx_refresh_token_url, refresh_token, "refresh_token");
		token = WxMpUtil.getTokenResult(url);
		return token;
	}

	/**
	 * 验证网页授权access_token
	 * @param access_token
	 * @param openid
	 * @return
	 * @throws IOException
     */
	public static WxAccessTokenVo doAuthAccessToken(String access_token, String openid) throws IOException {
		WxAccessTokenVo token = new WxAccessTokenVo();
		String url = String.format(ThirdPart.wx_auth_token_rul, access_token, openid);
		token = WxMpUtil.getTokenResult(url);
		return token;
	}

	/**
	 * 获取用户数据
	 * @param access_token
	 * @param openid
	 * @return
	 * @throws IOException
     */
	public static WxUserinfoVo getUserInfo(String access_token, String openid) throws IOException {
		WxUserinfoVo userinfo = null;
		String ret = null;

		String url = String.format(ThirdPart.wx_userinfo_url, access_token, openid);

		URLConnectionHelper uh = new URLConnectionHelper(url);
		ret = uh.doGet();

		if (!StringUtil.isEmpty(ret)) {
			// uh.setContent("{\"openid\":\"OPENID\",\"nickname\":\"NICKNAME\",\"sex\":\"1\",\"province\":\"PROVINCE\",\"city\":\"CITY\",\"country\":\"COUNTRY\",\"headimgurl\":\"xxx\",\"privilege\":[\"PRIVILEGE1\",\"PRIVILEGE2\"],\"unionid\":\"o6_bmasdasdsad6_2sgVt7hMZOPfL\"}");
			userinfo = (WxUserinfoVo) JsonUtil.fromJson(ret, WxUserinfoVo.class);
		}
		return userinfo;
	}
}
