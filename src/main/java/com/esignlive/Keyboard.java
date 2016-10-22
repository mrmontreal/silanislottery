package com.esignlive;
/**
 * This class implement invoker function in the command pattern
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.0
 */
public class Keyboard {
	private ICommand drawCmd, purchaseCmd, winnersCmd;

	public Keyboard(ICommand purchaseCmd, ICommand drawCmd, ICommand winnersCmd) {
		this.purchaseCmd = purchaseCmd;
		this.drawCmd = drawCmd;
		this.winnersCmd = winnersCmd;
	}

	public void inputPurchase() {
		purchaseCmd.execute();
	}

	public void inputDraw() {
		drawCmd.execute();
	}

	public void inputWinners() {
		winnersCmd.execute();
	}

}
