package com.homevip.util.security;

import com.homevip.util.system.Const;

import java.util.UUID;


/**
 * 用户生成md5密码
 * @author root
 *
 */
public class MD5MixUtil {



	/**
	 * 先添加混淆串再进行两次md5
	 * @param str
	 * @return
	 */
	public static String md5Mix(String str) {
		String chartset = "utf-8";
		if (str == null) {
			return null;
		}
		String md5Pwd = MD5Util.MD5Encode(Const.MD5_MIX + str, chartset);
		return MD5Util.MD5Encode(md5Pwd, chartset);
	}

	/**
	 * 获取加密随机数
	 * @param id
	 * @return
	 */
	public static String getEncodeRandomStr(int id){
		return MD5MixUtil.md5Mix(UUID.randomUUID().toString()) + id;
	}

	/**
	 * 获取加密随机数
	 * @param id
	 * @return
	 */
	public static String getEncodeRandomStr(String id){
		return MD5MixUtil.md5Mix(UUID.randomUUID().toString()) + id;
	}

	/**
	 * 解密
	 * @param erStr
	 * @return
     */
	public static String getDecodeRandomStr(String erStr){
		if(erStr.length()>32){
			return erStr.substring(32,erStr.length());
		}else{
			return erStr;
		}
	}
	public static void main(String [] args) {
		System.out.println(MD5MixUtil.md5Mix("123456"));
//		System.out.println(MD5MixUtil.getDecodeRandomStr("330f8b22a8e95e98e6753e3f3ec38e6c"));
	}
}
