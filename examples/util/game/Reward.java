package com.homevip.util.game;

/**
 * 
 * 
 *        抽奖奖品
 */

public class Reward {

	/**
	 * 
	 * 奖品编号
	 */

	public int index;

	/**
	 * 
	 * 奖品数量
	 */

	public int amount;

	/**
	 * 
	 * 中奖概率
	 */

	public int succPercent;

	public Reward(int index, int amount, int succPercent) {

		super();

		this.index = index;

		this.amount = amount;

		this.succPercent = succPercent;

	}

	@Override
	public String toString() {

		return "Reward [index=" + index + ", amount=" + amount + ", succPercent="

		+ succPercent + "]";

	}

}
