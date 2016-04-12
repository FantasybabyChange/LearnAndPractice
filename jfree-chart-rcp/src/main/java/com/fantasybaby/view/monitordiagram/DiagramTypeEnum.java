package com.fantasybaby.view.monitordiagram;
/**
 * show all the diagram's type
 * @author FantasyBaby
 *
 */
public enum DiagramTypeEnum {
	HISTOGRAM("柱状图","histogramChart"),
	LINECHART("折线图","lineChart"),
	PIECHART("饼图","pieChart");
	
	private String name;
	private String value;
	private DiagramTypeEnum(String name,String value) {
		this.name = name;
		this.value = value;
	}
	public String getName(){
		return this.name;
	}
	public String getValue(){
		return this.value;
	}

}
