package com.fantasybaby.test;

import org.junit.Test;

import com.fantasybaby.inter.AbstractLinkList;
import com.fantasybaby.inter.impl.SimpleLinkList;

public class TestLinkList {
	
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

}
