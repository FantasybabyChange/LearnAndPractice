package com.fantasybaby.view;
/**
 * show all the diagram's type
 * @author FantasyBaby
 *
 */
public enum DiagramTypeEnum {
	HISTOGRAM("��״ͼ","histogramChart"),
	LINECHART("����ͼ","lineChart"),
	PIECHART("��ͼ","pieChart");
	
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
