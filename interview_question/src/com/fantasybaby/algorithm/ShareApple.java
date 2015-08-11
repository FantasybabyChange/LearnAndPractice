package com.fantasybaby.algorithm;

import java.util.Scanner;

/**
 * question 分苹果
 * 1.小明准备把M个同样的苹果分在N个同样的篮子里，
 * 允许有的篮子空着不放，那么一共有多少种不同的分法呢？
 * 说明：3，1，1和1，3，1 是同一种分法。 输入:
 * 每个用例包含二个整数M和N。0<=M<=10，1<=N<=10。
 * 输出: 一个整数K，表示一共有K种分苹果的方法。
 * 样例输入: 7 3样例输出: 8
 * 
 * @author 刘曦
 *
 */
public class ShareApple {
	private int appleNum;
	private int basketNum;
	
	public ShareApple() {
		initNum();
	}
	private void initNum(){
		 Scanner in=new Scanner(System.in);
		 try {
			 System.out.println("请输入苹果的个数");
			 String firstNum = in.nextLine();
			 appleNum =  Integer.parseInt(firstNum);
			 validateNum(appleNum);
			 System.out.println("请输入篮子的个数");
			 String secondNum = in.nextLine();
			 basketNum =  Integer.parseInt(secondNum);
			 validateNum(basketNum);
		} catch (Exception e) {
			System.out.println("the input is illegal");
		}finally{
			in.close();
		}
	}
	/**
	 * validate the num
	 * @param num
	 * @throws Exception
	 */
	public void validateNum(int num) throws Exception{
		if (num <0 || num > 10) {
			throw new Exception("the input is illegal");
		}
	}
	public int shareAppleInBasket(int currentNum,int lastNum,int summaryNum,int countCategory,int currentBasket) throws Exception{
		for (int i = 0; i < currentNum ; i++) {
			int tmpCurrentNum = currentNum - i;
			int tmpSummaryNum = tmpCurrentNum + summaryNum;
			if (summaryNum > appleNum || currentBasket > basketNum) {
				throw new Exception("There is a error in programmer");
			}
			if (tmpSummaryNum == appleNum && currentNum <= lastNum) {
				countCategory++;
			}else{
				if (2 * tmpCurrentNum > (appleNum - tmpSummaryNum) ) {
					countCategory = shareAppleInBasket(appleNum - tmpSummaryNum,tmpCurrentNum,tmpSummaryNum,countCategory,(currentBasket + 1));
				}else{
					return countCategory;
				}
			}
			if ((currentBasket + 1) >= basketNum) {
				return countCategory;
			}
		}
		return countCategory;
	}
	public int getCategoryNum() throws Exception{
		int count = shareAppleInBasket(appleNum ,appleNum, 0 , 0 , 0);
		return appleNum <= basketNum ? count+1 : count;
	}
	

}
