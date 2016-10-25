package com.esignlive;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * This class implement lottery machine three functions:purchase,draw,get winner
 * list
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.2
 */
public class Machine {

	public static final int LOTTERY_BALL_SIZE = 50;
	private static final int DRAW_TIMES = 3;
	private static final double LOTTERY_PRICE = 10.00;
	private static final double PRIZE_TOTAL = 200.00;
	private static final double PRIZE_AVAILABLE_RATE = 0.5;
	private static final double[] PRIZE_RATE = { 0.75, 0.15, 0.10 };

	// Record sales records
	private ArrayList<Lottery> lotterySoldRecordList = new ArrayList<Lottery>();
	// Record the balls in the machine
	private ArrayList<Ball> lotteryBallList = new ArrayList<Ball>();
	// Record drawn ball numbers
	private ArrayList<Integer> drawBallsList = new ArrayList<Integer>();
	// save winner information
	private String winnerList[];
	// Record draw next date and set default value is when the machine stars
	private Calendar drawNextDate = Calendar.getInstance();
	private Calendar drawDate;
	private Screen screen = new Screen();
	private Prize prize;

	/**
	 * Initialize Lottery Ball Number and prize when this class is instanced
	 */
	public Machine() {
		// Initialize Lottery Ball Number
		this.initializeLotteryBallList();
		// Initialize Prize
		this.prize = Prize.getInstance(PRIZE_TOTAL);

	}

	/**
	 * Initialize Lottery Ball Number
	 */
	private void initializeLotteryBallList() {
		this.lotteryBallList.clear();
		for (int i = 1; i <= LOTTERY_BALL_SIZE; i++) {
			Ball bl = new Ball();
			bl.setBallNum(i);
			this.lotteryBallList.add(bl);
		}
	}

	public ArrayList<Lottery> getLotterySoldRecordList() {
		return lotterySoldRecordList;
	}

	public void setLotterySoldRecordList(ArrayList<Lottery> lotterySoldRecordList) {
		this.lotterySoldRecordList = lotterySoldRecordList;
	}

	public ArrayList<Ball> getLotteryBallList() {
		return lotteryBallList;
	}

	public void setLotteryBallList(ArrayList<Ball> lotteryBallList) {
		this.lotteryBallList = lotteryBallList;
	}

	public ArrayList<Integer> getDrawBallsList() {
		return drawBallsList;
	}

	public void setDrawBallsList(ArrayList<Integer> drawBallsList) {
		this.drawBallsList = drawBallsList;
	}

	/**
	 * purchase a lottery, need to verify: 1.client is null or not 2.boundary of
	 * balls 3.buy the same number situation
	 * 
	 * @param Client
	 *            input a client purchase information
	 * 
	 */
	public void purchaseLottery(Lottery lottery) {
		boolean soldFlag = false;
		boolean occupyFlag = false;
		// 1.Verify the client purchased information
		if (lottery != null && lottery.getClientName() != null && lottery.getBallNum() > 0
				&& lottery.getBallNum() <= LOTTERY_BALL_SIZE) {
			// 2.Verify the same number to be sold again situation
			for (Lottery lt : lotterySoldRecordList) {
				if (lt.getBallNum() == lottery.getBallNum())
					occupyFlag = true;
			}
			// 3. make sure the lottery ball list has correct values
			if (this.lotteryBallList != null && this.lotteryBallList.size() <= LOTTERY_BALL_SIZE
					&& this.lotteryBallList.size() > 0) {
				// 4. if the lottery did not sell, so can be sold. and sell the
				// lottery
				if (!occupyFlag) {

					// remove the number from lottery balls list
					for (Ball bl : this.lotteryBallList) {

						if (bl.getBallNum() == lottery.getBallNum()) {
							// V1.2 add a flag to verify whether purchase
							// successfully
							soldFlag = this.lotteryBallList.remove(bl);
							break;
						}
					}
					// 5.if purchase successfully
					if (soldFlag) {
						// 5.1 add money to prize pot
						this.prize.addAmount(LOTTERY_PRICE);
						screen.setContent("Now prize amount is: " + this.prize.getAmount());
						screen.showContent();
						// 5.2 add purchase date on lottery
						lottery.setPurchaseDate(Calendar.getInstance().getTime());
						// 5.3 add lottery information to lottery sold list
						lotterySoldRecordList.add(lottery);

						// 5.4 show information on the screen
						screen.setContent("The number you purchased is: " + lottery.getBallNum());
						screen.showContent();
						screen.setContent("Purchase successfully...");
						screen.showContent();
					} else {
						screen.setContent(
								"Purchase unsuccessfully: the machine has an error, please contact with admin...");
						screen.showContent();
					}

				} else {
					// show information on the screen
					screen.setContent("Purchase unsuccessfully: this number was sold....please try others...");
					screen.showContent();
				}

			} else {
				// show information on the screen
				screen.setContent("Purchase unsuccessfully: the machine has an error, please contact with admin...");
				screen.showContent();
			}
		} else {
			// show information on the screen
			screen.setContent("Purchase unsuccessfully: lottery information has errors....please try again...");
			screen.showContent();
		}

	}

