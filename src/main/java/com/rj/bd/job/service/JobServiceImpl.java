package com.rj.bd.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rj.bd.job.dao.JobMapper;
import com.rj.bd.job.eneity.Job;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:07:10
 */

@Transactional
@Service("jobService")
public class JobServiceImpl implements IJobService{
	@Autowired
	private JobMapper jobMapper;
	@Override
	public List<Job> queryJobs() {
		return jobMapper.selectList(null);
	}

}
