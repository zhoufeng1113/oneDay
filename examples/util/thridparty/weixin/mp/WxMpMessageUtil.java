package com.homevip.util.thridparty.weixin.mp;

import com.homevip.core.util.ThirdPart;
import com.homevip.util.StringUtil;
import com.homevip.util.gson.JsonUtil;
import com.homevip.util.system.MsgTemplate;
import com.homevip.util.system.MsgTemplate.EnumMsgTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信消息类
 * @author panpan
 */
public class WxMpMessageUtil extends WxMpBase {
	Log _log = LogFactory.getLog(this.getClass());

	String token;
	String sendUrl;

	public WxMpMessageUtil(String token){
		this.token = token;
		this.sendUrl = String.format(ThirdPart.wx_message_send, token);
	}

	/**
	 * 发微信消息
	 * @throws Exception 
	 */
	public void send(String openId, String templateId, String url, Map<String, Map<String, Object>> data) throws Exception {
		if("true".equals(ThirdPart.sms_debug)){
			try{
				templateId = MsgTemplate.wxTestTemplateIds.get(templateId);
			}catch (Exception e){
				return;
			}
		}
		String httpRet = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("touser", openId);
		map.put("template_id", templateId);
		map.put("topcolor", "#FF0000");
		if(null != url){
			map.put("url", url);
		}
		map.put("data", data);
		httpRet = WxMpUtil.apiPost(this.sendUrl, JsonUtil.toJson(map));
		_log.info("openId->" + openId + ",templateId->" + templateId + ",data->" + data);
		if(!StringUtil.isEmpty(httpRet)){
			try{
				Map<String, Object> ret = (Map<String, Object>) JsonUtil.fromJson(httpRet, Map.class);
				if(0 != (Integer)ret.get("errcode")){
					_log.error("wx message send fail:" + map.get("errmsg").toString());
				}
			}catch(Exception e){
				_log.error("wx message send recv fail:" + map.toString());
			}
		}else{
			_log.error("wx message send fail:" + map.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		String token = "piw5R2-aXBrkYASZ8bHoe08Otn3CpX5iWKj_pn1Fq8KZteHj2__jfUSEAmlno5DiehGp-xKX7wfW4ShG0MPi-yXx_8bWcGCJYknTS5xokn4";
		Map<String, Map<String, Object>> data = new HashMap<String, Map<String, Object>>();
		WxMpMessageUtil message = new WxMpMessageUtil(token);
		
		Map<String, Object> first = new HashMap<String, Object>();
		first.put("value", "您已成功获得总额为[888888元]的优惠券礼包！");
		data.put("first", first);

		String keywordA = "营销礼包优惠券";
		Map<String, Object> keywordAMap = new HashMap<String, Object>();
		keywordAMap.put("value", keywordA);
		data.put("keyword1", keywordAMap);

		String keywordB = "无";
		Map<String, Object> keywordBMap = new HashMap<String, Object>();
		keywordBMap.put("value", keywordB);
		data.put("keyword2", keywordBMap);

		Map<String, Object> keywordCMap = new HashMap<String, Object>();
		String keywordC = 999 + "张";
		keywordCMap.put("value", keywordC);
		data.put("keyword3", keywordCMap);

		String remark = "";
		remark += "如有疑问请致电客服热线4006-518051。";
		Map<String, Object> remarkMap = new HashMap<String, Object>();
		remarkMap.put("value", remark);
		data.put("remark", remarkMap);
		
		message.send("oh7d7sz014WWt0Hz4H-xIUjoRfl8", EnumMsgTemplate._32.getTemplateId(), null, data);
	}
}
