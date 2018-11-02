package com.fantasybaby.ireport;

import com.fantasybaby.file.freemarker.FreeMarkerGenerate;
import com.fantasybaby.file.print.PrintPDF2;
import com.fantasybaby.ireport.walle.PrintFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.export.*;

import javax.print.PrintService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author reid.liu
 * @date 2018-10-31 15:57
 */
public class JasperReportsIntro {
    public static String getReportPath(String name){
        String path1 = FreeMarkerGenerate.class.getClassLoader().getResource("").getPath();
        File file1 = new File(path1).getParentFile();
        return  file1.getAbsolutePath() + File.separator + "resources"  + File.separator + name;
    }
    public static void main(String[] args)
    {
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try
        {
            String reportPath = getReportPath("ireport"+ File.separator+"sg.jrxml");
            System.out.println(reportPath);
            jasperReport = JasperCompileManager.compileReport(reportPath);
            HashMap hashMap = new HashMap();

            jasperPrint = JasperFillManager.fillReport(
                    jasperReport, hashMap, new JRBeanCollectionDataSource(PrintFactory.print()));
            PrintService myPrintService = PrintPDF2.findPrintService("FX DocuCentre-IV C2260 PCL 6");
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
