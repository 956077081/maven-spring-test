package com.pht.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.annot.PdfWidgetAnnotation;

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
            }
        });
    }
}
