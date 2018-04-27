package com.homevip.util.file;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class PdfFileUtil {

	/** 
     * 将word文档， 转换成pdf
     * @param source 源为word文档， 必须为docx文档 
     * @param target 目标输出 
     * @param params 需要替换的变量 
     * @throws Exception 
     */  
    public static void wordConverterToPdf(String source,  
    		String target, Map<String, String> params) throws Exception {
    	PdfOptions options = PdfOptions.create(); 
        wordConverterToPdf(source, target, options, params);      
    }  
    
    @SuppressWarnings("resource")
	public static void wordConverterToPdf(String source, String target,   
            PdfOptions options,  
            Map<String, String> params) throws Exception {  
    	
    	InputStream in = new FileInputStream(source);  
        OutputStream out = new FileOutputStream(target); 
    	
         XWPFDocument doc = new XWPFDocument(in);
         paragraphReplace(doc.getParagraphs(), params);  
         for (XWPFTable table : doc.getTables()) {  
           for (XWPFTableRow row : table.getRows()) {  
               for (XWPFTableCell cell : row.getTableCells()) {  
                   paragraphReplace(cell.getParagraphs(), params);  
               }  
           }  
         }
         PdfConverter.getInstance().convert(doc, out, options);
         in.close();
     	 out.flush();
     	 out.close();
    } 
    public static void wordConverterToPdf(String source, String target) throws Exception {  
    	
    	InputStream in = new FileInputStream(source);  
        OutputStream out = new FileOutputStream(target);
        
        PdfOptions options = PdfOptions.create();
        
    	XWPFDocument doc = new XWPFDocument(in);
    	PdfConverter.getInstance().convert(doc, out, options);
    	in.close();
    	out.flush();
    	out.close();
    } 
    
    /** 替换段落中内容 */  
    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {  
        if (MapUtils.isNotEmpty(params)) {
            for (XWPFParagraph p : paragraphs){  
            	String text = p.getText();
//            	System.out.println(text);
            	Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
            	while(iterator.hasNext()) {
            		Entry<String, String> next = iterator.next();
            		String key = next.getKey();
            		String value = next.getValue();
            		
            		if(text.indexOf(key) >= 0) {
            			List<XWPFRun> run=p.getRuns();
            			 for(int i=0;i<run.size();i++){
            				 String text2 = run.get(i).getText(0);
            				 System.out.println(text2);
	            			  if(text2!=null 
	            					  &&   text2.equals(key)){	
	            			    /**参数0表示生成的文字是要从哪一个地方开始放置,一开始这里的代码是
	            			     * run.get(i).setText("AAAA",run.get(i).getTextPosition());
	            			     * 结果AAAA总是添加到要被替换的文字之后,经查看API知道,设置文字从位置0开始
	            			     * 就可以把原来的文字全部替换掉了
	            			    * */	    
	            				run.get(i).setText(value,0);	
	            			  }	
            			 }	
            		}
            	}
//            	System.out.println(text);
                /*for (XWPFRun r : p.getRuns()){  
                	int textPosition = r.getTextPosition();
                    String content = r.getText(textPosition);
//                     System.out.println(content);
                    if(StringUtils.isNotEmpty(content) && params.containsKey(content)) {  
                        r.setText(params.get(content), 0);  
                    }  
                }  */
            }  
        }  
    }
    
    public static XWPFDocument abc(XWPFDocument document,Map<String, String> map) {
    	try {
	    	Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();  
	        while (itPara.hasNext()) {  
	            XWPFParagraph paragraph = (XWPFParagraph) itPara.next();  
	            //String s = paragraph.getParagraphText();          
	            Set<String> set = map.keySet();  
	            Iterator<String> iterator = set.iterator();  
	            while (iterator.hasNext()) {  
	                String key = iterator.next();  
	                List<XWPFRun> run=paragraph.getRuns();  
	                 for(int i=0;i<run.size();i++)  
	                 {  
	                  if(run.get(i).getText(run.get(i).getTextPosition())!=null && run.get(i).getText(run.get(i).getTextPosition()).equals(key))  
	                  {      
	                    /**参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始 
	                     * 就可以把原来的文字全部替换掉了 
	                    * */           
	                      run.get(i).setText(map.get(key),0);      
	                  }      
	                 }      
	            }      
	        }  
	
	        // 替换表格中的指定文字  
	        Iterator<XWPFTable> itTable = document.getTablesIterator();  
	        while (itTable.hasNext()) {  
	            XWPFTable table = (XWPFTable) itTable.next();  
	            int rcount = table.getNumberOfRows();  
	            for (int i = 0; i < rcount; i++) {  
	                XWPFTableRow row = table.getRow(i);  
	                List<XWPFTableCell> cells = row.getTableCells();  
	                for (XWPFTableCell cell : cells) {  
	                    for (Entry<String, String> e : map.entrySet()) {  
	                        if (cell.getText().equals(e.getKey())) {  
	                            cell.removeParagraph(0);  
	                            cell.setText(e.getValue());  
	                        }  
	                    }  
	                }  
	            }  
	        }  
	          
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
    	
    	return document;
    }

	public static void newEmptyPdf(String path) throws Exception {
		// 1.新建document对象
		// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			FileOutputStream out = new FileOutputStream(path);
			// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
			// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
			PdfWriter writer = PdfWriter.getInstance(document, out);
			// 3.打开文档
			document.open();
			// 4.向文档中添加内容
			// 通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 150, 200))));
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}finally {
			// 5.关闭文档
			document.close();
		}
	}
    
    public static void main(String[] args) throws Exception {
    	
		String filepath = "E:/ideaworkspace/Web/webapp/uploads/contract/51家庭管家会员服务协议中文版（20170619）-年卡.docx";
        String outpath = "E:/ideaworkspace/Web/webapp/uploads/contract/2017-11-30_徐卫华_51家庭管家会员服务协议中文版（20170619）-年卡.pdf";
        
        Map<String, String> params = new HashMap<String, String>();  
        params.put("${customerName}", "张三");
        params.put("${phone}", "13800138000");
        params.put("${idNumber}", "身份证");
        params.put("${serviceAddress}", "广州市天河区");
        params.put("${productNames}", "标保");
        params.put("${contractAmount}", "￥9850");
        params.put("${remark}", "备注");
        PdfOptions options = PdfOptions.create(); 
        
        wordConverterToPdf(filepath,outpath,options,params);
//		writeSimplePdf("E:/adbc.pdf");
	}
}
