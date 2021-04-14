package com.rj.bd.staff.service;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:06:09
 */

import java.util.List;

import com.rj.bd.staff.eneity.Staff;

public interface IStaffService {
	void delete(Integer staffid);
	List<Staff> queryAll();
}
