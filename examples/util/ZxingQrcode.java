package com.homevip.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.geom.RoundRectangle2D;

/**
 * 二维码生成工具类
 * @author root
 *
 */
public class ZxingQrcode {

	private static final String CODE = "utf-8";
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;

	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int WIDTH = 60;
	// LOGO高度
	private static final int HEIGHT = 60;


	/**
	 * 快速生成 (不带小图标)
	 * @param content 内容
	 * @param outsrc 输出路径　使用jpg
	 * @throws Exception
	 */
	public static void encoderQRCode(String content, String outsrc) throws Exception {
		encoderQRCode(content, outsrc, "jpg", 300, 300, 0, 0, 2, null);
	}
	
	/**
	 * 快速生成
	 * @param content 内容
	 * @throws Exception
	 */
	public static BufferedImage encoderQRCode(String content) throws Exception {
		return encoderQRCode(content, "jpg", 300, 300, 0, 0, 2, null);
	}
	
	/**
	 * 完整参数生成二维码
	 * @param content 生成二维码内容
	 * @param outsrc　输入图片路径
	 * @param format　图片格式 png,jpg,gif
	 * @param width 图片宽
	 * @param height 图片高
	 * @param onColor 字体颜色
	 * @param offColor　背景色
	 * @param level 0 = M ~15%, 1 = L ~7%, 2 = H ~30% 3 = Q ~25%
	 * @param charset 字符集，需要小写
	 * @throws Exception
	 */
	public static void encoderQRCode(String content, String outsrc, String format, int width, int height, int onColor, int offColor, int level, String charset) throws Exception {
		Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		if (charset == null || charset.trim().length() == 0) {
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		}
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.forBits(level));
		hints.put(EncodeHintType.MARGIN, 1);
		
		MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
		if (onColor == 0 && offColor == 0) {
			config = new MatrixToImageConfig();
		}
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		File outFile = new File(outsrc);
		outFile.mkdirs();
		MatrixToImageWriter.writeToFile(bitMatrix, format, new File(outsrc), config);
	}
	/**
	 * 完整参数生成二维码
	 * @param content 生成二维码内容
	 * @param format　图片格式 png,jpg,gif
	 * @param width 图片宽
	 * @param height 图片高
	 * @param onColor 字体颜色
	 * @param offColor　背景色
	 * @param level 0 = M ~15%, 1 = L ~7%, 2 = H ~30% 3 = Q ~25%
	 * @param charset 字符集，需要小写
	 * @throws Exception
	 */
	public static BufferedImage encoderQRCode(String content, String format, int width, int height, int onColor, int offColor, int level, String charset) throws Exception {
		Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		if (charset == null || charset.trim().length() == 0) {
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		}
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.forBits(level));
		hints.put(EncodeHintType.MARGIN, 1);
		
		MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
		if (onColor == 0 && offColor == 0) {
			config = new MatrixToImageConfig();
		}
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage bufferedImage = toBufferedImage(bitMatrix);
		return bufferedImage;
	}
	/**
	 * 生成一维码，写到文件中
	 *
	 * @author wuhongbo
	 * @param str
	 * @param height
	 * @param file
	 * @throws IOException
	 */
	public static void getBarcodeWriteFile(String str, Integer width,
										   Integer height, File file) throws IOException
	{
		BufferedImage image = getBarcode(str, width, height);
		ImageIO.write(image, "png", file);
	}


	/**
	 * 生成一维码（128）
	 *
	 * @author wuhongbo
	 * @param str
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getBarcode(String str, Integer width,
										   Integer height)
	{

		if (width == null || width < 200)
		{
			width = 200;
		}

		if (height == null || height < 50)
		{
			height = 50;
		}

		try
		{
			// 文字编码
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, CODE);

			BitMatrix bitMatrix = new MultiFormatWriter().encode(str,
					BarcodeFormat.CODE_128, width, height, hints);

			return toBufferedImage(bitMatrix);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换成图片
	 *
	 * @author wuhongbo
	 * @param matrix
	 * @return
	 */
	private static BufferedImage toBufferedImage(BitMatrix matrix)
	{
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @param photoName
	 * 			  生成二维码文件名
	 * @throws Exception
	 */
	public static String encodeHasLOGO(String content, String imgPath,
								String destPath,String photoName,boolean needCompress) throws Exception {
		BufferedImage image = ZxingQrcode.createImage(content, imgPath,needCompress);
		mkdirs(destPath);
//		String destFileName = new Random().nextInt(99999999) + ".jpg";
		String destFileName = photoName + ".jpg";
		String destFileFullPath = destPath + "/" + destFileName;
		ImageIO.write(image, FORMAT_NAME, new File(destFileFullPath));

		return destFileFullPath;
	}

	/**
	 * 生成和解析带LOGO的二维码
	 * @param content
	 * @param imgPath
	 * @param needCompress
	 * @return
	 * @throws Exception
	 */
	private static BufferedImage createImage(String content, String imgPath,
											 boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		// hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
		bitMatrix = deleteWhite(bitMatrix);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
						: 0xFFFFFFFF);
			}
		}
		if (imgPath == null || "".equals(imgPath)) {
			return image;
		}
		// 插入图片
		ZxingQrcode.insertImage(image, imgPath, needCompress);
		return image;
	}

	/**
	 * 插入LOGO
	 * @param source
	 *            二维码图片
	 * @param imgPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, String imgPath,
									boolean needCompress) throws Exception {
		File file = new File(imgPath);
		if (!file.exists()) {
			System.err.println("" + imgPath + "   该文件不存在！");
			return;
		}
		Image src = ImageIO.read(new File(imgPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = QRCODE_SIZE / 2- 68;
		int y = QRCODE_SIZE / 2- 68;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
	 *
	 * @author lanyuan Email: mmm333zzz520@163.com
	 * @date 2013-12-11 上午10:16:36
	 * @param destPath
	 *            存放目录
	 */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		// 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	/**
	 * 删除四周的白边
	 */
	private static BitMatrix deleteWhite(BitMatrix matrix) {
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}

	public static void main(String [] args) throws Exception {
//		encoderQRCode(UUID.randomUUID().toString().substring(0,8), "d:/"+UUID.randomUUID().toString().substring(0,32)+".jpg");
		encodeHasLOGO(UUID.randomUUID().toString().substring(0,8),"E:/headImage.jpg","E:/","returnName",true);
	}
}
