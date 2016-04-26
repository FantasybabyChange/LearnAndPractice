/*package com.walle.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Test;

import com.kc.walle.operation.console.ui.monitordiagram.ChartDataSet;
import com.kc.walle.operation.console.ui.monitordiagram.DefaultDiagramJfreeChartAbstract;
import com.kc.walle.operation.console.ui.monitordiagram.DiagramTypeEnum;
import com.kc.walle.operation.console.ui.monitordiagram.IDiagramJFreeChart;
import com.kc.walle.operation.console.ui.monitordiagram.JFreeChartWalleSWTImp;

public class TestJfreeChart {
	@Test
	public void test1(){
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		System.out.println("hello world");
	}
	@Test
	public void testHisChart(){
		Map<String, List<Double>> map = new HashMap<String, List<Double>>();
		List<Double> list = new ArrayList<Double>();
		List<String> list2 = new ArrayList<String>();
		 String series1 = "1";
		  String series2 = "2";
		  String series3 = "3";
		 list2.add("һ�·�");
		 list2.add("���·�");
		 list2.add("���·�");
		 list2.add("���·�");
		 list2.add("���·�");
		  list.add(1.0);
		  list.add(4.0);
		  list.add(3.0);
		  list.add(5.0);
		  list.add(5.0);
		  map.put(series1, list);
		  List<Double> list3 = new ArrayList<Double>();
		  list3.add(5.0);
		  list3.add(7.0);
		  list3.add(6.0);
		  list3.add(8.0);
		  list3.add(4.0);
		  map.put(series2, list3);
		  List<Double> list4 = new ArrayList<Double>();
		  list4.add(4.0);
		  list4.add(3.0);
		  list4.add(2.0);
		  list4.add(3.0);
		  list4.add(6.0);
		  map.put(series3, list4);
		  ChartDataSet data = new ChartDataSet("��״ͼ", "x��","y��",list2,map);
	      DefaultDiagramJfreeChartAbstract db = new JFreeChartWalleSWTImp();
	      db.renderChartByDataSet(data, DiagramTypeEnum.HISTOGRAM);
	}
	@Test
	public void testLineChart(){
		Map<String, List<Double>> map = new HashMap<String, List<Double>>();
		List<Double> list = new ArrayList<Double>();
		List<String> list2 = new ArrayList<String>();
		String series = "Ѫ��ֵ";  
        String[] time = new String[15];
        String[] timeValue = { "6-1��", "6-2��", "6-3��", "6-4��", "6-5��", "6-6��",
                      "6-7��", "6-8��", "6-9��", "6-10��", "6-11��", "6-12��", "6-13��",
                      "6-14��", "6-15��" };
        for (int i = 0; i < 15; i++) {
               time[i] = timeValue[i];
        }
        for (int i = 0; i < 15; i++) {
               java.util.Random r = new java.util.Random();
               list.add(i + i * 9.34 + r.nextLong() % 100);
               list2.add(time[i]);
        }
        map.put(series, list);
        List<Double> list3 = new ArrayList<Double>();
        String series1 = "Ѫֵ֬";  
        for (int i = 0; i < 15; i++) {
        	java.util.Random r = new java.util.Random();
        	list3.add(i + i * 9.34 + r.nextLong() % 100);
        }
        map.put(series1, list);
        ChartDataSet data = new ChartDataSet("����ͼ", "x��","y��",list2,map);
	    DefaultDiagramJfreeChartAbstract db = new JFreeChartWalleSWTImp();
	    db.renderChartByDataSet(data, DiagramTypeEnum.LINECHART);
	}
	
	@Test
	public void testPieChart(){
		Map<String, Double> pieDataMap = new HashMap<String, Double>();
		pieDataMap.put("C", 17.00D);
	    pieDataMap.put("Java", 17.00D);
	    pieDataMap.put("C++", 9.00D);
	    pieDataMap.put("Objective-C", 8.00D);
	    pieDataMap.put("C#", 7.00D);
	    pieDataMap.put("Other", 42.00D);
	    ChartDataSet data = new ChartDataSet("Pieͼ��", pieDataMap);
	    DefaultDiagramJfreeChartAbstract db = new JFreeChartWalleSWTImp();
	    db.renderChartByDataSet(data, DiagramTypeEnum.PIECHART);
	}
	@Test
	public void testAddChart(){
		Map<String, Double> pieDataMap = new HashMap<String, Double>();
		pieDataMap.put("C", 17.00D);
	    pieDataMap.put("Java", 17.00D);
	    pieDataMap.put("C++", 9.00D);
	    pieDataMap.put("Objective-C", 8.00D);
	    pieDataMap.put("C#", 7.00D);
	    pieDataMap.put("Other", 42.00D);
	    ChartDataSet data = new ChartDataSet("Pieͼ��", pieDataMap);
		
		IDiagramJFreeChart freeChart = new JFreeChartWalleSWTImp();
		freeChart.renderChartByDataSet(data, DiagramTypeEnum.PIECHART);
	}
}





DiagramDefinition diagramDefition = new DiagramDefinition(DiagramTypeEnum.LINECHART, "����ͼ", new IChartRefresh() {
				
				@Override
				public ChartDataSet getRefreshData() {
					 Map<String, List<Double>> map = new HashMap<String, List<Double>>();
						List<Double> list = new ArrayList<Double>();
						List<String> list2 = new ArrayList<String>();
						String series = "Ѫ��ֵ";  
				        String[] time = new String[15];
				        String[] timeValue = { "6-1��", "6-2��", "6-3��", "6-4��", "6-5��", "6-6��",
				                      "6-7��", "6-8��", "6-9��", "6-10��", "6-11��", "6-12��", "6-13��",
				                      "6-14��", "6-15��" };
				        for (int i = 0; i < 15; i++) {
				               time[i] = timeValue[i];
				        }
				        for (int i = 0; i < 15; i++) {
				               java.util.Random r = new java.util.Random();
				               list.add(i + i * 9.34 + r.nextLong() % 100);
				               list2.add(time[i]);
				        }
				        map.put(series, list);
				        List<Double> list3 = new ArrayList<Double>();
				        String series1 = "Ѫֵ֬";  
				        for (int i = 0; i < 15; i++) {
				        	java.util.Random r = new java.util.Random();
				        	list3.add(i + i * 9.34 + r.nextLong() % 100);
				        }
				        map.put(series1, list);
				        final ChartDataSet data = new ChartDataSet("x��","y��",list2,map);
					return data;
				}
			}, true, true, 3);
		    jfreeChartDiagram.renderChartByDataSet(diagramDefition);
		    
		    DiagramDefinition diagramDefition2 = new DiagramDefinition(DiagramTypeEnum.HISTOGRAM, "��״ͼ", new IChartRefresh() {
		    	int count = 0;
				@Override
				public ChartDataSet getRefreshData() {
					Map<String, List<Double>> map = new HashMap<String, List<Double>>();
					List<Double> list = new ArrayList<Double>();
					List<String> list2 = new ArrayList<String>();
					 String series1 = "1";
					  String series2 = "2";
					  String series3 = "3";
					 list2.add("һ�·�");
					 list2.add("���·�");
					 list2.add("���·�");
					 list2.add("���·�");
					 list2.add("���·�");
					  list.add(1.0+count);
					  list.add(4.0+count);
					  list.add(3.0+count);
					  list.add(5.0+count);
					  list.add(5.0+count);
					  map.put(series1, list);
					  List<Double> list3 = new ArrayList<Double>();
					  list3.add(5.0+count);
					  list3.add(7.0+count);
					  list3.add(6.0+count);
					  list3.add(8.0+count);
					  list3.add(4.0+count);
					  map.put(series2, list3);
					  List<Double> list4 = new ArrayList<Double>();
					  list4.add(4.0+count);
					  list4.add(3.0+count);
					  list4.add(2.0+count);
					  list4.add(3.0+count);
					  list4.add(6.0+count);
					  map.put(series3, list4);
					  ChartDataSet data = new ChartDataSet("x��","y��",list2,map);
					  count++;
					return data;
				}
			}, true, false, 2);
		      DefaultDiagramJfreeChartAbstract db = new JFreeChartWalleSWTImp();
		      db.renderChartByDataSet(diagramDefition2);
		    sc.setExpandHorizontal(true);  
		    sc.setExpandVertical(true);
		    sc.setContent(rootComposite);
		    sc.setMinWidth(500);
		    sc.setMinHeight(500);
		    sc.addControlListener(new ControlListener() {
				
				@Override
				public void controlResized(ControlEvent arg0) {
					resizeParentPanel();
				}
				
				@Override
				public void controlMoved(ControlEvent arg0) {
					
				}
			});
*/