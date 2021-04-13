package com.rj.bd.staff.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.staff.service.IStaffService;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:01:54
 */
@Controller
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	public IStaffService staffService;
	
	
	
	
	
	@RequestMapping("/delete")
	public String delete(Integer staffid)
	{
		staffid=99;
		System.out.println("delete---->"+staffid);
		staffService.delete(staffid);
		return null;
	}

}
