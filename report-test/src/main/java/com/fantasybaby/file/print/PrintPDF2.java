package com.fantasybaby.file.print;

import com.fantasybaby.file.freemarker.FreeMarkerGenerate;
import com.fantasybaby.file.html2pdf.Html2Pdf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * @author reid.liu
 * @date 2018-10-18 16:33
 */
public class PrintPDF2 {
    public static void main(String args[]) throws Exception {
        long l = System.currentTimeMillis();
        String renderHtml = FreeMarkerGenerate.testFreeMarker("ybpick-express-no-page.ftl");
        System.out.println(System.currentTimeMillis() - l);
//        System.out.println(renderHtml);
        OutputStream out = new ByteArrayOutputStream();
        Html2Pdf.generatePlus(renderHtml,out);
        System.out.println(System.currentTimeMillis() - l);
        byte[] buff = ((ByteArrayOutputStream) out).toByteArray();
        System.out.println("***********************");
        ByteArrayInputStream bin = new ByteArrayInputStream(buff);
        System.out.println(System.currentTimeMillis() - l);
        PDDocument document = PDDocument.load(bin);
//        FX DocuCentre-IV C2260 PCL 6
//        Send To OneNote 2016
        PrintService myPrintService = findPrintService("Send To OneNote 2016");
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setPrintService(myPrintService);
        job.print();
        System.out.println(System.currentTimeMillis() - l);
    }

    public static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }
}
