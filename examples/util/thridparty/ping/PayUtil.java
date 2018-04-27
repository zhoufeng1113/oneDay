package com.homevip.util.thridparty.ping;

import com.homevip.core.util.Global;
import com.homevip.core.util.ThirdPart;
import com.homevip.util.StringUtil;
import com.homevip.util.http.HttpUtil;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ping++ 支付工具
 *
 * Created by hhq on 2017/3/16.
 */
public class  PayUtil {
    //初始化ping++
    public static void initPing(){
        Pingpp.DEBUG = ThirdPart.ping_debug;
        if(Pingpp.DEBUG){
            Pingpp.apiKey = ThirdPart.ping_test_apikey;
        }else {
            Pingpp.apiKey = ThirdPart.ping_apikey;
        }
        Pingpp.privateKeyPath = StringUtil.concat("/", Global.Path, ThirdPart.ping_rsa_private_key_url);
    }

    //获取charge请求对象
    public static String getCharge(String channel,Map<String, Object> extra,String appId,String amount, String currency,String subject,
                            String orderno,String client_ip,String description,Map<String, Object> metadata,String body){

        Charge charge = null;
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", amount);//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
        chargeMap.put("currency", "cny");
        chargeMap.put("subject",subject);
        chargeMap.put("body", body);
        chargeMap.put("order_no", StringUtil.concat("", channel, orderno));// 推荐使用 8-20 位，要求数字或字母，不允许其他字符
        chargeMap.put("channel", channel);// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
        chargeMap.put("client_ip", client_ip); // 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1
        chargeMap.put("description", StringUtil.toString(description));
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", appId);
        chargeMap.put("app", app);
        chargeMap.put("extra", extra);
        chargeMap.put("metadata", metadata);
        String chargeString="";
        try {
            // 发起 charge 创建请求
            charge = Charge.create(chargeMap);
            // 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
            chargeString = charge.toString();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
        return chargeString;

    }


}
