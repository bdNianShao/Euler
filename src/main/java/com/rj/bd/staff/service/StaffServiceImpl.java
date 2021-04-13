package com.rj.bd.staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rj.bd.staff.dao.StaffMapper;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:07:10
 */
@Transactional
@Service("staffService")
public class StaffServiceImpl implements IStaffService{
	@Autowired
	private StaffMapper staffMapper;
	@Override
	public void delete(Integer staffid) {
		staffMapper.delete(staffid);
	}

}
