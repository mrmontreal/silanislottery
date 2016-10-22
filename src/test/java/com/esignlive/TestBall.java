package com.esignlive;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBall {
	Ball bl = new Ball();

	@Test
	public void testSetBallNum() {
		bl.setBallNum(10);
		assertEquals(10, bl.getBallNum());
	}

}
