package com.fantasybaby.validtor.hibernateValidtor.bean;

import lombok.Data;

import java.util.List;
@Data
public class UserChildList<S extends UserLogin> {
	private String childName;
	private List<S> list;

}
