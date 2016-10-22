package com.esignlive;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestBase {

	ByteArrayOutputStream outContent = null;
	String separator = System.getProperty("line.separator");

	public void monitorConsole() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

	}

	public void checkConsole(String expectedOutput) {
		assertEquals(expectedOutput + separator, outContent.toString());
		monitorConsole();
	}

	
}
