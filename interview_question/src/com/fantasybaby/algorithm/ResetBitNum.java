package com.fantasybaby.algorithm;

import java.util.Scanner;

/**
 * 将无符号数的指定比特进行置1
 * 1. 将无符号数的指定比特进行置1：输入无符号数，输入数字N（N>= 0;
 * N<=31），将此无符号数的第N个bit置1，输出置1后的数值。
 * 例如：输入 891 7 891
 * 转换成二进制为11 0111 1011
 * 第3个bit为1，将第3个bit清零后的二进制值为11 1111 1011，
 * 转化为10进制为1019
 * 输出1019约束：只考虑32位机，无符号数的bit位从第0 bit到第31 bit。
 * 输入: 无符号数，指定的bit位输出:
 * 指定的bit被置1后的值样例输入: 891 7样例输出: 1019
 * 
 * @author Reid.Liu
 *
 */
public class ResetBitNum {
	private int unsignedInteger;
	private int bitDig;
	public ResetBitNum() {
		initNum();
	}
	private void initNum(){
		 Scanner in=new Scanner(System.in);
		 try {
			 System.out.println("请输入转换的无符号数 和 相应的位数 用空格隔开");
			 String firstNum = in.nextLine();
			 //deal  the multiple space
			 String[] split = firstNum.split("\\s+");
			 validateInput(split);
			
		} catch (Exception e) {
			System.out.println("the input is illegal");
		}finally{
			in.close();
		}
	}
	//assign the value
	private void validateInput(String[] array) throws Exception{
		if (array != null && array.length == 2) {
			 unsignedInteger = Integer.parseInt(array[0]);
			 bitDig = Integer.parseInt(array[1]);
			 System.out.println(unsignedInteger + "-" + bitDig);
		}else{
			throw new Exception("input is illegal");
		}
	}
	/**
	 * convert the decimal value to binary by manual
	 * @param decimal
	 * @return the binary num
	 */
	private StringBuffer converDecimalToBinary(int decimal){
		StringBuffer binary = new StringBuffer();
		while(decimal/2 != 0){
			if(decimal % 2 == 0){
				binary.insert(0, "0");	
			}else{
				binary.insert(0,"1");
			}
			decimal = decimal/2;
			if(decimal <= 1){
				if(decimal == 1){
					binary.insert(0,"1");
				}else if(decimal == 0){
					binary.insert(0,"0");
				}
				return binary;
			}
		}
		return binary;
	}
	public String resetTheDig(){
		StringBuffer resetBinary = converDecimalToBinary(unsignedInteger);
		String binaryString = Integer.toBinaryString(unsignedInteger);
		System.out.println("java the binary get is" + binaryString);
		System.out.println("the binary get is" + resetBinary);
		resetBinary.replace(bitDig, bitDig + 1, "1");
		System.out.println("the Reset binary get is" + resetBinary);
		return resetBinary.toString();
	}
}
