package com.esignlive;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMachine extends TestBase {
	Prize pz = null;
	Machine mc = null;

	@Before
	public void setUp() throws Exception {
		pz = Prize.getInstance(200);
		mc = new Machine();
		monitorConsole();

	}

	@After
	public void tearDown() throws Exception {
		pz = null;
		mc = null;
	}

	@Test
	public void testMachine() {

		ArrayList<Ball> ballNumList = mc.getLotteryBallList();
		// Initialize Lottery Ball Number
		int num = 1;
		for (Ball bl : ballNumList) {
			assertEquals(num, bl.getBallNum());
			num++;
		}
		// Initialize Prize
		assertEquals(200.0, pz.getAmount(), 0.1);
	}

	@Test
	public void testPurchaseLottery() {

		// Verify lottery information
		// lottery in null
		String expectedOutput = "Purchase unsuccessfully: lottery information has errors....please try again...";
		Lottery lt = null;
		mc.purchaseLottery(lt);
		checkConsole(expectedOutput);
		// client's name is null
		lt = new Lottery();
		lt.setClientName(null);
		mc.purchaseLottery(lt);
		expectedOutput = "Purchase unsuccessfully: lottery information has errors....please try again...";
		checkConsole(expectedOutput);

		// client's purchase number more then 50 or less than 0
		lt.setBallNum(-1);
		mc.purchaseLottery(lt);
		checkConsole(expectedOutput);
		lt.setBallNum(51);
		mc.purchaseLottery(lt);
		checkConsole(expectedOutput);

		// Verify the same number situation, but the client name can be same
		ArrayList<Lottery> lotterySoldRecordList = mc.getLotterySoldRecordList();
		Lottery ltAdd = new Lottery();
		ltAdd.setClientName("Xiao");
		ltAdd.setBallNum(10);
		lotterySoldRecordList.add(ltAdd);
		mc.setLotterySoldRecordList(lotterySoldRecordList);
		lt.setClientName("Xiao");
		lt.setBallNum(10);
		mc.purchaseLottery(lt);
		expectedOutput = "Purchase unsuccessfully: this number was sold....please try others...";
		checkConsole(expectedOutput);
	}

	@Test
	public void testDrawBalls() {
		// compare two closed draw date
		mc.drawBalls();
		monitorConsole();
		mc.drawBalls();
		String expectedOutput = "Sorry, The Silanis lottery happens once a month,Please wait next time...";
		checkConsole(expectedOutput);
		mc.drawBalls();
		try {
			Thread.currentThread();
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			//
			e.printStackTrace();
		}
		// draw list is not empty
		monitorConsole();
		mc.drawBalls();
		expectedOutput = "Sorry, You can not repeat draw, you need to see winner list, then draw again...";
		checkConsole(expectedOutput);

	}

	@Test
	public void testGetWinnerList() {
		// check before getting winner list, you should draw
		// mc.restList();
		mc.getWinnerList();
		String expectedOutput = "You did not draw, please draw firstly....";
		checkConsole(expectedOutput);
		
	}

}
