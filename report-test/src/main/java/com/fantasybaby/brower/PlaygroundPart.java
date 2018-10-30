package com.fantasybaby.brower;

import com.fantasybaby.file.html2image.ConvertHtml2Image;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class PlaygroundPart {
    public static void main(String args[])

    {

        Display display=new Display();

       Shell shell=new Shell(display);

//        shell.setText("SWT Browser Test");

        shell.setSize(1000,700);

/*

        Button button=new Button(shell,SWT.BORDER);

        button.setBounds(680,5,100,25);

        button.setText("打印");

        Label label=new Label(shell,SWT.LEFT);

        label.setText("打印 :");

        label.setBounds(5, 5, 100, 25);
*/
        final Browser browser=new Browser(shell,SWT.FILL);

        browser.setBounds(5,30,780,560);



     /*   button.addListener(SWT.Selection, new Listener()

        {

            public void handleEvent(Event event)

            {
                SwtBrower.pirntBrowser(browser);
            }

        });*/


//        browser.setUrl("http://www.baidu.com");
        String html = ConvertHtml2Image.getHtmlContent("print3.html","UTF-8");
        browser.setText(html,true);
        shell.open();
//       new Thread(()->SwtBrower.pirntBrowser(browser)).start();
//        Display.getDefault().asyncExec(() -> {
//            SwtBrower.pirntBrowser(browser);
////           shell.layout();
////            display.dispose();
//        });
        while (!shell.isDisposed()) {

            if (!display.readAndDispatch())

                display.sleep();
        }
    }
}