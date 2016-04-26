package com.fantasybaby.view;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.widgets.Composite;
import org.jfree.chart.JFreeChart;

public  abstract class DefaultDiagramJfreeChartAbstract implements IDiagramJFreeChart {
	private static Logger _log = LogManager.getLogger(DefaultDiagramJfreeChartAbstract.class);
	protected String title;
	protected String xAxisName;
	protected String yAxisName;
	protected Map<String, List<Double>> columnDataMap;
	protected List<String> rowKeys;
	protected JFreeChart chart;
	protected Map<String, Double> pieDataMap;
	protected DiagramTypeEnum type;
	protected ChartDataSet chartDataSet;
	protected IChartRefresh refresh;
	protected String chartKey;
	protected boolean isDisplay = false;
	protected DiagramDefinition diagramInfo;
	
	protected void initData(ChartDataSet datas){
		this.columnDataMap = datas.getColumnDataMap();
		this.rowKeys = datas.getRowKeys();
		this.xAxisName = datas.getxAxisName();
		this.yAxisName = datas.getyAxisName();
		this.pieDataMap = datas.getPieDataMap();
	}
	@Override
	public void renderChartByDataSet(DiagramDefinition diagramInfo) {
		try {
			this.diagramInfo = diagramInfo;
			if (this.diagramInfo == null) {
				throw new Exception("DiagramDefinition should not be null");
			}
			this.refresh = diagramInfo.getRefresh();
			if(this.refresh == null){
				throw new Exception("please init refresh method");
			}
			this.title = diagramInfo.getDiagramTitle();
			if (StringUtils.isEmpty(this.title)) {
				throw new Exception("please set a chart title");
			}
			this.isDisplay = diagramInfo.getIsDispaly();
			this.type = diagramInfo.getDiagramType();
			renderChartByDataSet();
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	public void renderChartByDataSet() throws Exception{
		if(this.isDisplay == true){
			if (refresh != null) {
				this.chartDataSet = refresh.getRefreshData();
			}
			if(this.chartDataSet != null){
				initData(this.chartDataSet);
				if(this.type == null){
					throw new Exception("please init chart type");
				}
				Method method = this.getClass().getDeclaredMethod(getMethodName("prepare", this.type.getValue()));
				if (method != null) {
					 method.invoke(this);
				}
			}else{
				throw new Exception("the ChartDataSet should not be empty");
			}
		}
		renderChartInUI();
	}
	public void renderChartByDataSet(boolean isDisplay){
		this.isDisplay = isDisplay;
		try {
			renderChartByDataSet();
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	public abstract void initRootPanel(Composite composite ,MultipleSelectionCombo multipleCombo);
	protected abstract void prepareHistogramChart();
	protected abstract void prepareLineChart();
	protected abstract void preparePieChart();
	public abstract void renderChartInUI()throws Exception;
	protected String getMethodName(String pre,String middle,String sufix){
		middle = middle.substring(0,1).toUpperCase()+middle.substring(1,middle.length());
		return pre + middle + sufix;
	}
	protected String getMethodName(String pre,String middle){
		return this.getMethodName(pre, middle, "");
	}
}
