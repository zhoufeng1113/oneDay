package com.homevip.util.thridparty.weixin.qy;

import com.homevip.core.util.ThirdPart;
import com.homevip.util.thridparty.weixin.WxBase;
import com.homevip.util.thridparty.weixin.qy.crypt.WXBizMsgCrypt;

/**
 * 微信工具基础方法
 * @author panpan
 */
public class WxQyBase extends WxBase {

	protected static String APP_CORP_ID = ThirdPart.qy_corpid;
	protected static String APP_TOKEN = ThirdPart.qy_token;
	protected static String APP_AES_KEY = ThirdPart.qy_aeskey;
	protected static String APP_SECRET = ThirdPart.qy_secret;

	/**
	 * 统一格式化accesstoken地址
	 */
	protected static String formatAccessUrl() {
		String url = ThirdPart.qy_access_token_url;
		url = url.replace("{corpid}", APP_CORP_ID);
		url = url.replace("{secrect}", APP_SECRET);
		return url;
	}

	protected static class SingleBiz {
		protected static WXBizMsgCrypt instance;
		protected SingleBiz() throws Exception {
			instance = new WXBizMsgCrypt(APP_TOKEN, APP_AES_KEY, APP_CORP_ID);
		}
	}

}
