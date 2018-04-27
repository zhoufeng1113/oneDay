package com.homevip.util.thridparty.esign;

import com.timevale.esign.sdk.tech.service.EsignsdkService;
import com.timevale.esign.sdk.tech.service.factory.EsignsdkServiceFactory;
import com.timevale.tech.sdk.bean.HttpConnectionConfig;
import com.timevale.tech.sdk.bean.ProjectConfig;
import com.timevale.tech.sdk.bean.SignatureConfig;
import com.timevale.tech.sdk.constants.HttpType;

public class EsignsdkUtils {

	public static void initEsignsdkService(boolean ambient) {
		
		if(ambient) {
			initTestEsignsdkService();
		}else {
			initProductEsignsdkService();
		}
		
	}
	
	private static void initTestEsignsdkService() {
		EsignsdkService esignsdkService = EsignsdkServiceFactory.instance();
		
		ProjectConfig projectConfig = new ProjectConfig();
		//测试环境
		projectConfig.setProjectId("1111563517");
		projectConfig.setProjectSecret("95439b0863c241c63a861b87d1e647b7");
		projectConfig.setItsmApiUrl("http://121.40.164.61:8080/tgmonitor/rest/app!getAPIInfo2");
		
		HttpConnectionConfig httpConnectConfig = new HttpConnectionConfig();
		httpConnectConfig.setRetry(5);
		httpConnectConfig.setHttpType(HttpType.HTTP);
		SignatureConfig signatureConfig = new SignatureConfig();
		
		esignsdkService.init(projectConfig, httpConnectConfig, signatureConfig);
	}
	
	private static void initProductEsignsdkService() {
		EsignsdkService esignsdkService = EsignsdkServiceFactory.instance();
		//正式环境
		ProjectConfig projectConfig = new ProjectConfig();
		projectConfig.setProjectId("1111564765");
		projectConfig.setProjectSecret("cbb87de10a9c01102d78c1e558e53263");
		projectConfig.setItsmApiUrl("http://itsm.tsign.cn/tgmonitor/rest/app!getAPIInfo2");

		HttpConnectionConfig httpConnectConfig = new HttpConnectionConfig();
		httpConnectConfig.setRetry(5);
		httpConnectConfig.setHttpType(HttpType.HTTP);
		SignatureConfig signatureConfig = new SignatureConfig();
		
		esignsdkService.init(projectConfig, httpConnectConfig, signatureConfig);
	}
}
