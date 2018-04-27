package com.homevip.util.thridparty.weixin.mp;

import com.homevip.core.util.ThirdPart;
import com.homevip.util.thridparty.weixin.WxBase;
import com.homevip.util.thridparty.weixin.vo.WxAccessTokenVo;
import com.homevip.util.StringUtil;
import com.homevip.util.gson.JsonUtil;
import com.homevip.util.http.URLConnectionHelper;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.util.Formatter;
import java.util.Map;

/**
 * 微信工具基础方法
 * @author panpan
 */
public class WxMpBase extends WxBase {

	protected static String APP_ID = ThirdPart.wx_fwh_appid;
	protected static String PAY_KEY = ThirdPart.wx_fwh_pay_appsecret;
	protected static String MCH_ID = ThirdPart.wx_fwh_mch_id;

	/**
	 * 统一格式化api的appid参数
	 */
	public static String formatWithAppId(String url, Object... args) {
		url = url.replace("{appid}", APP_ID);
		return String.format(url, args);
	}

	/**
	 * 统一格式化api的appid参数
	 */
	public static String formatBaseWithAppId(String url, Object... args) {
		url = url.replace("{appid}", APP_ID);
		url = url.replace("snsapi_userinfo", "snsapi_base");
		return String.format(url, args);
	}
}
