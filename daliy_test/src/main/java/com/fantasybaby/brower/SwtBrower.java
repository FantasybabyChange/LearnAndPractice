package com.fantasybaby.brower;

import com.fantasybaby.file.freemarker.FreeMarkerGenerate;
import com.fantasybaby.file.html2image.ConvertHtml2Image;
import com.fantasybaby.file.print.PrintImage;
import freemarker.template.TemplateException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;

/**
 * @author reid.liu
 * @date 2018-10-12 14:13
 */
public class SwtBrower {
    private Browser browser;
    public void openBrowser(){
        String renderHtml=null;
        try {
            renderHtml = FreeMarkerGenerate.testFreeMarker("print.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        renderHtml += ConvertHtml2Image.getHtmlContent("scriptDemo.html","UTF-8");
        Display display = new Display();
        Shell shell = new Shell(SWT.None);
        shell.setLayout(new FillLayout());
//        shell.setBounds(Display.getDefault().getPrimaryMonitor().getBounds());
        try {
            browser = new Browser(shell, SWT.NONE);
            /*GridData dataRoot = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
            dataRoot.widthHint = 480;
            dataRoot.heightHint = 480;*/
//            browser.setLayoutData(dataRoot);
//            browser.setBounds(Display.getDefault().getPrimaryMonitor().getBounds());
        } catch (SWTError e) {
            System.out.println("Could not instantiate Browser: " + e.getMessage());
            display.dispose();
            return;
        }
        browser.setText(renderHtml,true);
        new BrowserFunction(browser, "myCallJava"){

            @Override

            public Object function(Object[] arguments) {
                SwtBrower.pirntBrowser(browser);
//                display.dispose();
                return super.function(arguments);
            }
        };

//        browser.layout();
//        shell.open();
//        Display.getDefault().asyncExec(() -> {
//            SwtBrower.pirntBrowser(browser);
////           shell.layout();
//            display.dispose();
//        });
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
//        display.dispose();
    }
    public static void main(String [] args) {
        new SwtBrower().openBrowser();
    }
    public static void pirntBrowser(Composite shell){
//        PrinterData printerData = new PrinterData("", "Microsoft Print to PDF");
        PrinterData printerData = new PrinterData("", "FX DocuCentre-IV C2260 PCL 6");
//        PrinterData printerData = new PrinterData("", "Send To OneNote 2016");
        Printer printer = new Printer(printerData);
        Point point = new Point(0,0);
        PrintImage.printControl(shell,printer,point);
    }
}

