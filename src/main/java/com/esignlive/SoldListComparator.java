package com.esignlive;

import java.util.Comparator;
/**
 * Implement Comparator interface to descending order  lottery object
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.0
 */
public class SoldListComparator implements Comparator<Object> {
	/**
	 * compare two lottery ball number
	 * @param Lottery1
	 * @param Lottery2
	 * @return 1,-1,0
	 */
	@Override
	public int compare(Object o1, Object o2) {
		Lottery l1 = (Lottery) o1;
		Lottery l2 = (Lottery) o2;
		if (l1.getBallNum() < l2.getBallNum())
			return 1;
		else if (l1.getBallNum() > l2.getBallNum())
			return -1;
		else
			return 0;
	}
}
