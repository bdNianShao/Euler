package com.rj.bd.staff.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.department.eneity.Department;
import com.rj.bd.job.eneity.Job;
import com.rj.bd.logs.eneity.Logs;
import com.rj.bd.logs.service.ILogsService;
import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.staff.eneity.Staff;
import com.rj.bd.staff.service.IStaffService;
import com.rj.bd.tool.DateTool;

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
	 @Autowired
	 private IRootService rootService;
	 @Autowired
	 private ILogsService logsService;
	/**
	 * 查询员工
	 * @param token
	 * @return
	 */
@RequestMapping("/query")
@ResponseBody

	public List<Staff> queryRoot(String token){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			List<Staff> list = new ArrayList<Staff>();
			list.add(new Staff());
			return  list;
		}
		
		List<Staff> staffs = staffService.queryAll();
		
	
		for (Staff staff : staffs) 
		{
			staff.setJoindate(DateTool.convertTimestamp2Date(staff.getJoindate()));
		}
		
		return staffs;
	}


	/**
	 * 删除员工
	 * @param token
	 * @param staffid
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String token,Integer staffid){
	Map<String, Object> map = new HashMap<String, Object>();
	
		if ( ! rootService.rootBytoken(token)) 
			
		{
			map.put("msc", -2);
			map.put("text", "登录失效");
			return  map;
		}
		staffService.delete(staffid);
		map.put("msc", 200);
		map.put("text", "删除成功");
		
		Logs logs = new Logs();
		logs.setLogtext("删除"+staffid+"员工");
		logs.setLogtime(DateTool.getNowTimeNum());
		Root root = rootService.queryRootBytoken(token);
		System.out.println("root"+root);
		logs.setRoot(root);
		logsService.addLogs(logs);
		return map;
	}
	/**
	 * 查询单条员工
	 * @param token
	 * @param staffid
	 * @return
	 */
	@RequestMapping("/queryOne")
	@ResponseBody
	public Staff queryOneRoot(String token,String staffid){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			return  new Staff();
		}
		
		Staff staff = staffService.queryOne(staffid);
		return staff;
	}

	/**
	 * 修改员工
	 * @param token
	 * @param staff
	 * @return
	 */
	@RequestMapping("/editStaff")
	@ResponseBody
	public Map<String, Object> editStaff(String token,Staff staff)
	{
		Map<String, Object> map = new HashMap<>();
		if ( ! rootService.rootBytoken(token)) 
			
		{
			map.put("msc", -1);
			map.put("text", "未登录");
			return map;
		}
		staffService.edit(staff);
		map.put("msc", 200);
		map.put("text", "修改成功");
		
		Logs logs = new Logs();
		logs.setLogtext("修改"+staff.getStaffid()+"员工的个人基本信息");
		logs.setLogtime(DateTool.getNowTimeNum());
		Root root = rootService.queryRootBytoken(token);
		logs.setRoot(root);
		logsService.addLogs(logs);
		
		return map;
		
	}
	/**
	 * 添加员工
	 * @param token
	 * @param staff
	 * @param job
	 * @param department
	 * @return
	 */
	@RequestMapping("addStaff")
	@ResponseBody
	public Map<String, Object> add(String token,Staff staff,Job job,Department department){
		Map<String, Object> map = new HashMap<String,Object>();
		if ( ! rootService.rootBytoken(token)) 
			
		{
				map.put("msc", -1);
				map.put("text", "添加失败");
			return  map;
		}
		staff.setJoindate(DateTool.getNowTimeNum());
		staff.setJob(job);
		staff.setDepartment(department);
		staffService.save(staff);
		
		
		Logs logs = new Logs();//开始添加日志
		
		logs.setLogtime(DateTool.getNowTimeNum());
		String text = "添加了一个员工";
		
		Root root = rootService.queryRootBytoken(token);
		logs.setRoot(root);
		logs.setLogtext(text);
		logsService.addLogs(logs);
		
		map.put("msc", 200);
		map.put("text", "添加成功");
			return  map;
		
	}

}
