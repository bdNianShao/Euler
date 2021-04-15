package com.rj.bd.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.department.dao.DepartMapper;
import com.rj.bd.department.eneity.Department;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:07:10
 */

@Service("departService")
public class DepartServiceImpl implements IDepartService{

	@Autowired
	private DepartMapper departMapper;
	public void delete(Integer departid) {
		departMapper.delete(departid);
	}
	
	@Override
	public List<Department> queryAll() {
		return departMapper.selectList(null);
	}
	@Override
	public void save(Department d) {
		departMapper.insert(d);
	}

	@Override
	public List<Department> queryById(Integer departid) {
		
		return departMapper.queryOneById(departid);
	}

	@Override
	public void update(Department department) {
		departMapper.update(department);
		
	}
	

}
