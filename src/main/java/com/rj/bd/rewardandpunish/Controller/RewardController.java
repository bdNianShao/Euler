package com.rj.bd.rewardandpunish.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.rewardandpunish.eneity.Rewardandpunish;
import com.rj.bd.rewardandpunish.service.IRewardService;
import com.rj.bd.root.entity.Root;
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
	public String add(Rewardandpunish r,Root root,Staff staff)//,int rootid,int staffid
	{
		r.setRptime(DateTool.getNowTimeNum());
		r.setRoot(root);
		r.setStaff(staff);
	System.out.println("-------add()------》");
System.out.println(r);
	rewardService.save(r);
	return null;
	}	
	
}
