package com.rj.bd.department.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.department.eneity.Department;
import com.rj.bd.department.service.IDepartService;
import com.rj.bd.logs.eneity.Logs;
import com.rj.bd.logs.service.ILogsService;
import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.tool.DateTool;

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
	@Autowired
	public ILogsService logsService;
	
	
	@RequestMapping("query")
	@ResponseBody
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
	
	
	@RequestMapping("addDepartment")
	
	@ResponseBody
	public Map<String, Object> add( String token,Logs logs,Department department)//,int rootid,int staffid
	{
		Map<String , Object> map = new HashMap<String, Object>();
		if ( ! rootService.rootBytoken(token)) 
		
	{
			map.put("msc", -1);
			map.put("text", "添加失败");
		return  map;
	}
		departService.save(department);
		
		logs.setLogtime(DateTool.getNowTimeNum());//开始添加日志
		String text = "添加了一个部门";
		logs.setLogtext(text);
		Root root = rootService.queryRootBytoken(token);
		logs.setRoot(root);
		logsService.addLogs(logs);
		map.put("msc", 200);
		map.put("text", "添加成功");
		return map;
	}	
	

}
