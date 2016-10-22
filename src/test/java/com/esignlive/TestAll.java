package com.esignlive;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBall.class, TestClient.class, TestLottery.class, TestMachine.class, TestPrize.class,
		TestScreen.class, TestSoldListComparator.class })
public class TestAll {

}
