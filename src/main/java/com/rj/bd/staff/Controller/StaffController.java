package com.rj.bd.staff.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.logs.eneity.Logs;
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



@RequestMapping("/delete")
public Map<String, Object> delete(String token,Integer staffid){
Map<String, Object> map = new HashMap();

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
	logs.setRoot(root);
	return map;
}

}
