package com.homevip.util.image;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * 图片下载
 * 
 * @author Administrator
 * 
 */
public class ImageDownLoad {
	static final Log _Log = LogFactory.getLog(ImageDownLoad.class);

	private ImageDownLoad() {
	}

	/**
	 * 根据etag,lastModified下载图片
	 * 
	 * @param src
	 *            图片地址
	 * @param etag
	 *            标记
	 * @param lastModified
	 *            最后修改时间
	 * @param filepath
	 *            保存的文件路径
	 * @return ImageTag 下载成功返回图片信息，失败为null
	 * @throws Exception
	 */
	static public ImageTag download(String src, String etag, String lastModified, File filepath)
			throws Exception {
		try {
			if (src == null || src.toLowerCase().indexOf("http://") == -1) {
				throw new Exception("Error url");
			}
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
			HttpClient httpClient = new DefaultHttpClient(httpParams);
			HttpGet get = new HttpGet(src);
			// 文件标识
			if (etag != null && etag.length() > 1)
				get.addHeader("If-None-Match", etag);
			// 最后修改时间
			if (lastModified != null && lastModified.length() > 1)
				get.addHeader("If-Modified-Since", lastModified);

			HttpResponse response = httpClient.execute(get);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				ImageTag imageTag = new ImageTag();
				Header[] headrs = response.getAllHeaders();
				for (Header header : headrs) {
					if ("ETag".equals(header.getName()))
						imageTag.setEtag(header.getValue());
					if ("Last-Modified".equals(header.getName()))
						imageTag.setLastModified(header.getValue());
					if ("Content-Type".equals(header.getName()))
						imageTag.setContentType(header.getValue());
					if ("Content-Length".equals(header.getName())) {
						int contentLength = 0;
						try {
							contentLength = Integer.parseInt(header.getValue());
						} catch (Exception e) {
							contentLength = 0;
						}
						imageTag.setContentLength(contentLength);
					}
				}

				String fileType = getFileType(imageTag.getContentType());
				if (fileType != null) {
					InputStream in = response.getEntity().getContent();
					// String fileName = DateTimeUtil.formatDateTime(new Date(),
					// "yyyyMMddHHmmss")
					// + RandomStringUtils.random(2, true, true) + "." +
					// fileType;
					// String saveName = SystemConfig.IMG_ORI + "/" + fileName;
					// String savePath = SystemConfig.IMG_ABS_PATH + saveName;
					// String filename=filepath+"." + fileType;
					// File storeFile = new File(filename);
					DataOutputStream out = new DataOutputStream(new FileOutputStream(filepath));
					byte[] buffer = new byte[4096];
					int count = 0;
					while ((count = in.read(buffer)) > 0) {
						out.write(buffer, 0, count);
					}
					out.close();
					in.close();

					// if (storeFile.exists()) { // 添加路径 /ori/x.jpg
					imageTag.setSrc(src);
					imageTag.setSaveName(filepath.getPath());
					imageTag.setFileName(filepath.getName());
					imageTag.setContentType(fileType);
					imageTag.setAbsFilePath(filepath.getAbsolutePath());
					return imageTag;
					// }
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	/**
	 * 获取支持文件格式
	 * 
	 * @param contentType
	 * @return
	 */
	static private String getFileType(String contentType) {
		String fileType = null;
		if ("image/bmp".equals(contentType))
			fileType = "bmp";
		if ("image/gif".equals(contentType))
			fileType = "gif";
		if ("image/jpeg".equals(contentType))
			fileType = "jpg";
		if ("image/png".equals(contentType))
			fileType = "png";
		if ("video/quicktime".equals(contentType))
			fileType = "mov";
		return fileType;
	}

	public static void main(String args[]) {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					ImageTag it = download("http://pic.pedaily.cn/201202/20120207@5711.jpg", "", "", new File("c:\\aa.jpg"));
					System.out.println(this + ":" + it);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					ImageTag it = download(
							"http://images.qianlong.com/mmsource/images/2008/03/03/qlysguwen080303032.bmp",
							"", "", new File("c:\\bb.jpg"));
					System.out.println(this + ":" + it);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					ImageTag it = download(
							"http://img229.ph.126.net/g3dA8cMkWM1o1wxwrp4ZvQ==/1469299378430685330.png",
							"", "", new File("c:\\cc.jpg"));
					System.out.println(this + ":" + it);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Thread t4 = new Thread(new Runnable() {
			public void run() {
				try {
					ImageTag it = download(
							"http://www.faithmedia.com.cn/UploadFile/2008414115810499.gif", "", "", new File("c:\\dd.jpg"));
					System.out.println(this + ":" + it);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}