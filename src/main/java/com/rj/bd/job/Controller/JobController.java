package com.rj.bd.job.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.job.service.IJobService;

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
}
