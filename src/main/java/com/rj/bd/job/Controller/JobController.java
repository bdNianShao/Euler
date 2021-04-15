package com.rj.bd.job.Controller;

import java.util.ArrayList;
import java.util.List;

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
}
