package com.rj.bd.staff.dao;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.staff.eneity.Staff;


@Service("staffMapper")
public interface StaffMapper extends BaseMapper<Staff>{
	@Delete("delete from staff where staffid=#{staffid}")
	public void delete(Integer staffid);
}
