package com.fantasybaby.view.monitordiagram;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public interface IDiagramJFreeChart {
	public JFreeChart createHistogramChart();
	
	public void refreshHistogramChart(JFreeChart freeChart,DefaultCategoryDataset categoryDataSet);
	
	public JFreeChart createLineChart();
	
	public JFreeChart createPieChart();
	
}
