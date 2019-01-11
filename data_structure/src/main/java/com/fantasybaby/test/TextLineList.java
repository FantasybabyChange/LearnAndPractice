package com.fantasybaby.test;

import org.junit.Test;

import com.fantasybaby.entity.SoryEntity;
import com.fantasybaby.inter.AbstractSQLList;
import com.fantasybaby.inter.ILinerList;
import com.fantasybaby.inter.impl.SimpleArrayImpl;
import com.fantasybaby.inter.impl.SimpleSQLList;
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
		Integer [] bList = {3,4,6,5,3};
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
	@Test
	public void testLinerListEssential(){
		AbstractSQLList<SoryEntity> s = new SimpleSQLList<SoryEntity>();
		/*SoryEntity s1 = new SoryEntity(1);
		SoryEntity s2 = new SoryEntity(2);
		s.add(s1);
		s.add(s2);*/
		
		for (int i = 0; i < 10; i++) {
			SoryEntity s3 = new SoryEntity(i);
			s.add(s3);
		}
		long first = System.currentTimeMillis();
//		SoryEntity s4 = new SoryEntity(34);
//		s.insert(9991, s4);
		long second = System.currentTimeMillis();
		System.out.println(second - first);
		System.out.println(s.getCurrentIndex());
		System.out.println(s.getLength());
		
		for (int i = 0; i < s.getCurrentIndex(); i++) {
			System.out.println(s.get(i));
		}
		System.out.println("---------------");
		SoryEntity s4 = new SoryEntity(11);
		s.delete(s4);
		for (int i = 0; i < s.getCurrentIndex(); i++) {
			System.out.println(s.get(i));
		}
		
//		SoryEntity s2 = new SoryEntity(1);
	}
	@Test
	public void testUtilMergeCollections1(){
		AbstractSQLList<SoryEntity> s = new SimpleSQLList<SoryEntity>();
		SoryEntity s1 = new SoryEntity(1);
		SoryEntity s2 = new SoryEntity(2);
		SoryEntity s3 = new SoryEntity(3);
		s.add(s1);
		s.add(s2);
		s.add(s3);
		AbstractSQLList<SoryEntity> se1 = new SimpleSQLList<SoryEntity>();
		SoryEntity s4 = new SoryEntity(3);
		SoryEntity s5 = new SoryEntity(4);
		SoryEntity s6 = new SoryEntity(6);
		SoryEntity s7 = new SoryEntity(5);
		SoryEntity s8 = new SoryEntity(3);
		se1.add(s4);
		se1.add(s5);
		se1.add(s6);
		se1.add(s7);
		se1.add(s8);
		AbstractSQLList<SoryEntity> mergeCollections1 = MergeUtil.mergeCollections1(s,se1);
		for (int i = 0; i < mergeCollections1.getCurrentIndex(); i++) {
			System.out.println(mergeCollections1.get(i));
		}
	} 
	@Test
	public void testUtilMergeLinearList1(){
		AbstractSQLList<SoryEntity> s = new SimpleSQLList<SoryEntity>();
		SoryEntity s1 = new SoryEntity(1);
		SoryEntity s2 = new SoryEntity(2);
		SoryEntity s3 = new SoryEntity(3);
		s.add(s1);
		s.add(s2);
		s.add(s3);
		AbstractSQLList<SoryEntity> se1 = new SimpleSQLList<SoryEntity>();
		SoryEntity s4 = new SoryEntity(2);
		SoryEntity s5 = new SoryEntity(4);
		SoryEntity s6 = new SoryEntity(6);
		se1.add(s4);
		se1.add(s5);
		se1.add(s6);
		AbstractSQLList<SoryEntity> mergeCollections1 = MergeUtil.mergelinear1(s, se1);
		for (int i = 0; i < mergeCollections1.getCurrentIndex(); i++) {
			System.out.println(mergeCollections1.get(i));
		}
	} 
}
