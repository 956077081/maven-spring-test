package com.pht.pdf;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.annot.PdfWidgetAnnotation;
import org.apache.poi.hssf.record.formula.functions.T;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PdfOperator {

    public static void main(String[] args) throws Exception {
        PdfReader pdfReader=new PdfReader("pdf/借款合同测试.pdf");
        PdfDocument pdfDocument = new PdfDocument(pdfReader);
        PdfAcroForm acroForm = PdfAcroForm.getAcroForm(pdfDocument, false);
        Map<String, PdfFormField> formFields = acroForm.getFormFields();
        formFields.forEach((name,formField)->{
            List<PdfWidgetAnnotation> widgets = formField.getWidgets();
            for (PdfWidgetAnnotation widget : widgets) {
                PdfPage page = widget.getPage();
                print(pdfDocument.getPageNumber(page));
                print(page.getPageSize());
                print(page.getPageSize().getHeight());
                print(page.getPageSize().getWidth());
                Rectangle rectangle = widget.getRectangle().toRectangle();//获取签名域
                print(rectangle);
                print(rectangle.getX());//签名域X 坐标 中心角为左下角
                print(rectangle.getY());//签名域Y 坐标
                print(rectangle.getHeight());//高度
                print(rectangle.getWidth());//宽度
                print(rectangle.getBottom());//下高度
                print(rectangle.getTop());//上高度
                print(rectangle.getRight());//右侧距离
                print(rectangle.getLeft());//左侧距离
            }
            if(!StringUtils.isEmpty(formField.getValue())){
                try {
                    System.out.println("--------------------------------"+new String(formField.getValue().toString().getBytes("GB2312"), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        });
    }
    public static  void print(Object messafge){
        System.out.println(messafge);
    }
}
