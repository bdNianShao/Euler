package com.rj.bd.department.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.department.eneity.Department;
import com.rj.bd.department.service.IDepartService;
import com.rj.bd.root.service.IRootService;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:01:54
 */
@Controller
@RequestMapping("/department")
public class DepartController {
	@Autowired
	public IDepartService departService;
	@Autowired
	public IRootService rootService;
	@RequestMapping("query")
	
	public List<Department> queryAll(String token){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			List<Department> list = new ArrayList<Department>();
			list.add(new Department());
			return  list;
		}
		
		List<Department> departments = departService.queryAll();
		for (Department department : departments) 
		{
			System.out.println(department);
		}
		return departments;
		
	}
	
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer departid)
	{
		System.out.println("delete---->"+departid);
		departService.delete(departid);
		return null;
	}
	
	
	@RequestMapping("add")
	public Map add(Department d){
		
		
		return null;
		
	}
	

}
