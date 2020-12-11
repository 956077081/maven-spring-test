package com.pht.poi;

import com.pht.TestMain;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 创建excel文档
 */
public class PoiMain {
    public static void main(String[] args) {
        System.out.println(TestMain.class.getSimpleName());
    }
    @Test
    public  void crtExcel() throws IOException {
        HSSFWorkbook workbook =new HSSFWorkbook();
        HSSFSheet sheet =workbook.createSheet("客户信息");
        HSSFCellStyle style =getStyle(workbook);//单元格样式
        HSSFCellStyle columnTopStyle =getColumnTopStyle(workbook);
        int columnNum =10;
        int rowNum1 =20;
        List<List<Object>> excelData = getExcelData(rowNum1,columnNum);
        //创建标头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnNum; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(columnTopStyle);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            HSSFRichTextString hssfRichTextString= new HSSFRichTextString("标题"+i);
            cell.setCellValue(hssfRichTextString);//赋值
        }
        for (int i = 1; i <rowNum1; i++) {
            HSSFRow row1 = sheet.createRow(i);
            for (int j = 0; j < columnNum; j++) {
                HSSFCell cell = row1.createCell(j, HSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(style);
                cell.setCellValue(excelData.get(i-1).get(j).toString()+"潘宏涛的创建excel");
            }
        }
        // 让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < columnNum; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                // 当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        int length = 0;
                        try {
                            length = currentCell.getStringCellValue().getBytes().length;
                        } catch (Exception e) {
                            // e.printStackTrace();
                        }
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if (colNum == 0) {
                sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                System.out.println(columnWidth);
            } else {
                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
            }
        }
        FileOutputStream fileOutputStream =new FileOutputStream("a.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    private List<List<Object>>  getExcelData(int rowNum,int columnNum){
        List<List<Object>> list = new ArrayList<>();

        for (int i = 0; i <rowNum ; i++) {
            ArrayList<Object> list1 = new ArrayList<>();
            for (int j = 0; j <columnNum ; j++) {
                list1.add("张三"+i);
            }
            list.add(list1);
        }
        return list;
    }
    private  HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
         font.setFontHeightInPoints((short)15);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");

        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }
    /*
     * 列头单元格样式
     */
    private static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }
}
