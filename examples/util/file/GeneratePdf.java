package com.homevip.util.file;

import com.homevip.util.StringUtil;
import com.mortennobel.imagescaling.ResampleOp;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import sun.awt.image.ImageFormatException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周枫 on 2017/12/1.
 */
public class GeneratePdf {

    public int getPercent1(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        if (h > w) {
            p2 = 297 / h * 100;
        } else {
            p2 = 210 / w * 100;
        }
        p = Math.round(p2);
        return p;
    }

    public static void yPic(List<BufferedImage> piclist, String outPath) {// 纵向处理图片
        if (piclist == null || piclist.size() <= 0) {
            System.out.println("图片数组为空!");
            return;
        }
        try {
        	BufferedImage imageResult = createImage(piclist);
            File outFile = new File(outPath);
            ImageIO.write(imageResult, "png", outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage  createImage(List<BufferedImage> piclist) {

    	BufferedImage imageResult = null;

    	if (piclist == null || piclist.size() <= 0) {
            System.out.println("图片数组为空!");
            return null;
        }
        try {
            int height = 0, // 总高度
                    width = 0, // 总宽度
                    _height = 0, // 临时的高度 , 或保存偏移高度
                    __height = 0, // 临时的高度，主要保存每个高度
                    picNum = piclist.size();// 图片的数量
            int[] heightArray = new int[picNum]; // 保存每个文件的高度
            BufferedImage buffer = null; // 保存图片流
            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
            int[] _imgRGB; // 保存一张图片中的RGB数据
            for (int i = 0; i < picNum; i++) {
                buffer = piclist.get(i);
                heightArray[i] = _height = buffer.getHeight();// 图片高度
                if (i == 0) {
                    width = buffer.getWidth();// 图片宽度
                }
                height += _height; // 获取总高度
                _imgRGB = new int[width * _height];// 从图片中读取RGB
                _imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
                imgRGB.add(_imgRGB);
            }
            _height = 0; // 设置偏移高度为0
            // 生成新图片
            imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < picNum; i++) {
                __height = heightArray[i];
                if (i != 0) _height += __height; // 计算偏移高度
                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageResult;
    }

    public static BufferedImage getBufferedImage(String pdfFile) {
    	List<BufferedImage> piclist = structureImage(pdfFile);
    	BufferedImage image = createImage(piclist);
    	return image;
    }


    private static List<BufferedImage> structureImage(String pdfFile) {

    	List<BufferedImage> piclist = null;

    	try {
            InputStream is = new FileInputStream(pdfFile);
            PDDocument pdf = PDDocument.load(is);
            int actSize  = pdf.getNumberOfPages();
            piclist = new ArrayList<BufferedImage>();
            for (int i = 0; i < actSize; i++) {
                BufferedImage  image = new PDFRenderer(pdf).renderImageWithDPI(i,130,ImageType.RGB);
                piclist.add(image);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return piclist;
    }

    public static void pdf2multiImage(String pdfFile, String imageOutpath,String afterResizeImagePath, Integer width, Integer height) throws IOException, ImageFormatException {
    		List<BufferedImage> piclist = structureImage(pdfFile);
            yPic(piclist, imageOutpath);
            if (!StringUtil.isEmpty(afterResizeImagePath) && null != width && width > 0 && null != height && height > 0){
                File inputFile = new File(imageOutpath);
                GeneratePdf myImage = new GeneratePdf();
                InputStream input = new FileInputStream(inputFile);
                byte[] byteArrayImage=myImage.readBytesFromIS(input);
                input.read(byteArrayImage);
                resizeImage(byteArrayImage, afterResizeImagePath, width, height, "jpg");
            }
    }

    /**
     * <b>function:</b> 可以设置图片缩放质量，并且可以根据指定的宽高缩放图片
     * @param width 缩放的宽度
     * @param height 缩放的高度
     * @return 图片保存路径、名称
     * @throws ImageFormatException
     * @throws IOException
     */

        public static boolean resizeImage(InputStream input, String writePath,
                               Integer width, Integer height, String format) {
        try {
            BufferedImage inputBufImage = ImageIO.read(input);
//            System.out.print("转前图片高度和宽度：" + inputBufImage.getHeight() + ":"+ inputBufImage.getWidth());
            ResampleOp resampleOp = new ResampleOp(width, height);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,
                    null);
            ImageIO.write(rescaledTomato, format, new File(writePath));
//            System.out.print("转后图片高度和宽度：" + rescaledTomato.getHeight() + ":"+ rescaledTomato.getWidth());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean resizeImage(File file, String writePath, Integer width,
                               Integer height, String format) {
        try {
            BufferedImage inputBufImage = ImageIO.read(file);
            inputBufImage.getType();
            System.out.print("转前图片高度和宽度：" + inputBufImage.getHeight() + ":"+ inputBufImage.getWidth());
            ResampleOp resampleOp = new ResampleOp(width, height);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,
                    null);
            ImageIO.write(rescaledTomato, format, new File(writePath));
            System.out.print("转后图片高度和宽度：" + rescaledTomato.getHeight() + ":"+ rescaledTomato.getWidth());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean resizeImage(byte[] RGBS, String writePath, Integer width,
                               Integer height, String format) {
        InputStream input = new ByteArrayInputStream(RGBS);
        return resizeImage(input, writePath, width, height, format);
    }

    public static byte[] readBytesFromIS(InputStream is) throws IOException {
        int total = is.available();
        byte[] bs = new byte[total];
        is.read(bs);
        return bs;
    }

    private static final float DEFAULT_SCALE_QUALITY = 1f;
    /**
     * <b>function:</b> 设置图片压缩质量枚举类；
     */
    public enum ImageQuality {
        max(1.0f), high(0.75f), medium(0.5f), low(0.25f);

        private Float quality;
        public Float getQuality() {
            return this.quality;
        }
        ImageQuality(Float quality) {
            this.quality = quality;
        }
    }

    private static java.awt.Image image;

    /**
     * <b>function:</b> 可以设置图片缩放质量，并且可以根据指定的宽高缩放图片
     * @param width 缩放的宽度
     * @param height 缩放的高度
     * @param quality 图片压缩质量，最大值是1； 使用枚举值：{@link ImageQuality}
     *             Some guidelines: 0.75 high quality、0.5  medium quality、0.25 low quality
     * @param savePath 保存目录
     * @param targetImage 即将缩放的目标图片
     * @return 图片保存路径、名称
     * @throws ImageFormatException
     * @throws IOException
     */
    public static String resize(int width, int height, Float quality, String savePath, java.awt.Image targetImage) throws ImageFormatException, IOException {
        width = Math.max(width, 1);
        height = Math.max(height, 1);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(targetImage, 0, 0, width, height, null);

        if (savePath == null || "".equals(savePath)) {
            savePath = "C:/temp-" + System.currentTimeMillis() + ".jpg";
        }

        FileOutputStream fos = new FileOutputStream(new File(savePath));
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);

        JPEGEncodeParam encodeParam = JPEGCodec.getDefaultJPEGEncodeParam(image);
        if (quality == null || quality <= 0) {
            quality = DEFAULT_SCALE_QUALITY;
        }
        /** 设置图片压缩质量 */
        encodeParam.setQuality(quality, true);
        encoder.encode(image, encodeParam);

        image.flush();
        fos.flush();
        fos.close();

        return savePath;
    }

    /**
     * 通过指定的比例和图片对象，返回一个放大或缩小的宽度、高度
     * @param image 图片对象
     * @return 返回宽度、高度
     */
    public static int[] getSizeByHeight(int height, java.awt.Image image) {
        int targetWidth = image.getWidth(null);
        int targetHeight = image.getHeight(null);
        long width = Math.round((targetWidth * height) / (targetHeight * 1.00f));
        return new int[] { Integer.parseInt(String.valueOf(width)), height };
    }

    /**
     * 按照固定高度进行等比缩放本地图片
     * @param height 固定高度
     * @param savePath 保存路径、名称
     * @param targetFile 本地目标文件
     * @return 返回保存路径
     */
    public static String resizeByHeight(int height, String savePath, File targetFile) throws ImageFormatException, IOException {
        image = ImageIO.read(targetFile);
        int[] size = getSizeByHeight(height, image);
        return resize(size[0], size[1],null, savePath, image);
    }




    public static void main(String[] args) {

        try {
            /**
             * PDF变图片
             * 需添加fontbox and pdfbox 两个jar包
             */

            int width = 153;
            int height = 241;
            File inputFile = new File("E://510700_20170513.jpg");
            File outFile = new File("E://fzlthjs.jpg");
            String outPath = outFile.getAbsolutePath();
            GeneratePdf myImage = new GeneratePdf();
            InputStream input = new FileInputStream(inputFile);
            byte[] byteArrayImage=myImage.readBytesFromIS(input);
            input.read(byteArrayImage);
            myImage.resizeImage(byteArrayImage, outPath, width, height, "jpg");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
