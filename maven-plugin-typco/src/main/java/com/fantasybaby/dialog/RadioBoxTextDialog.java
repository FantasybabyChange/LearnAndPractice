package com.fantasybaby.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class RadioBoxTextDialog extends TitleAreaDialog{
	private String dialogTitle = "请选择要生成二维码的编码规则";
	private Button firstBarCode;
	private Button secondBarCode;
	private Shell shell;
	private int  barCodeType = 0;
	
	public RadioBoxTextDialog(Shell parentShell,String dialogTitle) {
		super(parentShell);
		shell = parentShell;
		this.dialogTitle = dialogTitle;
		
	}
	protected Control createDialogArea(Composite parent) {
		setTitle(dialogTitle);
		setTitleImage(null);
		firstBarCode = new Button(parent, SWT.RADIO);
		firstBarCode.setText("一代二维码");
		firstBarCode.setFont(new Font(shell.getDisplay(),"宋体",12,SWT.NORMAL));
		secondBarCode = new Button(parent, SWT.RADIO);
		secondBarCode.setText("二代二维码");
		secondBarCode.setFont(new Font(shell.getDisplay(),"宋体",12,SWT.NORMAL));
		return super.createDialogArea(parent);
	}
	 protected void createButtonsForButtonBar(Composite parent) {
	        createButton(parent, IDialogConstants.OK_ID, "确定", true);
	        createButton(parent, IDialogConstants.CANCEL_ID, "取消", false);
	 }
	 @Override
	protected void okPressed() {
		 if(secondBarCode != null && secondBarCode.getSelection()){
			 barCodeType = 1;
		 }
		super.okPressed();
	}
	 public int getCheckType(){
		 return this.barCodeType;
	 }
}