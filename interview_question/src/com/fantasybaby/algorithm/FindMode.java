package com.fantasybaby.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 找众数1. 众数（Mode）统计学名词，在统计分布上具有明显集中趋势点的数值
 * ，代表数据的一般水平（众数可以不存在或多于一个）。修正定义：
 * 是一组数据中出现次数最多的数值
 * ，叫众数，有时众数在一组数中有好几个，
 * 用M表示。理性理解：简单的说，就是一组数据中占比列最多的那个数。从小到大输入10个整数，
 * 输出这10个数中的众数注意：可能有多个众数，多个众数以英文逗号分割输出输入: 从小到大输入10个整数这10个数中的众数输出:
 * 注意：可能有多个众数，多个众数以英文逗号分割输出样例输入: 123456789样例输出: 6
 * 
 * @author 刘曦
 *
 */
public class FindMode {
	private Map<Integer, Integer> countNum = new HashMap<Integer, Integer>();
	public FindMode() {
		initNum();
	}
	private void initNum(){
		 Scanner in=new Scanner(System.in);
		 try {
			 int i = 0;
			 while (i < 10) {
				 System.out.println("请输入第" + (i + 1) + "个数");
				 String firstNum = in.nextLine();
				 int inputNum = Integer.parseInt(firstNum);
				 Integer integer = countNum.get(inputNum);
				 if (integer == null) {
					 countNum.put(inputNum, 0);
				}else{
					countNum.put(inputNum, (integer+1));
				}
				 i++;
			 }
			
		} catch (Exception e) {
			System.out.println("the input is illegal");
		}finally{
			in.close();
		}
	}
	public String getTheModeNum(){
		Set<Integer> keySet = countNum.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		StringBuffer sb = new StringBuffer();
		Integer lastNum  = null;
		while (iterator.hasNext()) {
			Integer currentNum = iterator.next();
			Integer currentNumValue = countNum.get(currentNum);
			if (lastNum == null) {
				sb.append(currentNum+",");
				lastNum = currentNum;
			}else{
				Integer lastNumValue = countNum.get(lastNum);
				if (lastNumValue < currentNumValue ) {
					lastNum = currentNum;
					sb.setLength(0);
					sb.append(currentNum+",");
					
				}else if(lastNumValue == currentNumValue){
					sb.append(currentNum + ",");
				}
			}
		}
		return sb.toString();
	}
	

}
