package com.fantasybaby.entity;

import java.io.Serializable;

public class SoryEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1373869009287460257L;
	
	private int data;
	public SoryEntity() {
		// TODO Auto-generated constructor stub
	}
	public SoryEntity(int _data) {
		data = _data;
	}
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object obj) {
		SoryEntity tmp = (SoryEntity)obj;
		if (tmp.data == this.data) {
			return true;
		}else{
			return false;
		}
	}
	@Override
	public String toString() {
		return "value:" + data;
	}
}
