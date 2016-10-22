package com.esignlive;
/**
 * This class implement ICommand interface to command pattern
 * 
 * @author Xiao Su (fuzii@live.com)
 * @version 1.0
 */
public class DrawCommandImp implements ICommand {

	private Machine mh;

	public DrawCommandImp(Machine mh) {
		this.mh = mh;
	}

	@Override
	public void execute() {
		this.mh.drawBalls();
	}

}
