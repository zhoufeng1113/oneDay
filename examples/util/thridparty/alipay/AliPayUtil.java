package com.homevip.util.thridparty.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.homevip.core.util.Global;
import com.homevip.core.util.ThirdPart;
import com.homevip.util.system.Const;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.alipay.api.AlipayConstants.*;

/**
 * 支付宝支付工具
 *
 * Created by fate on 2017/11/19.
 */
public class AliPayUtil {

    private static AlipayClient getAlipayClient(){
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(ThirdPart.alipay_server_url, ThirdPart.alipay_app_id, ThirdPart.alipay_app_private_key, FORMAT_JSON, CHARSET_UTF8, ThirdPart.alipay_app_public_key, SIGN_TYPE_RSA2);
        return alipayClient;
    }

    private static AlipayTradeAppPayRequest getAlipayTradeAppPayRequest(){
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl(Global.WebPath + ThirdPart.alipay_callback_url);
        return request;
    }

    private static AlipayTradeAppPayModel createAlipayTradeAppPayModel(String out_trade_no,String fee,String body,String subject,String passback_params) throws UnsupportedEncodingException {
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(out_trade_no);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(fee);
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setPassbackParams(URLEncoder.encode(passback_params, Const.UTF8));
        return model;
    }

    /**
     * 创建对象
     * @param out_trade_no
     * @param fee 支付金额，单位元
     * @param body
     * @param subject
     * @param passback_params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getCharge(String out_trade_no,String fee,String body,String subject,String passback_params) throws UnsupportedEncodingException {
        try {
            AlipayClient alipayClient = getAlipayClient();
            AlipayTradeAppPayRequest request = getAlipayTradeAppPayRequest();
            AlipayTradeAppPayModel model = createAlipayTradeAppPayModel(out_trade_no, fee, body, subject,passback_params);
            request.setBizModel(model);
            request.setNotifyUrl(Global.WebPath + ThirdPart.alipay_callback_url);
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            return response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}
