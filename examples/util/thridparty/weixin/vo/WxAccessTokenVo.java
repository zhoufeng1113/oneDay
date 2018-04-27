package com.homevip.util.thridparty.weixin.vo;

import java.io.Serializable;

/**
 * 微信接口accesstoken返回
 * 
 * @author pyepye
 * @version 2015-02-12
 */
public class WxAccessTokenVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5435066823650214561L;
	private int id;// 数据库id,获取基础token需使用
	private String access_token;// 全局唯一票据(基础)/网页授权/普通
	private String expires_in;// 接口调用凭证超时时间，单位（秒）
	private String refresh_token;// 用户刷新access_token
	private String openid;// 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	private String scope;// 用户授权的作用域，使用逗号（,）分隔
	private String ticket;// jsapi_ticket
	private String errcode;// 错误代码
	private String errmsg;// 错误
	private String apicode; //编码
	private String unionid;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getApicode() {
		return apicode;
	}

	public void setApicode(String apicode) {
		this.apicode = apicode;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
