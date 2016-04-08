package com.fantasybaby.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class HelloAction extends Action{
	private IWorkbenchWindow window;  

	public HelloAction(IWorkbenchWindow window)	{  

		this.window = window;  
	
		this.setText("Hello World");  
	
		ImageDescriptor imgDes =  
	
		WorkbenchImages.getImageDescriptor(  
	
		IWorkbenchGraphicConstants.IMG_ETOOL_IMPORT_WIZ);  
	
		this.setImageDescriptor(imgDes);
	}
	@Override
	public void run(){  
		MessageBox mb = new MessageBox(window.getShell(), SWT.OK);  
		mb.setMessage("Hello world!");  
		mb.setText("Demo");  
		mb.open();  
	} 

}