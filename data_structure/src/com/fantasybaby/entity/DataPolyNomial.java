package com.fantasybaby.entity;

import java.io.Serializable;

public class DataPolyNomial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3619877283257964935L;
	private int coefficient;
	private int exponent;
	public int getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	public int getExponent() {
		return exponent;
	}
	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
}
