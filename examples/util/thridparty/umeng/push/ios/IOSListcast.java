package com.homevip.util.thridparty.umeng.push.ios;

import com.homevip.util.thridparty.umeng.push.IOSNotification;

public class IOSListcast extends IOSNotification {
	public IOSListcast(String appkey, String appMasterSecret) throws Exception{
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");
	}
	
	public void setDeviceTokens(String tokens) throws Exception {
    	setPredefinedKeyValue("device_tokens", tokens);
    }
}
