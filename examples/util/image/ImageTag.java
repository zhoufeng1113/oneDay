package com.homevip.util.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 图片
 * @author Administrator
 *
 */
public class ImageTag {
	private String tag;// 原<img>标签
	private String src;// 图片url地址
	private String etag;//文件标识
	private String lastModified;//最后修改时间
	private String contentType;//图片的类型
	private int contentLength;//图片长度
	private String saveName;//保存到文件表名称 /xx/xx.jpg
	private String fileName;//本地文件名称
	private String absFilePath;//文件绝对路径
	
	private int width;//源图宽
	private int height;//源图高
	
	public ImageTag() {
		
	}
	
	public ImageTag(String src) {
		this.src = src;
	}
	
	/**
	 * 保存路径
	 * 源文件路径
	 */
	public ImageTag(String saveName, String src) {
		this.saveName = saveName;
		this.src = src;
	}
	

	public boolean equals(Object obj) {
		if (obj instanceof ImageTag) {
			ImageTag it = (ImageTag) obj;
			if (this.src != null && this.src.equals(it.src))
				return true;
		}
		return false;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAbsFilePath() {
		return absFilePath;
	}

	public void setAbsFilePath(String absFilePath) {
		this.absFilePath = absFilePath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	/**
	 * 获取图片高宽
	 */
	public void initSize() {
		if (this.absFilePath != null)
		{
			try {
				File file = new File(absFilePath);
				if (file.exists())
				{
					BufferedImage bufferedImage = ImageIO.read(file);
					this.width = bufferedImage.getWidth();
					this.height = bufferedImage.getHeight();
					bufferedImage = null;
					file = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
