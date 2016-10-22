package com.esignlive;
/**
 * This class describe the Screen properties and show content in console
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.0
 */
public class Screen {
	private String content;
	private static final String[] PRIZE_NAME = { "1st ball", "2nd ball", "3rd ball" };

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void showContent() {
		System.out.println(content);
	}

	public void showContent(String winnerList[]) {

		StringBuilder firstLine = new StringBuilder();
		StringBuilder secLine = new StringBuilder();
		for (int i = 0; i < winnerList.length; i++) {
			firstLine.append(PRIZE_NAME[i] + "\t");
			secLine.append(winnerList[i] + "$\t");
		}
		System.out.println(firstLine.toString() + "\n" + secLine.toString());
	}

}
