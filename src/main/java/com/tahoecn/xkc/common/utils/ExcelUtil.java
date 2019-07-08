package com.tahoecn.xkc.common.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

public class ExcelUtil {


    public static void listToExcel(List<?> list,String title, OutputStream outputStream) throws Exception {
        if(list!=null){
            if(list.size()!=0){
                ExportParams exportParams = new ExportParams(title,"SheetData");
                Workbook workbook = ExcelExportUtil.exportExcel(exportParams, list.get(0).getClass(), list);
                workbook.write(outputStream);
            }
        }
    }

    public static void listToExcel(List<ExcelExportEntity> entity, List<Map<String,Object>> list, String title, OutputStream outputStream) throws Exception {
        if(list!=null){
            if(list.size()!=0){
                Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title,"SheetData"), entity,list);
                workbook.write(outputStream);
            }
        }
    }

    public static JSONResult excelToList(InputStream inputStream, Class<?> cls, int titleRows, int headRows) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(titleRows);
        importParams.setHeadRows(headRows);

        List<Object> list = ExcelImportUtil.importExcel(inputStream, cls, importParams);
        long index = titleRows + headRows;

        List<String> errors = new ArrayList();
        for(Object obj : list){
            index++;
            List<String> rowErrors = ModelValidatorUtil.modelValidator(obj);
            if(rowErrors.size()!=0){
                errors.add("第"+index+"行"+rowErrors);
            }
        }

        JSONResult jsonResult = new JSONResult();

        //有一个错误整个EXCEL都不能导入到LIST中
        if(errors.size()!=0){
            jsonResult.setCode(GlobalConstants.E_CODE);
            jsonResult.setMsg("Excel读取失败！");
            jsonResult.setData(errors);
        }else{
            jsonResult.setCode(GlobalConstants.S_CODE);
            jsonResult.setMsg("Excel读取成功！");
            jsonResult.setData(list);
        }

        return jsonResult;
    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName,boolean isCreateHeader, HttpServletResponse response) throws Exception {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response) throws Exception {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws Exception {
        defaultExport(list, fileName, response);
    }

    public static void exportExcel(List<ExcelExportEntity> entity,List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entity,list);
        downLoadExcel(fileName, response, workbook);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws Exception {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new Exception();
        }
    }
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

}