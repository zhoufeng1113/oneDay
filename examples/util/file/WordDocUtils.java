package com.homevip.util.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class WordDocUtils {

	public static void converDocParam(String source, String target,Map<String, String> params) throws Exception {  
    	
		
    } 
	
	public static void converDocxParam(String source, String target,Map<String, String> params){  
    	
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(target);

			XWPFDocument doc = new XWPFDocument(in);
			paragraphReplace(doc.getParagraphs(), params);
			for (XWPFTable table : doc.getTables()) {
				for (XWPFTableRow row : table.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						paragraphReplace(cell.getParagraphs(), params);
					}
				}
			}
			doc.write(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    } 
	
	/** 
     * 替换变量 
     * @param para 要替换的段落 
     * @param params 参数 
     */  
    private void replaceInPara(XWPFParagraph para, Map<String, String> params) {  
       List<XWPFRun> runs;  
       Matcher matcher;  
       if (matcher(para.getParagraphText()).find()) {  
          runs = para.getRuns();  
          for (int i=0; i<runs.size(); i++) {
             XWPFRun run = runs.get(i);  
             String runText = run.toString(); 
             matcher = matcher(runText);
             if (matcher.find()) { 
                 while ((matcher = this.matcher(runText)).find()) {  
                    runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1)))); 
                 }  
                 //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，  
                 //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。  
                System.out.println(runText);
                if(runText.indexOf("\n")>-1) {
                    String[] text = runText.split("\n");
                    para.removeRun(i); 
                    for(int f=text.length-1;f>=0;f--) {
                        System.out.println(f);
                        para.insertNewRun(i).setText(text[f]);
                       if(f!=0) {
                        para.insertNewRun(i).addCarriageReturn();//硬回车
                       }
                    }
                }else {
                    para.removeRun(i); 
                     para.insertNewRun(i).setText(runText);
                }
             }  
          }  
       }  
    }  
	
    private static Matcher matcher(String str) {
	      Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
	      Matcher matcher = pattern.matcher(str);
	      return matcher;
	}
    
	/** 替换段落中内容 */  
	private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
		if (MapUtils.isNotEmpty(params)) {
			for (XWPFParagraph p : paragraphs) {
				String text = p.getText();
				Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, String> next = iterator.next();
					String key = next.getKey();
					String value = next.getValue();

					if (text.indexOf(key) >= 0) {
						List<XWPFRun> run = p.getRuns();
						for (int i = 0; i < run.size(); i++) {
							String text2 = run.get(i).getText(0);
							if (text2 != null) {
								text2 = text2.trim();
								if(text2.equals(key)) {
									run.get(i).setText(value, 0);
								}
							}
						}
					}
				}
			}
		}
	}
}
