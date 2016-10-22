package com.esignlive;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestScreen extends TestBase {

	Screen sr = new Screen();

	@Before
	public void setUp() throws Exception {
		monitorConsole();

	}

	@Test
	public void testSetContent() {
		sr.setContent("Xiao");
		assertEquals("Xiao", sr.getContent());
	}

	@Test
	public void testShowContent() {
		sr.setContent("OK");
		sr.showContent();
		String expectedOutput = "OK";
		checkConsole(expectedOutput);
	}

	@Test
	public void testShowContentStringArray() {

		String winnerList[] = { "No One: 0", "No One: 0", "No One: 0" };
		String[] PRIZE_NAME = { "1st ball", "2nd ball", "3rd ball" };
		StringBuilder firstLine = new StringBuilder();
		StringBuilder secLine = new StringBuilder();
		for (int i = 0; i < winnerList.length; i++) {
			firstLine.append(PRIZE_NAME[i] + "\t");
			secLine.append(winnerList[i] + "$\t");
		}
		sr.showContent(winnerList);

		String expectedOutput = firstLine.toString() + "\n" + secLine.toString();
		checkConsole(expectedOutput);
	}

}
