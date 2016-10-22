package com.esignlive;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPrize {

	Prize pz = null;

	@Before
	public void setUp() throws Exception {
		pz = Prize.getInstance(200);

	}

	@After
	public void tearDown() throws Exception {
		pz = null;
	}

	@Test
	public void testGetInstance() {
		assertEquals(pz, Prize.getInstance(200));
	}

	@Test
	public void testGetAmount() {
		assertEquals(200.0, pz.getAmount(), 0.1);
	}

	@Test
	public void testAddAmount() {
		pz.addAmount(10.0);
		assertEquals(210.0, pz.getAmount(), 0.1);
	}

	@Test
	public void testReduceAmount() {
		pz.reduceAmount(10.0);
		assertEquals(200.0, pz.getAmount(), 0.1);
	}

}
