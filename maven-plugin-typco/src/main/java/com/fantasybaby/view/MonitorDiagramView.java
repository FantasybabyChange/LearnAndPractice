package com.fantasybaby.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

import com.fantasybaby.constant.HistogramData;
import com.fantasybaby.dialog.RadioBoxDialog;

public class MonitorDiagramView extends ViewPart {

	public static final String ID = "fantasybaby.MonitorDiagramView"; //$NON-NLS-1$
	private  ScrolledComposite sc ;
	private Composite rootComposite ;
	private MultipleSelectionCombo combo;
	private static Logger _log = LogManager.getLogger(MonitorDiagramView.class);
//	private IPickingJobDas pickingJobDas;
//	private IJobDas jobDas;
	public MonitorDiagramView() {
	}
	
	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		try {
			initializeToolBar(parent);
//			parent.setBackground(new Color(parent.getDisplay(), 0, 255, 0));
			parent.setLayout(new FormLayout());
			combo = new MultipleSelectionCombo(parent,SWT.NONE,this);
			FormData formData = new FormData();
			formData.top = new FormAttachment(0, 5);
			formData.left = new FormAttachment(0, 5);
			formData.bottom = new FormAttachment(0, 40);
			formData.right = new FormAttachment(30, -5);
			formData.width = 300;
			combo.setLayoutData(formData);
			Composite scParent = new Composite(parent, SWT.None);
			FormData formData1 = new FormData();
		    formData1.top = new FormAttachment(combo, 5);
		    formData1.left = new FormAttachment(0, 5);
		    formData1.bottom = new FormAttachment(100, -5);
		    formData1.right = new FormAttachment(100, -5);
		    scParent.setLayoutData(formData1);
			scParent.setLayout(new FillLayout());
		    sc = new ScrolledComposite(scParent, SWT.BORDER  
		            | SWT.V_SCROLL | SWT.H_SCROLL);  
			rootComposite = new Composite(sc, SWT.NONE);
//			rootComposite.setBackground(new Color(rootComposite.getDisplay(), 255, 0, 0));
			RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
			rowLayout.wrap = true;
			rowLayout.justify = false;
			rowLayout.fill = false;
			rowLayout.spacing = 10;
			rowLayout.marginBottom = 0;
			rowLayout.marginHeight = 0;
			rowLayout.marginLeft = 0;
			rowLayout.marginRight = 0;
			rowLayout.marginTop = 0;
			rowLayout.type = SWT.LEFT;
		    rootComposite.setLayout(rowLayout);
		    DefaultDiagramJfreeChartAbstract jfreeChartDiagram = new JFreeChartWalleSWTImp();
		    jfreeChartDiagram.initRootPanel(rootComposite,combo);
			new Thread(new Runnable() {
				@Override
				public void run() {
					 DiagramDefinition diagramDefition = new DiagramDefinition(DiagramTypeEnum.HISTOGRAM, "出入库量分时图",new PickingJobData(), false, false, 2);
					    DefaultDiagramJfreeChartAbstract db = new JFreeChartWalleSWTImp();
				        db.renderChartByDataSet(diagramDefition);
				}
			}).run();
			RadioBoxDialog radio = new RadioBoxDialog(parent.getShell(), "测试Title");
			radio.open();
//		    jobDas = DasFactoryImpl.getInstance().createDas(IJobDas.class);
			/*   new Thread(new Runnable() {
						@Override
						public void run() {
							DiagramDefinition diagramDefition1 = new DiagramDefinition(DiagramTypeEnum.HISTOGRAM, "车辆任务分配图",new JobData(), false, false, 2);
							DefaultDiagramJfreeChartAbstract db1 = new JFreeChartWalleSWTImp();
							db1.renderChartByDataSet(diagramDefition1);
				        }
					}).run();
	        
	        
		    new Thread(new Runnable() {
				@Override
				public void run() {
					DiagramDefinition diagramDefition2 = new DiagramDefinition(DiagramTypeEnum.HISTOGRAM, "工作站完成总量图",new StationData(), false, false, 2);
			        DefaultDiagramJfreeChartAbstract db2 = new JFreeChartWalleSWTImp();
			        db2.renderChartByDataSet(diagramDefition2);
		        }
			}).run();*/
	          
		    sc.setExpandHorizontal(true);  
		    sc.setExpandVertical(true);
		    sc.setContent(rootComposite);
		    sc.addControlListener(new ControlListener() {
				@Override
				public void controlResized(ControlEvent arg0) {
					resizeParentPanel();
				}
				
				@Override
				public void controlMoved(ControlEvent arg0) {
					resizeParentPanel();
				}
			});
		    parent.addControlListener(new ControlListener() {
				
				@Override
				public void controlResized(ControlEvent arg0) {
					resizeParentPanel();
					
				}
				
				@Override
				public void controlMoved(ControlEvent arg0) {
					resizeParentPanel();
				}
			});
		    resizeParentPanel();
			parent.layout();
		} catch (Exception e) {
			_log.error(e);
		}
		
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar(Composite parent) {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBarManager = actionBars.getToolBarManager();
		toolBarManager.add(new AddChartAction());
		
	}
	public  void resizeParentPanel(){
		int y = sc.getSize().y;
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
					sc.setMinWidth((length+1) * ChartDiagramConstant.CHART_DEFAULT_WIDTH);
					sc.setMinHeight( ChartDiagramConstant.CHART_DEFAULT_HEIGHT + 20);
//					rootComposite.setSize((length+1) * ChartDiagramConstant.CHART_DEFAULT_WIDTH, ChartDiagramConstant.CHART_DEFAULT_HEIGHT);
				}
			}else{
				if(count > 0){
					int length = count;
					int x = rootComposite.getSize().x;
					int line = x / ChartDiagramConstant.CHART_DEFAULT_WIDTH;
					sc.setMinWidth(ChartDiagramConstant.CHART_DEFAULT_WIDTH);
					sc.setMinHeight((length/line+1)*(ChartDiagramConstant.CHART_DEFAULT_HEIGHT+10));
//					rootComposite.setSize(ChartDiagramConstant.CHART_DEFAULT_WIDTH, (length/line+1) *  ChartDiagramConstant.CHART_DEFAULT_HEIGHT);
			}
		}
	}else{
		sc.setMinWidth( ChartDiagramConstant.CHART_DEFAULT_WIDTH);
		sc.setMinHeight(2*  ChartDiagramConstant.CHART_DEFAULT_HEIGHT);
//		rootComposite.setSize((ChartDiagramConstant.CHART_DEFAULT_WIDTH+10)*2, ChartDiagramConstant.CHART_DEFAULT_HEIGHT);
	}
	    sc.layout();
	    rootComposite.layout();
}
class PickingJobData implements IChartRefresh{

