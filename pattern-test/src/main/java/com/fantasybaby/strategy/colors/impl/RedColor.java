package com.fantasybaby.strategy.colors.impl;

import com.fantasybaby.strategy.colors.IColors;
import com.fantasybaby.strategy.colors.annotation.MessageType;
@MessageType(value="red")
public class RedColor implements IColors {
	private String colorName="red";
	@Override
	public String printColor() {
		return colorName;
	}

}
