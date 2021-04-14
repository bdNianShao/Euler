package com.rj.bd.rewardandpunish.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.logs.eneity.Logs;
import com.rj.bd.logs.service.ILogsService;
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
	@Autowired
	public IRootService rootService;
	 @Autowired
	 private ILogsService logService;
	
	 
	 /**
	  * 添加
	  * @param token
	  * @param r
	  * @param root
	  * @param staff
	  * @return
	  */
	@RequestMapping("/addRewardPunish")	
	@ResponseBody
	public Map<String, Object> add( String token,Rewardandpunish r,Root root,Staff staff,Logs logs)//,int rootid,int staffid
	{
		Map<String , Object> map = new HashMap<String, Object>();
		if ( ! rootService.rootBytoken(token)) 
		
	{
			map.put("msc", -1);
			map.put("text", "添加失败");
		return  map;
	}
		r.setRptime(DateTool.getNowTimeNum());
		r.setRoot(root);
		r.setStaff(staff);
		rewardService.save(r);
		
		logs.setLogtime(DateTool.getNowTimeNum());//开始添加日志
		String text = "添加了一个奖励或者惩罚内容";
		logs.setLogtext(text);
		root = rootService.queryRootBytoken(token);
		logs.setRoot(root);
		logService.addLogs(logs);
		map.put("msc", 200);
		map.put("text", "添加成功");
		return map;
	}	
	

	/**
	 * 查询奖励
	 * @param token
	 * @return
	 */
	@RequestMapping("/queryReward")
	@ResponseBody					
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
	
	/**
	 * 查询惩罚
	 * @param token
	 * @return
	 */
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
