package com.fantasybaby.rcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class TestShell {
	public static void main(String[] args) throws InterruptedException {   
	Display display = new Display();
    Shell shell=new Shell(display);//shell是程序的主窗体 

    shell.setLayout(null);         //设置shell的布局方式 

    shell.setText("Java应用程序");  //设置主窗体的标题 

    shell.setSize(1000,500);        //设置主窗体的大小 

//    Color color=new Color(Display.getCurrent(),255,255,255);//声明颜色对象 

    shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));   //设置窗体的背景颜色
    Shell shell2 = new Shell(shell,SWT.BORDER_SOLID);
    shell2.setLayout(new FillLayout());
    shell2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
    shell2.setAlpha(150);
    shell2.setBackgroundMode(SWT.INHERIT_FORCE); 
//    shell2.setSize(1000,100);
    final Label hello=new Label(shell2,SWT.NONE); //声明一个可以显示多行信息的文本框
//    OS.SetWindowLong(hello.handle,OS.GWL_EXSTYLE,OS.GetWindowLong(hello.handle, OS.GWL_EXSTYLE)^0x80000);
    hello.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
    hello.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", StationUIUtils.scale(15), SWT.BOLD));
    hello.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
    
    hello.setText("有拣货任务分配在正在（排队）补货的货架上，订单已经超时，请立即完成补货操作以释放货架！！");//设置文本框信息 
    hello.pack();    //自动调整文本框的大小
    hello.setLocation(0,0);
    shell2.setSize(400,hello.getBounds().height+2);
//    shell2.pack();  //自动调整主窗体的大小 
    shell.open();    //打开主窗体 
    shell2.open();

	

    final int width = hello.getBounds().width;
    new Thread(new Runnable() {
		
		@Override
		public void run() {

			 for (int i = 0; i < width; i++) {

					try {
						Thread.sleep(500);
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								hello.setLocation(hello.getLocation().x-3,hello.getLocation().y);
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				
				}
		}
	}).start();
   
   
    while(!shell.isDisposed()){  //如果主窗体没有关闭则一直循环 

        if(!display.readAndDispatch()){  //如果display不忙 

        display.sleep();    //休眠 

        } 

    } 

    display.dispose();      //销毁display 
    }
	public void runTheWords(){
		
	}
	}
