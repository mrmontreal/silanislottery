package com.esignlive;

import java.util.ArrayList;
/**
 * Lottery Tools Class
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.0
 */
public class LotteryTools {

	/**
	 * Randomly specify the number of N distincts within the range
	 * 
	 * @param max
	 *            Specifies the maximum range
	 * @param n
	 *            Number of random numbers
	 * @return  some random number in ArrayList<Integer> 
	 */
	public static ArrayList<Integer> randomCommon(int max, int n) {
		if (n > max - 1 || max < 1) {
			return null;
		} else {
			ArrayList<Integer> result = new ArrayList<Integer>();
			int count = 0;
			while (count < n) {
				// Math.random()*(n-m)+m; from m to n
				int num = (int) (Math.random() * (max - 1)) + 1;

				boolean flag = true;
				for (Integer i : result) {
					if (num == i.intValue()) {
						flag = false;
						break;
					}
				}
				if (flag) {
					result.add(num);
					count++;
				}
			}
			return result;
		}
	}
}
