package com.esignlive;

/**
 * This class implement ICommand interface to command pattern
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.0
 */
public class PurchaseCommandImp implements ICommand {

	private Machine mh;

	private Lottery lt;

	public PurchaseCommandImp(Machine mh, Lottery lt) {
		this.mh = mh;
		this.lt = lt;
	}

	@Override
	public void execute() {

		this.mh.purchaseLottery(lt);

	}

}
