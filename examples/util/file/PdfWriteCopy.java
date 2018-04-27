package com.homevip.util.file;

import com.homevip.certificate.entity.ImageVo;
import com.homevip.certificate.entity.TextVo;
import com.homevip.core.util.Global;
import com.homevip.util.StringUtil;
import com.homevip.util.system.Const;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import sun.awt.image.ImageFormatException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;


/**
 * Created by 周枫 on 2017/12/4.
 */
public class PdfWriteCopy {

    /**
     * 毫米转像素
     * @param
     */
    public static float mmTopx(float mm){
        mm = (float)(mm*3.33);
        return mm;
    }

    /**
     * 添加图片及文字信息到PDF(证书)
     */
    public static void putInfoToPdf(String baseMap, String originalPath, String templatePath,TextVo textVo, ImageVo imageVo) throws Exception {
        createPdfEquealSize(originalPath,baseMap);
        PdfStamper stamper = null;
        PdfReader reader = null;
        //添加图片
        try {
            FileUtil.copyFile(originalPath,templatePath);
            reader = new PdfReader(templatePath);
            stamper = new PdfStamper(reader, new FileOutputStream(originalPath));
            PdfContentByte overContent = stamper.getOverContent(1);
            PdfContentByte authority = stamper.getOverContent(1);
            Image signet = Image.getInstance(imageVo.getSignet());
            Image headImage = Image.getInstance(imageVo.getHeadImage());
            Image organization = Image.getInstance(imageVo.getOrganization());
            headImage.scaleAbsolute(mmTopx(44),mmTopx(57));
            signet.scaleAbsolute(mmTopx(55),mmTopx(55));
            organization.scaleAbsolute(mmTopx(55),mmTopx(55));
            headImage.setAbsolutePosition(1254,444);
            signet.setAbsolutePosition(1310,325);
            organization.setAbsolutePosition(720,130);
            overContent.addImage(headImage);
            overContent.addImage(signet);
            overContent.addImage(organization);

            //添加文字
            BaseFont font = BaseFont.createFont(Global.Path+"/WEB-INF/res/fonts/simsun.ttc,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            overContent.beginText();
            overContent.setFontAndSize(font, 30);
            overContent.setTextMatrix(200, 200);
            System.out.print("开始添加文字");
            overContent.showTextAligned(Element.ALIGN_CENTER,textVo.getDate(),880,170,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,textVo.getName(),1300,355,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,textVo.getSex(),1500,355,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,textVo.getQualifeation(),1390,280,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,textVo.getCertificateNo(),1380,205,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,textVo.getIdCard(),1390,135,0);
            overContent.endText();
            authority.beginText();
            authority.setFontAndSize(font,22);
            authority.showTextAligned(Element.ALIGN_CENTER,textVo.getAuthority(),930,240,0);
            authority.endText();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            if(null != stamper){
                stamper.close();
            }
            stamper.close();
            reader.close();
            FileUtil.delFile(templatePath);
        }
    }

    /**
     * 添加图片及文字信息到PDF(工牌)
     */
    public static void putInfoToPdfForBadge(String templateBaseMap,String originalPath, String templatePath,TextVo textVo, ImageVo imageVo) throws Exception {
        createPdfEquealSize(originalPath,templateBaseMap);
        PdfStamper stamper = null;
        PdfReader reader = null;
        //添加图片
        String secondLineOfPost = null;//临时第二行职位（防止职位过长，超过6个字就换行显示）
        if (!StringUtil.isEmpty(textVo.getWorkerPost())){
            if (textVo.getWorkerPost().length() > 6){
                String temp = textVo.getWorkerPost();
                textVo.setWorkerPost(textVo.getWorkerPost().substring(0, 6));
                secondLineOfPost = temp.substring(6,temp.length());
            }
        }
        boolean special = false;
        //判断职位是否超过6个字，如果超过要用小号的字体
        if (!StringUtil.isEmpty(textVo.getWorkerPost())){
            if(textVo.getWorkerPost().length() > 6 ){
                special = true;
            }
        }
        try {
            FileUtil.copyFile(originalPath,templatePath);
            reader = new PdfReader(templatePath);
            stamper = new PdfStamper(reader, new FileOutputStream(originalPath));
            PdfContentByte overContent = stamper.getOverContent(1);
            PdfContentByte overContent2 = stamper.getOverContent(1);
            PdfContentByte overContent3 = stamper.getOverContent(1);
            Image background = Image.getInstance(imageVo.getBackground());
            Image qrcode = Image.getInstance(imageVo.getQrCode());
            Image headImage = Image.getInstance(imageVo.getHeadImage());
//            Image border = Image.getInstance("E:/border.png");
            Image border = Image.getInstance(Const.USER_BADGE_TEMPLATE+"border.png");

            float scalePercentage = (72 / 300f) * 100.0f;
            background.scalePercent(scalePercentage, scalePercentage);

            /*background.scaleAbsolute(153,241);
            border.scaleAbsolute(62,82);
            headImage.scaleAbsolute(60,80);*/
            qrcode.scalePercent(100);
            headImage.scalePercent(100);
            border.scalePercent(100);
            Rectangle pageSize = reader.getPageSize(1);
            float height = pageSize.getHeight();
            float width = pageSize.getWidth();
            System.out.println("width = "+width+", height = "+height);

            border.setAbsolutePosition(width/2-(border.getScaledWidth()/2),height/7+border.getHeight());
            headImage.setAbsolutePosition(width/2-(headImage.getScaledWidth()/2),height/7+headImage.getHeight()+6);
            qrcode.setAbsolutePosition(width/2-(qrcode.getScaledWidth()/2),height/20);
            overContent.addImage(border);
            overContent.addImage(headImage);
            overContent.addImage(qrcode);

            //添加文字
            BaseFont font = BaseFont.createFont(Global.Path+"/WEB-INF/res/fonts/simhei.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            overContent.beginText();
            overContent.setFontAndSize(font, 36);
            overContent.setTextMatrix(200, 200);
            System.out.print("开始添加文字");
//            overContent.showTextAligned(Element.ALIGN_LEFT,textVo.getWorkerName(),width/2+81,height/3+60,0);
//            overContent.showTextAligned(Element.ALIGN_LEFT,textVo.getWorkerPost(),width/2+81,height/3+15,0);   (2018-03-21前的原坐标)
            overContent.showTextAligned(Element.ALIGN_LEFT,textVo.getWorkerName(),width/2+41,height/3+40,0);
            overContent.showTextAligned(Element.ALIGN_LEFT,textVo.getWorkerPost(),width/2+41,height/3-5,0);
            if (!StringUtil.isEmpty(secondLineOfPost)){
                overContent.showTextAligned(Element.ALIGN_LEFT,secondLineOfPost,width/2+42,height/3-45,0);
            }
            overContent.endText();

            overContent3.beginText();
            overContent3.setFontAndSize(font,30);
            overContent3.endText();

            overContent2.beginText();
            overContent2.setFontAndSize(font,28);
            float ta = 1f, tb = 0f, tc = 0f, td = 1f, tx = 0f, ty = 0f;
            overContent2.setTextMatrix(ta, tb, tc, td, tx, ty);
            overContent2.showTextAligned(Element.ALIGN_LEFT,textVo.getWorkerCode(),width/2+10,height/3+110,0);
            overContent2.endText();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            if(null != stamper){
                stamper.close();
            }
            stamper.close();
            reader.close();
            FileUtil.delFile(templatePath);
        }
    }

    /**
     * 创建图片同等大小的PDF
     */
    public static void createPdfEquealSize(String pdfPath, String imagePath){
        try {
            //建立com.itextpdf.text.Document对象的实例
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document(PageSize.A4, 0, 0, 0, 0);
            //建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
            com.itextpdf.text.pdf.PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));
            //打开文档
            doc.open();

            File file = new File(imagePath);
            if (file.exists()) {
                com.itextpdf.text.Image png = null;
                png = com.itextpdf.text.Image.getInstance(file.getAbsolutePath());
                //根据图片像素设置图片的大小，单位是磅
//                          png.scaleAbsolute(png.getWidth()/96*72, png.getHeight()/96*72);
                png.scalePercent(100);
                System.out.println(png.getScaledWidth()+ "*" +png.getScaledHeight());
                //根据图片大小设置页面的大小
                com.itextpdf.text.Rectangle pageSize = new com.itextpdf.text.Rectangle(png.getScaledWidth(), png.getScaledHeight());
                System.out.println(pageSize);
                doc.setPageSize(pageSize);
                //新加一页
                doc.newPage();
                //将图片放入文档中
                doc.add(png);
            }
            //关闭文档
            doc.close();
            //进行pdf文件生产
            File pdfFile = new File(pdfPath);
            if (pdfFile.exists()) {
                pdfFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, DocumentException {
        //证书
        /*createPdfEquealSize("E:/A.pdf","E:/certificate.jpg");
        String name = "周枫";
        PdfStamper stamper = null;
        PdfReader reader = null;
        Document document = new Document();
        try {
            FileUtil.copyFile("E:/A.pdf","E:/Atmp.pdf");
            reader = new PdfReader("E:/Atmp.pdf");
            System.out.print("开始复制");
            stamper = new PdfStamper(reader, new FileOutputStream("E:/A.pdf"));
            PdfContentByte overContent = stamper.getOverContent(1);
            Image signet = Image.getInstance("E:/signet.jpg");
            Image headImage = Image.getInstance("E:/headImage.jpg");
            Image organization = Image.getInstance("E:/signet.jpg");
            headImage.scaleAbsolute(mmTopx(44),mmTopx(57));
            signet.scaleAbsolute(mmTopx(55),mmTopx(55));
            organization.scaleAbsolute(mmTopx(55),mmTopx(55));
            headImage.setAbsolutePosition(1254,444);
            signet.setAbsolutePosition(1310,325);
            organization.setAbsolutePosition(720,130);
            overContent.addImage(headImage);
            overContent.addImage(signet);
            overContent.addImage(organization);

            //添加文字
            BaseFont font = BaseFont.createFont("E:/IdeaWorkSpace/Web/webapp/WEB-INF/res/fonts/simsun.ttc,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            overContent.beginText();
//            Font fontChinese = new Font(font,30, Font.BOLD);
//            Paragraph t = new Paragraph("",fontChinese)
            overContent.setFontAndSize(font, 30);
            overContent.setTextMatrix(200, 200);
            System.out.print("开始添加文字");
//            float ta = 10f, tb = 0f, tc = 0f, td = 10f, tx = 0f, ty = 0f;
//            overContent.setTextMatrix(ta, tb, tc, td, tx, ty);
            overContent.showTextAligned(Element.ALIGN_CENTER,"广州市诺兰德职业培训学校",980,240,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,"2018-01-29",880,170,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,name,1310,355,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,"男",1500,355,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,"初级工程师",1360,280,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,"NL2154544",1360,205,0);
            overContent.showTextAligned(Element.ALIGN_CENTER,"104252153210215210",1380,135,0);
            overContent.endText();
        }catch (Exception e){
            e.printStackTrace();
            if(null != stamper){
                stamper.close();
            }
        }finally {
            stamper.close();
            reader.close();
            document.close();
            FileUtil.delFile("E:/Atmp.pdf");
        }*/

        //工牌
        String originalPath = "E:/A.pdf";
        String templatePath = "E:/temp.pdf";
        String post = "客户经理客户可刻意";
        String secondLineOfPost = null;
        if (!StringUtil.isEmpty(post)){
            if (post.length() > 6){
                String temp = post;
                post = post.substring(0, 6);
                secondLineOfPost = temp.substring(6,temp.length());
            }
        }
        String code = "123456";
        String name = "周某某";
        createPdfEquealSize(originalPath,"E:/background.jpg");
        PdfStamper stamper = null;
        PdfReader reader = null;
        //添加图片
        boolean special = false;
        //判断职位是否超过6个字，如果超过要用小号的字体
        try {
            FileUtil.copyFile(originalPath,templatePath);
            reader = new PdfReader(templatePath);
            stamper = new PdfStamper(reader, new FileOutputStream(originalPath));
            PdfContentByte overContent = stamper.getOverContent(1);
            PdfContentByte overContent2 = stamper.getOverContent(1);
            PdfContentByte overContent3 = stamper.getOverContent(1);
            Image background = Image.getInstance("E:/background.jpg");
            Image qrcode = Image.getInstance("E:/qrcode.jpg");
            Image headImage = Image.getInstance("E:/headImage.jpg");
//            Image border = Image.getInstance("E:/border.png");
            Image border = Image.getInstance("E:/IdeaWorkSpace/51homevip/webapp/uploads/badge/template/border.png");

            float scalePercentage = (72 / 300f) * 100.0f;
            background.scalePercent(scalePercentage, scalePercentage);

            /*background.scaleAbsolute(153,241);
            border.scaleAbsolute(62,82);
            headImage.scaleAbsolute(60,80);*/
            qrcode.scalePercent(100);
            headImage.scalePercent(100);
            border.scalePercent(100);
            Rectangle pageSize = reader.getPageSize(1);
            float height = pageSize.getHeight();
            float width = pageSize.getWidth();
            System.out.println("width = "+width+", height = "+height);

            border.setAbsolutePosition(width/2-(border.getScaledWidth()/2),height/7+border.getHeight());
            headImage.setAbsolutePosition(width/2-(headImage.getScaledWidth()/2),height/7+headImage.getHeight()+6);
            qrcode.setAbsolutePosition(width/2-(qrcode.getScaledWidth()/2),height/19);
            overContent.addImage(border);
            overContent.addImage(headImage);
            overContent.addImage(qrcode);

            //添加文字
            BaseFont font = BaseFont.createFont("E:/IdeaWorkSpace/Web/webapp/WEB-INF/res/fonts/fzlthj.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            overContent.beginText();
            overContent.setFontAndSize(font, 36);
            overContent.setTextMatrix(200, 200);
            System.out.print("开始添加文字");
            overContent.showTextAligned(Element.ALIGN_LEFT,name,width/2+41,height/3+40,0);
            if (special == false){
                overContent.showTextAligned(Element.ALIGN_LEFT,post,width/2+41,height/3-5,0);
                if (!StringUtil.isEmpty(secondLineOfPost)){
                    overContent.showTextAligned(Element.ALIGN_LEFT,secondLineOfPost,width/2+42,height/3-43,0);
                }
            }
            overContent.endText();

            overContent3.beginText();
            overContent3.setFontAndSize(font,30);
            overContent3.endText();

            overContent2.beginText();
            overContent2.setFontAndSize(font,28);
            float ta = 1f, tb = 0f, tc = 0f, td = 1f, tx = 0f, ty = 0f;
            overContent2.setTextMatrix(ta, tb, tc, td, tx, ty);
            overContent2.showTextAligned(Element.ALIGN_LEFT,code,width/2+10,height/3+110,0);
            overContent2.endText();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != stamper){
                stamper.close();
            }
            stamper.close();
            reader.close();
            FileUtil.delFile(templatePath);
        }

    }
}
