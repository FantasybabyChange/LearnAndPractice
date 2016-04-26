package com.fantasybaby.constant;

import java.io.Serializable;

public class HistogramData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7986021945791905769L;
	public HistogramData() {
	}
	private String key;
	private String data;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

}
