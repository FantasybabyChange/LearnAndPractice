package com.fantasybaby.test;

import org.junit.Test;

import com.fantasybaby.entity.DataPolyNomial;
import com.fantasybaby.entity.Node;
import com.fantasybaby.exception.FantasyBabyException;
import com.fantasybaby.inter.AbstractLinkList;
import com.fantasybaby.inter.impl.SimpleLinkList;
import com.fantasybaby.util.MergeUtil;

public class TestLinkList {
	MergeUtil<Integer> mereUtil = new MergeUtil<Integer>();
	
	@Test
	public void test() {
		AbstractLinkList<Integer> al = new SimpleLinkList<Integer>();
		try {
			al.add(1);
			al.add(2);
			System.out.println(al.getSize());
			for (int i = 0; i < al.getSize(); i++) {
				System.out.println(al.get(i));
			}
			System.out.println("--");
			al.set(1, 3);
			for (int i = 0; i < al.getSize(); i++) {
				System.out.println(al.get(i));
			}
			System.out.println("--");
			al.insert(2, 8);
			for (int i = 0; i < al.getSize(); i++) {
				System.out.println(al.get(i));
			}
			System.out.println("-------");
			al.delete(0);
			for (int i = 0; i < al.getSize(); i++) {
				System.out.println(al.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test1() {
		AbstractLinkList<Integer> al = new SimpleLinkList<Integer>();
		try {
			Integer[] intArray = {1,2,3,4};
			al.addPro(intArray);
			System.out.println(al.getSize());
			for (int i = 0; i < al.getSize(); i++) {
				System.out.println(al.get(i));
			}
			System.out.println("--");
			al.set(1, 3);
			for (int i = 0; i < al.getSize(); i++) {
				System.out.println(al.get(i));
			}
			System.out.println("--");
			al.insert(0, 8);
			for (int i = 0; i < al.getSize(); i++) {
				System.out.println(al.get(i));
			}
			System.out.println("-------");
			al.delete(0);
			for (int i = 0; i < al.getSize(); i++) {
				System.out.println(al.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test2() throws FantasyBabyException {
		AbstractLinkList<Integer> al = new SimpleLinkList<Integer>();
		AbstractLinkList<Integer> al1 = new SimpleLinkList<Integer>();
		al.add(2);
		al.add(4);
		al1.add(1);
		al1.add(3);
		AbstractLinkList<Integer> mergeTwoSeqLinkList = mereUtil.mergeTwoSeqLinkList2(al, al1);
		/*for (int i = 0; i < mergeTwoSeqLinkList.getSize(); i++) {
			System.out.println(mergeTwoSeqLinkList.get(i));
		}*/
		int count = 0;
		while (count < al.getSize()) {
			System.out.println(al.get(count));
			count++;
			
		}
	}
	
	@Test
	public void test3() throws FantasyBabyException {
		AbstractLinkList<Integer> al = new SimpleLinkList<Integer>();
		AbstractLinkList<Integer> al1 = new SimpleLinkList<Integer>();
		DataPolyNomial dp = new DataPolyNomial(1,1);
		DataPolyNomial dp1 = new DataPolyNomial(3,3);
		
		DataPolyNomial dq = new DataPolyNomial(2,2);
		DataPolyNomial dq1 = new DataPolyNomial(4,3);
		al.add(dp);
		al.add(dp1);
		al1.add(dq);
		al1.add(dq1);
		mereUtil.sumPolynomial(al, al1);
		/*for (int i = 0; i < mergeTwoSeqLinkList.getSize(); i++) {
			System.out.println(mergeTwoSeqLinkList.get(i));
		}*/
		int count = 0;
		Node<Integer> headeNode = al.getHeadeNode();
		Node<Integer> next = headeNode.getNext();
		while ( next != null) {
			DataPolyNomial data = (DataPolyNomial)next.getData();
			System.out.print(data.getCoefficient()+"x^"+data.getExponent()+"+");
			next = next.getNext();
			
		}
	}

}
