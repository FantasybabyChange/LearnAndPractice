package com.fantasybaby.view;


public abstract interface IDiagramJFreeChart {
	public void renderChartByDataSet(DiagramDefinition diagramInfo);
	
	public void refreshChart(ChartDataSet datas);
	
	public void refreshChart()throws Exception;
	
}
