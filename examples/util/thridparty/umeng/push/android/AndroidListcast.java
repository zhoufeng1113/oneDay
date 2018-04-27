package com.homevip.util.thridparty.umeng.push.android;


import com.homevip.util.thridparty.umeng.push.AndroidNotification;

public class AndroidListcast extends AndroidNotification {
	public AndroidListcast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");
	}
	
	public void setDeviceTokens(String tokens) throws Exception {
    	setPredefinedKeyValue("device_tokens", tokens);
    }

}