package com.fantasybaby.util;

import com.fantasybaby.entity.DataPolyNomial;
import com.fantasybaby.entity.Node;
import com.fantasybaby.entity.SoryEntity;
import com.fantasybaby.exception.FantasyBabyException;
import com.fantasybaby.inter.AbstractLinkList;
import com.fantasybaby.inter.AbstractSQLList;
import com.fantasybaby.inter.ILinerList;
import com.fantasybaby.inter.impl.SimpleArrayImpl;
import com.fantasybaby.inter.impl.SimpleSQLList;

public class MergeUtil<T> {
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
	/**
	 * merge two link sequential list
	 * @param link1
	 * @param link2
	 * @return
	 * @throws FantasyBabyException 
	 */
	public AbstractLinkList<Integer> mergeTwoSeqLinkList(AbstractLinkList<Integer> link1,AbstractLinkList<Integer> link2) throws FantasyBabyException{
		Node<Integer> headeNode = link1.getHeadeNode();
		Node<Integer> headeNode2 = link2.getHeadeNode();
		Node<Integer> node = headeNode.getNext();
		Node<Integer> node2 = headeNode2.getNext();
		Node<Integer> nodeTmp = null;
		Integer data = (Integer) node.getData();
		Integer data2 = (Integer) node2.getData();
		if (data <= data2) {
			nodeTmp = node;
//			headeNode.setNext(nodeTmp);
			node = node.getNext();
		}else{
			headeNode.setNext(node2);
			nodeTmp = node2;
			node2 = node2.getNext();
			
		}
		while (node != null && node2 != null) {
			if ((Integer)node.getData() <= (Integer)node2.getData()) {
				nodeTmp.setNext(node);
				nodeTmp = node;
				node = node.getNext();
			}else{
				nodeTmp.setNext(node2);
				nodeTmp = node2;
				node2 = node2.getNext();
			}
		}
		if (node != null) {
			nodeTmp.setNext(node);
		}else{
			nodeTmp.setNext(node2);
		}
		link1.setSize(link1.getSize() + link2.getSize());
		return link1;
	}
	/**
	 * merge two link sequential list2
	 * @param link1
	 * @param link2
	 * @return
	 * @throws FantasyBabyException 
	 */
	public AbstractLinkList<Integer> mergeTwoSeqLinkList2(AbstractLinkList<Integer> link1,AbstractLinkList<Integer> link2) throws FantasyBabyException{
		Node<Integer> headeNode = link1.getHeadeNode();
		Node<Integer> headeNode2 = link2.getHeadeNode();
		Node<Integer> node = headeNode.getNext();
		Node<Integer> node2 = headeNode2.getNext();
		Node<Integer> nodeTmp = headeNode;
		while (node != null && node2 != null) {
			if ((Integer)node.getData() <= (Integer)node2.getData()) {
				nodeTmp.setNext(node);
				nodeTmp = node;
				node = node.getNext();
			}else{
				nodeTmp.setNext(node2);
				nodeTmp = node2;
				node2 = node2.getNext();
			}
		}
		if (node != null) {
			nodeTmp.setNext(node);
		}else{
			nodeTmp.setNext(node2);
		}
		link1.setSize(link1.getSize() + link2.getSize());
		return link1;
	}
	/**
	 * 计算polynomial的和    (x^1+2x^2+...+nx^n)+(2x^1+3x^2+...+nx^n) 
	 * 指数相同 系数相加 记得处理  系数和为零的情况
	 * @param link1
	 * @param link2
	 */
	public void sumPolynomial(AbstractLinkList<Integer> link1,AbstractLinkList<Integer> link2){
		Node<Integer> link1Head = link1.getHeadeNode();
		Node<Integer> link2Head = link2.getHeadeNode();
		Node<Integer> pa = link1Head.getNext();
		Node<Integer> pb = link2Head.getNext();
		Node<Integer> pre = link1Head;
		while (pa != null && pb != null) {
			DataPolyNomial paData = (DataPolyNomial)pa.getData();
			DataPolyNomial pbData = (DataPolyNomial)pb.getData();
			int exp1 = paData.getExponent();
			int exp2 = pbData.getExponent();
			if (exp1 < exp2) {
				pre = pa;
				pa = pa.getNext();
			}else if(exp1 == exp2){
			    int x = paData.getCoefficient() + pbData.getCoefficient();
			    if (x > 0) {
					paData.setCoefficient(x);
					pre = pa;
				}else{
					pre.setNext(pa.getNext());
					
				}
			    pb = pb.getNext();
			    pa = pa.getNext();
			}else{
				Node<Integer> pbTmp = pb.getNext();
				pb.setNext(pre.getNext());
				pre.setNext(pb);
				pre = pb;
				pb = pbTmp;
			}
		}
		if (pb != null) {
			pre.setNext(pb);
		}
	} 
}
