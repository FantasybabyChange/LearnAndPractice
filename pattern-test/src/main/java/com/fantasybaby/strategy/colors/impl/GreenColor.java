package com.fantasybaby.strategy.colors.impl;

import com.fantasybaby.strategy.colors.IColors;
import com.fantasybaby.strategy.colors.annotation.MessageType;

@MessageType(value="green")
public class GreenColor implements IColors{

	@Override
	public String printColor() {
		
		return "green";
	}
	
}
