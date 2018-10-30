package com.fantasybaby.file.print;

import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author reid.liu
 * @date 2018-10-18 14:44
 */
public class PrintPdf {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("D:\\hello1.pdf"); // 获取选择的文件

        // 构建打印请求属性集


        // 设置打印格式，因为未确定类型，所以选择autosense

        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        //请求一个彩色打印机
//                pras.add(Chromaticity.COLOR);
        //请求横向模式
//                pras.add(OrientationRequested.LANDSCAPE);
        //美国Letter大小的纸质属性
//                pras.add(MediaSizeName.NA_LETTER);

        // European A4 paper
        pras.add(MediaSize.ISO.A4);

        //请求装订
//                pras.add(Finishings.STAPLE);
//
//                //整理多个副本
//                pras.add(SheetCollate.COLLATED);
//
//                //请求双面
//                pras.add(Sides.ONE_SIDED);
//
//                // 2页到一个工作表
//                pras.add(new NumberUp(2));

        //多少个副本
        pras.add(new Copies(1));

        FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
        Doc doc = new SimpleDoc(fis, flavor, null);
        // 查找所有的可用的打印服务
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultPrinter=null;
        System.out.println("-------------------All services-------------------");
         for (int i = 0; i < printService.length; i++) {
            PrintService printService1 = printService[i];
            String name = printService1.getName();
             System.out.println(name);
//             "Microsoft Print to PDF");
//, "FX DocuCentre-IV C2260 PCL 6");
//, "Send To OneNote 2016");
            if(name.equals("Send To OneNote 2016")){
                defaultPrinter=printService1;
            }
        }

        // 定位默认的打印服务

        //PrintService service1 = PrintServiceLookup.lookupDefaultPrintService();
         if (defaultPrinter != null) {

            System.out.println("-------------------Choose Printer-------------------");
            System.out.println(defaultPrinter.getName());
            //指定使用 Microsoft XPS Document Writer

            //获取打印机属性
            AttributeSet attributes = defaultPrinter.getAttributes();
            for (Attribute a : attributes.toArray()) {
                String name = a.getName();
                String value = attributes.get(a.getClass()).toString();
                System.out.println(name + " : " + value);
            }
            // 显示打印对话框 静默模式不显示对话框
            PrintService service = ServiceUI.printDialog(null, 200, 200, printService,
                defaultPrinter, flavor, pras);
            try {
                System.out.println("Begin Print PDF: " + file.getName());
                DocPrintJob job = defaultPrinter.createPrintJob(); // 创建打印作业
//                DocAttributeSet das = new HashDocAttributeSet();


                job.print(doc, pras);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}