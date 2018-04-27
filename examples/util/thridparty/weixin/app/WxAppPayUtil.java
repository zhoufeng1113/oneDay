package com.homevip.util.thridparty.weixin.app;

import com.homevip.core.util.Global;
import com.homevip.core.util.ThirdPart;
import com.homevip.util.NumberUtil;
import com.homevip.util.StringUtil;
import com.homevip.util.XmlHelper;
import com.homevip.util.http.HttpUtil;
import com.homevip.util.security.MD5MixUtil;
import com.homevip.util.security.MD5Util;
import com.homevip.util.system.Const;
import com.homevip.util.thridparty.weixin.WxBase;
import com.homevip.util.thridparty.weixin.mp.WxMpBase;
import com.homevip.util.thridparty.weixin.mp.WxMpUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 微信支付类
 * @author panpan
 */
public class WxAppPayUtil extends WxBase {

    protected static String APP_ID = ThirdPart.wxpay_appid;
    protected static String PAY_KEY = ThirdPart.wxpay_appsecret;
    protected static String MCH_ID = ThirdPart.wxpay_mch_id;

    /**
     * 创建支付xml数据
     *
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> createUnifiedorder(String ip, String fee, String notify_url, String out_trade_no, String attachs, String body)
            throws UnsupportedEncodingException {
        Map<String, String> params = new TreeMap<String, String>();
        String sign;
        String nonce_str = createNonceStr();

        fee = StringUtil.cutString(fee, ".");
        params.put("appid", APP_ID);
        params.put("body", body);
        params.put("is_subscribe", "Y");
        params.put("attach", attachs);
        params.put("mch_id", MCH_ID);
        params.put("nonce_str", nonce_str);
        params.put("notify_url", notify_url);
        params.put("out_trade_no", out_trade_no);
        params.put("spbill_create_ip", ip);
        //单位分且整数
        params.put("total_fee", fee);
        params.put("trade_type", "APP");
        sign = createSign(params, PAY_KEY);
        params.put("sign", sign);
        String xml = createXml(params);
        params.put("xml", xml);
        return params;
    }

    /**
     * 创建支付pay sign
     */
    public static Map<String, String> paySign(String prepayId) {
        Map<String, String> params = new TreeMap<String, String>();
        String sign;
        String nonce_str = createNonceStr();
        String timeStamp = createTimestamp();

        params.put("appid", APP_ID);
        params.put("noncestr", nonce_str);
        params.put("timestamp", timeStamp);
        params.put("prepayid", prepayId);
        params.put("partnerid", MCH_ID);
        params.put("package", "Sign=WXPay");

        sign = createSign(params, PAY_KEY);

        params.put("noncestr", nonce_str);
        params.put("timestamp", timeStamp);
        params.put("sign", sign);

        return params;
    }

    /**
     * 检测支付返回的数据
     * @throws Exception
     *
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> checkPayResult(HttpServletRequest request) throws Exception {
        String sign;
        String verify;
        Map<String, String> params;
        String xml;

        xml = HttpUtil.getRequestAsString(request);
        params = formatPayResultXML(xml);

        if(!APP_ID.equals(params.get("appid"))){
            throw new Exception("支付处理错误：AppId异常");
        }
        //在return_code 和result_code都为SUCCESS的时候有返回
        if("SUCCESS".equals(params.get("result_code"))){
            //检测签名是否正确
            sign = params.get("sign");
            verify = createSign(params, PAY_KEY);
            if(!verify.equals(sign)){
                throw new Exception("微信支付查询结果签名异常：[" + sign + "][" + verify + "]");
            }else{
                //TODO 应该再次主动对账
            }
        }else{
            throw new Exception("支付处理错误：" +params.get("err_code_des"));
        }
        return params;
    }

    /**
     * 获取支付下单结果
     * @param ip
     * @param fee 支付金额，单位分
     * @param attachs
     * @param body
     * @return
     * @throws IOException
     */
    public static Map<String, String> getWxPayResult(String ip, String fee, String out_trade_no, List<String> attachs, String body)
            throws IOException {
        String url = ThirdPart.wx_unifiedorder_url;
        String notifyUrl = Global.WebPath + ThirdPart.wxpay_callback_url;
        String strAttachs = StringUtil.list2Str(attachs, ",");

        Map<String, String> params = WxAppPayUtil.createUnifiedorder(ip, fee, notifyUrl, out_trade_no, strAttachs, body);

        String ret = WxAppPayUtil.apiPost(url, params.get("xml"));
        if (!StringUtil.isEmpty(ret)) {
            Node node;
            XmlHelper xh = new XmlHelper();
            xh.load(ret);
            node = xh.selectChildElement("xml/return_code");
            String code = XmlHelper.getElementText(node);
            if ("SUCCESS".equals(code)) {
                node = xh.selectChildElement("xml/prepay_id");
                String prepayId = XmlHelper.getElementText(node);
                return WxAppPayUtil.paySign(prepayId);
            }
        }
        return null;
    }
}
