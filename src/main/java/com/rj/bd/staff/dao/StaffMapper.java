package com.rj.bd.staff.dao;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.staff.eneity.Staff;


@Service("staffMapper")
public interface StaffMapper extends BaseMapper<Staff>{
	@Delete("delete from staff where staffid=#{staffid}")
	public void delete(Integer staffid);
	
	
	@Select("SELECT* FROM staff AS s	LEFT JOIN department AS d ON ( s.departid = d.departid )	LEFT JOIN job AS j on(	j.jobid = s.jobid)")
	public List<Staff> findAll();
}
