package com.fantasybaby.view;

import java.util.List;
import java.util.Map;

/**A object which use to init chart diagram data
 * 
 * @author FantasyBaby
 *
 */
public  class ChartDataSet {
	/**
	 * the name of horizontal axis
	 */
	private String xAxisName;
	/**
	 * the name of vertical axis
	 */
	private String yAxisName;
	/**
	 * the names of degree scale
	 */
	private List<String> rowKeys;
	/**
	 * key String
	 * Datas List
	 * 
	 * 
	 */
	private Map<String, List<Double>> columnDataMap ;
	
	private Map<String,Double> pieDataMap;
	public ChartDataSet() {
	}
	public ChartDataSet(String xAxisName, String yAxisName,
			List<String> rowKeys, Map<String, List<Double>> columnDataMap) {
		super();
		this.xAxisName = xAxisName;
		this.yAxisName = yAxisName;
		this.rowKeys = rowKeys;
		this.columnDataMap = columnDataMap;
	}
	public ChartDataSet(String title,
			Map<String,Double> pieDataMap) {
		super();
		this.pieDataMap = pieDataMap;
	}

	public String getxAxisName() {
		return xAxisName;
	}
	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}
	public String getyAxisName() {
		return yAxisName;
	}
	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}
	public List<String> getRowKeys() {
		return rowKeys;
	}
	public void setRowKeys(List<String> rowKeys) {
		this.rowKeys = rowKeys;
	}
	public Map<String, List<Double>> getColumnDataMap() {
		return columnDataMap;
	}
	public void setColumnDataMap(Map<String, List<Double>> columnDataMap) {
		this.columnDataMap = columnDataMap;
	}
	public Map<String, Double> getPieDataMap() {
		return pieDataMap;
	}
	public void setPieDataMap(Map<String, Double> pieDataMap) {
		this.pieDataMap = pieDataMap;
	}
	
}
