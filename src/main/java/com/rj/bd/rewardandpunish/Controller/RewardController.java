package com.rj.bd.rewardandpunish.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.logs.eneity.Logs;
import com.rj.bd.rewardandpunish.eneity.Rewardandpunish;
import com.rj.bd.rewardandpunish.service.IRewardService;
import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.staff.eneity.Staff;
import com.rj.bd.tool.DateTool;


/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:01:54
 */
@Controller
@RequestMapping("/rewardandpunish")
public class RewardController {
	@Autowired
	public IRewardService rewardService;	
	
	@RequestMapping("/add")	
	public Map<String, Object> add(Rewardandpunish r,Root root,Staff staff)//,int rootid,int staffid
	{
		r.setRptime(DateTool.getNowTimeNum());
		r.setRoot(root);
		r.setStaff(staff);
		rewardService.save(r);
		return null;
	}	
	
	@Autowired
	public IRootService rootService;
	
	@RequestMapping("/queryReward")
	@ResponseBody					//查询奖励
	public List<Rewardandpunish> queryReward(String token){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			List<Rewardandpunish> list = new ArrayList<Rewardandpunish>();
			list.add(new Rewardandpunish());
			return  list;
		}
		
		List<Rewardandpunish> rewardandpunishs = rewardService.queryreward();
		for (Rewardandpunish rewardandpunish : rewardandpunishs) 
		{
			rewardandpunish.setRptime(DateTool.convertTimestamp2Date(rewardandpunish.getRptime()));
		}
		
		return rewardandpunishs;
	}
	
	
	@RequestMapping("/queryPunishs")
	@ResponseBody					//查询惩罚
	public List<Rewardandpunish> queryPubishs(String token){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			List<Rewardandpunish> list = new ArrayList<Rewardandpunish>();
			list.add(new Rewardandpunish());
			return  list;
		}
		
		List<Rewardandpunish> rewardandpunishs = rewardService.querypunish();
		for (Rewardandpunish rewardandpunish : rewardandpunishs) 
		{
			rewardandpunish.setRptime(DateTool.convertTimestamp2Date(rewardandpunish.getRptime()));
		}
		
		return rewardandpunishs;
	}
	
}
