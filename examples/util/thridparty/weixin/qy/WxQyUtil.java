package com.homevip.util.thridparty.weixin.qy;

import com.homevip.util.StringUtil;
import com.homevip.util.gson.JsonUtil;
import com.homevip.util.http.URLConnectionHelper;
import com.homevip.util.thridparty.weixin.vo.WxAccessTokenVo;
import com.homevip.util.thridparty.weixin.qy.crypt.AesException;
import com.homevip.util.thridparty.weixin.qy.crypt.WXBizMsgCrypt;

import java.io.IOException;

/**
 * 企业微信帮助工具
 * Created by panpan on 2016/8/8.
 */
public class WxQyUtil extends WxQyBase {

    /**
     * 验证服务器
     * @param msgSignature
     * @param timeStamp
     * @param nonce
     * @param echoStr
     * @return
     * @throws AesException
     */
    public static String verifyURL(String msgSignature, String timeStamp, String nonce, String echoStr) throws AesException {
        WXBizMsgCrypt biz = SingleBiz.instance;
        return biz.VerifyURL(msgSignature, timeStamp, nonce, echoStr);
    }

    /**
     * 获取accesstoken
     * @return
     * @throws IOException
     */
    public static WxAccessTokenVo getAccessToken() throws IOException {
        String ret = null;
        String url = null;
        WxAccessTokenVo token = new WxAccessTokenVo();

        url = formatAccessUrl();
        URLConnectionHelper uh = new URLConnectionHelper(url);
        ret = uh.doGet();

        if (!StringUtil.isEmpty(ret)) {
            token = (WxAccessTokenVo) JsonUtil.fromJson(ret, WxAccessTokenVo.class);
        }
        return token;
    }

}
