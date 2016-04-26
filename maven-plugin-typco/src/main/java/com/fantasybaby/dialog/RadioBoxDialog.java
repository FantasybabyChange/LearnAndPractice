package com.fantasybaby.dialog;


import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class RadioBoxDialog extends Dialog {
	private String dialogTitle = "请选择要生成二维码的编码规则";
	private Button firstBarCode;
	private Button secondBarCode;
	private Shell shell;
	private int  barCodeType = 0;
	public RadioBoxDialog(Shell parentShell,String dialogTitle) {
		super(parentShell);
		this.shell = parentShell;
		if(StringUtils.isNotEmpty(dialogTitle)){
			this.dialogTitle = dialogTitle;
		}
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		 Composite comp = new Composite(parent, SWT.None);  
		 comp.setSize(400,200);
         comp.setLayout(new GridLayout(2,false));
         Label label = new Label(comp,SWT.None);
         label.setText(dialogTitle);
         label.setFont(new Font(shell.getDisplay(),"宋体",11,SWT.BOLD));
         /*　　第一个参数：水平方向如何对齐

		       　　第二个参数：竖直方向如何对齐
		
		       　　第三个参数：是否占用水平的剩余空间
		
		       　　第四个参数：是否占用竖直的剩余空间
		
		       　　第五个参数：水平的列数
		
		       　　第六个参数：竖直的行数*/
         GridData ld1 = new GridData(GridData.FILL,GridData.CENTER,true,true,2,1);
         label.setLayoutData(ld1);
         GridData ld2 = new GridData(GridData.FILL_BOTH,GridData.FILL_BOTH,true,true,1,1);
         firstBarCode = new Button(comp, SWT.RADIO);
         firstBarCode.setText("一代二维码");
         firstBarCode.setLayoutData(ld2);
         firstBarCode.setFont(new Font(shell.getDisplay(),"宋体",9,SWT.NORMAL));
         secondBarCode = new Button(comp, SWT.RADIO);
         secondBarCode.setText("二代二维码");
         secondBarCode.setFont(new Font(shell.getDisplay(),"宋体",9,SWT.NORMAL));
         secondBarCode.setLayoutData(ld2);
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
		  @Override  
	        protected Point getInitialSize() {  
	            return new Point(500, 200);// 重新设置大小  
	        }  
		 
}