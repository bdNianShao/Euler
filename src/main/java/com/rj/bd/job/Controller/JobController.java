package com.rj.bd.job.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.job.eneity.Job;
import com.rj.bd.job.service.IJobService;
import com.rj.bd.root.service.IRootService;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:01:54
 */

@Controller
@RequestMapping("/job")
public class JobController {
	@Autowired
	public IJobService jobService;
	@Autowired
	public IRootService rootService;
	
	@RequestMapping("/queryJob")
	@ResponseBody
	public List<Job> queryJob(String token){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			List<Job> list = new ArrayList<Job>();
			list.add(new Job());
			return  list;
		}
	
	List <Job> jobs = jobService.queryJobs();
	for (Job job : jobs) {
		System.out.println(job.getJobid()+"----------------------<");
	}
	return jobs;
		
		
		
	}
	
	
	@RequestMapping("queryNum")
	@ResponseBody
	public List<Map<String, Object>> queryNum(String token){
		List<Map<String, Object>> list = new ArrayList<>();
		if ( ! rootService.rootBytoken(token)) 
		{
			Map<String, Object> map = new HashMap<>();
			map.put("msc", -1);
			map.put("text", "未登录");
			list.add(map);
			return  list;
		}
		list = jobService.queryNum();
		System.out.println(list);
		return list;
		
	}

}
