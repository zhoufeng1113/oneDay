package com.homevip.util.file;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.homevip.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件操作
 * 
 * @author elwingao
 * 
 */

public class FileUtil {
	private static final Log _Log = LogFactory.getLog(FileUtil.class);
	static final int BUFFER = 8192;

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			_Log.error("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

		} catch (Exception e) {
			_Log.error("新建目录操作出错");
			e.printStackTrace();

		}

	}

	// 检测文件是否存在
	public static boolean exists(String filePath) {
		try {
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				return false;
			} else
				return true;
		} catch (Exception e) {
			_Log.error("找不到文件");
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			if (myDelFile.isFile()){
				myDelFile.delete();
			}

		} catch (Exception e) {
			_Log.error("删除文件操作出错");
		}
	}
	
	/**
	 * 删除匹配的文件
	 * 
	 * @param path
	 *            String 文件路径
	 * @param name
	 *            String 名称 
	 * @return boolean
	 */
	public static void delFile(String path, String name) {
		try {
			File f=new File(path);
			File lst[]=f.listFiles(new FileFilterUtil(name)); 	
			for(int i=0;i<lst.length;i++){ 
				if(lst[i].isFile()){ 
					lst[i].delete();
				}
			}	

		} catch (Exception e) {
			_Log.error("删除文件操作出错");
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            String 文件夹路径及名称 如c:/fqf
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (myFilePath.isDirectory()){
				myFilePath.delete(); // 删除空文件夹
			}

		} catch (Exception e) {
			_Log.error("删除文件夹操作出错");
		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				//生成目标文件夹
				newPath = newPath.replace("\\", "/");
				newFolder(newPath.substring(0,newPath.lastIndexOf("/")));
				inStream = new FileInputStream(oldPath); // 读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				
			}
		} catch (Exception e) {
			_Log.error("复制单个文件操作出错");
		}finally{
			try {
				if(null != inStream)inStream.close();
				if(null != fs)fs.close();
			} catch (Exception e2) {}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {
		FileInputStream input = null;
		FileOutputStream output = null;
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					input = new FileInputStream(temp);
					output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			_Log.error("复制整个文件夹内容操作出错");
		} finally{
			try {
				if( null != output)output.close();
				if( null != input)input.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		//TODO 因东南无端丢失图片，故先保留原图
		//delFile(oldPath);

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 创建目录
	 */
	public static boolean createFileDir(String dirName) {
		boolean CreateOk = false;

		File f = new File(dirName);
		if (!f.exists()) {
			CreateOk = true;
			f.mkdirs();
		} else
			CreateOk = true;

		return CreateOk;
	}  
  
    /**
     * 压缩文件 zip
     * @param file
     * @param out
     */
    public static void compressFile(File file,ZipOutputStream out) {
        if (!file.exists()) {  
            return;  
        }
        BufferedInputStream bis = null ;
        try {  
            bis = new BufferedInputStream(  
                    new FileInputStream(file));  
            ZipEntry entry = new ZipEntry(file.getName());
            out.putNextEntry(entry);  
            int count;  
            byte data[] = new byte[1024 * 512];  
            while ((count = bis.read(data, 0, 1024 * 512)) != -1) {  
                out.write(data, 0, count);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  finally{
        	try{
        		if(null != bis){bis.close();}
        	}catch(Exception e){e.printStackTrace();}
        }
    }


	/**
	 * 解压RAR文件
	 * @param srcRarPath
	 * @param dstDirectoryPath
	 * @throws Exception
	 */
	public static void unrar(String srcRarPath,String dstDirectoryPath)throws Exception {
		if (!srcRarPath.toLowerCase().endsWith(".rar")) {
			System.out.println("非rar文件！");
			return;
		}
		File dstDiretory = new File(dstDirectoryPath);
		if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
			dstDiretory.mkdirs();
		}
		File fol=null,out=null;
		Archive a = null;
		try {
			a = new Archive(new File(srcRarPath));
			if (a != null){
				a.getMainHeader().print(); // 打印文件信息.
				FileHeader fh = a.nextFileHeader();
				while (fh != null){
					if (fh.isDirectory()) { // 文件夹
						// 如果是中文路径，调用getFileNameW()方法，否则调用getFileNameString()方法，还可以使用if(fh.isUnicode())
						if(existZH(fh.getFileNameW())){
							fol = new File(dstDirectoryPath + File.separator
									+ (fh.getFileNameString().substring(fh.getFileNameString().indexOf("\\"),fh.getFileNameString().length())));
						}else{
							fol = new File(dstDirectoryPath + File.separator
									+ fh.getFileNameString());
						}
						fol.mkdirs();
					} else { // 文件
						if(existZH(fh.getFileNameW())){
							out = new File(dstDirectoryPath + File.separator
									+ (fh.getFileNameString().substring(fh.getFileNameString().indexOf("\\"),fh.getFileNameString().length())));
						}else{
							out = new File(dstDirectoryPath + File.separator
									+ (fh.getFileNameString().substring(fh.getFileNameString().indexOf("\\"),fh.getFileNameString().length())));
						}
						try {// 之所以写try，是因为万一这里面有了异常，不影响继续解压.
							if (!out.exists()) {
								if (!out.getParentFile().exists()){// 相对路径可能多级，可能需要创建父目录.
									out.getParentFile().mkdirs();
								}
							}
							FileOutputStream os = new FileOutputStream(out);
							a.extractFile(fh, os);
							os.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					fh = a.nextFileHeader();
				}
				a.close();
				delFolder(fol.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否是中文
	 * @param str
	 * @return
	 */

	public static boolean existZH(String str){
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}

    public static void saveFile(InputStream inputStream,String fileSavePath) {
    	
		try {
			FileOutputStream out = new FileOutputStream(fileSavePath);
			byte buffer[] = new byte[1024];
	        int len = 0;
	        while((len=inputStream.read(buffer))>0){
	            out.write(buffer, 0, len);
	        }
	        inputStream.close();
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
    public static void saveNewFile(String targetFilePath,byte[] sourceStream){
    	
    	FileOutputStream fos = null;
		try {
			
			if(FileUtil.exists(targetFilePath)) {
	        	FileUtil.delFile(targetFilePath);
	        }
			
			fos = new FileOutputStream(targetFilePath);
			fos.write(sourceStream);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }


	/**
	 * 生产文件，如果文件所在路径不存在则生成路径
	 * @param fileName	文件名，带路径
	 * @param isDirectory	是否为路径
	 * @return
	 */
	public static File buildFile(String fileName, boolean isDirectory) {
		File target = new File(fileName);
		if (isDirectory) {
			target.mkdirs();
		} else {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
				target = new File(target.getAbsolutePath());
			}
		}
		return target;

	}
	/**
	 * 解压文件
	 * zipFilePath	zip文件路径
	 * targetPath 	解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录同名的文件夹下
	 */
	public static void unzip(String zipFilePath, String targetPath) throws IOException{
		OutputStream os = null;
		InputStream is = null;
		org.apache.tools.zip.ZipFile zipFile = null;
		try {
			zipFile = new org.apache.tools.zip.ZipFile(zipFilePath);
			String directoryPath = "";
			if (StringUtil.isEmpty(targetPath)){
				directoryPath = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));
			}else{
				directoryPath = targetPath;
			}
			Enumeration entryEnum = zipFile.getEntries();
			if (null != entryEnum){
				org.apache.tools.zip.ZipEntry zipEntry = null;
				while (entryEnum.hasMoreElements()){
					zipEntry = (org.apache.tools.zip.ZipEntry) entryEnum.nextElement();
					if (zipEntry.isDirectory()){
						directoryPath = directoryPath + File.separator + "/";
						System.out.println(directoryPath);
						continue;
					}
					if (zipEntry.getSize() > 0){
						//文件
						File targetFile = FileUtil.buildFile(directoryPath + File.separator + (zipEntry.getName().substring(zipEntry.getName().indexOf("/"),zipEntry.getName().length())), false);
						os = new BufferedOutputStream(new FileOutputStream(targetFile));
						is = zipFile.getInputStream(zipEntry);
						byte[] buffer = new byte[4096];
						int readLen = 0;
						while((readLen = is.read(buffer, 0, 4096)) >= 0){
							os.write(buffer, 0 ,readLen);
						}
						os.flush();
						os.close();
					}else{
						//空目录
						FileUtil.buildFile(directoryPath + File.separator + zipEntry.getName(), true);
					}
				}
			}
		}catch (IOException ex){
			throw ex;
		}finally {
			if (null != is){
				is.close();
			}
			if (null != os){
				os.close();
			}
			if (null != zipFile){
				zipFile.close();
			}
		}
	}

	/**
	 * 压缩文件夹  Zip
	 * @param sourcePath 原文件路径
	 * @param zipPath 目标文件路径  （包括压缩包名字）
	 */
	public static void compressFolder(String sourcePath, String zipPath) {
		FileOutputStream fos = null;
		org.apache.tools.zip.ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath);
			zos = new org.apache.tools.zip.ZipOutputStream(fos);
			zos.setEncoding("gbk");//此处修改字节码方式。
			//createXmlFile(sourcePath,"293.xml");
			writeZip(new File(sourcePath), "", zos);
		} catch (FileNotFoundException e) {
			_Log.error("创建ZIP文件失败",e);
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				_Log.error("创建ZIP文件失败",e);
			}

		}
	}

	private static void writeZip(File file, String parentPath, org.apache.tools.zip.ZipOutputStream zos) {
		if(file.exists()){
			if(file.isDirectory()){//处理文件夹
				parentPath+=file.getName()+File.separator;
				File [] files=file.listFiles();
				if(files.length != 0)
				{
					for(File f:files){
						writeZip(f, parentPath, zos);
					}
				}
				else
				{       //空目录则创建当前目录
					try {
						zos.putNextEntry(new org.apache.tools.zip.ZipEntry(parentPath));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else{
				FileInputStream fis=null;
				try {
					fis=new FileInputStream(file);
					org.apache.tools.zip.ZipEntry ze = new org.apache.tools.zip.ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte [] content=new byte[1024];
					int len;
					while((len=fis.read(content))!=-1){
						zos.write(content,0,len);
						zos.flush();
					}

				} catch (FileNotFoundException e) {
					_Log.error("创建ZIP文件失败",e);
				} catch (IOException e) {
					_Log.error("创建ZIP文件失败",e);
				}finally{
					try {
						if(fis!=null){
							fis.close();
						}
					}catch(IOException e){
						_Log.error("创建ZIP文件失败",e);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
//		delFile("D:\\Projects\\Xtown\\Catalog\\XPSCatalog\\webapp\\myfiles\\company\\1\\product\\11", "9f0141fc-6d07-4276-a196-ddbf5260d495");
//		try {
//			System.out.println(URLEncoder.encode("http://test.51homevip.com/weixin/main/member/good/purchase?id=3&code=monthgood&recommentcode=15625084950","utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		delFile("E:/new/");
//		unzip("F:/A.zip","F:/");
//		unrar("E:/B.rar","F:/");
//		delFolder("F:/A");
		compressFolder("E:/B","E:/Bc.zip");

	}
}
