package com.pht.pdf;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfSignatureFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.annot.PdfWidgetAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import org.apache.poi.hssf.record.formula.functions.T;
import org.codehaus.groovy.util.StringUtil;
import org.junit.Test;
import org.springframework.util.StringUtils;
import sun.awt.FontDescriptor;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;
import java.util.Map;

public class PdfOperator {

    public static void main(String[] args) throws Exception {
        //@TODO   pdf赋值问题 setValue失败
        PdfReader pdfReader=new PdfReader("pdf/借款合同测试.pdf");
        File newPdfFile =new File("D:\\javaproject\\重构\\mavenrtest2\\src\\main\\resources\\pdf\\aaa.pdf");
        if (!newPdfFile.exists()) {
            File parent = newPdfFile.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            newPdfFile.createNewFile();
        }
        PdfWriter pdfWriter = new PdfWriter("D:\\javaproject\\重构\\mavenrtest2\\src\\main\\resources\\pdf\\aaa.pdf");
        PdfDocument pdfDocument = new PdfDocument(pdfReader,pdfWriter);
        PdfAcroForm acroForm = PdfAcroForm.getAcroForm(pdfDocument, true);
        String fontStr ="D:\\javaproject\\重构\\mavenrtest2\\src\\main\\resources\\pdf\\simsun.ttc,0";
        PdfFont pfFont = PdfFontFactory.createFont(fontStr, PdfEncodings.IDENTITY_H, false);
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
                System.out.println("+++++++++++++++widget");
            }
            if (formField instanceof PdfTextFormField) {
                System.out.println("文本域");
                float width = formField.getWidgets().get(0).getRectangle().toRectangle().getWidth();//签名域宽度
                int fontSize = getFontSize(formField);
                String value ="你好啊填充值AAAAAAAA";
                fontSize =calcFontSize(width,value,fontSize,pfFont);
                System.out.println(fontSize);
                formField.setValue(value).setFont(pfFont).setFontSize(fontSize);
            }else if( formField instanceof  PdfSignatureFormField){
                System.out.println("签名域");
                try{
                    byte[] bytes = drawImage("pht的签名！");
//                    renderFormFieldByImage(pdfDocument,formField,bytes);
                    fillPdfpicture(formField,bytes,pdfDocument);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            System.out.println("+++++++++++++++formField");
        });
//        acroForm.flattenFields(); //锁定表单 不许修改 （文本域内容无法修改）
        pdfDocument.close();
    }
    public static  void print(Object messafge){
        System.out.println(messafge);
    }
    /**
     * 获取字体大小
     */
    public static int calcFontSize(float boxwidth,String value,int fontsize,PdfFont pdfFont){
        float width = pdfFont.getWidth(value, fontsize);//获取当前字符串长度
        if(width > boxwidth){
             return  calcFontSize(boxwidth,value,++fontsize,pdfFont);
        }
        return fontsize;
    }

    /**
     * 获取adobe中设置的字体大小
     *
     * @param formField
     * @return
     */
    private static int getFontSize(PdfFormField formField) {
        try {
            String defaultAppearance = formField.getDefaultAppearance().toString();
            String[] daTable = defaultAppearance.split(" ");
            int fontSize = Integer.valueOf(daTable[PdfFormField.DA_SIZE]);
            if(fontSize <= 0) {
                fontSize = 12;
            }
            return fontSize;
        } catch (Exception e) {
            return 12;
        }
    }
    @Test
    public void  test() throws IOException {
        drawImage("你好啊");
    }

    private static  byte[] drawImage(String value) throws IOException {
        Font font = new Font("宋体", 0, 14);
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = metrics.charsWidth(value.toCharArray(),0,value.length());//计算值长度
        int height =36;
        //创建画布
        BufferedImage bufferedImage =new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D= bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1.0f));
        graphics2D.fillRect(0,0,width,height);
        graphics2D.dispose();
        //填充内容
        graphics2D= bufferedImage.createGraphics();
        graphics2D.setColor(Color.RED);// 将此图形上下文的当前颜色设置为指定颜色。
        graphics2D.drawString(value, 1, height / 2 + metrics.getHeight() / 4);
        graphics2D.dispose();

        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        FileOutputStream fileOutputStream =new FileOutputStream("a.png");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        return bytes;
    }
    public static void fillPdfpicture( PdfFormField formField,byte[] imagedata,PdfDocument pdfDocument){
        Rectangle rectangle = formField.getWidgets().get(0).getRectangle().toRectangle();


        PdfCanvas canvas = new PdfCanvas(formField.getWidgets().get(0).getPage());//画布
        canvas.saveState();//存储pdfdocument
        PdfExtGState pdfExtGState =new PdfExtGState();
        pdfExtGState.setFillOpacity(1f);//设置pdf笔画透明度

        canvas.setExtGState(pdfExtGState);
        ImageData imageData = ImageDataFactory.create(imagedata);
        //获取 签名域的长度宽
        float rectangleWidth = rectangle.getWidth();
        float rectangleHeight = rectangle.getHeight();
        //图画大小
        float imageWidth = imageData.getWidth();
        float imageHeight = imageData.getHeight();

        float tempWidth = 0;
        float tempHeight = 0;

        int result = 1; // 压缩宽度
        if (imageWidth > rectangleWidth) {
            if(imageHeight < rectangleHeight){
                tempWidth= rectangleWidth;
                tempHeight =  imageHeight;
            }else{
                result = 2;
                tempWidth= imageWidth;
                tempHeight= rectangleHeight;
            }

//            tempHeight = imageHeight * rectangleWidth / imageWidth;//计算宽度
//            if (tempHeight > rectangleHeight) {
//                tempHeight = rectangleHeight;
//                result = 2; // 压缩高度
//            } else {
//                tempWidth = rectangleWidth;//压缩宽度
//            }
        } else {
            if (imageHeight > rectangleHeight) {
                tempHeight = rectangleHeight;//压缩高度
                result = 2;
            } else {
                result = 3;
            }
        }
        float y = 0;
        if (result == 1) { // 压缩宽度
            y = rectangleHeight - tempHeight;
        } else if (result == 3) { // 不压缩
            y = rectangleHeight - imageHeight;
        }
        // y/=2; // 如果想要图片在表单域的上下对齐，这个值除以2就行。同理可以计算x的偏移
        if (result == 1) {
            canvas.addImage(imageData, rectangle.getX(), rectangle.getY() + y, tempWidth, false);
        } else if (result == 2) {
            canvas.addImage(imageData, rectangle.getX(), rectangle.getY(), tempHeight, false, false);
        } else if (result == 3) {
            canvas.addImage(imageData, rectangle.getX(), rectangle.getY() + y, false);
        }
        canvas.restoreState();
    }

    private static void renderFormFieldByImage(PdfDocument pdf, PdfFormField formField, byte[] imgData) {
        Rectangle rectangle = formField.getWidgets().get(0).getRectangle().toRectangle(); // 获取表单域的xy坐标
        PdfCanvas canvas = new PdfCanvas(formField.getWidgets().get(0).getPage());
        canvas.saveState();
        PdfExtGState extGState = new PdfExtGState();
        extGState.setFillOpacity(1f);
        canvas.setExtGState(extGState);

        ImageData image = ImageDataFactory.create(imgData);
        float imageWidth = image.getWidth();
        float imageHeight = image.getHeight();
        float rectangleWidth = rectangle.getWidth();
        float rectangleHeight = rectangle.getHeight();

        float tempWidth = 0;
        float tempHeight = 0;

        int result = 1; // 压缩宽度
        if (imageWidth > rectangleWidth) {
            tempHeight = imageHeight * rectangleWidth / imageWidth;
            if (tempHeight > rectangleHeight) {
                tempHeight = rectangleHeight;
                result = 2; // 压缩高度
            } else {
                tempWidth = rectangleWidth;
                tempHeight = imageHeight * rectangleWidth / imageWidth;
            }
        } else {
            if (imageHeight > rectangleHeight) {
                tempHeight = rectangleHeight;
                result = 2;
            } else {
                result = 3;
            }
        }
        float y = 0;
        if (result == 1) { // 压缩宽度
            y = rectangleHeight - tempHeight;
        } else if (result == 3) { // 不压缩
            y = rectangleHeight - imageHeight;
        }
        // y/=2; // 如果想要图片在表单域的上下对齐，这个值除以2就行。同理可以计算x的偏移
        if (result == 1) {
            canvas.addImage(image, rectangle.getX(), rectangle.getY() + y, tempWidth, false);
        } else if (result == 2) {
            canvas.addImage(image, rectangle.getX(), rectangle.getY(), tempHeight, false, false);
        } else if (result == 3) {
            canvas.addImage(image, rectangle.getX(), rectangle.getY() + y, false);
        }
        canvas.restoreState();
    }
}
