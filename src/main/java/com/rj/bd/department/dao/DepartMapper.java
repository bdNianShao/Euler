package com.rj.bd.department.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.department.eneity.Department;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */
@Service("departMapper")
public interface DepartMapper extends BaseMapper<Department>{
	
	@Delete("delete from department where departid=#{departid}")
	public void delete(Integer departid);
	
	@Select("select * from department  where departid=#{departid}")
	Department queryOneById();
}
