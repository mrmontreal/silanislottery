package com.esignlive;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestClient {
	Client cl = new Client();

	@Test
	public void testSetClientName() {
		cl.setClientName("Xiao");
		assertEquals("Xiao", cl.getClientName());
	}

	@Test
	public void testSetPurchaseNum() {
		cl.setPurchaseNum(10);
		assertEquals(10, cl.getPurchaseNum());
	}

}
