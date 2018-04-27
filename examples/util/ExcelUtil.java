package com.homevip.util;

import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.homevip.util.date.DateTimeUtil;

import static org.springframework.beans.BeanUtils.getPropertyDescriptor;

/**
 * 导出excel文档工具类
 * 
 * @author fate
 *
 */
public class ExcelUtil {

	/**
	 * 创建excel文档
	 * 
	 * @param list
	 *            文档数据（注：list().get(0)为参数，包含sheetName等）
	 * @param keys
	 *            文档对应的属性名
	 * @param columnNames
	 *            列名
	 * @return
	 */
	public static XSSFWorkbook createWorkBook(List<Map<String, Object>> list,
			String keys[], String columnNames[]) {
		// 创建excel工作簿
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建第一个sheet（页），并命名
		XSSFSheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * 150));
		}

		// 创建第一行
		XSSFRow row = sheet.createRow((short) 0);

		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
//		f.setBold(true);
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());

		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置列名
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
		// 设置每行每列的值
		for (int i = 1; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			XSSFRow row1 = sheet.createRow(i);
			// 在row行上创建一个方格
			for (int j = 0; j < keys.length; j++) {
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list
						.get(i).get(keys[j]).toString());
				cell.setCellStyle(cs2);
			}
		}
		return wb;
	}

	/**
	 * 添加多个sheet
	 * @param wb
	 * @param list
	 * @param keys
	 * @param columnNames
	 * @return
	 */
	public static XSSFWorkbook createWorkBookAndMultipleSheet(XSSFWorkbook wb ,List<Map<String, Object>> list,
											  String keys[], String columnNames[]) {
		// 创建第一个sheet（页），并命名
		XSSFSheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth(i, NumberUtil.toInt(35.7 * 150));
		}

		// 创建第一行
		XSSFRow row = sheet.createRow(0);

		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
