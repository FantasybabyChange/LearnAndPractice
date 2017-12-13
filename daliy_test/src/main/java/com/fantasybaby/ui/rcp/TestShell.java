package com.fantasybaby.ui.rcp;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestShell {
	public static void main(String[] args) throws InterruptedException {
		
		Display display = new Display();
	    Shell shell=new Shell(display);//shell是程序的主窗体 

	    shell.setLayout(null);         //设置shell的布局方式 

	    shell.setText("Java应用程序");  //设置主窗体的标题 

	    shell.setSize(1000,500);        //设置主窗体的大小 
	    AlertMessageDialog am =  AlertMessageDialog.getInstance();
	    am.init("有拣货任务分配在正在（排队）补货的货架上，订单已经超时，请立即完成补货操作以释放货架！！", shell, 800, 28, 200,20);
	    am.setAlpha(150);
	    am.setIsMove(true);
	    am.setMoveSpeed(20);
//	    am.setIsAutoClose(true);
//	    am.setCloseSeconds(10);
	    shell.open();
	    am.showAlert();
	    while(!shell.isDisposed()){  //如果主窗体没有关闭则一直循环 

	        if(!display.readAndDispatch()){  //如果display不忙 

	        display.sleep();    //休眠 

	        } 

	    } 
	    display.dispose();  
		/*   
	Display display = new Display();
    Shell shell=new Shell(display);//shell是程序的主窗体 

    shell.setLayout(null);         //设置shell的布局方式 

    shell.setText("Java应用程序");  //设置主窗体的标题 

    shell.setSize(1000,500);        //设置主窗体的大小 

//    Color color=new Color(Display.getCurrent(),255,255,255);//声明颜色对象 

    shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));   //设置窗体的背景颜色
    Shell shell2 = new Shell(shell,SWT.BORDER_SOLID);
//    shell2.setLayout(new FillLayout());
    shell2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
    shell2.setAlpha(150);
    shell2.setBackgroundMode(SWT.INHERIT_FORCE); 
//    shell2.setSize(1000,100);
    final Label hello=new Label(shell2,SWT.NONE); //声明一个可以显示多行信息的文本框
//    OS.SetWindowLong(hello.handle,OS.GWL_EXSTYLE,OS.GetWindowLong(hello.handle, OS.GWL_EXSTYLE)^0x80000);
    hello.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
    hello.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", StationUIUtils.scale(15), SWT.BOLD));
//    hello.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
    String str = "有拣货任务分配在正在（排队）补货的货架上，订单已经超时，请立即完成补货操作以释放货架！！";
    hello.setText(str);//设置文本框信息 
//    hello.pack();    //自动调整文本框的大小
    hello.setSize(1000, 28);
    hello.setLocation(0,0);
    
    final Label hello1=new Label(shell2,SWT.NONE); //声明一个可以显示多行信息的文本框
    hello1.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
    hello1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", StationUIUtils.scale(15), SWT.BOLD));
	//  hello.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	  hello1.setText("aaaa"+str);//设置文本框信息 
	//  hello.pack();    //自动调整文本框的大小
	  hello1.setSize(1000, 28);
	  hello1.setLocation(hello.getSize().x,0);
    shell2.setSize(15*str.length(),hello.getBounds().height+2);
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
								if(hello != null &&!hello.isDisposed()){
									Point firstLocation = hello.getLocation();
									Point secondLocation = hello1.getLocation();
									if(firstLocation.x < secondLocation.x){
										if(firstLocation.x+hello.getSize().x <= 0){
											hello.setLocation(secondLocation.x+hello1.getSize().x, 0);
										}
									}else{
										if(secondLocation.x+hello1.getSize().x <= 0){
											hello1.setLocation(firstLocation.x+hello.getSize().x, 0);
										}
									}
									hello.setLocation(hello.getLocation().x-10,hello.getLocation().y);
									hello1.setLocation(hello1.getLocation().x-10,hello.getLocation().y);
								}else{
									return;
								}
								
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
    */
	}
	public void runTheWords(){
		
	}
	}
