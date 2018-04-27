package com.homevip.util.thridparty.umeng.push;

import com.homevip.core.util.ThirdPart;
import com.homevip.util.StringUtil;
import com.homevip.util.system.CommArray;
import com.homevip.util.thridparty.umeng.push.android.AndroidListcast;
import com.homevip.util.thridparty.umeng.push.android.AndroidUnicast;
import com.homevip.util.thridparty.umeng.push.ios.IOSListcast;
import com.homevip.util.thridparty.umeng.push.ios.IOSUnicast;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WorkPushClient {
	
	// The user agent
	protected final String USER_AGENT = "Mozilla/5.0";

	// This object is used for sending the post request to Umeng
	protected HttpClient client = new DefaultHttpClient();
	
	// The host
	protected static final String host = "http://msg.umeng.com";
	
	// The upload path
	protected static final String uploadPath = "/upload";
	
	// The post path
	protected static final String postPath = "/api/send";

	public boolean send(UmengNotification msg) throws Exception {
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		msg.setPredefinedKeyValue("timestamp", timestamp);
        String url = host + postPath;
        String postBody = msg.getPostBody();
        String sign = DigestUtils.md5Hex(("POST" + url + postBody + msg.getAppMasterSecret()).getBytes("utf8"));
        url = url + "?sign=" + sign;
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", USER_AGENT);
        StringEntity se = new StringEntity(postBody, "UTF-8");
        post.setEntity(se);
        HttpResponse response = client.execute(post);
        int status = response.getStatusLine().getStatusCode();
        System.out.println("Work->UmengNotification->Post Body : " + msg.getPostBody());
        System.out.println("Work->UmengNotification->Response Code : " + status);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println("Work->UmengNotification->" + result.toString());
        return true;
    }

	// Upload file with device_tokens to Umeng
	public String uploadContents(String appkey,String appMasterSecret,String contents) throws Exception {
		// Construct the json string
		JSONObject uploadJson = new JSONObject();
		uploadJson.put("appkey", appkey);
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		uploadJson.put("timestamp", timestamp);
		uploadJson.put("content", contents);
		// Construct the request
		String url = host + uploadPath;
		String postBody = uploadJson.toString();
		String sign = DigestUtils.md5Hex(("POST" + url + postBody + appMasterSecret).getBytes("utf8"));
		url = url + "?sign=" + sign;
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);
		StringEntity se = new StringEntity(postBody, "UTF-8");
		post.setEntity(se);
		// Send the post request and get the response
		HttpResponse response = client.execute(post);
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		// Decode response string and get file_id from it
		JSONObject respJson = JSONObject.fromObject(result.toString());
		String ret = respJson.getString("ret");
		if (!ret.equals("SUCCESS")) {
			throw new Exception("Failed to upload file");
		}
		JSONObject data = respJson.getJSONObject("data");
		String fileId = data.getString("file_id");
		// Set file_id into rootJson using setPredefinedKeyValue
		
		return fileId;
	}

	//发送消息统一入口(单播)
	public void sendMsgUnicast(PushMassageVo vo) throws Exception {
		//ios
//		if (vo.getDevice().length() > 44) {
		if(!vo.isAndroid()){
			IOSUnicast unicast = new IOSUnicast(ThirdPart.work_umeng_ios_appkey, ThirdPart.work_umeng_ios_appMasterSecret);
			unicast.setDeviceToken(vo.getDevice());
			unicast.setAlert(vo.getContent());
			unicast.setBadge(0);
			unicast.setSound("default");
			unicast.setProductionMode(!ThirdPart.work_push_debug);
			// Set customized fields
			unicast.setCustomizedField("catalog_code", vo.getCatalogCode());
			if(!StringUtil.isEmpty(vo.getAction())){
				unicast.setCustomizedField("action", vo.getAction());
			}
			send(unicast);
		}
		//android
		else {
			AndroidUnicast unicast = new AndroidUnicast(ThirdPart.work_umeng_android_appkey,ThirdPart.work_umeng_android_appMasterSecret);
			unicast.setDeviceToken(vo.getDevice());
			unicast.setTicker(vo.getTicker());
			unicast.setTitle(vo.getTitle());
			unicast.setText(vo.getContent());
			unicast.goCustomAfterOpen(new JSONObject());
			unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			unicast.setProductionMode(!ThirdPart.work_push_debug);
			// Set customized fields
			unicast.setExtraField("catalog_code", vo.getCatalogCode());
			if(!StringUtil.isEmpty(vo.getAction())){
				unicast.setExtraField("action", vo.getAction());
			}
			send(unicast);
		}
	}

	//发送消息统一入口(列播)
	public void sendMsgListcast(PushMassageVo vo,String tokens,String client) throws Exception {
		//ios
		if(StringUtil.isEmpty(client)){
			return;
		}
		if (client.equals(CommArray.EnumClient._iphone.getCode())) {
			IOSListcast listcast = new IOSListcast(ThirdPart.work_umeng_ios_appkey, ThirdPart.work_umeng_ios_appMasterSecret);
			listcast.setDeviceTokens(tokens);
			listcast.setAlert(vo.getContent());
			listcast.setBadge(0);
			listcast.setSound("default");
			listcast.setProductionMode(!ThirdPart.work_push_debug);
			// Set customized fields
			listcast.setCustomizedField("catalog_code", vo.getCatalogCode());
			if(!StringUtil.isEmpty(vo.getAction())){
				listcast.setCustomizedField("action", vo.getAction());
			}
			send(listcast);
		}
		//android
		else if(client.equals(CommArray.EnumClient._android.getCode())){
			AndroidListcast listcast = new AndroidListcast(ThirdPart.work_umeng_android_appkey,ThirdPart.work_umeng_android_appMasterSecret);
			listcast.setDeviceTokens(tokens);
			listcast.setTicker(vo.getTicker());
			listcast.setTitle(vo.getTitle());
			listcast.setText(vo.getContent());
			listcast.goCustomAfterOpen(new JSONObject());
			listcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			listcast.setProductionMode(!ThirdPart.work_push_debug);
			// Set customized fields
			listcast.setExtraField("catalog_code", vo.getCatalogCode());
			if(!StringUtil.isEmpty(vo.getAction())){
				listcast.setExtraField("action", vo.getAction());
			}
			send(listcast);
		}
	}

}
