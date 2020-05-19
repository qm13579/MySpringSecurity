package cn.people.utils.common;
import cn.people.utils.aspect.annotation.Excel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf文件处理类
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:20
 * @description:
 */
public class PDFUtils<T> {
    /**
     * 设置请求头
     * @param response
     *
     */
    private HttpServletResponse setHeader(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        response.setHeader("content-Disposition","filename=text");
        return response;
    }

    /**
     * 创建pdf
     * @param fileName
     * @param document
     * @param T
     * @param pdfWriter
     */
    private  void createPdf(String fileName, Document document,List<T> T,PdfWriter pdfWriter) throws IOException, DocumentException, IllegalAccessException {
        document.addTitle("example of PDF");
        document.open();
        document.add(new Paragraph(fileName,fontChinese()));
        document.add(title(fileName));
        PdfPTable table = createTable(pdfWriter, T);
        document.add(table);
        document.close();
    }

    /**
     * 设置字体
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private static Font fontChinese() {
        BaseFont font = null;
        try {
            font = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            return new Font(font);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建表(基于注解)
     * @param writer
     * @param T
     * @return
     */
    private PdfPTable createTable(PdfWriter writer,List<T> T) throws IllegalAccessException {
        PdfPCell cell;
        List<String> columnName = getColumn(T);
        PdfPTable table = new PdfPTable(columnName.size());

        for (String name :columnName) {
            table.addCell(createCell(name));
        }

        for (Object object :T) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field :fields) {
                field.setAccessible(true);
                Excel excel = field.getAnnotation(Excel.class);
                if (excel != null){
                    table.addCell(createCell(field.get(object).toString()));
                }
            }
        }
        return table;
    }

    /**
     * 创建单元格
     * @param value
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private static PdfPCell createCell(String value) {
        return new PdfPCell(new Paragraph(value,fontChinese()));
    }

    /**
     * 设置纸张大小及旋转方式
     * @return
     */
    private Document setPageSizeForA4(){
        return new Document(PageSize.A4.rotate());
    }

    public void getPdfFile(HttpServletResponse response,List<T> T) throws IOException, DocumentException, IllegalAccessException {
        Document document = setPageSizeForA4();
        HttpServletResponse res = setHeader(response);
        ServletOutputStream os = res.getOutputStream();
        ByteOutputStream pdf = new ByteOutputStream();

        PdfWriter writer = PdfWriter.getInstance(document, pdf);
        String fileName = System.currentTimeMillis() + ".pdf";
        createPdf(fileName,document,T,writer);
        pdf.writeTo(os);
        os.flush();
        os.close();
    }

    /**
     * 设置标题
     * @param content
     * @return
     */
    private Paragraph title(String content){
        return new Paragraph(content,fontChinese());
    }

    /**
     * 获取数据列名
     * @param T
     * @return
     */
    public List<String> getColumn(List<T> T){
        List<String> list = new ArrayList<>();
        for (Object object:T) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field :fields) {
                Excel excel = field.getAnnotation(Excel.class);
                if (excel != null){
                   list.add(excel.name());
                }
            }
        }
        return list;
    }
}
