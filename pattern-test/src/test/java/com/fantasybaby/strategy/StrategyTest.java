package com.fantasybaby.strategy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantasybaby.strategy.colors.MyColor;
import com.fantasybaby.strategy.colors.impl.BlueColor;
import com.fantasybaby.strategy.colors.impl.RedColor;

/**
 * 策略模式 测试
 * @author FantasyBaby
 *
 */
public class StrategyTest {
	private static Logger _logger = LoggerFactory.getLogger(StrategyTest.class);
	@Test
	public void test1(){
//		MyColor myColor = new MyColor(new RedColor());
		MyColor myColor = new MyColor("black");
		_logger.info(myColor.showColor());
//		System.out.println(myColor.showColor());
	}
}
