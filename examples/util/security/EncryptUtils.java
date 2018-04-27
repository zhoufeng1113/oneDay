package com.homevip.util.security;

import java.security.Key;

import javax.crypto.Cipher;

/**
 * 加解密工具类
 * @author cjh
 *
 */
public class EncryptUtils {

	/** 字符串默认键值 */  
    private static String strDefaultKey = "2G0s0T0q3z2n07261D420I142r1h0b08";  
	
    public static String byteArr2HexStr(byte[] arrB) throws Exception {  
        int iLen = arrB.length;  
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍  
        StringBuffer sb = new StringBuffer(iLen * 2);  
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];  
            // 把负数转换为正数  
            while (intTmp < 0) {  
                intTmp = intTmp + 256;  
            }  
            // 小于0F的数需要在前面补0  
            if (intTmp < 16) {
                sb.append("0");  
            }  
            sb.append(Integer.toString(intTmp, 16));  
        }  
        return sb.toString();  
    }  
    /** 
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB) 
     * 互为可逆的转换过程 
     * @param strIn 需要转换的字符串 
     * @return 转换后的byte数组 
     */  
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {  
        byte[] arrB = strIn.getBytes();  
        int iLen = arrB.length;  
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2  
        byte[] arrOut = new byte[iLen / 2];  
        for (int i = 0; i < iLen; i = i + 2) {  
            String strTmp = new String(arrB, i, 2);  
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);  
        }  
        return arrOut;  
    }  
    /** 
     * 加密字节数组 
     * @param arrB  需加密的字节数组 
     * @return 加密后的字节数组 
     */  
    public static byte[] encrypt(byte[] arrB) throws Exception {  
    	
    	Cipher encryptCipher = Cipher.getInstance("DES");  
    	encryptCipher.init(Cipher.ENCRYPT_MODE, getKey(strDefaultKey.getBytes()));
        
        return encryptCipher.doFinal(arrB);  
    }  
    /** 
     * 加密字符串 
     * @param strIn  需加密的字符串 
     * @return 加密后的字符串 
     */  
    public static String encrypt(String strIn) throws Exception {  
        return byteArr2HexStr(encrypt(strIn.getBytes()));  
    }  
    /** 
     * 解密字节数组 
     * @param arrB  需解密的字节数组 
     * @return 解密后的字节数组 
     */  
    public static byte[] decrypt(byte[] arrB) throws Exception {  
    	
    	Cipher decryptCipher = Cipher.getInstance("DES");  
        decryptCipher.init(Cipher.DECRYPT_MODE, getKey(strDefaultKey.getBytes()));
    	
        return decryptCipher.doFinal(arrB);  
    }  
    /** 
     * 解密字符串 
     * @param strIn  需解密的字符串 
     * @return 解密后的字符串 
     */  
    public static String decrypt(String strIn){  
        try {
			return new String(decrypt(hexStr2ByteArr(strIn)));
		} catch (Exception e) {
			e.printStackTrace();
		}  
        
        return "";
    }  
    /** 
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位 
     * @param arrBTmp  构成该字符串的字节数组 
     * @return 生成的密钥 
     */  
    private static Key getKey(byte[] arrBTmp) throws Exception {  
        // 创建一个空的8位字节数组（默认值为0）  
        byte[] arrB = new byte[8];  
        // 将原始字节数组转换为8位  
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {  
            arrB[i] = arrBTmp[i];  
        }  
        // 生成密钥  
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");  
        return key;  
    }  
    public static void main(String[] args) {  
        try {  
            String test1 = "16";  
            System.out.println("加密前的字符：" + test1);  
            System.out.println("加密后的字符：" + encrypt(test1));  
            System.out.println("解密后的字符：" + decrypt(encrypt(test1)));  
              
            String test2 = "19";  
            System.out.println("加密前的字符：" + test2);  
            System.out.println("加密后的字符：" + encrypt(test2));  
            System.out.println("解密后的字符：" + decrypt(encrypt(test2))); 
            
            String test3 = "20";  
            System.out.println("加密前的字符：" + test3);  
            System.out.println("加密后的字符：" + encrypt(test3));  
            System.out.println("解密后的字符：" + decrypt(encrypt(test3)));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        
        
    }  

}
