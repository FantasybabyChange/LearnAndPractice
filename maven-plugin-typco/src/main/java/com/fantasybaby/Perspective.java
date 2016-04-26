package com.fantasybaby;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.fantasybaby.view.MonitorDiagramView;

public class Perspective implements IPerspectiveFactory {
	public static final String ID = "maven-plugin-typco.perspective"; //$NON-NLS-1$
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.65f, editorArea);
		folder.addView(MonitorDiagramView.ID);
		IFolderLayout folderBottom = layout.createFolder("bottoms", IPageLayout.BOTTOM, 0.65f, editorArea);
		layout.getViewLayout(MonitorDiagramView.ID).setCloseable(false);
	}
}
