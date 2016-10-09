package com.fantasybaby.rcp;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

/**
 * A alert message Dialog in a new shell
 * @author FantasyBaby
 *
 */
public class AlertMessageDialog {
//	private static Display display = new Display();
    private  Shell shell;
    private Label fisrtLabel;
    private Label secondLabel;
    private static AlertMessageDialog _instance;
    private Layout shellLayout;
    private  String alertInfo = "请初始化提示!!!";
    private boolean isAutoClose = false;
    private int closeSeconds = 3; 
    private int shellWidth = 100;
    private int shellHeight = 100;
    private int shellLocationx = 0;
    private int shellLocationy = 0;
    private boolean isMove = false;
    private int moveSpeed = 3;
    private int alpha = 200;
    private ShellPositionEnum position = null;
    
    private Color shellBackColor = SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW);
    private Font labelFont = SWTResourceManager.getFont("Microsoft YaHei UI", StationUIUtils.scale(30), SWT.BOLD);
    private Color labelBackColor = SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW);
    private Color labelColor = SWTResourceManager.getColor(SWT.COLOR_WHITE);
    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private Thread moveThread;
	private AlertMessageDialog() {
		
	}
	public void init(String alertInfo,Shell parentShell){
		this.alertInfo = alertInfo;
		if(parentShell != null){
			shell = new Shell(parentShell,SWT.NONE);
		}else{
			shell = new Shell(new Display());
		}
	}
	public void init(String alertInfo,Shell parentShell,boolean isAutoClose,int closeSeconds){
		this.isAutoClose = isAutoClose;
		if(closeSeconds > 0 ){
			this.closeSeconds = closeSeconds;
		}
		init(alertInfo,parentShell);
	}
	public void init(String alertInfo,Shell parentShell,int shellWidth,int shellHeight,ShellPositionEnum position){
		if(shellWidth > 0 && shellHeight > 0){
			this.shellWidth = shellWidth;
			this.shellHeight = shellHeight;
		}
		this.position = position;
		init(alertInfo, parentShell);
	}
	public void init(String alertInfo,Shell parentShell,int shellWidth,int shellHeight,int shellLocationx,int shellLocationy){
		if(shellWidth > 0 && shellHeight > 0){
			this.shellWidth = shellWidth;
			this.shellHeight = shellHeight;
		}
		this.shellLocationx = shellLocationx;
		this.shellLocationy = shellLocationy;
		init(alertInfo, parentShell);
	}
	public void setIsAutoClose(boolean isAutoClose){
		this.isAutoClose = isAutoClose;
	}
	public void setIsMove(boolean isMove){
		this.isMove = isMove;
	}
	public void setMoveSpeed(int moveSpeed){
		this.moveSpeed = moveSpeed;
	}
	private void setShellPosition(ShellPositionEnum position){
		int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
	    int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
	    Point size = shell.getSize();
	    switch (position) {	
	    case TOP_LEFT:
	    	break;
		case TOP_MID:
			shellLocationx = screenW/2;
			break;
		case TOP_RIGHT:
			shellLocationx = screenW - size.x;
		break;
		case MID_LEFT:
			shellLocationy = screenH/2;
		break;
		case MID_MID:
			shellLocationx = screenW/2;
			shellLocationy = screenH/2;
		break;
		case MID_RIGTH:
			shellLocationx = screenW - size.x;
			shellLocationy = screenH/2;
		break;
		case BOT_LEFT:
			shellLocationy = screenH - size.y;
		break;
		case BOT_MID:
			shellLocationx = screenW/2;
			shellLocationy = screenH - size.y;
		break;
		case BOT_RIGTH:
			shellLocationx = screenW - size.x;
			shellLocationy = screenH - size.y;
		break;
		default:
			break;
	    }
	}
	public static AlertMessageDialog getInstance(){
		if(_instance == null){
			_instance = new AlertMessageDialog();
		}
		return _instance;
	}
	
	public void showAlert(){
		//end the Timer
		initTimer();
		shell.setBackground(shellBackColor);
		shell.setAlpha(alpha);
		shell.setSize(this.shellWidth,this.shellHeight);
		shell.setBackgroundMode(SWT.INHERIT_FORCE); 
		if(shellLayout != null){
			shell.setLayout(shellLayout);
		}
		float height = this.labelFont.getFontData()[0].height;
		float labelLength = 1.5f*this.alertInfo.length()*height;
		Composite parentComposite = new Composite(shell,SWT.NONE);
		parentComposite.setSize((int)labelLength, 2*(int)height);
		parentComposite.setLocation(10, 0);
		this.fisrtLabel = new Label(parentComposite,SWT.NONE);
		fisrtLabel.setBackground(labelBackColor);
		fisrtLabel.setForeground(labelColor);
		fisrtLabel.setFont(labelFont);
		fisrtLabel.setText(this.alertInfo);
		fisrtLabel.setLocation(0,0);
//		fisrtLabel.pack();
		
		fisrtLabel.setSize((int)labelLength, 2*(int)height);
		if(isMove){
			this.secondLabel = new Label(parentComposite,SWT.NONE);
			secondLabel.setBackground(labelBackColor);
			secondLabel.setForeground(labelColor);
			secondLabel.setFont(labelFont);
			secondLabel.setText(this.alertInfo);
			secondLabel.setLocation(fisrtLabel.getSize().x,0);
			secondLabel.setSize((int)labelLength, 2*(int)height);
		}
		if(this.position != null){
			setShellPosition(position);
		}
		shell.setLocation(shellLocationx, shellLocationy);
		shell.open();
		if(this.isMove){
			if(moveThread == null){
				moveThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						autoMove();
						
					}
				});
				moveThread.start();
			}
		}
		if(this.isAutoClose){
			if(timer == null){
				timer = new Timer();
			}
			if(timerTask == null){
				timerTask = new TimerTask() {
					@Override
					public void run() {
						autoCloseAlert();
					}
					};
			}
	    	timer.schedule(timerTask, this.closeSeconds*1000);
		    
		}
