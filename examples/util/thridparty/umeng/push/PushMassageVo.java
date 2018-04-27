package com.homevip.util.thridparty.umeng.push;

import com.homevip.util.system.MsgTemplate;

/**
 *
 * Created by panpan on 2017/4/24.
 */
public class PushMassageVo {
    private String device; //设备token
    private String content; //消息类容
    private String action; //app动作
    private String title; //标题
    private String ticker; //状态栏
    private String catalogCode; //分类code
    private String sendId; //消息标识（系统生成）
    private MsgTemplate.EnumMsgTemplate enumMsgTemplate; //模板

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.ticker = title;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(String catalogCode) {
        this.catalogCode = catalogCode;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public MsgTemplate.EnumMsgTemplate getEnumMsgTemplate() {
        return enumMsgTemplate;
    }

    public void setEnumMsgTemplate(MsgTemplate.EnumMsgTemplate enumMsgTemplate) {
        this.enumMsgTemplate = enumMsgTemplate;
    }

    public boolean isAndroid(){
        //ios
        if(this.device.length() > 44){
            return false;
        }
        //android
        else{
            return true;
        }
    }

}
