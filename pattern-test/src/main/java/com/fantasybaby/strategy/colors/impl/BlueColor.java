package com.fantasybaby.strategy.colors.impl;

import com.fantasybaby.strategy.colors.IColors;
import com.fantasybaby.strategy.colors.annotation.MessageType;
@MessageType(value="blue")
public class BlueColor implements IColors{
	private String colorName="blue";
	@Override
	public String printColor() {
		return this.colorName;
	}

}