//		 shell.setSize(15*str.length(),hello.getBounds().height+2);
		
	}
	public void setLabelBackGround(Color labelBackColor){
		this.labelBackColor = labelBackColor; 
	}
	public void setLabelFontColor(Color labelColor){
		this.labelColor = labelColor; 
	}
	public void setLabelFont(Font labelFont){
		this.labelFont = labelFont;
	}
	public void setShellLayout(Layout shellLayout){
		this.shellLayout = shellLayout;
	}
	public void setAlpha(int alpha){
		this.alpha = alpha;
	}
	public void closeAlert(){
		if(this.fisrtLabel != null && !fisrtLabel.isDisposed()){
			fisrtLabel.isDisposed();
			fisrtLabel = null;
		}
		if(this.secondLabel != null && !secondLabel.isDisposed()){
			secondLabel.isDisposed();
			secondLabel = null;
		}
		if(shell != null && !shell.isDisposed()){
			shell.close();
			shell.dispose();
			shell = null;
		}
	}
		private void autoCloseAlert(){
			Display.getDefault().syncExec(new Runnable() {
	    	    public void run() {
	    	 closeAlert();
	    	 initTimer();
			  }
		});
	}
		private void initTimer(){
			Display.getDefault().syncExec(new Runnable() {
	    	    public void run() {
	    	 if(timer != null){
	    		 timer.cancel();
	    		 timer = null;
	    	 }
			if(timerTask != null){
				timerTask.cancel();
				timerTask = null;
			}
			  }
		});
	}
	public void setCloseSeconds(int closeSeconds){
		this.closeSeconds = closeSeconds;
	} 
	private void autoMove(){
		while (true) {
			try {
				Thread.sleep(500);
				if(fisrtLabel != null &&!fisrtLabel.isDisposed()){
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
								Point firstLocation = fisrtLabel.getLocation();
								Point secondLocation = secondLabel.getLocation();
								if(firstLocation.x < secondLocation.x){
									if(firstLocation.x+fisrtLabel.getSize().x <= 0){
										fisrtLabel.setLocation(secondLocation.x+secondLabel.getSize().x, 0);
									}
								}else{
									if(secondLocation.x+secondLabel.getSize().x <= 0){
										secondLabel.setLocation(firstLocation.x+fisrtLabel.getSize().x, 0);
									}
								}
								fisrtLabel.setLocation(fisrtLabel.getLocation().x-moveSpeed,fisrtLabel.getLocation().y);
								secondLabel.setLocation(secondLabel.getLocation().x-moveSpeed,secondLabel.getLocation().y);
							
						}
					});
			}else{
				moveThread = null;
				return;
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
