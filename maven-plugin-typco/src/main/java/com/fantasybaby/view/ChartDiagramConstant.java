package com.fantasybaby.view;

import java.util.HashMap;
import java.util.Map;

public class ChartDiagramConstant {
	public static Map<String,DefaultDiagramJfreeChartAbstract> allDiagramImpMap = new HashMap<String,DefaultDiagramJfreeChartAbstract>();
	
	public static Map<String,JFreeChartComposite> allDiagramMap = new HashMap<String,JFreeChartComposite>();
	
	public final static String  STOCK_IN = "in";
	public final static String  STOCK_OUT = "out";
	
	public final static String  STOCK_IN_NAME = "���";
	public final static String  STOCK_OUT_NAME = "����";
	
	public final static int CHART_DEFAULT_WIDTH = 600;
	public final static int CHART_DEFAULT_HEIGHT = 500;
}
