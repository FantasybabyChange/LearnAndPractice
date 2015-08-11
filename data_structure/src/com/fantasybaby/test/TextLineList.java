package com.fantasybaby.test;

import org.junit.Test;

import com.fantasybaby.inter.ILinerList;
import com.fantasybaby.inter.impl.SimpleArrayImpl;
import com.fantasybaby.util.MergeUtil;

public class TextLineList {
	@Test
	public void test(){
		ILinerList<Integer> ll = new SimpleArrayImpl();
		Integer [] aList = {1,2,3};
		aList = ll.delete(aList, 1);
		for (Integer integer : aList) {
			System.out.println(integer);
		}
		
		
	}
	@Test
	public void testUtilMergeCollections(){
		Integer [] aList = {1,2,3};
		Integer [] bList = {3,4,6};
		Integer[] a_list = MergeUtil.mergeCollections(aList, bList);
		for (Integer integer : a_list) {
			System.out.println(integer);
		}
	} 
	@Test
	public void testUtilMergeLinearList(){
		Integer [] aList = {1,2,3};
		Integer [] bList = {2,4,6};
		Integer[] c_list = MergeUtil.mergelinear(aList, bList);
		for (Integer integer : c_list) {
			System.out.println(integer);
		}
	} 
}