	@Override
	public ChartDataSet getRefreshData() {/*
		pickingJobDas = DasFactoryImpl.getInstance().createDas(IPickingJobDas.class);
		List<String> scales = new ArrayList<String>();
		Map<String, List<Double>> columnDataMap = new LinkedHashMap<String, List<Double>>();
		Map<String, List<HistogramData>>  histogramMap = pickingJobDas.selectHistogramDataByHour(getStartAndEndDateStr(0),getStartAndEndDateStr(1));
		ChartDataSet chartData = new ChartDataSet("时间/h","件数/件",scales,columnDataMap);
		if(histogramMap != null){
			List<HistogramData> inList = histogramMap.get(ChartDiagramConstant.STOCK_IN);
			List<HistogramData> outList = histogramMap.get(ChartDiagramConstant.STOCK_OUT);
			List<Double> inDatas = new ArrayList<Double>();
			List<Double> ouDatas = new ArrayList<Double>();
			int countIn = 0;
			int countou = 0;
			for (int i = 0; i <= 23; i++) {
				if(inList != null){
					if(countIn >= inList.size()){
						inDatas.add(0.0);
					}else{
						HistogramData histogramDataIn = inList.get(countIn);
						if(histogramDataIn != null){
							String inkey = histogramDataIn.getKey();
							int keyInt = Integer.parseInt(inkey);
							String inData = histogramDataIn.getData();
							if(keyInt == i){
								inDatas.add(Double.parseDouble(inData));
								countIn++;
							}else{
								inDatas.add(0.0);
							}
						}
					}
				}
				if(outList != null){
					if(outList.size() <= countou){
						ouDatas.add(0.0);
					}else{
						HistogramData histogramDataOut = outList.get(countou);
						if(histogramDataOut != null){
							String outkey = histogramDataOut.getKey();
							int keyInt = Integer.parseInt(outkey);
							String outData = histogramDataOut.getData();
							if(keyInt == i){
								ouDatas.add(Double.parseDouble(outData));
								countou++;
							}else{
								ouDatas.add(0.0);
							}
							}
					}
				}
				scales.add(i+"");
			}
			
			if (inDatas.size() > 0) {
				columnDataMap.put(ChartDiagramConstant.STOCK_IN_NAME, inDatas);
			}
			if(ouDatas.size() > 0){
				columnDataMap.put(ChartDiagramConstant.STOCK_OUT_NAME, ouDatas);
			}
		}
		
		return chartData;
	
	*/
		Map<String, List<Double>> map = new HashMap<String, List<Double>>();
		List<Double> list = new ArrayList<Double>();
		List<String> list2 = new ArrayList<String>();
		 String series1 = "1";
		  String series2 = "2";
		  String series3 = "3";
		 list2.add("一月份");
		 list2.add("二月份");
		 list2.add("三月份");
		 list2.add("四月份");
		 list2.add("五月份");
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
		  ChartDataSet data = new ChartDataSet("x轴","y轴",list2,map);
		  return data;
	}
	
}
/*class JobData implements IChartRefresh{

	@Override
	public ChartDataSet getRefreshData() {
//		IJobDas createPickingJobDas = DasFactoryImpl.getInstance().createJobDas();
		List<HistogramData> lists = createPickingJobDas.selectHistogramDataByHour(getStartAndEndDateStr(0),getStartAndEndDateStr(1));
		List<String> scales = new ArrayList<String>();
		scales.add("  ");
		Map<String, List<Double>> columnDataMap = new LinkedHashMap<String, List<Double>>();
		for (HistogramData string : lists) {
			List<Double> datas = new ArrayList<Double>();
			datas.add(Double.parseDouble(string.getData()));
			columnDataMap.put(string.getKey(), datas);
		}
		
		ChartDataSet chartData = new ChartDataSet("车辆ID","当天任务数（拣货+补货）",scales,columnDataMap);
		return null;
	}
	
}

class StationData implements IChartRefresh{

	@Override
	public ChartDataSet getRefreshData() {
		List<String> scales = new ArrayList<String>();
		Map<String, List<Double>> columnDataMap = new LinkedHashMap<String, List<Double>>();
		Map<String, List<HistogramData>>  histogramMap = jobDas.selectStationHistogramDataByHour(getStartAndEndDateStr(0),getStartAndEndDateStr(1));
		ChartDataSet chartData = new ChartDataSet("工作站ID","今天为止的完成量/件",scales,columnDataMap);
		if(histogramMap != null){
			
			List<HistogramData> inList = histogramMap.get(ChartDiagramConstant.STOCK_IN);
			List<HistogramData> outList = histogramMap.get(ChartDiagramConstant.STOCK_OUT);
			Map<String, Double> inMap = new HashMap<String, Double>();
			Map<String, Double> ouMap = new HashMap<String, Double>();
			List<Double> inDatas = new ArrayList<Double>();
			List<Double> ouDatas = new ArrayList<Double>();
			if (inList != null) {
				for (HistogramData histogramDataIn : inList) {
					String key = histogramDataIn.getKey();
					inMap.put(key, Double.parseDouble(histogramDataIn.getData()));
					scales.add(key);
				}
			}
			if(outList != null){
				for (HistogramData histogramDataOut :outList) {
					String key = histogramDataOut.getKey();
					if(!scales.contains(key)){
						scales.add(key);
					}
					ouMap.put(key, Double.parseDouble(histogramDataOut.getData()));
				}
			}
		
			for (String string : scales) {
				if (inMap.containsKey(string)) {
					inDatas.add(inMap.get(string));
				}else{
					inDatas.add(0.0);
				}
				if(ouMap.containsKey(string)){
					ouDatas.add(ouMap.get(string));
					}else{
						ouDatas.add(0.0);
				}
			}
			if (inDatas.size() > 0) {
				columnDataMap.put("补货", inDatas);
			}
			if(ouDatas.size() > 0){
				columnDataMap.put("拣货", ouDatas);
			}
		}
		return chartData;
	
		return null;
	}
	
}*/
//test for add button
 class AddChartAction extends Action{
	 public AddChartAction(){
		 this.setText("Add");
	 }
	 @Override
	public void run() {
		 Map<String, List<Double>> map = new HashMap<String, List<Double>>();
			List<Double> list = new ArrayList<Double>();
			List<String> list2 = new ArrayList<String>();
			String series = "血糖值";  
	        String[] time = new String[15];
	        String[] timeValue = { "6-1日", "6-2日", "6-3日", "6-4日", "6-5日", "6-6日",
	                      "6-7日", "6-8日", "6-9日", "6-10日", "6-11日", "6-12日", "6-13日",
	                      "6-14日", "6-15日" };
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
	        String series1 = "血脂值";  
	        for (int i = 0; i < 15; i++) {
	        	java.util.Random r = new java.util.Random();
	        	list3.add(i + i * 9.34 + r.nextLong() % 100);
	        }
	        map.put(series1, list);
	        final ChartDataSet data = new ChartDataSet("x轴","y轴",list2,map);
		    DefaultDiagramJfreeChartAbstract db = new JFreeChartWalleSWTImp();
		    DiagramDefinition diagramDefition = new DiagramDefinition(DiagramTypeEnum.LINECHART, "折线图", new IChartRefresh() {
				
				@Override
				public ChartDataSet getRefreshData() {
					return data;
				}
			}, false, false, 2);
		    db.renderChartByDataSet(diagramDefition);
//		    db.renderChartByDataSet(data, DiagramTypeEnum.LINECHART);
	}
	 
 }
	@Override
	public void setFocus() {
		
	}
//	int count = 0;
	private String getStartAndEndDateStr(int type){
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 3, 11);
//		count++;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String format2 = format.format(new Date());
		if(type == 0){
			return format2 +" 00:00:00";
		}else{
			return format2 + " 23:59:59";
		}
	}
 

	}
