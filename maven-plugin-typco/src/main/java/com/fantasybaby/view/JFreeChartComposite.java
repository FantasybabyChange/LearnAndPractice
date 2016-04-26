package com.fantasybaby.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.swt.ChartComposite;

import swing2swt.layout.BorderLayout;

public class JFreeChartComposite extends Composite {
	private Logger _loger = LogManager.getLogger(JFreeChartComposite.class);
	private Text refreshTime;
	private Composite composite;
	private Button btnAutoRefresh;
	public String compositeTitle;
	private Composite parentComposite;
	public boolean isEnable = true;
	private JFreeChartComposite jFreeChartComposite = this;
	private DiagramDefinition diagramDefinition;
	private boolean isAutoRefresh = false;
	private long refreshRate = 2;
	private TimerTask timerTask;
	private Timer timer = new Timer();
	private DefaultDiagramJfreeChartAbstract jfreeChartImp;
	public TimerTask getTimeTask(){
		return timerTask;
	}
	public void setIsEnable(boolean isEnable){
		this.isEnable = isEnable;
	}
	public JFreeChartComposite(Composite parent, int style, JFreeChart chart, DefaultDiagramJfreeChartAbstract _jfreeChartImp,final MultipleSelectionCombo multipleCombo,boolean isDispaly) throws Exception {
		super(parent, style);
		this.isEnable = isDispaly;
		parentComposite = parent;
		this.jfreeChartImp = _jfreeChartImp;
		compositeTitle = jfreeChartImp.title;
		if(isEnable == true){
			RowData rowData = new RowData(ChartDiagramConstant.CHART_DEFAULT_WIDTH,ChartDiagramConstant.CHART_DEFAULT_HEIGHT);
			this.setLayoutData(rowData);
			setLayout(new BorderLayout(0, 0));
			composite = new Composite(this, SWT.NONE);
			composite.setLayoutData(BorderLayout.NORTH);
			composite.setLayout(new FillLayout(SWT.HORIZONTAL));
			DiagramDefinition diagramInfo = jfreeChartImp.diagramInfo;
			if (diagramInfo != null) {
				this.diagramDefinition = diagramInfo;
				this.isAutoRefresh = this.diagramDefinition.getIsAutoRefresh();
				if (isAutoRefresh == true) {
					this.refreshRate = this.diagramDefinition.getRefreshRate();
				}
			}
			refreshTime = new Text(composite, SWT.BORDER);
			
			refreshTime.setText(refreshRate + "");
			
			Label lbls = new Label(composite, SWT.NONE);
			lbls.setText("\u5237\u65B0\u65F6\u95F4/s");
			
			btnAutoRefresh = new Button(composite, SWT.CHECK);
			btnAutoRefresh.setSelection(isAutoRefresh);
			if (isAutoRefresh) {
				timerTask = new RefreshChart();
				timer.schedule(timerTask,0,refreshRate*1000);
			}
			btnAutoRefresh.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						String timeSecond = refreshTime.getText();
						if(StringUtils.isNotEmpty(timeSecond)){
							refreshRate = Long.parseLong(timeSecond);
						};
						if(btnAutoRefresh.getSelection() ){
							timerTask = new RefreshChart();
							timer.schedule(timerTask,0,refreshRate*1000);
						}else{
							if(timerTask != null){
								timerTask.cancel();
								timerTask = null;
							}
						}
					} catch (Exception e2) {
						_loger.error(e2);
					}
					
				}
			});
			btnAutoRefresh.setText("auto refresh");
			Button btnNewButton = new Button(composite, SWT.NONE);
			btnNewButton.setText("refresh");
			btnNewButton.setToolTipText("refresh");
//			btnNewButton.setImage(Activator.getImageDescriptor(Walle.IMG_REFRESH).createImage());
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						jfreeChartImp.refreshChart();
					} catch (Exception e2) {
						_loger.error(e2);
					}
	    	}
			});
			Button closeButton = new Button(composite, SWT.NONE);
			closeButton.setText("close");
			closeButton.setToolTipText("close");
//			closeButton.setImage(Activator.getImageDescriptor(Walle.IMG_CLOSE).createImage());
			closeButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(timer != null){
						timer.cancel();
					}
					if(jFreeChartComposite != null){
						jFreeChartComposite.dispose();
					}
					isEnable = false;
					parentComposite.layout();
					resizeParentPanel();
					multipleCombo.resetSelect();
				}
			});
			ChartComposite freeChartComposite = new ChartComposite(this, SWT.None, chart, true);
			freeChartComposite.setDisplayToolTips(true);
			freeChartComposite.setHorizontalAxisTrace(true);
			freeChartComposite.setVerticalAxisTrace(true);
			freeChartComposite.pack();
			freeChartComposite.layout();
			resizeParentPanel();
		}else{
			this.dispose();
		}
		ChartDiagramConstant.allDiagramMap.put(jfreeChartImp.chartKey, this);
		ChartDiagramConstant.allDiagramImpMap.put(jfreeChartImp.chartKey, jfreeChartImp);
		}
		
		public  void resizeParentPanel(){
			ScrolledComposite scrollParent = (ScrolledComposite) parentComposite.getParent();
			int y = scrollParent.getSize().y;
			Map<String, JFreeChartComposite> allDiagramMap = ChartDiagramConstant.allDiagramMap;
			Set<String> keySet = allDiagramMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				String next = iterator.next();
				JFreeChartComposite jFreeChartComposite = allDiagramMap.get(next);
				if (jFreeChartComposite.isEnable == true) {
					count = count + 1;
				}
			}
			if(y > 0){
				if(y <= (ChartDiagramConstant.CHART_DEFAULT_HEIGHT + 20)){
					if(count > 0){
						int length = count;
						scrollParent.setMinWidth((length+1) * ChartDiagramConstant.CHART_DEFAULT_WIDTH);
						scrollParent.setMinHeight( ChartDiagramConstant.CHART_DEFAULT_HEIGHT + 20);
					}
				}else{
					if(count > 0){
						int length = count;
						int x = parentComposite.getSize().x;
						int line = x/ChartDiagramConstant.CHART_DEFAULT_WIDTH;
						scrollParent.setMinWidth( ChartDiagramConstant.CHART_DEFAULT_WIDTH);
						scrollParent.setMinHeight((length/line+1) * (ChartDiagramConstant.CHART_DEFAULT_HEIGHT+10));
				}
			}
		}else{
			//TODO if we need init panel when load panel first time. 
		}
		scrollParent.layout();
		parentComposite.layout();
	}
	class RefreshChart extends TimerTask{
		@Override
		public void run() {
		  Display.getDefault().syncExec(new Runnable() {
			    public void run() {
					try {
						jfreeChartImp.refreshChart();
					} catch (Exception e) {
						_loger.error(e);
					}
			    	}
			    }); 
		}
		
	}
}
