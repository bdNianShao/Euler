package com.rj.bd.department.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	public List<Department> queryOneById(Integer departid);

	
	@Update("UPDATE department SET departname = #{departname}, departtext = #{departtext} WHERE departid = #{departid}")
	public void update(Department department);

	@Select("SELECT\n" +
			"	d.departname,\n" +
			"	COUNT(s.departid) as num \n" +
			"FROM\n" +
			"	staff as s\n" +
			"LEFT JOIN department as d on (s.departid=d.departid)\n" +
			"GROUP BY\n" +
			"	s.departid")
	List<Map<String, Object>> queryNum();
}
