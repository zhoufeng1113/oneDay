package com.homevip.util.image;

import java.io.File;
import java.util.Random;

import com.homevip.util.em.ObjectPool;
import com.homevip.util.file.FileLogic;
import com.homevip.util.http.NetworkUtil;

public class WxImgDownLoad {
	public static Object[] downloadImg(String access_token, String mediaId, String realBasePath){
		String targetPath = FileLogic.createDateSplitDirYYYYMMDDHH(realBasePath);
		String buildPath = targetPath.replace(realBasePath, "");
		
		String wxUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + access_token + "&media_id=" + mediaId;
		
		ObjectPool op = ObjectPool.getInstance();
		Random random = new Random();
		int ri1 = random.nextInt(10);
		int ri2 = random.nextInt(10);
		int ri3 = random.nextInt(10);
		int ri4 = random.nextInt(10);
		StringBuilder tempStr = op.getStringBuilder();
		tempStr.append(System.currentTimeMillis()).append(ri1).append(ri2).append(ri3).append(ri4).append(".jpg");
		String fileName =  tempStr.toString();
		op.ccStringBuilder(tempStr);
		String targetFile = targetPath + "/" + fileName;
		boolean hasDown = NetworkUtil.downloadImage(wxUrl, targetFile);
		
		boolean rs = true;
		if(!hasDown){
			File file = new File(targetFile);
			if(file.exists()){
				file.delete();
			}
			rs = false;
		}
		return new Object[]{buildPath, fileName, targetFile, rs};
	}
}
