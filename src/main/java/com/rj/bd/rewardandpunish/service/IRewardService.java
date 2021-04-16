package com.rj.bd.rewardandpunish.service;

import java.util.List;

import com.rj.bd.rewardandpunish.eneity.Rewardandpunish;

/**
 * @desc: 奖罚的接口
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:06:09
 */
public interface IRewardService {
	void save(Rewardandpunish r);
	List<Rewardandpunish> queryreward();
	List<Rewardandpunish> querypunish();
	
	
}
