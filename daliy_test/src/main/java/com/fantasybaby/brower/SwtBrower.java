package com.fantasybaby.brower;

import com.fantasybaby.file.html2image.ConvertHtml2Image;
import com.fantasybaby.file.html2image.ConvertHtml2ImageNew;
import com.fantasybaby.file.print.PrintImage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author reid.liu
 * @date 2018-10-12 14:13
 */
public class SwtBrower {
    private Browser browser;
    public void openBrowser(){
        String html = ConvertHtml2Image.getHtmlContent("print3.html","UTF-8");
        Display display = new Display();
        Shell shell = new Shell(SWT.CENTER);
        GridLayout gridLayout = new GridLayout(1,false);
        shell.setLayout(gridLayout);
//        shell.setSize(3000,1000);
        shell.setBounds(Display.getDefault().getPrimaryMonitor().getBounds());
        try {
            browser = new Browser(shell, SWT.CENTER);
            browser.setBounds(Display.getDefault().getPrimaryMonitor().getBounds());
        } catch (SWTError e) {
            System.out.println("Could not instantiate Browser: " + e.getMessage());
            display.dispose();
            return;
        }
        browser.setText(html);
//        shell.open();
        pirntBrowser(browser);
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
    public static void main(String [] args) {
        new SwtBrower().openBrowser();
    }
    public static void pirntBrowser(Composite shell){
//        PrinterData printerData = new PrinterData("", "Microsoft Print to PDF");
        PrinterData printerData = new PrinterData("", "FX DocuCentre-IV C2260 PCL 6");
        Printer printer = new Printer(printerData);
        Point point = new Point(0,0);
        PrintImage.printControl(shell,printer,point);
    }
}

