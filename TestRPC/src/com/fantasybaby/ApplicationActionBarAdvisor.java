package com.fantasybaby;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.fantasybaby.action.HelloAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private HelloAction helloAction;
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	if (helloAction == null) {
			helloAction = new HelloAction(window);
		}
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	super.fillMenuBar(menuBar);
    	MenuManager demoMenu = new MenuManager("&Demo", "");  
    	demoMenu.add(helloAction);  
    	menuBar.add(demoMenu); 
    }
    @Override
    protected void fillCoolBar(ICoolBarManager coolBar)  
    {  
 /*   IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);  
    coolBar.add(new ToolBarContributionItem(toolbar, "main"));  
    toolbar.add(helloAction);  */

    } 
}
