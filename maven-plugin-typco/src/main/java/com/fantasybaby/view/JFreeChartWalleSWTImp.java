package com.fantasybaby.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;


public class JFreeChartWalleSWTImp extends DefaultDiagramJfreeChartAbstract{
	private  static Composite rootComposite;
	private static MultipleSelectionCombo multipleCombo;
	private static Logger _log = LogManager.getLogger(JFreeChartWalleSWTImp.class);
	private static int countDiagramNum = 0;
	private static final String DIAGRAM_KEY = "_chart";
	@Override
	public synchronized void renderChartInUI() throws Exception {
		if(rootComposite != null){
			if(StringUtils.isEmpty(chartKey)){
				getChartKey();
			}
			new JFreeChartComposite(rootComposite, SWT.None,this.chart,this,multipleCombo,this.isDisplay);
		}else{
			throw new Exception("please init parentComposite");
		}
		
	}
	private  void getChartKey(){
		if(StringUtils.isEmpty(chartKey)){
			countDiagramNum = countDiagramNum + 1 ;
			chartKey = countDiagramNum + DIAGRAM_KEY;
		}
	}
	public void initRootPanel(Composite parent,MultipleSelectionCombo _multipaleCombo){
		if (rootComposite == null) {
			rootComposite = parent;
		}
		if (multipleCombo == null) {
			multipleCombo = _multipaleCombo;
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void prepareHistogramChart() {
		chart = ChartFactory.createBarChart(this.title,// 标题
			   this.xAxisName,// x轴
			    this.yAxisName,// y轴
			    getHistogramChartData(),// 数据
			    PlotOrientation.VERTICAL,// 定位，VERTICAL：垂直
			    true,
			    true,
			    false);
		  // 周围的背景色
		  chart.setBackgroundPaint(Color.white);
		  /*chart.getLegend().setVisible(true);
		  chart.getLegend().setPosition(RectangleEdge.RIGHT);*/
		  // 设置字体，否则会显示乱码
		  Font font = new Font("宋体", 10, 20);
		  TextTitle title = chart.getTitle();
		  // 设置标题字体
		  title.setFont(font);
		  // 得到一个参考
		  CategoryPlot plot = (CategoryPlot) chart.getPlot();
		  // 生成图片的背景色
		  plot.setBackgroundPaint(Color.white);
		  // 行线的颜色
		  plot.setRangeGridlinePaint(Color.BLACK);
		  // 刻度字体
		  plot.getDomainAxis().setTickLabelFont(font);
		  // X轴名称字体
		  plot.getDomainAxis().setLabelFont(font);
		  // LayeredBarRenderer lbr = new LayeredBarRenderer();//(BarRenderer)类：
		  // //void setSeriesBarWidth(int series,double width)
		  // 设定每个分类的宽度（注意设置不要使某分类被覆盖）
		  // lbr.setSeriesBarWidth(1,0.1);
		  // 设置显示整数
		  NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		  rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		  // 设置上部空白
		  rangeAxis.setUpperMargin(0.15);
		  // 设置y轴名称字体
		  rangeAxis.setLabelFont(font);

		  CategoryItemRenderer renderer = plot.getRenderer();
		  renderer
		    .setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		  // renderer.setDrawOutlines(true);//是否折线数据点根据不同数据使用不同的形状
		  // renderer.setSeriesShapesVisible(0, true);
		  renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);

		  CategoryAxis domainAxis = plot.getDomainAxis();
		  domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);// 倾斜45度

		  BarRenderer renderer1 = new BarRenderer();// 设置柱子的相关属性
		  // 设置柱子宽度
		  // renderer1.setMaximumBarWidth(0.9);
		  // renderer1.setMaximumBarWidth(0.10000000000000001D); //宽度
		  // 设置柱子高度
		  renderer1.setMinimumBarLength(0.5);
		  // 设置柱子边框颜色
		  // renderer1.setBaseOutlinePaint(Color.BLACK);
		  // 设置柱子边框可见
		  // renderer1.setDrawBarOutline(true);
		  // 设置每个地区所包含的平行柱的之间距离，数值越大则间隔越大，图片大小一定的情况下会影响柱子的宽度，可以为负数
		  renderer1.setItemMargin(0.1);
		  // 是否显示阴影
		  renderer1.setShadowVisible(false);
		  // 阴影颜色
		  // renderer1.setShadowPaint(Color.white);
		  renderer1.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		  renderer1.setBaseItemLabelsVisible(true);
		  plot.setRenderer(renderer1);
		  plot.setBackgroundAlpha((float) 0.5); // 数据区的背景透明度（0.0～1.0）
		  plot.setNoDataMessage("没有数据");
		  // 设置柱的透明度
		  // plot.setForegroundAlpha(1.0f);
		  // 设置图形的宽度
//		  CategoryAxis caxis = plot.getDomainAxis();
		  // 设置图形右边的空白
		  // caxis.setUpperMargin(0.2);
		  // 设置左边的空白
		  // caxis.setLowerMargin(0.2);
	}
	private DefaultCategoryDataset getHistogramChartData(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<String> rowKeyList = this.rowKeys;
		Map<String, List<Double>> columnMap = this.columnDataMap;
		int count = 0;
		if(columnMap != null){
			Set<String> columnKeys = columnMap.keySet();
			Iterator<String> iterator = columnKeys.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				List<Double> datas = columnMap.get(key);
				for (Double str : datas) {
					if(count < rowKeyList.size()){
						dataset.addValue(str, key, rowKeyList.get(count));
						count = count + 1;
					}
				}
				count = 0;
			}
		}
		return dataset;
	}
	private DefaultCategoryDataset getLineChartData(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<String> rowKeyList = this.rowKeys;
		Map<String, List<Double>> columnMap = this.columnDataMap;
		int count = 0;
		if(columnMap != null){
			Set<String> columnKeys = columnMap.keySet();
			Iterator<String> iterator = columnKeys.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				List<Double> datas = columnMap.get(key);
				for (Double str : datas) {
					if(count < rowKeyList.size()){
						dataset.addValue(str, key, rowKeyList.get(count));
						count = count + 1;
					}
				}
				count = 0;
			}
		}
			
		return dataset;
	}
	private DefaultPieDataset getPieChartData(){
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		Map<String, Double> pieDataMap = this.pieDataMap;
		Set<String> keys = pieDataMap.keySet();
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			defaultpiedataset.setValue(key, pieDataMap.get(key));
		}
		return defaultpiedataset;
	}
	@Override
	protected void prepareLineChart() {
		  chart = ChartFactory.createLineChart(this.title,
	            this.xAxisName, 
	            this.yAxisName, 
	            getLineChartData(), 
	            PlotOrientation.VERTICAL, 
	            true, 
	            false, 
	            false 
	            );
		CategoryPlot plot = chart.getCategoryPlot();
		// 图像属性部分
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinesVisible(true);  //设置背景网格线是否可见
		plot.setDomainGridlinePaint(Color.BLACK); //设置背景网格线颜色
		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setNoDataMessage("没有数据");//没有数据时显示的文字说明。 
		// 数据轴属性部分
		CategoryAxis domainAxis = plot.getDomainAxis();   
        domainAxis.setLabelFont(new Font("宋书", Font.PLAIN, 15)); // 设置横轴字体
        domainAxis.setTickLabelFont(new Font("宋书", Font.PLAIN, 15));// 设置坐标轴标尺值字体
        domainAxis.setLowerMargin(0.01);// 左边距 边框距离
        domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
        domainAxis.setMaximumCategoryLabelLines(10);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);// 横轴 lable 的位置 横轴上的 Lable 45度倾斜 DOWN_45
		// 数据渲染部分 主要是对折线做操作
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		renderer.setSeriesPaint(0, Color.black);    //设置折线的颜色
		renderer.setBaseShapesFilled(true);
		renderer.setBaseItemLabelsVisible(true);     
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
		renderer.setBaseItemLabelFont(new Font("Dialog", 1, 14));  //设置提示折点数据形状
		plot.setRenderer(renderer);
		/*//区域渲染部分
		double lowpress = 4.5; 
		double uperpress = 8;   
		IntervalMarker inter = new IntervalMarker(lowpress, uperpress);  
		inter.setLabelOffsetType(LengthAdjustmentType.EXPAND); //  范围调整——扩张
		inter.setPaint(Color.LIGHT_GRAY);  
		inter.setLabelFont(new Font("SansSerif", 41, 14));  
		inter.setLabelPaint(Color.RED);  
		inter.setLabel("正常血糖值范围");    
		plot.addRangeMarker(inter,Layer.BACKGROUND); */ 
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void preparePieChart() {
		       chart = ChartFactory.createPieChart(this.title, getPieChartData(), true,
			        true, true);
			    // 设置图片的背景色
			    chart.setBackgroundPaint(java.awt.Color.white);
			    // 设置图片标题的字体和大小
			    TextTitle title = new TextTitle("编程语言排名");
			    chart.setTitle(title);

			    PiePlot pie = (PiePlot) chart.getPlot();
			    pie.setInsets(new RectangleInsets(5, 5, 5, 5));
			    // 指定 section 轮廓线的颜色
			    pie.setOutlinePaint(java.awt.Color.BLACK);
			    // 指定 section 轮廓线的厚度
			    pie.setOutlineStroke(new BasicStroke(1));
			    // 设置第一个 section 的开始位置，默认是12点钟方向,90度，逆时针
			    pie.setStartAngle(90);
			    // 指定 section 的色彩
			    pie.setSectionPaint(1, new Color(0x99, 0x99, 0xFF));
			    pie.setLabelFont(new Font("黑体", Font.BOLD, 12));
			    // 指定显示的饼图上圆形还椭圆形。true为圆形，false为椭圆形。默认为false
			    pie.setCircular(true);
			    // 指定图片的透明度
			    pie.setForegroundAlpha(0.5f);
			    pie.setLabelGap(0.01);// 间距
			    pie.setNoDataMessage("No data available");
	}
	@Override
	public void refreshChart(ChartDataSet datas) {
		try {
			if(datas != null){
				initData(datas);
				Method method = this.getClass().getDeclaredMethod(getMethodName("get", this.type.getValue(),"Data"));
				if (method != null) {
					 if (type.equals(DiagramTypeEnum.PIECHART)) {
						 PiePlot plot = (PiePlot)chart.getPlot();
						 DefaultPieDataset dataSet = (DefaultPieDataset) method.invoke(this);
						 plot.setDataset(dataSet);
					}else{
						CategoryPlot plot = (CategoryPlot)chart.getPlot();
						 DefaultCategoryDataset dataSet = (DefaultCategoryDataset) method.invoke(this);
						 plot.setDataset(dataSet);
					}
					
				}
			
			}
			
		} catch (Exception e) {
			_log.error(e);
		}
		
	}
	@Override
	public void refreshChart() throws Exception {
		if(this.refresh != null){
			refreshChart(this.refresh.getRefreshData());
		}else{
			throw new Exception("please init a refresh method to call"); 
		}
	}
	
}