	/**
	 * start to draw balls,firstly,we need to check draw data,it should happen
	 * once a month secondly, we need to avoid repeat draw situation
	 * lastly,compare [drawBallsList] with [lotterySoldRecordList], and put the
	 * result to [winnerList]
	 * 
	 */
	public void drawBalls() {
		// draw current date
		Calendar drawCurrentDate = Calendar.getInstance();
		// 1.verify the draw date
		if ((drawCurrentDate.compareTo(drawNextDate)) >= 0) {
			if (drawBallsList.size() == 0) {
				winnerList = new String[DRAW_TIMES];
				// 2. generate drawn balls number at random
				this.drawBallsList = LotteryTools.randomCommon(LOTTERY_BALL_SIZE, DRAW_TIMES);
				 //this.drawBallsList = LotteryTools.randomCommon(5,DRAW_TIMES);
				// show draw numbers
				for (Integer i : drawBallsList) {
					screen.setContent("Draw Number is: " + i);
					screen.showContent();
				}
				// 3. change draw date, add one month for the next time
				drawNextDate = Calendar.getInstance();
				// drawNextDate.add(Calendar.MONTH, 1);
				drawNextDate.add(Calendar.SECOND, 10);// I set 10
														// seconds to easy to
														// test
				drawDate = drawCurrentDate;
				// 4.compare [drawBallsList] with [lotterySoldRecordList],
				// firstly, need to check
				// [lotterySoldRecordList] is null or empty.if null or empty, we
				// do not need to compare.

				// 4.1 sort list to optimize the search
				SoldListComparator slc = new SoldListComparator();
				Collections.sort(this.lotterySoldRecordList, slc);

				int drawTimer = 0;
				for (Integer drawBallNum : this.drawBallsList) {
					String result = "No One: 0";
					for (Lottery soldLottery : lotterySoldRecordList) {
						// v1.1 add verify purchase date before drawing
						// 4.2 check who purchased the same number as same
						// as draw number
						if ((drawBallNum.intValue() == soldLottery.getBallNum())
								&& (soldLottery.getPurchaseDate().compareTo(drawDate.getTime()) != 1)) {
							String winnerName = soldLottery.getClientName();
							// 4.3 calculate prize
							double eachPrize = Math
									.round(this.prize.getAmount() * PRIZE_AVAILABLE_RATE * PRIZE_RATE[drawTimer]);
							result = winnerName + ": " + Double.toString(eachPrize);
							// 4.4 reduce the prize
							this.prize.reduceAmount(eachPrize);
							break;
						}
					}
					winnerList[drawTimer] = result;
					drawTimer++;
				}

				// 5.after drawn, initialize [LotteryBallList] and empty
				// [LotterySoldRecordList]
				initializeLotteryBallList();
				this.lotterySoldRecordList.clear();

			} else {
				screen.setContent("Sorry, You can not repeat draw, you need to see winner list, then draw again...");
				screen.showContent();
			}
		} else {

			screen.setContent("Sorry, The Silanis lottery happens once a month,Please wait next time...");
			screen.showContent();
		}
	}

	/**
	 * show [winerList] on the screen,then reset [winnerList] and
	 * [drawBallsList]
	 */
	public void getWinnerList() {

		if (winnerList != null && winnerList.length > 0) {

			// show on screen
			screen.showContent(winnerList);

		} else {

			screen.setContent("You did not draw, please draw firstly....");
			screen.showContent();

		}
		// reset winner list and draw ball list
		winnerList = null;
		this.drawBallsList.clear();

	}

}
