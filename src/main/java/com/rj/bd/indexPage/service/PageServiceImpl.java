package com.rj.bd.indexPage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rj.bd.department.dao.DepartMapper;
import com.rj.bd.logs.dao.LogsMapper;
import com.rj.bd.rewardandpunish.dao.RewardMapper;
import com.rj.bd.rewardandpunish.eneity.Rewardandpunish;
import com.rj.bd.staff.dao.StaffMapper;


@Transactional
@Service("pageService")
public class PageServiceImpl implements IPageService
{	
	@Autowired 
	public StaffMapper staffMapper;
	
	@Autowired
	public DepartMapper departMapper;
	
	@Autowired
	public LogsMapper logsMapper;
	
	@Autowired
	public RewardMapper rewardMapper;
	
	public int getStaffnum() {
		return staffMapper.selectCount(null);
	}

	public int getDepartmentnum() {
		return departMapper.selectCount(null);
	}

	public int getLogsnum() {
		return logsMapper.selectCount(null);
	}
	
	public int getRewardnum(){
		
		QueryWrapper<Rewardandpunish> queryWrapper =  new QueryWrapper<Rewardandpunish>();
		queryWrapper.eq(true, "rpkind", 0);
		return rewardMapper.selectCount(queryWrapper);
	}
	public int getPunishnum() {
		QueryWrapper<Rewardandpunish> queryWrapper =  new QueryWrapper<Rewardandpunish>();
		queryWrapper.eq(true, "rpkind", 1);
		return rewardMapper.selectCount(queryWrapper);
	}


}
