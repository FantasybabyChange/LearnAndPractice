package com.fantasybaby.util;

import com.fantasybaby.constant.ExpressionOper;
import com.fantasybaby.inter.AbstractStack;
import com.fantasybaby.inter.impl.SimpleSQLStack;

/**
 * 
 * @author FantasyBaby
 *
 */
public class StackApplyUtil {
	private static final char[] opers = {'+','-','*','/','(',')','#'};
	public static int getValueByChar(char value){
		int returnValue = ExpressionOper.NUMBERSIGN.getValue();
		if (Character.isDigit(value)) {
			returnValue = ExpressionOper.DIGITAL.getValue();
		}else{
			switch(value){
				case '+':
					returnValue = ExpressionOper.ADD.getValue();
				   break;
				case '-':
					returnValue = ExpressionOper.SUBSTRACT.getValue();
				   break;
				case '*':
					returnValue = ExpressionOper.MULTIPLY.getValue();
				   break;
				case '/':
					returnValue = ExpressionOper.DIVIDE.getValue();
				   break;
				case '(':
					returnValue = ExpressionOper.RBRACKET.getValue();
				   break;
				case ')':
					returnValue = ExpressionOper.LBRACKET.getValue();
				   break;
				case '#':
					returnValue = ExpressionOper.NUMBERSIGN.getValue();
				   break;
				default:
					break;
			}
		}
		return returnValue;
	}
	/**
	 * Use the method to deal with the split 
	 * the String then  analytic the char is digital
	 * or  symbol
	 * @return
	 */
	public static String evaluateExpression(String str){
		AbstractStack<String> oper = new SimpleSQLStack<String>();
		AbstractStack<String> data = new SimpleSQLStack<String>();
		StringBuffer expression = new StringBuffer(str);
		int count = 0;
		/*if (getValueByChar(expression.substring(0,1)) > 0) {
			
		}*/
		int expressionLength = expression.length();	
		while (count < expressionLength) {
			System.out.println(expression.substring(0,1));
			expression.deleteCharAt(0);
			count ++;
		}
		return null;
	}

}
