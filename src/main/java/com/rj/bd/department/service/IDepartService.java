package com.rj.bd.department.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.department.eneity.Department;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:06:09
 */


public interface IDepartService {
	
	void delete(Integer departid);

	List<Department> queryAll();
	
	void save(Department d);

	List<Department> queryById(Integer departid);

	void update(Department department);
	
	List<Map<String, Object>> queryNum();

}
