package com.rj.bd.rewardandpunish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rj.bd.rewardandpunish.dao.RewardMapper;
import com.rj.bd.rewardandpunish.eneity.Rewardandpunish;


/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:07:10
 */

@Transactional
@Service("rewardService")
public class RewardServiceImpl implements IRewardService{
	@Autowired
	private RewardMapper rewardMapper;
	@Override
	public void save(Rewardandpunish r) {
		rewardMapper.save(r);
	}
	@Override
	public List<Rewardandpunish> queryreward() {		//查询奖励
		
		return rewardMapper.queryReward();
	}
	@Override
	public List<Rewardandpunish> querypunish() {		//查询惩罚
		
		return rewardMapper.queryPunish();
	}

}
