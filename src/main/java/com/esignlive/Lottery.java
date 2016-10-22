package com.esignlive;

import java.util.Date;

/**
 * This class describe the lottery properties
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.1
 */
public class Lottery {

	private String clientName;
	private int ballNum;
	private Date purchaseDate;// add purchase date to avoid after the drawn, and
								// then buy the same number lottery

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getBallNum() {
		return ballNum;
	}

	public void setBallNum(int ballNum) {
		this.ballNum = ballNum;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
