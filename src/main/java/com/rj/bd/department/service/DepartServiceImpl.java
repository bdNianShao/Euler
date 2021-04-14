package com.rj.bd.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
		departMapper.deleteById(departid);
	}
	
	@Override
	public List<Department> queryAll() {
		return departMapper.selectList(null);
	}
	@Override
	public void save(Department d) {
		departMapper.insert(d);
	}

}
