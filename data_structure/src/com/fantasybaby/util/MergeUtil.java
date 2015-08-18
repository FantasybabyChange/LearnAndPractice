package com.fantasybaby.util;

import com.fantasybaby.entity.SoryEntity;
import com.fantasybaby.inter.AbstractSQLList;
import com.fantasybaby.inter.ILinerList;
import com.fantasybaby.inter.impl.SimpleArrayImpl;
import com.fantasybaby.inter.impl.SimpleSQLList;

public class MergeUtil {
	private static ILinerList<Integer> linerList = new SimpleArrayImpl();
	/**
	 * A = A U B
	 */
	public static Integer[] mergeCollections(Integer[] a_list, Integer[] b_list){
		for (int i = 0; i < b_list.length; i++) {
			int locate = linerList.locate(a_list, b_list[i]);
			if (locate < 0) {
				int length = linerList.getLength(a_list);
				a_list = linerList.insert(a_list , length + 1 , b_list[i]);
			}
		}
		return a_list;
	}
	/**
	 * merge two linear increased 
	 */
	public static Integer[] mergelinear(Integer[] a_list, Integer[] b_list){
		Integer[] c_list = null;
		int i=0;
		int j=0;
		int k =0;
		while (a_list.length > i && b_list.length > j) {
			Integer aElementData = linerList.get(a_list, i);
			Integer bElementData = linerList.get(b_list, j);
			if (aElementData.intValue() < bElementData.intValue()) {
				c_list = linerList.insert(c_list, k + 1, aElementData);
				i++;
				k++;
			}else{
				c_list = linerList.insert(c_list, k + 1, bElementData);
				j++;
				k++;
			}
		}
		if(i < a_list.length  ){
			while (i < a_list.length -1 ) {
				c_list = linerList.insert(c_list, k + 1, linerList.get(a_list, i));
				i++;
				k++;
			}
		}else if(j < b_list.length){
			while (j < b_list.length) {
				c_list = linerList.insert(c_list, k + 1, linerList.get(b_list, j));
				j++;
				k++;
			}
		}
		
		return c_list;
	}
	/**
	 * A = A U B
	 */
	public static AbstractSQLList<SoryEntity> mergeCollections1(AbstractSQLList<SoryEntity> a_list, AbstractSQLList<SoryEntity> b_list){
		for (int i = 0; i < b_list.getCurrentIndex(); i++) {
			int locate = a_list.locate(b_list.get(i));
			if (locate < 0) {
				a_list.insert(a_list.getCurrentIndex(),b_list.get(i));
			}
		}
		return a_list;
	}
	/**
	 * merge two linear increased 
	 */
	public static AbstractSQLList<SoryEntity> mergelinear1(AbstractSQLList<SoryEntity> a_list, AbstractSQLList<SoryEntity> b_list){
		AbstractSQLList<SoryEntity>  c_list = new SimpleSQLList<SoryEntity>(a_list.getCurrentIndex()+b_list.getCurrentIndex());
		int i=0;
		int j=0;
		int k =1;
		while (a_list.getCurrentIndex() > i && b_list.getCurrentIndex() > j) {
			SoryEntity soryEntity = a_list.get(i);
			SoryEntity soryEntity2 = b_list.get(j);
			if (soryEntity.getData() < soryEntity2.getData()) {
				c_list.insert(k, soryEntity);
				i++;
				k++;
			}else{
				c_list.insert(k, soryEntity2);
				j++;
				k++;
			}
		}
		if(i < a_list.getCurrentIndex()  ){
			while (i < a_list.getCurrentIndex()) {
				c_list.insert( k, a_list.get(i));
				i++;
				k++;
			}
		}else if(j < b_list.getCurrentIndex()){
			while (j < b_list.getCurrentIndex()) {
				c_list.insert(k, b_list.get(j));
				j++;
				k++;
			}
		}
		
		return c_list;
	}
}
