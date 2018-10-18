package com.fantasybaby.file.print;

import com.fantasybaby.file.freemarker.FreeMarkerGenerate;
import com.fantasybaby.file.html2pdf.Html2Pdf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.Arrays;

/**
 * @author reid.liu
 * @date 2018-10-18 16:33
 */
public class PrintPDF2 {
    public static void main(String args[]) throws Exception {
        String renderHtml = FreeMarkerGenerate.testFreeMarker("ybpick-express-no-page.ftl");
        System.out.println(renderHtml);
        OutputStream out = new ByteArrayOutputStream();
        Html2Pdf.generatePlus(renderHtml,out);
        byte[] buff = ((ByteArrayOutputStream) out).toByteArray();
        for(int i=0; i<buff.length; i++)
            System.out.println(buff[i]);
        System.out.println("***********************");
        ByteArrayInputStream bin = new ByteArrayInputStream(buff);

        PDDocument document = PDDocument.load(bin);
//        FX DocuCentre-IV C2260 PCL 6
//        Send To OneNote 2016
        PrintService myPrintService = findPrintService("Send To OneNote 2016");

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setPrintService(myPrintService);
        job.print();

    }

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }
}
