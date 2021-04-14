package com.rj.bd.job.dao;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.job.eneity.Job;


@Service("jobMapper")
public interface JobMapper extends BaseMapper<Job>{
	
	@Select("select * from Job  where jobid=#{jobid}")
	Job queryOneById();
}
