package com.fantasybaby.ireport;

import com.fantasybaby.file.print.PrintPDF2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;

import javax.print.PrintService;
import java.io.File;
import java.util.HashMap;

/**
 * @author reid.liu
 * @date 2018-11-01 16:08
 */
public class JasperPrintDbTemplate {
    public static void main(String[] args)
    {
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try
        {
            String reportPath = JasperReportsIntro.getReportPath("ireport"+ File.separator+"reportdb.jrxml");
            System.out.println(reportPath);
            jasperReport = JasperCompileManager.compileReport(reportPath);
            HashMap hashMap = new HashMap();
//            hashMap.put("details","12312");
//            hashMap.put("detail",DataFactory.generateBoxDetails());
            jasperPrint = JasperFillManager.fillReport(
                    jasperReport, hashMap, new JRBeanCollectionDataSource(DataFactory.genereateBoxDate()));
            PrintService myPrintService = PrintPDF2.findPrintService("Send To OneNote 2016");
            JRPrintServiceExporter je = new JRPrintServiceExporter();
            SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
            configuration.setDisplayPageDialog(false);
            configuration.setPrintService(myPrintService);
            SimpleExporterInput input = new SimpleExporterInput(jasperPrint);
            je.setConfiguration(configuration);
            je.setExporterInput(input);
            je.exportReport();
//            je.setExporterInput(input);
            /*JasperExportManager.exportReportToPdfFile(
                    jasperPrint, "D://simple_report.pdf");*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
