package com.homevip.util.security;

import com.homevip.util.StringUtil;
import com.homevip.util.system.Const;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * base64工具类
 * @author panpan
 */
public class Base64Util {

    Base64 base64 = new Base64();

    private Base64Util() {
    }

    private static class Single{
        private static Base64Util instance = new Base64Util();
    }

    public static Base64Util getInstance() {
        return Single.instance;
    }

    public byte[] encode(byte[] bytes) throws Exception {
        byte[] encodeByte = base64.encode(bytes);
        return encodeByte;
    }

    public byte[] encode(String str) throws Exception {
        if(StringUtil.isEmpty(str)) return null;
        return encode(str.getBytes());
    }

    public String encodeToString(byte[] bytes) throws Exception {
        return new String(encode(bytes), Const.UTF8);
    }

    public byte[] decode(byte[] bytes) throws Exception {
        byte[] decodeByte = base64.decode(bytes);
        return decodeByte;
    }

    public byte[] decode(String str) throws Exception {
        if(StringUtil.isEmpty(str)) return null;
        return decode(str.getBytes());
    }

    public String decodeoString(String str) throws Exception {
        if(StringUtil.isEmpty(str)) return null;
        return new String(decode(str.getBytes()), Const.UTF8);
    }
}
