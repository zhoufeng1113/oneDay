package com.homevip.util.kexin;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author yuanshuo
 *
 */
public class KexinClient {
		private String kexinAKey;
		private String kexinSKey;
		private String kexinApiUrl;
		protected String scheme;
		protected String hostName;
		protected int port;
		protected Integer connTimeout;
		protected Integer readTimeout;
		protected String charset = "UTF-8";
		protected String mimeType;
		
		/**
		 * 初始化入口
		 * @param akey	client_id
		 * @param skey	password
		 * @param url	请求URL 可以在URL中指定返回json 或 xml
		 */
		public KexinClient (String akey, String skey, String url) {
			this.kexinAKey = akey;
			this.kexinSKey = skey;
			this.kexinApiUrl = url;
			try {
				URI uri = new URI(url);
				this.scheme = uri.getScheme();
				this.hostName = uri.getHost();
				this.port = uri.getPort();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		/**
		 * 设置连接超时
		 * @param time 毫秒
		 */
		public void setConnTimeout(Integer time) {
			this.connTimeout = time;
		}
		
		/**
		 * 设置请求超时
		 * @param time 毫秒
		 */
		public void setReadTimeout(Integer time) {
			this.readTimeout = time;
		}
		
		/**
		 * 设置编码
		 * @param charset 默认UTF-8
		 */
		public void setCharset(String charset) {
			this.charset = charset;
		}
		
		/**
		 * 设置传输类型
		 * @param mimeType
		 */
		public void setMimeType(String mimeType) {
			this.mimeType = mimeType;
		}
		


		
		/**
		 * get请求
		 * @return
		 * @throws 
		 */
		
	public	String getVerification(String idCardCode,String idCardName) throws ConnectTimeoutException, SocketTimeoutException, Exception {
			HttpHost host = new HttpHost(hostName, port, scheme);
			HttpClient client = kexinApiUrl.startsWith("https") ? createSSLInsecureClient() : HttpClients.custom().build();    
			idCardName=URLEncoder.encode(idCardName, "utf-8");


			HttpGet get = new HttpGet(kexinApiUrl+"?idCardCode="+idCardCode+"&idCardName="+idCardName);
           
	        String result = "";
	        try {
	        	//自定义客户端请求参数设置
	            Builder customReqConf = RequestConfig.custom();  
	            if (connTimeout != null) {  
	                customReqConf.setConnectTimeout(connTimeout);  
	            }  
	            if (readTimeout != null) {  
	                customReqConf.setSocketTimeout(readTimeout);  
	            }
	            //保存参数
	            get.setConfig(customReqConf.build());  
	            
	            HttpResponse res;  
	            //创建并初始化认证提供者
	            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		        credentialsProvider.setCredentials(new AuthScope(host.getHostName(), host.getPort()), new UsernamePasswordCredentials(kexinAKey, kexinSKey));
		        HttpClientContext localContext = HttpClientContext.create();		        
		        
		        localContext.setCredentialsProvider(credentialsProvider);

//		        //发送请求
	            res = client.execute(get,localContext);
		        //保存请求结果
	            result = IOUtils.toString(res.getEntity().getContent(), charset);  
	        } finally {  
	        	//释放连接
	        	get.releaseConnection();  
	            if (kexinApiUrl.startsWith("https") && client != null  
	                    && client instanceof CloseableHttpClient) {  
	                ((CloseableHttpClient) client).close();  
	            }  
	        }  
	        return result;  
		}
		
		
		
		
		
		
		
		
		
		
		
		
		/**
		 * 建立SSL连接
		 * @return
		 * @throws GeneralSecurityException
		 */
		private static CloseableHttpClient createSSLInsecureClient()  
	            throws GeneralSecurityException {  
	        try {  
	            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(  
	                    null, new TrustStrategy() {  
	                        public boolean isTrusted(X509Certificate[] chain,  
	                                String authType) throws CertificateException {  
	                            return true;  
	                        }  
	                    }).build();  
	            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
	                    sslContext, new X509HostnameVerifier() {  
	  
	                        @Override  
	                        public boolean verify(String arg0, SSLSession arg1) {  
	                            return true;  
	                        }  
	  
	                        @Override  
	                        public void verify(String host, SSLSocket ssl)  
	                                throws IOException {  
	                        }  
	  
	                        @Override  
	                        public void verify(String host, X509Certificate cert)  
	                                throws SSLException {  
	                        }  
	  
	                        @Override  
	                        public void verify(String host, String[] cns,  
	                                String[] subjectAlts) throws SSLException {  
	                        }  
	  
	                    });  
	            return HttpClients.custom().setSSLSocketFactory(sslsf).build();  
	        } catch (GeneralSecurityException e) {  
	            throw e;  
	        }  
	    }  
}
