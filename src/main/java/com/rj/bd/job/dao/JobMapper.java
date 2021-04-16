package com.rj.bd.job.dao;
/**
 * @desc: job的持久层
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.job.eneity.Job;


@Service("jobMapper")
public interface JobMapper extends BaseMapper<Job>{
	
	@Select("select * from Job  where jobid=#{jobid}")
	Job queryOneById();
	
	@Select("SELECT\n" +
			"	j.jobname,\n" +
			"	COUNT(s.jobid)  as num \n" +
			"FROM\n" +
			"	staff as s\n" +
			"LEFT JOIN job as j on (s.jobid=j.jobid)\n" +
			"GROUP BY\n" +
			"	s.jobid")
	List<Map<String, Object>> queryNum();
}
