package com.homevip.util.image;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.homevip.util.em.DataUtil;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/***
 * 图片处理工具 
 */
public class ImageUtil {
	static final Log _Log = LogFactory.getLog(ImageUtil.class);

	private BufferedImage bufferedImage;
	private String fileName;
	/** 图片格式名 */
	private String formatName;
	/**图片大小**/
	private long fileSize;
	/**图片保存路径**/
	private String savePath; 
	/**原图片保存路径**/
	private String oriPath; 
	/**要处理的宽度**/
	private int pwidth;
	/**要处理的高度**/
	private int pheight;
	/**水印图片**/
	private String waterMarkPath;
	/**默认是按照比例缩放的 **/
	private boolean isKeepAspectRatio = true;
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getPwidth() {
		return pwidth;
	}

	public void setPwidth(int pwidth) {
		this.pwidth = pwidth;
	}

	public int getPheight() {
		return pheight;
	}

	public void setPheight(int pheight) {
		this.pheight = pheight;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getOriPath() {
		return oriPath;
	}

	public void setOriPath(String oriPath) {
		this.oriPath = oriPath;
	}

	public String getWaterMarkPath() {
		return waterMarkPath;
	}

	public void setWaterMarkPath(String waterMarkPath) {
		this.waterMarkPath = waterMarkPath;
	}

	public boolean isKeepAspectRatio() {
		return isKeepAspectRatio;
	}

	public void setKeepAspectRatio(boolean isKeepAspectRatio) {
		this.isKeepAspectRatio = isKeepAspectRatio;
	}

	/*****
	 * 将普通文件流变成 BufferedImage方式
	 * @param imageFile
	 * @throws IOException
	 */
	public ImageUtil(File imageFile) throws IOException {
		//this.savePath = savepath;
		ImageInputStream in = ImageIO.createImageInputStream(imageFile);
		if (null != in) {
			Iterator<ImageReader> it = ImageIO.getImageReaders(in);
			if (it.hasNext()) {
				ImageReader reader = it.next();
				// 取得图片格式名
				formatName = reader.getFormatName();
				// 读取图片为bufferedImage
				ImageReadParam imagereadparam = reader.getDefaultReadParam();
				reader.setInput(in, true, true);
				bufferedImage = reader.read(0, imagereadparam);
				reader.dispose();
				in.close();
			}
		}
		fileName = imageFile.getAbsolutePath();
	}
	
	/****
	 * 直接将图片变成BufferedImage
	 */
	public ImageUtil(String oripath) throws IOException {
		this.oriPath = oripath;
		this.bufferedImage = Thumbnails.of(oripath).asBufferedImage();  
	}
	
	/**
	 * 普通的缩放图片
	 * @throws IOException 
	 * 方法名: resize <br />  
	 * 描述:使用thumbnailator.jar <br /> 
	 * 参数：<br />  
	 * @return void <br />    
	 * @throws
	 */
	public void resize() throws IOException {
		int nWidth = bufferedImage.getWidth(); //原图片宽
		int nHeight = bufferedImage.getHeight(); //原图片高
		if(nWidth>pwidth || nHeight>pheight){
			 Thumbnails.of(this.bufferedImage)
			           .size(pwidth, pheight)
			           .keepAspectRatio(this.isKeepAspectRatio)
			           .outputQuality(0.8f)
			           .toFile(savePath);
		} /*else {
			 Thumbnails.of(bufferedImage)
			    .size(nWidth, nHeight)
			    .toFile(savePath);			
		}*/
	}
	
	/*****
	 * 处理水印图片
	 */
	public void resizeAndWaterMark() throws IOException {
		int nWidth = bufferedImage.getWidth(); //原图片宽
		int nHeight = bufferedImage.getHeight(); //原图片高
		if(nWidth>pwidth || nHeight>pheight){
			 Thumbnails.of(bufferedImage)
			           .size(pwidth, pheight)
			           .keepAspectRatio(this.isKeepAspectRatio)
			           //watermark(位置，水印图，透明度) 
			           .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(this.waterMarkPath)), 0.5f)   
			           .outputQuality(1.0f)
			           .toFile(savePath);
		}else{
			 Thumbnails.of(bufferedImage)
			    .size(nWidth, nHeight)
	            .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(this.waterMarkPath)), 0.5f)   
	            .outputQuality(1.0f)
	            .toFile(savePath);
		}
	}
	
  
	/**
	 * 获取文件后缀
	 * @param file
	 * @return
	 */
	private static String getFileSuffix(File file){
		String suffix = null;
        if(file.getName().indexOf(".") > -1) {
            suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return suffix;
	}

	private static boolean resize(Image img, String suffix, int w, int h, String targetFileName) {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		File destFile = new File(targetFileName);
		try {
			ImageIO.write(image, suffix, destFile);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public static boolean captureCenter(String fileName, int w, int h, String targetFileName, boolean delSourceFile) {
		if(DataUtil.isBlank(fileName)){
			return false;
		}
		File file = new File(fileName);//读入文件
		if(!file.exists()){
			return false;
		}
		
		String suffix = getFileSuffix(file);
		
		Image img = null;
		try {
			//构造Image对象
			img = ImageIO.read(file);
		} catch (IOException e) {
		} 
		if(img == null ){
			return false;
		}
		
		int width = img.getWidth(null); //得到源图宽
		int height = img.getHeight(null); //得到源图长
		
		int x = 0;
		int y = 0;
		int toW = 0;
		int toH = 0;
		double toB = (double)w / h;
		double oldB = (double)width / height;
		if(toB > oldB){
			toW = width;
			toH = toW * h / w;
			y = (height - toH) / 2;
		}else{
			toH = height;
			toW = w * toH / h;
			x = (width - toW) / 2;
		}
		Rectangle rect = new Rectangle(x, y, toW, toH);
		
		FileInputStream fis = null;
		ImageInputStream iis = null;
		File destFile = new File(targetFileName);
		try {
			fis = new FileInputStream(file);
			iis = ImageIO.createImageInputStream(fis);
			ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
            reader.setInput(iis,true);
            ImageReadParam param = reader.getDefaultReadParam();
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    		image.getGraphics().drawImage(bi, 0, 0, w, h, null);
            
            ImageIO.write(image, suffix, destFile);
		} catch (IOException e1) {
			return false;
		}finally{
			if(iis != null){
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(delSourceFile){
			file.delete();
		}
		return true;
	}
	
	/**
	 * 按原比例等比缩放
	 * @param fileName 源文件路径
	 * @param w	最大宽度
	 * @param h 最大高度
	 * @param targetFileName 目标文件路径
	 * @return
	 */
	public static boolean compressWithProportion(String fileName, int w, int h, String targetFileName, boolean delSourceFile){
		if(DataUtil.isBlank(fileName)){
			return false;
		}
		File file = new File(fileName);//读入文件
		if(!file.exists()){
			return false;
		}
		
		Image img = null;
		try {
			//构造Image对象
			img = ImageIO.read(file);
		} catch (IOException e) {
		} 
		if(img == null ){
			return false;
		}
		
		String suffix = getFileSuffix(file);
		
		int width = img.getWidth(null); //得到源图宽
		int height = img.getHeight(null); //得到源图长
		boolean rs = false;
		if (width / height > w / h) {
			int toH = (int) (height * w / width);
			rs = resize(img, suffix, w, toH, targetFileName);
		} else {
			int toW = (int) (width * h / height);
			rs = resize(img, suffix, toW, h, targetFileName);
		}
		if(rs && delSourceFile){
			file.delete();
		}
		return true;
	}
	
	/**
	 * 不按原比例缩放（固定宽高）
	 * @param fileName 源文件路径
	 * @param w	最大宽度
	 * @param h 最大高度
	 * @param targetFileName 目标文件路径
	 * @return
	 */
	public static boolean compress(String fileName, int w, int h, String targetFileName, boolean delSourceFile){
		if(DataUtil.isBlank(fileName)){
			return false;
		}
		File file = new File(fileName);//读入文件
		if(!file.exists()){
			return false;
		}
		
		Image img = null;
		try {
			//构造Image对象
			img = ImageIO.read(file);
		} catch (IOException e) {
		} 
		if(img == null ){
			return false;
		}
		
		String suffix = getFileSuffix(file);
        
		boolean rs = resize(img, suffix, w, h, targetFileName);
		if(rs && delSourceFile){
			file.delete();
		}
		return true;
	}

	
	public static void main(String[] args) {
		File imageFile = new File("d:/bb.jpg");
		try {
			ImageUtil test = new ImageUtil(imageFile);
			//test.setOriPath("d:/bb.jpg");
			test.setSavePath("d:/bb_l.jpg");
			test.setWaterMarkPath("D:/common/各类图库/P200907221517581176030293.png");
			test.setPheight(801);
			test.setPwidth(801);
			test.resize();
			test.resizeAndWaterMark();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
