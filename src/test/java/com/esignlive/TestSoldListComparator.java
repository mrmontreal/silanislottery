package com.esignlive;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSoldListComparator {

	@Test
	public void testCompare() {
		Lottery l1=new Lottery();
		l1.setBallNum(10);
		Lottery l2 =new Lottery();
		l1.setBallNum(1);
		
		SoldListComparator sc=new SoldListComparator();
		assertEquals(-1, sc.compare(l1, l2));
	}

}
