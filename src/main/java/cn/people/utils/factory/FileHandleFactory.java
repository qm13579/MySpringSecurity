package cn.people.utils.factory;

import cn.people.utils.common.ExcelUtils;
import cn.people.utils.common.PDFUtils;

/**
 * 文件工厂类
 * @author : FENGZHI
 * create at:  2020/5/16  下午9:51
 * @description:
 */
public class FileHandleFactory {

    public static ExcelUtils getExcelHandle(){
        return new ExcelUtils();
    }
    public static PDFUtils getPdfHandle(){
        return new PDFUtils();
    }
}
