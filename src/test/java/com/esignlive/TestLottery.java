package com.esignlive;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestLottery {

	Lottery lt=new Lottery();
	@Test
	public void testSetClientName() {
		lt.setClientName("Xiao");
		assertEquals("Xiao", lt.getClientName());
	}

	@Test
	public void testSetBallNum() {
		lt.setBallNum(10);
		assertEquals(10, lt.getBallNum());
	}
	
	@Test
	public void testSetPurchaseDate() {
		Date dt=Calendar.getInstance().getTime();
		lt.setPurchaseDate(dt);
		assertEquals(dt.toString(), lt.getPurchaseDate().toString());
	}

}
