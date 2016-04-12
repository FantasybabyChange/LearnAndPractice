package com.fantasybaby;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.fantasybaby.view.monitordiagram.MonitorDiagramView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.65f, editorArea);
		folder.addView(MonitorDiagramView.ID);
		layout.getViewLayout(MonitorDiagramView.ID).setCloseable(false);
	}
}
