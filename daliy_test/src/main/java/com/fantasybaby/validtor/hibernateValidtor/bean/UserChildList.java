package com.fantasybaby.validtor.hibernateValidtor.bean;

import java.util.List;

public class UserChildList<S extends UserLogin> {
	private List<S> list;
	public List<S> getList() {
		return list;
	}

	public void setList(List<S> list) {
		this.list = list;
	}
	

}
