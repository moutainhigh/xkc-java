package com.tahoecn.xkc.common.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tahoecn.xkc.common.annotation.ExcelSign;
import com.tahoecn.xkc.common.exception.ExcelException;

/**
 * 
 * @author 张晓曦
 *
 */
public class ZExcelUtil {
	
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
	
	public static XSSFWorkbook exportExcel(HttpServletResponse response, List<Object> olist) {
		XSSFWorkbook book = new XSSFWorkbook();
		
		XSSFSheet sheet = book.createSheet();
		//设置excel表头
		XSSFRow row = sheet.createRow(0);
		
		//设置头样式
		XSSFCellStyle style = book.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		XSSFFont font = book.createFont();
		font.setBold(true);
		style.setFont(font);
		row.setRowStyle(style);
		row.createCell(0).setCellValue("序号");
		List<Field> list = Arrays.asList(olist.get(0).getClass().getDeclaredFields());
		for(Field field : list) {
			if(field.isAnnotationPresent(ExcelSign.class)) {
				ExcelSign excelSign = field.getAnnotation(ExcelSign.class);
				row.createCell(excelSign.cellNum()).setCellValue(excelSign.description());
			}
		}
		//设置内容
		int rowNum = 1;
		for(Object o : olist) {
			XSSFRow trow = sheet.createRow(rowNum);
			//设置序号
			trow.createCell(0).setCellValue(rowNum);
			//设置导出内容
			setRowValue(o,trow);
			rowNum++;
		}
		return book;
	}
	
	/**
	 * 封装excel内容
	 * @param o
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings("deprecation")
	private static void setRowValue(Object o,XSSFRow row) {
		List<Field> list = Arrays.asList(o.getClass().getDeclaredFields());
		for(Field field : list) {
			if(field.isAnnotationPresent(ExcelSign.class)) {
				ExcelSign excelSign = field.getAnnotation(ExcelSign.class);
				String fieldTypeName = field.getType().getName();
				String filedName = field.getName();
				try {
					Method method =o.getClass().getMethod("get"+filedName.substring(0, 1).toUpperCase()+filedName.substring(1));
					Object v = method.invoke(o);
					if(v!=null) {
						if (fieldTypeName.contains("String")) {
							row.createCell(excelSign.cellNum()).setCellValue(v.toString());
						}else if (fieldTypeName.contains("BigDecimal")) {
							BigDecimal b = new BigDecimal(v.toString());
							row.createCell(excelSign.cellNum()).setCellValue(b.doubleValue());
						}else if (fieldTypeName.contains("Date")) {
							SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
							row.createCell(excelSign.cellNum()).setCellValue(sd.format(new Date(v.toString())));
						}else{
							row.createCell(excelSign.cellNum()).setCellValue(v.toString());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	/**
	 * 设置导出文件名
	 * @param response
	 * @param fileName
	 */
	public static void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	/**
	 * 通用导入 基于注解ExcelSign
	 * @param in
	 * @param fileName
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static Object importExcel(InputStream in, String fileName,Class<?> t,Integer rowNum) throws Exception {
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		Workbook work = getWorkbook(in, fileType);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			int realRows = 0;// 获取工作表的有效行数 默认为0
			int nullCols = 0;
			for (int k = 0; k < sheet.getPhysicalNumberOfRows(); k++) {
				int cols = 0;
				cols = sheet.getRow(k).getLastCellNum();
				row = sheet.getRow(k);
				for (int j = 0; j < cols; j++) {
					Cell currentCell = row.getCell(j);
					if (currentCell == null) {
						nullCols++;
					}
				}
				if (nullCols == cols) {
					break;
				} else {
					realRows++;
				}
			}
			if (realRows < 2) {
				throw new ExcelException("Excel中没有数据");
			}
			for (int j = sheet.getFirstRowNum(); j < sheet.getPhysicalNumberOfRows(); j++) {
				Object obj = t.newInstance();
				List<Field> fieldList = Arrays.asList(obj.getClass().getDeclaredFields());
				if(j<rowNum)
					continue;
				row = sheet.getRow(j);
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
				for(Field field : fieldList) {
					if(field.isAnnotationPresent(ExcelSign.class)) {
						ExcelSign excelSign = field.getAnnotation(ExcelSign.class);
						int cellNum = excelSign.cellNum();
						cell = row.getCell(cellNum);
						if(cell==null)
							continue;
						//设置Type 用于读取纯数字
						cell.setCellType(Cell.CELL_TYPE_STRING);
						String cellValue = cell.getStringCellValue();
						String filedName = field.getName();
						String fieldTypeName = field.getType().getName();
						try {
							Field field1 = obj.getClass().getDeclaredField(filedName);
							field1.setAccessible(true);
							if (fieldTypeName.contains("BigDecimal")) {
								if(cellValue==null||cellValue.length()==0){
									field1.set(obj, new BigDecimal("0"));
								}else{
									field1.set(obj, new BigDecimal(cellValue));
								}
							}else if (fieldTypeName.contains("Integer")) {
								if(cellValue==null||cellValue.length()==0){
									field1.set(obj, null);
								}else{
									field1.set(obj, Integer.valueOf(cellValue));
								}
							}else if(fieldTypeName.contains("Long")){
								if(cellValue==null||cellValue.length()==0){
									field1.set(obj, null);
								}else{
									field1.set(obj, Long.valueOf(cellValue));
								}
							}else{
								if(cellValue==null||cellValue.length()==0){
									field1.set(obj, "");
								}else{
									field1.set(obj, cellValue);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				list.add(obj);
			}
		}
		return list;
	}
	
	
	public static XSSFWorkbook exportNullExcel(HttpServletResponse response, List<Object> olist) {
		XSSFWorkbook book = new XSSFWorkbook();
		
		XSSFSheet sheet = book.createSheet();
		//设置excel表头
		XSSFRow row = sheet.createRow(0);
		
		//设置头样式
		XSSFCellStyle style = book.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		XSSFFont font = book.createFont();
		font.setBold(true);
		style.setFont(font);
		row.setRowStyle(style);
		row.createCell(0).setCellValue("序号");
		List<Field> list = Arrays.asList(olist.get(0).getClass().getDeclaredFields());
		for(Field field : list) {
			if(field.isAnnotationPresent(ExcelSign.class)) {
				ExcelSign excelSign = field.getAnnotation(ExcelSign.class);
				row.createCell(excelSign.cellNum()).setCellValue(excelSign.description());
			}
		}
		return book;
	}
	
	public static Workbook getWorkbook(InputStream inStr, String fileType) throws Exception {
		Workbook wb = null;
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}
}
