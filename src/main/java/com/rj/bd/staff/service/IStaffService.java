package com.rj.bd.staff.service;
/**
 * @desc: 员工的接口
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:06:09
 */

import java.util.List;

import com.rj.bd.staff.eneity.Staff;

public interface IStaffService {
	
	void delete(Integer staffid);
	
	List<Staff> queryAll();
	
	Staff queryOne(String staffid);
	
	void edit(Staff staff);

	void save(Staff staff);
	
	void editJob(Staff staff);
}
