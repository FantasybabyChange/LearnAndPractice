package com.fantasybaby;

import com.fantasybaby.view.monitordiagram.IDiagramJFreeChart;

public class DiagramJfreeChartFactory {
	private static IDiagramJFreeChart _instance;
	
	public static IDiagramJFreeChart getDiagramJfreeChart(Class<? extends IDiagramJFreeChart> _class) throws InstantiationException, IllegalAccessException{
			_instance =  _class.newInstance();
			return _instance;
	} 
}
