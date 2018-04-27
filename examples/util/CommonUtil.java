package com.homevip.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.homevip.util.file.FileUtil;
import com.homevip.util.system.Const;


/**
 * 公共方法。
 */
public class CommonUtil {
	
	private static final Log _Log = LogFactory.getLog(CommonUtil.class);

	/**
	 * 剪切缓存文件夹的文件到目标
	 * 
	 * @param source
	 * @param targetPath
	 */
	public static void moveFromTmp(String source, String targetPath) {
		FileUtil.moveFile(Const.UPLOAD_FLODER_TMP + source, targetPath
				+ source);
	}

	/**
	 * 剪切缓存文件夹的文件到目标
	 * 
	 * @param source
	 * @param targetPath
	 */
	public static void moveFromTmp(String source, String targetPath,
			String targetFile) {
		FileUtil.moveFile(Const.UPLOAD_FLODER_TMP + source, targetPath
				+ targetFile);
	}

	/**
	 * 复制文件到指定目标
	 *
	 * @param source
	 * @param targetPath
	 */
	public static void copyFromTmp(String source, String targetPath,
								   String targetFile) {
		FileUtil.copyFile(source, targetPath + targetFile);
	}

	/**
	 * 复制文件到指定目标
	 *

	 */
	public static Integer formatWorkstationid(Integer workstationid) {
		if(workstationid!=null&&workstationid==-1){
			return null;
		}else{
			return workstationid;
		}
	}
	
}