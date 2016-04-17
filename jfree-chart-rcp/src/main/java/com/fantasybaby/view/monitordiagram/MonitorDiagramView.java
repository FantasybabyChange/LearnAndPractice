package com.fantasybaby.view.monitordiagram;

import java.lang.reflect.Method;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.swt.ChartComposite;
import org.jfree.data.category.DefaultCategoryDataset;

import com.fantasybaby.DiagramJfreeChartFactory;

import testrpc.Activator;

import org.eclipse.swt.custom.ScrolledComposite;

public class MonitorDiagramView extends ViewPart {

	public static final String ID = "walle.MonitorDiagramView"; //$NON-NLS-1$
//	private static Logger _log = Logger.getLogger(MonitorDiagramView.class);
	private  ChartComposite freeChartComposite;
	private  JFreeChart freeChart;
	private SashForm sashForm;
	private IDiagramJFreeChart diagramChar;
	private static final String ROOT_NODE_VALUE = "rootNode";
	private ScrolledComposite scrolledComposite;
	public MonitorDiagramView() {
	}
	class RefreshAction extends Action {
		private static final float count = 1;
		public final static String ID = "walle.versionListRefreshAction";
		public RefreshAction(String text) {
			this.setId(ID);
			this.setImageDescriptor(Activator.getImageDescriptor("refresh.gif"));
			this.setText(text);
			this.setToolTipText(text);
		}

		@Override
		public void run() {
			if (diagramChar != null) {
				String series1 = "1";
				  String series2 = "2";
				  String series3 = "3";
				  // 列
				  String category1 = "一月份";
				  String category2 = "二月份";
				  String category3 = "三月份";
				  String category4 = "四月份";
				  String category5 = "五月份";

				  // 创建数据源
				  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				  // 放入数据
				  dataset.addValue(1.0 + count, series1, category1);
				  dataset.addValue(4.0 + count, series1, category2);
				  dataset.addValue(3.0 + count, series1, category3);
				  dataset.addValue(5.0 + count, series1, category4);
				  dataset.addValue(5.0 + count, series1, category5);

				  dataset.addValue(5.0 + count, series2, category1);
				  dataset.addValue(7.0 + count, series2, category2);
				  dataset.addValue(6.0 + count, series2, category3);
				  dataset.addValue(8.0 + count, series2, category4);
				  dataset.addValue(4.0 + count, series2, category5);

				  dataset.addValue(4.0 + count, series3, category1);
				  dataset.addValue(3.0 + count, series3, category2);
				  dataset.addValue(2.0 + count, series3, category3);
				  dataset.addValue(3.0 + count, series3, category4);
				  dataset.addValue(6.0 + count, series3, category5);
				diagramChar.refreshHistogramChart(freeChart, dataset);
			}
		}
	}
	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		try {
			sashForm = new SashForm(parent, SWT.NONE);
			sashForm.setLayout(new FillLayout(SWT.HORIZONTAL));

			/*diagramChar = DiagramJfreeChartFactory.getDiagramJfreeChart(JFreeChartWalleSWTImp.class);
			freeChart = diagramChar.createLineChart();
			
			scrolledComposite_1 = new ScrolledComposite(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite_1.setExpandHorizontal(true);
			scrolledComposite_1.setExpandVertical(true);
			freeChartComposite = new ChartComposite(scrolledComposite_1, SWT.None, freeChart, true);
			freeChartComposite.setDisplayToolTips(true);
			freeChartComposite.setHorizontalAxisTrace(false);
			freeChartComposite.setVerticalAxisTrace(false);
			scrolledComposite_1.setContent(freeChartComposite);
			scrolledComposite_1.setMinSize(freeChartComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			scrolledComposite_1.pack();
			sashForm.setWeights(new int[] {1, 1});*/
		
			new LeftTreeComposite(sashForm, SWT.None); 
			initializeToolBar(parent);
		} catch (Exception e) {
//			_log.error(e);
		}
		
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar(Composite parent) {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		RefreshAction action = new RefreshAction("refresh");
		toolbarManager.add(action);
	}


	@Override
	public void setFocus() {
		
	}
	private void initDiagram(String value) throws Exception{
		if( value.length() > 1){
			if(scrolledComposite == null){
				scrolledComposite = new ScrolledComposite(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
				scrolledComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
				scrolledComposite.setExpandHorizontal(true);
				scrolledComposite.setExpandVertical(true);
			}
			String methodName = "create" + value.substring(0,1).toUpperCase() + value.substring(1,value.length()); 
			if(diagramChar == null){
				diagramChar = DiagramJfreeChartFactory.getDiagramJfreeChart(JFreeChartWalleSWTImp.class);
			}
			Method method = diagramChar.getClass().getMethod(methodName);
			Object invoke = method.invoke(diagramChar);
			freeChart = (JFreeChart) invoke;
			freeChartComposite = new ChartComposite(scrolledComposite, SWT.None, freeChart, true);
			freeChartComposite.setDisplayToolTips(true);
			freeChartComposite.setHorizontalAxisTrace(false);
			freeChartComposite.setVerticalAxisTrace(false);
			scrolledComposite.setContent(freeChartComposite);
			scrolledComposite.setMinSize(freeChartComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			sashForm.setWeights(new int[]{20,80});
			freeChartComposite.pack();
		}
	}
	class LeftTreeComposite extends Composite{
		private Tree chartTree;
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
	        			initDiagram(diagramTypeEnum.getValue());
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
		        					 freeChartComposite.dispose();
		        					 initDiagram(nodeData.toString());
		        					 sashForm.layout();
		        				 }
							} catch (Exception ex) {
//								_log.error("LeftTreeComposite Inner Class" + ex);
							}
	        			 }
	        		}
	        ); 
		}
		
	}
}
