package cn.people.utils.common;

import cn.people.utils.aspect.annotation.Excel;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * excel文件处理类
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:20
 * @description:
 */
public class ExcelUtils {

    /**
     * 设置下载请求头xlsx
     * @param workbook
     * @param response
     */
    private void setHeard(Workbook workbook, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/x-download");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition","attachment;filename=test.xlsx");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }
    /***
     * 创建工作薄
     * @param T
     * @param tableName
     * @return
     */
    private  Workbook createWork(List<T> T,String tableName) throws IllegalAccessException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        creatSheet(workbook, tableName,T);

        return workbook;
    }

    /**
     * 创建表
     * @param workbook
     * @param tableName
     * @return
     */
    private void creatSheet(XSSFWorkbook workbook,String tableName,List<T> T) throws IllegalAccessException {
        XSSFSheet sheet = workbook.createSheet(tableName);
        createCell(T,sheet);

    }

    /**
     * 填充单元格
     * @param T
     */
    private void createCell(List<T> T,XSSFSheet sheet) throws IllegalAccessException {
        //设置表格中的列名
        XSSFRow rowColumn = sheet.createRow(0);
        setColumnName(getColumnField(T),rowColumn);

        int rowNum = 1;
        for (Object object : T) {
            XSSFRow row = sheet.createRow(rowNum);
            setCellValue(T,row);
            rowNum += 1;
        }
    }

    /**
     * 设置列名
     * @param columnName
     * @param row
     */
    private void setColumnName(List<String> columnName,XSSFRow row){
        int colNum = 0;
        for (String name : columnName) {
            row.createCell(colNum).setCellValue(name);
            colNum += 1;
        }
    }

    /**
     * 设置单元格值
     * @param T
     * @param row
     */
    private void setCellValue(List<T> T,XSSFRow row) throws IllegalAccessException {
        int colNum = 0;
        for (Object object : T) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field :fields) {
                field.setAccessible(true);
                row.createCell(colNum).setCellValue(field.get(object).toString());
                colNum += 1;
            }
        }
    }

    /**
     * 获取列名
     * @param T
     * @return
     */
    private List<String> getColumnField(List<T> T){
        List<String> list = new ArrayList<>();
        Field[] fields = T.getClass().getDeclaredFields();

        for (Field field :fields) {
            Excel excel = field.getAnnotation(Excel.class);
            if (excel != null){
                list.add(excel.name());
            }
        }
        return list;
    }

    /**
     * 导出excel文件(注解方式)
     * @param tableName
     * @param objs
     */
    public void export(String tableName, List<T> objs,HttpServletResponse response) throws IllegalAccessException, IOException {
        Workbook workbook = createWork(objs, tableName);
        setHeard(workbook,response);
    }
    /***
     * 设置表格式
     */
}
