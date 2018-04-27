package com.homevip.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.homevip.member.entity.Member;
import com.homevip.user.entity.User;
import com.homevip.util.system.Const;

/**
 * 关于用户session操作
 * 
 * @author root
 * 
 */
public class UserSessionUtil {

	/**
	 * 保存openid
	 * @param request
	 * @return
	 */
	public static void setOpenid(String openid, HttpServletRequest request) {
		request.getSession().setAttribute(Const.SESSION_KEY_OPENID, openid);
	}

	/**
	 * 返回openid
	 * @param request
	 * @return
	 */
	public static String getOpenid(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(Const.SESSION_KEY_OPENID);
	}
	/**
	 * 返回openid
	 * @param request
	 * @return
	 */
	public static void removeOpenid(HttpServletRequest request) {
		request.getSession().removeAttribute(Const.SESSION_KEY_OPENID);
	}

	/**
	 * 保存app
	 * @param request
	 * @return
	 */
	public static void setApp(String app, HttpServletRequest request) {
		request.getSession().setAttribute(Const.SESSION_KEY_APP, app);
	}

	/**
	 * 返回app
	 * @param request
	 * @return
	 */
	public static String getApp(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(Const.SESSION_KEY_APP);
	}
	/**
	 * 删除app
	 * @param request
	 * @return
	 */
	public static void removeApp(HttpServletRequest request) {
		request.getSession().removeAttribute(Const.SESSION_KEY_APP);
	}

	/**
	 * 保存验证码
	 * @param request
	 * @return
	 */
	public static void setRandCode(String code, HttpServletRequest request) {
		request.getSession().setAttribute(Const.SESSION_KEY_RAND, code);
	}
	
	/**
	 * 返回验证码
	 * @param request
	 * @return
	 */
	public static String getRandCode(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(Const.SESSION_KEY_RAND);
	}

	/**
	 * @param request
	 */
	public static void setUser(User user, HttpServletRequest request) {
		if (user == null || user.getId() == 0) {
			return;
		}
		request.getSession().invalidate();
		request.getSession().setAttribute(Const.SESSION_KEY_USER, user);
	}

	/**
	 * @param request
	 */
	public static User getUser(HttpServletRequest request) {
		User user = (User) request.getAttribute(Const.SESSION_KEY_USER);
		if(null == user) {
			return (User) request.getSession().getAttribute(Const.SESSION_KEY_USER);
		}
		return user;
	}

	/**
	 * 移除后台用户
	 * @param request
	 */
	public static void removeUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Const.SESSION_KEY_USER);
		request.removeAttribute(Const.SESSION_KEY_USER);
	}
	
	/**
	 * @param request
	 */
	public static void updateUser(User user, HttpServletRequest request) {
		if (user == null || user.getId() == 0) {
			return;
		}
		request.getSession().setAttribute(Const.SESSION_KEY_USER, user);
	}
	
	/**
	 * @param member
	 * @param request
	 */
	public static void setMember(Member member, HttpServletRequest request) {
		if (member == null || member.getId() == 0) {
			return;
		}
		request.getSession().invalidate();
		request.getSession().setAttribute(Const.SESSION_KEY_MEMBER, member);
	}

	/**
	 * 设置member到request
	 * @param member
	 * @param request
	 */
	public static void setRequestMember(Member member, HttpServletRequest request) {
		if (member == null || member.getId() == 0) {
			return;
		}
		request.setAttribute(Const.SESSION_KEY_MEMBER, member);
	}

	/**
	 * @param request
	 */
	public static Member getMember(HttpServletRequest request) {
		Member member = (Member) request.getAttribute(Const.SESSION_KEY_MEMBER);
		if(null == member){
			member = (Member) request.getSession().getAttribute(Const.SESSION_KEY_MEMBER);
		}
		return member;
	}

	/**
	 * 移除后台用户
	 * @param request
	 */
	public static void removeMember(HttpServletRequest request) {
		request.getSession().removeAttribute(Const.SESSION_KEY_MEMBER);
	}
	
	/**
	 * @param member
	 * @param request
	 */
	public static void updateMember(Member member, HttpServletRequest request) {
		if (member == null || member.getId() == 0) {
			return;
		}
		request.getSession().setAttribute(Const.SESSION_KEY_MEMBER, member);
	}

	/**
	 * 设置临时参数列表
	 * @introduce 当获取openid，原参数会丢失，这个方法用于临时将参数保存在session中，获取到openid后再清除这个参数列表
	 */
	public static void setParamList(HashMap<String,Object> paramList,HttpServletRequest request){
		request.getSession().setAttribute(Const.SESSION_KEY_PARAMLIST, paramList);
	}
	/**获取参数列表**/
	public static HashMap<String,Object> getParamList(HttpServletRequest request){
		return (HashMap<String,Object>) request.getSession().getAttribute(Const.SESSION_KEY_PARAMLIST);
	}
	/**清除参数列表**/
	public static void cleanParamList(HttpServletRequest request){
		request.getSession().removeAttribute(Const.SESSION_KEY_PARAMLIST);
	}


	/**
	 * 获取wx_token
	 * @param request
	 * @return
	 */
    public static String getRequestWxToken(HttpServletRequest request) {
    	return (String) request.getAttribute(Const.WX_TOKEN);
    }

	public static void setRequestWxToken(String token,HttpServletRequest request) {
		request.setAttribute(Const.WX_TOKEN,token);
	}
}