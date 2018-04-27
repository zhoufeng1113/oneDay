package com.homevip.util.game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*

 * 抽奖主类

 */

public class Lottery {

	public static List<Reward> randomList;

	/**
	 * 
	 * 获取中奖编码数组
	 * 
	 * @param rlist
	 * 
	 * @param keyLength
	 * 
	 * @return
	 */

	public List<Reward> getKeys(List<Reward> rlist, int keyLength) {

		List<Reward> list = new ArrayList<Reward>();

		for (int i = 0; i < keyLength; i++) {

			list.add(getKey(rlist));

		}

		return list;

	}

	/**
	 * 
	 * 获取中奖编码
	 * 
	 * @param rlist
	 * 
	 * @return
	 */

	public Reward getKey(List<Reward> rlist) {

		// 随机列表

		List<Reward> randomList = getRandomList(rlist);

		// 根据随机列表得到的概率区段

		List<Integer> percentSteps = getPercentSteps(rlist);

		// 概率区段的最大值

		int maxPercentStep = percentSteps.get(percentSteps.size() - 1);

		// 在概率区段范围内取一个随机数

		int randomStep = new Random().nextInt(maxPercentStep);

		// 中间元素的下标

		int keyIndex = 0;

		int begin = 0;

		int end = 0;

		for (int i = 0; i < percentSteps.size(); i++) {

			if (i == 0) {

				begin = 0;

			} else {

				begin = percentSteps.get(i - 1);

			}

			end = percentSteps.get(i);

			// 判断随机数值是否在当前区段范围内

			if (randomStep > begin && randomStep <= end) {

				keyIndex = i;

				break;

			}

		}

		return randomList.get(keyIndex);

	}

	/**
	 * 
	 * 获取概率区段[如：10,15,25,30,40,60,75,80,90,95,100]
	 * 
	 * @param rlist
	 * 
	 * @return
	 */

	private List<Integer> getPercentSteps(List<Reward> rlist) {

		List<Integer> percentSteps = new ArrayList<Integer>();

		int percent = 0;

		for (Reward r : rlist) {

			percent += r.succPercent;

			percentSteps.add(percent);

		}

		return percentSteps;

	}

	/**
	 * 
	 * 获取随机列表
	 * 
	 * @param rlist
	 * 
	 * @return
	 */

	private List<Reward> getRandomList(List<Reward> rlist) {

		List<Reward> oldList = new ArrayList<Reward>(rlist);

		List<Reward> newList = new ArrayList<Reward>();

		// 随机排序的老序列中元素的下标

		int randomIndex = 0;

		// 随机排序下标的取值范围

		int randomLength = 0;

		for (int i = 0; i < rlist.size(); i++) {

			// 指向下标范围

			randomLength = oldList.size() - 1;

			// 取值范围元素的个数为多个时，从中随机选取一个元素的下标

			if (randomLength != 0) {

				randomIndex = new Random().nextInt(randomLength);

				// 取值范围元素的个数为一个时，直接返回该元素的下标

			} else {

				randomIndex = 0;

			}

			// 在新的序列当中添加元素，同时删除元素取值范围中的randomIndex下标所对应的元素

			newList.add(oldList.remove(randomIndex));

		}

		return newList;

	}

	/*public static boolean isWinner(List<Reward> list) {

		boolean isWinner = false;

		for (int i = 0; i < list.size(); i++) {

			for (int j = i + 1; j < list.size(); j++) {

				if (list.get(i).index != list.get(j).index) {

					return false;

				} else {

					isWinner = true;

				}

			}

		}

		return isWinner;

	}*/

	//根据各奖项等级机率计算
	@Deprecated
	public Integer getRewardByRands(List<Double> randoms){
		Integer result = -1;
		int sum = 0;
		if(null == randoms) return null;
		for(Double i : randoms){
			sum += i;
		}
		//补充不中奖的数据
		randoms.add(100.0 - sum);
		for(int i = 0; i < randoms.size(); i++){
//			double r = new Random().nextDouble() * sum;
			double r = new Random().nextInt(99) + 1;
			if(r < randoms.get(i)){
				//最后一个相当于没有中
				if(i == randoms.size() - 1){
					return -1;
				}else{
					return i;
				}
			}else{
				sum -= randoms.get(i);
			}
		}
		
		return result;
	}

	/**
	 * 抽奖
	 *
	 * @param orignalRates 原始的概率列表，保证顺序和实际物品对应
	 * @return 物品的索引
	 */
	public static int lottery(List<Double> orignalRates) {
		if (orignalRates == null || orignalRates.isEmpty()) {
			return -1;
		}

		int size = orignalRates.size();

		// 计算总概率，这样可以保证不一定总概率是1
		double sumRate = 0d;
		for (double rate : orignalRates) {
			sumRate += rate;
		}

		// 计算每个物品在总概率的基础下的概率情况
		List<Double> sortOrignalRates = new ArrayList<Double>(size);
		Double tempSumRate = 0d;
		for (double rate : orignalRates) {
			tempSumRate += rate;
			sortOrignalRates.add(tempSumRate / sumRate);
		}

		// 根据区块值来获取抽取到的物品索引
		double nextDouble = Math.random();
		sortOrignalRates.add(nextDouble);
		Collections.sort(sortOrignalRates);

		return sortOrignalRates.indexOf(nextDouble);
	}

	public static void main(String[] args) {
		List<Double> r = new ArrayList<Double>();
		r.add(0d);
		r.add(0d);
		r.add(90d);
		r.add(0d);
		r.add(0d);
		r.add(0d);
		r.add(0d);
		r.add(0d);
		r.add(0d);
		r.add(0d);
		r.add(0d);
		r.add(0d);
		System.out.println(new Lottery().lottery(r));
	}

}

