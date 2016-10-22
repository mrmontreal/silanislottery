package com.esignlive;

import java.util.Scanner;

/**
 * This class use three commands:purchase,draw,winners
 * 
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.0
 */
public class Vendor {
	public static void main(String[] args) {
		// machine
		Machine mh = new Machine();
		// keyBoard
		Keyboard kb = null;
		// purchase
		PurchaseCommandImp ph = null;
		// draw
		DrawCommandImp dr = new DrawCommandImp(mh);
		// winners
		WinnersCommandImp wn = new WinnersCommandImp(mh);

		System.out.println("Welcome to use the lottery system, enter '#' exit the system\n");
		System.out.println("Enter 'purchase'---To buy a lottery\n");
		System.out.println("Enter 'draw'---To draw 3 balls\n");
		System.out.println("Enter 'winners'---To show winners\n");
		Scanner input = new Scanner(System.in);
		String inputValue = null;
		do {
			try {
				System.out.print("Please enter your command: ");
				inputValue = input.next().trim().toLowerCase();
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			switch (inputValue) {
			case "purchase": {
				try {
					System.out.print("Please enter client name(only letters): ");
					String clientName = input.next().toLowerCase().toString();
					System.out.print("Please enter ball number(from 1 to " + Machine.LOTTERY_BALL_SIZE + "): ");
					String ballNum = input.next().toString();
					// Only letters and numbers can be entered
					if (clientName.matches("^[A-Za-z]+$") && ballNum.matches("^[0-9]*$")) {
						Client cl = new Client();
						cl.setClientName(clientName);
						cl.setPurchaseNum(Integer.parseInt(ballNum));
						// create a lottery
						Lottery lt = new Lottery();
						// put client information on it
						lt.setBallNum(cl.getPurchaseNum());
						lt.setClientName(cl.getClientName());
						// purchase
						ph = new PurchaseCommandImp(mh, lt);
						kb = new Keyboard(ph, dr, wn);
						kb.inputPurchase();

					} else {
						System.out.println("purchase input error.....try again");
					}
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

			}

				break;
			case "draw": {
				try {
					kb = new Keyboard(ph, dr, wn);
					kb.inputDraw();
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

			}
				break;
			case "winners": {
				try {
					kb = new Keyboard(ph, dr, wn);
					kb.inputWinners();
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

			}
				break;
			default:
				System.out.println("Invalid command....please try again...");
			}

		} while (!inputValue.equals("#"));
		System.out.println("You enter '#', the system will exit.... ");
		input.close();

	}
}
