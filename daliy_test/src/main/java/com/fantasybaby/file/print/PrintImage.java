package com.fantasybaby.file.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import java.awt.image.RenderedImage;
import java.io.*;

/**
 * @author reid.liu
 * @date 2018-10-12 12:59
 */
public class PrintImage {
    private static final int A4_WIDTH = 210;
    private static final int A4_HEIGHT = 210;
    public static void printControl(Composite root, Printer printer, Point location) {
        root.getParent().layout();
        root.getChildren();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        GC gc1 = new GC(printer);
        if (printer.startJob("Walle Printing")) {
            for (int i = 0; i < root.getChildren().length; i++) {
                Control control = root.getChildren()[i];
                Image image = new Image(root.getDisplay(), control.getBounds());

//                Image image = new Image(root.getDisplay(), 480,480);
                GC gc = new GC(image);
                gc.setAdvanced(true);
                gc.setAntialias(SWT.DEFAULT);
                /*boolean success = control.print(gc);
                if (success) {
                    System.out.println("control print success()");
                }*/
                root.print(gc);
                Point screenDPI = control.getDisplay().getDPI();
                Point printerDPI = printer.getDPI();

                // Rectangle trim = printer.computeTrim(100, 200, 300, 400);
                int w = control.getBounds().width;
                int h = control.getBounds().height;
                /*int w=480;
                int h =480;*/
                /*ImageLoader loader = new ImageLoader();
                loader.data = new ImageData[] {image.getImageData()};
                loader.save("D://swt.png", SWT.IMAGE_PNG);*/

                if (printer.startPage()) {
                    gc1.setAdvanced(true);
                    gc1.setAntialias(SWT.ON);// 设置消除锯齿
    //                    gc1.setInterpolation(SWT.HIGH); // 设置插值
                    gc1.drawImage(image, 0, 0, w, h, location.x * printerDPI.x / screenDPI.x, location.y * printerDPI.y
                                / screenDPI.y, w * printerDPI.x / screenDPI.x, h * printerDPI.y / screenDPI.y);

    //                    gc1.drawImage(image,0,0);
                        // gc1.drawImage(image, 0, 0, w, h, trim.x, trim.y, w *
                        // printerDPI.x / screenDPI.x, h * printerDPI.y
                        // / screenDPI.y);
                        printer.endPage();
                }
//                gc.dispose();
                image.dispose();
            }
            printer.endJob();
        }
        gc1.dispose();
        printer.dispose();
        return;
    }
    public static ByteArrayInputStream parseToInPutStream(OutputStream out) throws Exception
    {
        ByteArrayOutputStream   baos= new ByteArrayOutputStream();
        baos=(ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }
    public void printImg(InputStream inputStream,int count) {
        try {
            //DocFlavor dof = DocFlavor.INPUT_STREAM.AUTOSENSE;
            DocFlavor dof = DocFlavor.INPUT_STREAM.PNG;
            /*if (fileName.endsWith(".gif")) {
                dof = DocFlavor.INPUT_STREAM.GIF;
            } else if (fileName.endsWith(".jpg")) {
                dof = DocFlavor.INPUT_STREAM.JPEG;*/
//            } else if (fileName.endsWith(".png")) {
//            }
            PrintService pservice  = PrintServiceLookup.lookupDefaultPrintService();

            //打印属性
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

            pras.add(OrientationRequested.PORTRAIT);
            pras.add(new Copies(count));
            pras.add(PrintQuality.HIGH);
            pras.add(Chromaticity.MONOCHROME);
            //文档属性
            DocAttributeSet das = new HashDocAttributeSet();
            // 设置打印纸张的大小（将像素转化为mm）
            das.add(new MediaPrintableArea(0, 0, A4_WIDTH, A4_HEIGHT, MediaPrintableArea.MM));
            Doc doc = new SimpleDoc(inputStream, dof, das);
            DocPrintJob job = pservice.createPrintJob();
            job.print(doc, pras);
        } catch (PrintException pe) {
            pe.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
