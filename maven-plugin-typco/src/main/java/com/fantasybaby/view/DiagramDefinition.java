package com.fantasybaby.view;

public class DiagramDefinition {
	private DiagramTypeEnum diagramType;
	private String diagramTitle;
	private IChartRefresh refresh;
	private boolean isDispaly;
	private boolean isAutoRefresh;
	private long refreshRate;
	
	public DiagramDefinition(DiagramTypeEnum diagramType, String diagramTitle,
			IChartRefresh refresh, boolean isDispaly, boolean isAutoRefresh,
			long refreshRate) {
		super();
		this.diagramType = diagramType;
		this.diagramTitle = diagramTitle;
		this.refresh = refresh;
		this.isDispaly = isDispaly;
		this.isAutoRefresh = isAutoRefresh;
		this.refreshRate = refreshRate;
	}
	public DiagramDefinition(){
		
	}
	public DiagramTypeEnum getDiagramType() {
		return diagramType;
	}
	public void setDiagramType(DiagramTypeEnum diagramType) {
		this.diagramType = diagramType;
	}
	public String getDiagramTitle() {
		return diagramTitle;
	}
	public void setDiagramTitle(String diagramTitle) {
		this.diagramTitle = diagramTitle;
	}
	public IChartRefresh getRefresh() {
		return refresh;
	}
	public void setRefresh(IChartRefresh refresh) {
		this.refresh = refresh;
	}
	public boolean getIsDispaly() {
		return isDispaly;
	}
	public void setDispaly(boolean isDispaly) {
		this.isDispaly = isDispaly;
	}
	public boolean getIsAutoRefresh() {
		return isAutoRefresh;
	}
	public void setAutoRefresh(boolean isAutoRefresh) {
		this.isAutoRefresh = isAutoRefresh;
	}
	public long getRefreshRate() {
		return refreshRate;
	}
	public void setRefreshRate(long refreshRate) {
		this.refreshRate = refreshRate;
	}
	
}
