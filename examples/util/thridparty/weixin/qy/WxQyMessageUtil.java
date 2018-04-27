package com.homevip.util.thridparty.weixin.qy;

import com.homevip.core.util.ThirdPart;
import com.homevip.user.entity.User;
import com.homevip.util.CollectionUtil;
import com.homevip.util.NumberUtil;
import com.homevip.util.StringUtil;
import com.homevip.util.gson.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信消息类
 * @author panpan
 */
public class WxQyMessageUtil extends WxQyBase {

    Log _log = LogFactory.getLog(this.getClass());

    String token;
    String sendUrl;

    public WxQyMessageUtil(String token){
        this.token = token;
        this.sendUrl = String.format(ThirdPart.qy_messqge_url, token);
    }

    /**
     * 发微信消息
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void send(List<User> users, String message) throws Exception {
        if(CollectionUtil.isEmpty(users)){
            return;
        }
        String httpRet = null;
        for(User user : users){
            if(user==null|| StringUtil.isEmpty(user.getCode())){
                continue;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> textMap = new HashMap<String, Object>();
//            map.put("touser", formatUsers(users));
            map.put("touser", user.getCode());
            map.put("toparty", "@all");
            map.put("totag", "@all");
            map.put("msgtype", "text");
            map.put("agentid", ThirdPart.qy_agentid);
            map.put("safe", 0);
            textMap.put("content", message);
            map.put("text", textMap);
            _log.info("wx qy data" + JsonUtil.toJson(map));
            httpRet = WxQyUtil.apiPost(this.sendUrl, JsonUtil.toJson(map));
            if(!StringUtil.isEmpty(httpRet)) {
                try {
                    Map<String, Object> ret = (Map<String, Object>) JsonUtil.fromJson(httpRet, Map.class);
                    double errcode = (Double) ret.get("errcode");
                    if (0 != NumberUtil.toInt(errcode)) {
                        _log.error("wx qy message send fail:" + map.get("errmsg").toString());
                    }
                } catch (Exception e) {
                    _log.error("wx qy message send recv fail:" + map.toString());
                }
            }else{
                _log.error("wx qy message send recv fail:" + map.toString());
            }
            Thread.sleep(3000);
        }
    }

    String formatUsers(List<User> users){
        String ret = "";
        if(CollectionUtil.isEmpty(users))
            return ret;
        for(User user : users){
            _log.info("send->" + user.getAccount());
            ret += user.getCode() + "|";
        }
        _log.info("ret->" + ret);
        if(!StringUtil.isEmpty(ret)){
            ret = ret.substring(0,ret.length() - 1);
        }

        return ret;
    }
    public static  void main(String args[]){
        Map<String, Object> ret = (Map<String, Object>) new HashMap<String,Object>();
        ret.put("errcode",0.0);
        double s = (Double) ret.get("errcode");
        System.out.println(NumberUtil.toInt(s));
    }

}
