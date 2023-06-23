package com.nchu.software.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.text.DecimalFormat;

public class MyExcel {
    /**
     * 避免Excel中的数字按科学计数法显示
     * @param str
     * @return
     */
    public static String ClearScientificCounting(String str){
        DecimalFormat decimalFormat = new DecimalFormat("0"); // 设置格式化模式
        return decimalFormat.format(Double.parseDouble(str));
    }

    /**
     * 使Excel的单元格按字符串原样显示
     * @param cell
     * @return
     */
    public static String OriginalStringDisplay(Cell cell){
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }

}
