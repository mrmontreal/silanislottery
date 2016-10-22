package com.esignlive;

/**
 * This class describe the prize properties use Singleton to sure the prize
 * class that will only have one instance of the project
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.0
 */
public class Prize {
	private double amount;
	private static Prize uniqueInstance = null;

	private Prize(double amount) {
		this.amount = amount;
	}

	public static Prize getInstance(double amount) {

		if (uniqueInstance == null) {
			uniqueInstance = new Prize(amount);
		}
		return uniqueInstance;

	}

	public double getAmount() {
		return amount;
	}

	public void addAmount(double amount) {
		this.amount += amount;
	}

	public void reduceAmount(double amount) {
		this.amount -= amount;
	}
}
