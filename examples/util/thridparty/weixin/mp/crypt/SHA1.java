package com.homevip.util.thridparty.weixin.mp.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 公共加密码类
 * @author pyepye
 * @version 2015-02-12
 */
public class SHA1 {
	/**
    * 用SHA-1算法加密字符串并返回16进制串
    * @param strSrc
    * @return
    */
	public static String Encrypt(String strSrc) {
       MessageDigest md = null;
       String strDes = null;
       byte[] bt = strSrc.getBytes();
       try {
           md = MessageDigest.getInstance("SHA-1");
           md.update(bt);
           strDes = bytes2Hex(md.digest());
       } catch (NoSuchAlgorithmException e) {
           System.out.println("错误");
           return null;
       }
       return strDes;
    }

	/**
	 * 返回16进制串
	 * @param bts
	 * @return
	 */
	public static String bytes2Hex(byte[] bts) {
       String des = "";
       String tmp = null;
       for (int i = 0; i < bts.length; i++) {
           tmp = (Integer.toHexString(bts[i] & 0xFF));
           if (tmp.length() == 1) {
               des += "0";
           }
           des += tmp;
       }
       return des;
   }
}
