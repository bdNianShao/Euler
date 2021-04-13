package com.rj.bd.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.department.dao.DepartMapper;

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

}
