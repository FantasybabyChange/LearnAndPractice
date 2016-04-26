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
	
	@Override
	protected void prepareHistogramChart() {
		chart = ChartFactory.createBarChart(this.title,// ����
			   this.xAxisName,// x��
			    this.yAxisName,// y��
			    getHistogramChartData(),// ���
			    PlotOrientation.VERTICAL,// ��λ��VERTICAL����ֱ
			    true,
			    true,
			    false);
		  // ��Χ�ı���ɫ
		  chart.setBackgroundPaint(Color.white);
		  /*chart.getLegend().setVisible(true);
		  chart.getLegend().setPosition(RectangleEdge.RIGHT);*/
		  // �������壬�������ʾ����
		  Font font = new Font("����", 10, 20);
		  TextTitle title = chart.getTitle();
		  // ���ñ�������
		  title.setFont(font);
		  // �õ�һ���ο�
		  CategoryPlot plot = (CategoryPlot) chart.getPlot();
		  // ���ͼƬ�ı���ɫ
		  plot.setBackgroundPaint(Color.white);
		  // ���ߵ���ɫ
		  plot.setRangeGridlinePaint(Color.BLACK);
		  // �̶�����
		  plot.getDomainAxis().setTickLabelFont(font);
		  // X���������
		  plot.getDomainAxis().setLabelFont(font);
		  // LayeredBarRenderer lbr = new LayeredBarRenderer();//(BarRenderer)�ࣺ
		  // //void setSeriesBarWidth(int series,double width)
		  // �趨ÿ������Ŀ�ȣ�ע�����ò�Ҫʹĳ���౻���ǣ�
		  // lbr.setSeriesBarWidth(1,0.1);
		  // ������ʾ����
		  NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		  rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		  // �����ϲ��հ�
		  rangeAxis.setUpperMargin(0.15);
		  // ����y���������
		  rangeAxis.setLabelFont(font);

		  CategoryItemRenderer renderer = plot.getRenderer();
		  renderer
		    .setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		  // renderer.setDrawOutlines(true);//�Ƿ�������ݵ��ݲ�ͬ���ʹ�ò�ͬ����״
		  // renderer.setSeriesShapesVisible(0, true);
		  renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);

		  CategoryAxis domainAxis = plot.getDomainAxis();
		  domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);// ��б45��

		  BarRenderer renderer1 = new BarRenderer();// �������ӵ��������
		  // �������ӿ��
		  // renderer1.setMaximumBarWidth(0.9);
		  // renderer1.setMaximumBarWidth(0.10000000000000001D); //���
		  // �������Ӹ߶�
		  renderer1.setMinimumBarLength(0.5);
		  // �������ӱ߿���ɫ
		  // renderer1.setBaseOutlinePaint(Color.BLACK);
		  // �������ӱ߿�ɼ�
		  // renderer1.setDrawBarOutline(true);
		  // ����ÿ����������ƽ�����֮����룬��ֵԽ������Խ��ͼƬ��Сһ��������»�Ӱ�����ӵĿ�ȣ�����Ϊ����
		  renderer1.setItemMargin(0.1);
		  // �Ƿ���ʾ��Ӱ
		  renderer1.setShadowVisible(false);
		  // ��Ӱ��ɫ
		  // renderer1.setShadowPaint(Color.white);
		  renderer1.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		  renderer1.setBaseItemLabelsVisible(true);
		  plot.setRenderer(renderer1);
		  plot.setBackgroundAlpha((float) 0.5); // �����ı���͸���ȣ�0.0��1.0��
		  plot.setNoDataMessage("û�����");
		  // �������͸����
		  // plot.setForegroundAlpha(1.0f);
		  // ����ͼ�εĿ��
		  CategoryAxis caxis = plot.getDomainAxis();
		  // ����ͼ���ұߵĿհ�
		  // caxis.setUpperMargin(0.2);
		  // ������ߵĿհ�
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
		// ͼ�����Բ���
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinesVisible(true);  //���ñ���������Ƿ�ɼ�
		plot.setDomainGridlinePaint(Color.BLACK); //���ñ����������ɫ
		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setNoDataMessage("û�����");//û�����ʱ��ʾ������˵���� 
		// ��������Բ���
		CategoryAxis domainAxis = plot.getDomainAxis();   
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 15)); // ���ú�������
        domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 15));// �����������ֵ����
        domainAxis.setLowerMargin(0.01);// ��߾� �߿����
        domainAxis.setUpperMargin(0.06);// �ұ߾� �߿����,��ֹ���ߵ�һ����ݿ���������ᡣ
        domainAxis.setMaximumCategoryLabelLines(10);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);// ���� lable ��λ�� �����ϵ� Lable 45����б DOWN_45
		// �����Ⱦ���� ��Ҫ�Ƕ�����������
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		renderer.setSeriesPaint(0, Color.black);    //�������ߵ���ɫ
		renderer.setBaseShapesFilled(true);
		renderer.setBaseItemLabelsVisible(true);     
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
		renderer.setBaseItemLabelFont(new Font("Dialog", 1, 14));  //������ʾ�۵������״
		plot.setRenderer(renderer);
		/*//������Ⱦ����
		double lowpress = 4.5; 
		double uperpress = 8;   
		IntervalMarker inter = new IntervalMarker(lowpress, uperpress);  
		inter.setLabelOffsetType(LengthAdjustmentType.EXPAND); //  ��Χ��������
		inter.setPaint(Color.LIGHT_GRAY);  
		inter.setLabelFont(new Font("SansSerif", 41, 14));  
		inter.setLabelPaint(Color.RED);  
		inter.setLabel("��Ѫ��ֵ��Χ");    
		plot.addRangeMarker(inter,Layer.BACKGROUND); */ 
	}

	@Override
	protected void preparePieChart() {
		       chart = ChartFactory.createPieChart(this.title, getPieChartData(), true,
			        true, true);
			    // ����ͼƬ�ı���ɫ
			    chart.setBackgroundPaint(java.awt.Color.white);
			    // ����ͼƬ���������ʹ�С
			    TextTitle title = new TextTitle("�����������");
			    chart.setTitle(title);

			    PiePlot pie = (PiePlot) chart.getPlot();
			    pie.setInsets(new RectangleInsets(5, 5, 5, 5));
			    // ָ�� section �����ߵ���ɫ
			    pie.setOutlinePaint(java.awt.Color.BLACK);
			    // ָ�� section �����ߵĺ��
			    pie.setOutlineStroke(new BasicStroke(1));
			    // ���õ�һ�� section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���,90�ȣ���ʱ��
			    pie.setStartAngle(90);
			    // ָ�� section ��ɫ��
			    pie.setSectionPaint(1, new Color(0x99, 0x99, 0xFF));
			    pie.setLabelFont(new Font("����", Font.BOLD, 12));
			    // ָ����ʾ�ı�ͼ��Բ�λ���Բ�Ρ�trueΪԲ�Σ�falseΪ��Բ�Ρ�Ĭ��Ϊfalse
			    pie.setCircular(true);
			    // ָ��ͼƬ��͸����
			    pie.setForegroundAlpha(0.5f);
			    pie.setLabelGap(0.01);// ���
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
