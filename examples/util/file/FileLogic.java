package com.homevip.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.homevip.util.em.DataUtil;
import com.homevip.util.em.ObjectPool;

public class FileLogic {
	/**
	 * 获取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 返回文件内容
	 */
	public static String getFileData(String filePath, String fileEncode) {
		return getFileData(new File(filePath), fileEncode);
	}

	/**
	 * 获取文件内容
	 * 
	 * @param file
	 *            文件
	 * @return 返回文件内容
	 */
	public static String getFileData(File file, String fileEncode) {
		ObjectPool op = ObjectPool.getInstance();
		StringBuilder data = op.getStringBuilder();

		// FileReader fr = null;//用这个会乱码
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		String lineData = null;
		try {
			// fr = new FileReader(file);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, fileEncode);
			// br = new BufferedReader(fr);
			br = new BufferedReader(isr);
			lineData = br.readLine();
			while (lineData != null) {
				data.append(lineData);
				data.append("\r\n");
				lineData = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// try {
			// if (fr != null)
			// fr.close();
			// } catch (IOException e) {
			// LoggerProxy.warnLogger(0, "IO异常！！！");
			// e.printStackTrace();
			// }
			try {
				if (isr != null)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String dataStr = data.toString();
		op.ccStringBuilder(data);
		return dataStr;
	}

	/**
	 * 获取远程文件内容
	 * 
	 * @param fileUrl
	 *            网络url
	 * @return 返回文件内容
	 */
	public static String getRemoteFileData(String fileUrl) {
		ObjectPool op = ObjectPool.getInstance();
		StringBuilder data = op.getStringBuilder();

		URL url = null;
		HttpURLConnection httpConnection = null;
		int responseCode = 0;
		InputStream in = null;
		try {
			url = new URL(fileUrl);
			httpConnection = (HttpURLConnection) url.openConnection();
			responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = httpConnection.getInputStream();
				byte[] b = new byte[1024];
				int i = in.read(b);
				while (i != -1) {
					data.append(new String(b));
					b = new byte[1024];
					i = in.read(b);
				}
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String dataStr = data.toString();
		op.ccStringBuilder(data);
		return dataStr;
	}

	/**
	 * 把数据保存到文件
	 * 
	 * @param data
	 *            数据
	 * @param filePath
	 *            文件路径
	 * @param isCover
	 *            是否覆盖
	 * @return 0,保存成功；1，文件已经存在,保存失败；
	 */
	public static int saveData2File(String data, String filePath,
			boolean isCover, String fileEncode) {
		File file = null;
		// FileWriter fw = null;//用这个会乱码
		FileOutputStream fos = null;
		Writer out = null;
		BufferedWriter bw = null;

		try {
			file = new File(filePath);
			if (!isCover && file.exists()) {
				return 1;
			}
			fos = new FileOutputStream(file);
			out = new OutputStreamWriter(fos, fileEncode);
			// fw = new FileWriter(file);
			bw = new BufferedWriter(out);
			bw.write(data);
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// try {
			// if (fw != null)
			// fw.close();
			// } catch (IOException e) {
			// LoggerProxy.warnLogger(0, "IO异常！！！");
			// e.printStackTrace();
			// }
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static void createDir(String filePath) {
		if (isFileExists(filePath)) {
			return;
		} else {
			File f = new File(filePath);
			f.mkdirs();
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 判断结果
	 */
	public static boolean isFileExists(String filePath) {
		boolean rs = false;
		File file = new File(filePath);
		if (file.exists()) {
			rs = true;
		}
		return rs;
	}
	
	public static void copyFile(String oldPath, String newPath) {
		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		File newfile = new File(newPath);
		InputStream inStream = null;
		FileOutputStream fs = null;
		if (oldfile.exists()) {
			try {
				inStream = new FileInputStream(oldPath);
				if(!newfile.exists()){
					newfile.createNewFile();
				}
				fs = new FileOutputStream(newfile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(inStream != null){
					try {
						inStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fs != null){
					try {
						fs.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static String createDateSplitDirYYYYMM(String basePath){
		if(DataUtil.isBlank(basePath)){
			return null;
		}
		
		Date date = new Date();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMM");
		String ym = sDateFormat.format(date);
		String targetPath = basePath + "/" + ym;
		// 判断目录是否存在
		String[] paths = targetPath.split("/");
		if (paths.length > 1) {
			for (int i = 1; i < paths.length; i++) {
				String tempPath = paths[0];
				for (int j = 1; j <= i; j++) {
					tempPath += "/" + paths[j];
				}
				File ymDir = new File(tempPath);
				if (!ymDir.exists()) {
					ymDir.mkdir();
				}
			}
		}
		
		return targetPath;
	}
	
	public static String createDateSplitDirYYYYMMDD(String basePath){
		if(DataUtil.isBlank(basePath)){
			return null;
		}
		
		Date date = new Date();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMM-dd");
		String ymd = sDateFormat.format(date);
		String[] ymdArr = ymd.split("-");
		String targetPath = basePath + "/" + ymdArr[0] + "/" + ymdArr[1];
		// 判断目录是否存在
		String[] paths = targetPath.split("/");
		if (paths.length > 1) {
			for (int i = 1; i < paths.length; i++) {
				String tempPath = paths[0];
				for (int j = 1; j <= i; j++) {
					tempPath += "/" + paths[j];
				}
				File ymDir = new File(tempPath);
				if (!ymDir.exists()) {
					ymDir.mkdir();
				}
			}
		}
		
		return targetPath;
	}
	public static String createDateSplitDirYYYYMMDDHH(String basePath){
		if(DataUtil.isBlank(basePath)){
			return null;
		}
		
		Date date = new Date();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMM-dd-HH");
		String ymd = sDateFormat.format(date);
		String[] ymdArr = ymd.split("-");
		String targetPath = basePath + "/" + ymdArr[0] + "/" + ymdArr[1] + "/" + ymdArr[2];
		// 判断目录是否存在
		String[] paths = targetPath.split("/");
		if (paths.length > 1) {
			for (int i = 1; i < paths.length; i++) {
				String tempPath = paths[0];
				for (int j = 1; j <= i; j++) {
					tempPath += "/" + paths[j];
				}
				File ymDir = new File(tempPath);
				if (!ymDir.exists()) {
					ymDir.mkdir();
				}
			}
		}
		
		return targetPath;
	}
	
	public static void main(String[] args){
//		String data = getRemoteFileData("http://www.51homevip.com/portal/web/images/home-top.png");
//		saveData2File(data, "d:/aa.png", false, "UTF-8");
		String path = createDateSplitDirYYYYMMDDHH("d:/test");
		System.out.println(path);
	}
}