//		f.setBold(true);
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());

		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置列名
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
		// 设置每行每列的值
		for (short i = 1; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的

			// 创建一行，在页sheet上
			XSSFRow row1 = sheet.createRow(i);
			// 在row行上创建一个方格
			for (short j = 0; j < keys.length; j++) {
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list
						.get(i).get(keys[j]).toString());
				cell.setCellStyle(cs2);
			}
		}
		return wb;
	}
	/**
	 * 创建下载输出流
	 * 
	 * @param filename
	 * @param wb
	 * @param
	 * @param response
	 * @throws IOException
	 */
	public static void download(String filename, XSSFWorkbook wb,
			HttpServletResponse response) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String((filename + ".xlsx").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}

	}
	/**
	 * 获取excel表数据，object为返回列表数据的实体，数据从object的第二字段录入
	 * @author hehaoqun
	 * @date 2015年11月19日
	 * @param @param filePath 文件路径
	 * @param @param className 类名
	 * @param @return
	 * @throws Exception 
	 */
	public static List<Object> getExcelList(String filePath,String className) throws Exception{
		List<Object> excelList=new ArrayList<Object>();
        // 获取实体类的所有属性，返回Field数组
		Class<?> clz =  Class.forName(className);
		
        Field[] fields = clz.getDeclaredFields();  
		try {
			File file=new File(filePath);
			OPCPackage opcPackage=OPCPackage.open(file);
			XSSFWorkbook xssfWorkbook=new XSSFWorkbook(opcPackage);
			XSSFSheet xssfSheet=xssfWorkbook.getSheetAt(0);
			int totalRows=xssfSheet.getPhysicalNumberOfRows();
			for(int rownum=1;rownum<totalRows;rownum++){
				Object object=clz.newInstance();
				XSSFRow row=xssfSheet.getRow(rownum);
				 System.out.println(rownum);  
				//读取每个表格的数据
				int i=0;
				while(row.getCell(i)!=null){
					XSSFCell cellValue=row.getCell(i);
					Field f=fields[i+1];
					String type = f.getType().toString();//得到此属性的类型
					//利用反射设置属性值
		           if (type.endsWith("String")) {
		        	   if(cellValue.getCellType()==XSSFCell.CELL_TYPE_STRING){
		        		   f.set(object, cellValue.getStringCellValue()); //给属性设值  
		        	   }else if(cellValue.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
		        		    f.set(object, DateTimeUtil.formatDateTime(cellValue.getDateCellValue()));
		        	   }
		           }else if(type.endsWith("int") || type.endsWith("Integer")){  
		              System.out.println(f.getType()+"\t是int");  
		              f.set(object, cellValue.getNumericCellValue());    //给属性设值  
		           }else if(type.endsWith("double")||type.endsWith("Double")){
		        	   System.out.println(f.getType()+"\t是Double"); 
		        	   f.set(object, cellValue.getNumericCellValue());
		           }
		           else if(type.endsWith("boolean") || type.endsWith("Boolean")){  
		        	   f.set(object, cellValue.getBooleanCellValue());    //给属性设值   
		           }else if(type.endsWith("Date")){
		        	   f.set(object, cellValue.getDateCellValue());    //给属性设值   
		           }
		           i++;
				}
				excelList.add(object);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excelList;
	}
        
        // 把一个字符串的第一个字母大写、效率是最高的、  
        private static String getMethodName(String fildeName) throws Exception{  
            byte[] items = fildeName.getBytes();  
            items[0] = (byte) ((char) items[0] - 'a' + 'A');  
            return new String(items);  
        }


	public static List<List> getExcelLists(String filePath, boolean isNotSkip) throws Exception{
		List<List> excelList=new ArrayList<List>();
		try {
			File file=new File(filePath);
			OPCPackage opcPackage=OPCPackage.open(file);
			XSSFWorkbook xssfWorkbook=new XSSFWorkbook(opcPackage);
			XSSFSheet xssfSheet=xssfWorkbook.getSheetAt(0);
			int totalRows=xssfSheet.getPhysicalNumberOfRows();
			System.out.println("import total->" + totalRows);
			for(int rownum=1;rownum<=totalRows;rownum++){
				System.out.println("read row->" + rownum);
				Object object=new Object();
				XSSFRow row=xssfSheet.getRow(rownum);
				//读取每个表格的数据
				if(row==null){
					continue;
				}
				int i=0;
				List<String> objList=new ArrayList<String>();
				while(row.getCell(i)!=null){
					XSSFCell cellValue=row.getCell(i);
					if(cellValue!=null) {
					String obj=getCelValue(cellValue);
						if(isNotSkip){
							if(!obj.equals("")) {
								objList.add(obj);
							}
						}else{
							objList.add(obj);
						}

					}
					i++;
				}
				excelList.add(objList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excelList;
	}

		public static String getCelValue(XSSFCell cellValue){
			String obj = "";
			switch (cellValue.getCellType()) {

				case XSSFCell.CELL_TYPE_STRING:
					obj = StringUtil.toString(cellValue.getStringCellValue());
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// 处理日期格式、时间格式
					if (HSSFDateUtil.isCellDateFormatted(cellValue)) {
						SimpleDateFormat sdf = null;
						if (cellValue.getCellStyle().getDataFormat() == HSSFDataFormat
								.getBuiltinFormat("hh:mm")) {
							sdf = new SimpleDateFormat("HH:mm");
						} else {// 日期
							sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						}
						Date date = cellValue.getDateCellValue();
						obj = sdf.format(date);
					} else if (cellValue.getCellStyle().getDataFormat() == 58) {
						// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						double value = cellValue.getNumericCellValue();
						Date date = org.apache.poi.ss.usermodel.DateUtil
								.getJavaDate(value);
						obj = sdf.format(date);
					} else {
						DecimalFormat df = new DecimalFormat("######0.00");
						obj = df.format(cellValue.getNumericCellValue());
						String o=(String)obj;
						obj=o.replace(",","");
					}
					break;
			}
			return obj;
		}
	/**
	 * 获取excel表数据，object为返回列表数据的实体
	 * @param @param  inputStream 输入流
	 * @param @param  tClass 类
	 * @param @return
	 * @throws Exception
	 * @author pigy
	 * @date 2017-12-6
	 */
	public static <T> List<T> getExcelList(String filePath, Class<T> tClass) throws IOException {
		List<T> excelList = new ArrayList<T>();
		// 获取实体类的所有属性，返回Field数组
		Field[] fields = tClass.getDeclaredFields();
		OPCPackage opcPackage = null;
		XSSFWorkbook xssfWorkbook = null;
		try {
			File file = new File(filePath);
			opcPackage = OPCPackage.open(file);
			xssfWorkbook = new XSSFWorkbook(opcPackage);
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			int totalRows = xssfSheet.getPhysicalNumberOfRows();
			for (int rownum = 1; rownum < totalRows; rownum++) {
				T object = tClass.newInstance();
				XSSFRow row = xssfSheet.getRow(rownum);
				//读取每个表格的数据
				int i = 0;
				while (row.getCell(i) != null) {
					XSSFCell cellValue = row.getCell(i);
					Field f = fields[i];
					//得到此属性的类型
					String type = f.getType().toString();
					//利用反射设置属性值
					PropertyDescriptor pd = getPropertyDescriptorWithRule(tClass, f.getName());
					Method m = pd.getWriteMethod();
					if (type.endsWith("String")) {
						if (cellValue.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							m.invoke(object,StringUtil.toString(NumberUtil.toInt(cellValue.getNumericCellValue())));
						} else if (cellValue.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							m.invoke(object,cellValue.getStringCellValue());
						}
					} else if (type.endsWith("int") || type.endsWith("Integer")) {
						if (cellValue.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							m.invoke(object,NumberUtil.toInt(cellValue.getStringCellValue()));
						}else if(cellValue.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
							m.invoke(object,NumberUtil.toInt(cellValue.getNumericCellValue()));
						}
					} else if (type.endsWith("double") || type.endsWith("Double")) {
						if (cellValue.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							m.invoke(object,NumberUtil.ToDouble(cellValue.getStringCellValue()));
						}else if(cellValue.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
							m.invoke(object,cellValue.getNumericCellValue());
						}
					} else if (type.endsWith("boolean") || type.endsWith("Boolean")) {
						m.invoke(object,cellValue.getBooleanCellValue());
					} else if (type.endsWith("Date")) {
						m.invoke(object,cellValue.getDateCellValue());
					}
					i++;
				}
				excelList.add(object);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (xssfWorkbook != null){
				xssfWorkbook.close();
			}
			e.printStackTrace();
		}finally {
			if (xssfWorkbook != null){
				xssfWorkbook.close();
			}
		}
		return excelList;
	}

	/**
	 * 获取excel表数据，object为返回列表数据的实体
	 * @param @param  inputStream 输入流
	 * @param @param  tClass 类
	 * @param @return
	 * @throws Exception
	 * @author pigy
	 * @date 2017-12-6
	 */
	public static <T> List<T> getExcelList(InputStream inputStream, Class<T> tClass) {
		List<T> excelList = new ArrayList<T>();
		// 获取实体类的所有属性，返回Field数组
		Field[] fields = tClass.getDeclaredFields();
		try {
			OPCPackage opcPackage = OPCPackage.open(inputStream);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(opcPackage);
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			int totalRows = xssfSheet.getPhysicalNumberOfRows();
			for (int rownum = 1; rownum < totalRows; rownum++) {
				T object = tClass.newInstance();
				XSSFRow row = xssfSheet.getRow(rownum);
				//读取每个表格的数据
				int i = 0;
				while (row.getCell(i) != null) {
					XSSFCell cellValue = row.getCell(i);
					Field f = fields[i];
					//得到此属性的类型
					String type = f.getType().toString();
					//利用反射设置属性值
					PropertyDescriptor pd = getPropertyDescriptorWithRule(tClass, f.getName());
					Method m = pd.getWriteMethod();
					if (type.endsWith("String")) {
						if (cellValue.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							m.invoke(object,StringUtil.toString(cellValue.getNumericCellValue()));
						} else if (cellValue.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							m.invoke(object,cellValue.getStringCellValue());
						}
					} else if (type.endsWith("int") || type.endsWith("Integer")) {
						if (cellValue.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							m.invoke(object,NumberUtil.toInt(cellValue.getStringCellValue()));
						}else if(cellValue.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
							m.invoke(object,cellValue.getNumericCellValue());
						}
					} else if (type.endsWith("double") || type.endsWith("Double")) {
						if (cellValue.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							m.invoke(object,NumberUtil.ToDouble(cellValue.getStringCellValue()));
						}else if(cellValue.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
							m.invoke(object,cellValue.getNumericCellValue());
						}
					} else if (type.endsWith("boolean") || type.endsWith("Boolean")) {
						m.invoke(object,cellValue.getBooleanCellValue());
					} else if (type.endsWith("Date")) {
						m.invoke(object,cellValue.getDateCellValue());
					}
					i++;
				}
				excelList.add(object);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excelList;
	}

	private static PropertyDescriptor getPropertyDescriptorWithRule(Class clazz, String propertyName) {
		PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
		if(pd == null){
			String[] pStr = propertyName.split("_");
			String retStr = "";
			//如果长度大于1说明存在下划线，根据规则将下划线后面的英文字母变成大写，同时删除下划线
			if(pStr.length > 1){
				for(int i = 1;i<pStr.length;i++){
					String temp = pStr[i];
					String prefix = temp.substring(0,1);
					prefix = prefix.toUpperCase();
					retStr+= prefix+temp.substring(1);
				}
				return getPropertyDescriptor(clazz, pStr[0]+retStr);
			}
		}
		return  pd;
	}

	/**
	 * 创建自定义第一行excel表格
	 * @param list
	 * @param keys
	 * @param columnNames
	 * @return
	 */
	public static XSSFWorkbook createPersionWorkBook(List<Map<String, Object>> list,
											  String keys[], String columnNames[],List<Integer> colspan,List<String> fristColsname) {
		// 创建excel工作簿
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建第一个sheet（页），并命名
		XSSFSheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。


		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
//		f.setBold(true);
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());

		// Font f3=wb.createFont();
		// f3.setFontHeightInPoints((short) 10);
		// f3.setColor(IndexedColors.RED.getIndex());

		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);

		int s = 0;
		for (int j = 0; j < colspan.size(); j++) {
			s = s + colspan.get(j);
		}

		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * 150));
		}
		// 创建第一行
		XSSFRow frow = sheet.createRow((short) 0);
		for (int n = 0; n < s; n++) {
			Cell cell = frow.createCell(n);

			cell.setCellValue("");
			cell.setCellStyle(cs);
		}
		int v = 0;
		for (int i = fristColsname.size() - 1; i >= 0; i--) {
			v = v + colspan.get(i);
			Cell cell = frow.getCell(s-v);
			cell.setCellValue(fristColsname.get(i));

		}


		// 创建第二行
		XSSFRow row = sheet.createRow((short) 1);
		// 设置列名
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
		// 设置每行每列的值
		for (short i = 2; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			XSSFRow row1 = sheet.createRow((short) i);
			// 在row行上创建一个方格
			for (short j = 0; j < keys.length; j++) {
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list
						.get(i).get(keys[j]).toString());
				cell.setCellStyle(cs2);
			}

		}

		// 合并标题
		int k=0;
		for (int y= colspan.size()-1;y >=0; y--) {

			sheet.addMergedRegion(new CellRangeAddress(0, 0, s - k - colspan.get(y),
					s - k - 1));
			k=k+colspan.get(y);
		}

		return wb;
	}

	/**
	 * 客户经理业绩导出
	 */
	public static XSSFWorkbook createBillReportExcel(List<Map<String, Object>> list,
											  String keys[], String columnNames[]) {
		// 创建excel工作簿
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建第一个sheet（页），并命名
		XSSFSheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * 150));
		}

		// 创建第一行
		XSSFRow row = sheet.createRow((short) 0);

		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());

		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
		cs2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 设置列名
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
		// 设置每行每列的值
		int firstStart = 1,firstEnd = 4,secondStart = 5,secondEnd = 6;
		Integer total = null;
		for (short i = 1; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			XSSFRow row1 = sheet.createRow((short) i);
			//firstStart  firstStart1+1
			// 在row行上创建一个方格
			for (short j = 0; j < keys.length; j++) {
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list
						.get(i).get(keys[j]).toString());
				cell.setCellStyle(cs2);
			}
			total = (Integer) list.get(i).get("total");
			String dateRange = (String) list.get(i).get("dateRange");
			//服务站未变更的情况下是合并4行
			if(null == total && null != dateRange){
				sheet.addMergedRegion(new CellRangeAddress(firstStart,firstEnd,0,0));
				sheet.addMergedRegion(new CellRangeAddress(firstStart,firstEnd,1,1));
				sheet.addMergedRegion(new CellRangeAddress(firstStart,firstEnd,2,2));
				//重置合并行的开始索引
				firstStart += 4;
				firstEnd += 4;
			}
			if (null != total){
				sheet.addMergedRegion(new CellRangeAddress(firstStart,firstStart+1,0,2));
				firstStart += 2;
				firstEnd += 2;
			}
		}

		return wb;
	}

	public static void  main(String args[]) throws Exception{
        	List<List> list=getExcelLists("E:/ideaworkspace/51homevip/webapp/uploads/t1.xlsx", true);
			for (List l :list){
				for(Object s : l){
					System.out.println(s);
				}
			}

        	System.out.println(list.size());
        }



}
