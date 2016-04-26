package com.fantasybaby.view;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


class LeftTreeComposite extends Composite{
		private Tree chartTree;
		private static final String ROOT_NODE_VALUE = "rootNode";
		public LeftTreeComposite(Composite parent, int style) throws Exception {
			super(parent, style);
			this.setLayout(new FillLayout());  
	        chartTree = new Tree(this, SWT.BORDER);  
	        TreeItem rootNode = new TreeItem(chartTree, SWT.NONE);  
	        rootNode.setText("监控图");
	        rootNode.setData(ROOT_NODE_VALUE);
	        DiagramTypeEnum[] diagramTypes = DiagramTypeEnum.values();
	        int count = 0;
	        if (diagramTypes != null && diagramTypes.length > 0) {
	        	for (DiagramTypeEnum diagramTypeEnum : diagramTypes) {
	        		if(count == 0){
//	        			initDiagram(diagramTypeEnum.getValue());
	        			count = -1;
	        		}
	        		TreeItem childNode = new TreeItem(rootNode, SWT.NONE);  
	        		childNode.setText(diagramTypeEnum.getName());  
	        		childNode.setData(diagramTypeEnum.getValue());
				}
			}
	        rootNode.setExpanded(true);  
	        chartTree.addSelectionListener(
	        		new SelectionAdapter() {
	        			 @Override  
	        	            public void widgetSelected(SelectionEvent e) {
	        				 try {
		        				 Object nodeData = e.item.getData();
		        				 if(nodeData != null && !ROOT_NODE_VALUE.equals(nodeData.toString()) ){
//		        					 freeChartComposite.dispose();
//		        					 initDiagram(nodeData.toString());
//		        					 sashForm.layout();
		        				 }
							} catch (Exception ex) {
//								_log.error("LeftTreeComposite Inner Class" + ex);
							}
	        			 }
	        		}
	        ); 
		}
		
	}