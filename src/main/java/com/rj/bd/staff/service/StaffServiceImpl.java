package com.rj.bd.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rj.bd.staff.dao.StaffMapper;
import com.rj.bd.staff.eneity.Staff;

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
	@Override
	public List<Staff> queryAll() {
		return staffMapper.findAll();
	}

}
