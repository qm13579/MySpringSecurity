package cn.people.utils.common;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.people.domain.Dep;
import cn.people.domain.UserInfo;
import cn.people.utils.aspect.annotation.Excel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * excel文件处理类
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:20
 * @description:
 */
public class ExcelUtils<T> {

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
    private  Workbook createWork(List<T> T, String tableName) throws IllegalAccessException {

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
        System.out.println("单元格设置");

        //设置标志位
        XSSFRow rowFlag = sheet.createRow(1);
        setFlag(T,rowFlag);

        int rowNum = 2;
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
                Excel annotation = field.getAnnotation(Excel.class);
                if (annotation != null){
                    row.createCell(colNum).setCellValue(field.get(object).toString());
                }
                colNum += 1;
            }
        }
    }

    /**
     * 设置表中标志位,用户导入数据时获取实体类
     */
    private void setFlag(List<T> T,XSSFRow row){
        for (int i = 0; i < 1; i++) {
            BaseEntity obj = (BaseEntity) T.get(i);
            if (!obj.KEY.isEmpty()) {
                row.createCell(0).setCellValue("flag");
            }else {
                row.createCell(0).setCellValue("flag");
                row.createCell(1).setCellValue(obj.KEY);
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
        for (Object object :T) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field :fields) {
                field.setAccessible(true);
                Excel excel = field.getAnnotation(Excel.class);
                if (excel != null){
                    System.out.println(excel.name());
                    list.add(excel.name());
                }
            }
        }
        return list;
    }

    /**
     * 导出excel文件(注解方式)
     * @param tableName
     * @param objs
     */
    public void export(String tableName, List<T> objs, HttpServletResponse response) throws IllegalAccessException, IOException {
        System.out.println(objs);
        Workbook workbook = createWork(objs, tableName);
        setHeard(workbook,response);
    }
    /***
     * 通过hutool工具类实现表单下载
     */
    public void getExcel(HttpServletResponse response,List<T> T,String tableName) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(T);
        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("content-Disposition","attachment;filename="+ URLEncoder.encode(tableName,"UTF-8"));
        writer.flush(response.getOutputStream());
        writer.close();
    }

    /**
     * 把excel转化为list
     * @param file
     * @return
     */
    private List<T> excelToList(MultipartFile file){
        String filename = file.getOriginalFilename();
        if (!isExcel(filename)){
            return null;
        }
        try {
            List list = importExcel(file);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否excel文件
     * @return
     */
    private boolean isExcel(String filename){
        String substring = filename.substring(filename.lastIndexOf("."));

        if (".lxl".equals(substring) || ".xlsx".equals(substring)){
            return true;
        }

        return false;
    }

    /**
     * 创建一个工作薄，获取工作表
     * @param file
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    private List<Map<String,String>> importExcel(MultipartFile file) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<Map<String,String>> list = getRow(sheet);
        return list;
    }

    /***
     * 获取单元格中的内容
     * 根据表格反向生成对象
     * @param sheet
     * @return
     */
    private List<Map<String,String>> getRow(Sheet sheet){
        //获取总列数
        Row rowColumnName = sheet.getRow(2);
        short lastCellNum = rowColumnName.getLastCellNum();
        //获取总行数
        int lastRowNum = sheet.getLastRowNum();


        //获取列名
        //获取每行内容
        return null;

    }

    /**
     * 导出模板
     */
    public void templateExcel(T obj, HttpServletResponse response){
        String tableName = "template";
        List<T> list = Arrays.asList();
        list.add(obj);
        try {
            export(tableName,list,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据标签获取实体类
     * @return
     */
    private Map<String,Object> getPojo(){
        Map<String,Object> map= new HashMap<String,Object>(3);
        map.put(UserInfo.KEY,new UserInfo());
        return map;
    }

}
