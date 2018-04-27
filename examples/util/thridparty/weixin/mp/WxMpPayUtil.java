package com.homevip.util.thridparty.weixin.mp;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 微信支付类
 * @author panpan
 */
public class WxMpPayUtil extends WxMpBase {

    /**
     * 创建支付xml数据
     *
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> createUnifiedorder(int memberId,
                                                         String openid, String ip, double fee, String notify_url, String orderno)
            throws UnsupportedEncodingException {
        Map<String, String> params = new TreeMap<String, String>();
        String mid= MD5MixUtil.getEncodeRandomStr(memberId);
        String sign;
        String nonce_str = createNonceStr();
        orderno = StringUtil.toString(orderno);
        // TODO 订单号暂时用随机字符串
        String out_trade_no = createNonceStr();
        params.put("appid", APP_ID);
        params.put("body", "wx_charge");
        params.put("is_subscribe", "Y");
        params.put("attach", mid + "," + ("".equals(orderno) ? 0 : orderno));
        params.put("mch_id", MCH_ID);
        params.put("nonce_str", nonce_str);
        params.put("notify_url", notify_url);
        params.put("openid", openid);
        params.put("out_trade_no", out_trade_no);
        params.put("spbill_create_ip", ip);
        //单位分且整数
        params.put("total_fee", "" + NumberUtil.toInt(fee));
        params.put("trade_type", "JSAPI");
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

        params.put("appId", APP_ID);
        params.put("nonceStr", nonce_str);
        params.put("timeStamp", timeStamp);
        params.put("package", "prepay_id=" + prepayId);
        params.put("signType", "MD5");

        sign = createSign(params, PAY_KEY);

        params.put("nonceStr", nonce_str);
        params.put("timeStamp", timeStamp);
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
//		xml = "<xml>"
//				+ "<appid><![CDATA[wx1375e98a8433d429]]></appid>"
//				+ "<attach><![CDATA[20]]></attach>"
//				+ "<bank_type><![CDATA[CFT]]></bank_type>"
//				+ "<cash_fee><![CDATA[1]]></cash_fee>"
//				+ "<fee_type><![CDATA[CNY]]></fee_type>"
//				+ "<is_subscribe><![CDATA[Y]]></is_subscribe>"
//				+ "<mch_id><![CDATA[1230170902]]></mch_id>"
//				+ "<nonce_str><![CDATA[0212811477015359]]></nonce_str>"
//				+ "<openid><![CDATA[oh7d7szna5rzek0K8mOJeZm6SZcY]]></openid>"
//				+ "<out_trade_no><![CDATA[5365380722218531]]></out_trade_no>"
//				+ "<result_code><![CDATA[SUCCESS]]></result_code>"
//				+ "<return_code><![CDATA[SUCCESS]]></return_code>"
//				+ "<sign><![CDATA[EDF73B3EFBCE7844D8D57A41509F3CBF]]></sign>"
//				+ "<time_end><![CDATA[20150311092116]]></time_end>"
//				+ "<total_fee>1</total_fee>"
//				+ "<trade_type><![CDATA[JSAPI]]></trade_type>"
//				+ "<transaction_id><![CDATA[1000700566201503110030083378]]></transaction_id>"
//				+ "</xml>";
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
     * @param memberId
     * @param openid
     * @param ip
     * @param fee
     * @param orderno
     * @return
     * @throws IOException
     */
    public static Map<String, String> getWxPayResult(int memberId, String openid, String ip, double fee, String orderno)
            throws IOException {
        String url = ThirdPart.wx_unifiedorder_url;
        String notifyUrl = Global.WebPath + ThirdPart.wx_fwh_pay_callback_url;

        Map<String, String> params = WxMpPayUtil.createUnifiedorder(memberId, openid, ip, fee, notifyUrl, orderno);

        String ret = WxMpUtil.apiPost(url, params.get("xml"));
        if (!StringUtil.isEmpty(ret)) {
            Node node;
            XmlHelper xh = new XmlHelper();
            xh.load(ret);
            node = xh.selectChildElement("xml/return_code");
            String code = XmlHelper.getElementText(node);
            if ("SUCCESS".equals(code)) {
                node = xh.selectChildElement("xml/prepay_id");
                String prepayId = XmlHelper.getElementText(node);
                return WxMpPayUtil.paySign(prepayId);
            }
        }
        return null;
    }
}
