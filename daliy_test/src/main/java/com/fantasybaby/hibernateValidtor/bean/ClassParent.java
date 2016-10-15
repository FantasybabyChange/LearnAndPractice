package com.fantasybaby.hibernateValidtor.bean;

import java.io.Serializable;

public class ClassParent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8071987616729852324L;
	private String parentName;
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
