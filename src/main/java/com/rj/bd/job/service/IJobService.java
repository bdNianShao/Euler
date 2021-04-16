package com.rj.bd.job.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.job.eneity.Job;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:06:09
 */
public interface IJobService {
	/**
	 * 查询所有职位信息
	 * @return
	 */
	List<Job> queryJobs();
	/**
	 * 查询每个职位对应的人数
	 * @return
	 */
	List<Map<String, Object>> queryNum();
}
