package com.rj.bd.staff.dao;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.staff.eneity.Staff;


@Service("staffMapper")
public interface StaffMapper extends BaseMapper<Staff>{
	@Delete("delete from staff where staffid=#{staffid}")
	public void delete(Integer staffid);
	
	@Select("SELECT* FROM staff")
	@Results({
		@Result(column="staffid",property="staffid"),
		@Result(column="staffnum",property="staffnum"),
		@Result(column="name",property="name"),
		@Result(column="sex",property="sex"),
		@Result(column="edu",property="edu"),
		@Result(column="age",property="age"),
		@Result(column="joindate",property="joindate"),
		@Result(column="jobid",property="job",one=@One(select="com.rj.bd.job.dao.JobMapper.queryOneById")),
		@Result(column="departid",property="department",one=@One(select="com.rj.bd.department.dao.DepartMapper.queryOneById"))
	})
	public List<Staff> findAll();
}
