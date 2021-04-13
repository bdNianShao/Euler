package com.rj.bd.department.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.department.service.IDepartService;

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
	
	
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer departid)
	{
		System.out.println("delete---->"+departid);
		departService.delete(departid);
		return null;
	}

}
