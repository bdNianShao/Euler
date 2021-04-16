package com.rj.bd.staff.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.rj.bd.tool.ExcelTool;


/**
 * @desc: 员工C层
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
	 * 修改员工基本信息
	 * @param token
	 * @param staff
	 * @return
	 */
	@RequestMapping("/editStaff")
	@ResponseBody
	public Map<String, Object> editStaff(String token,Staff staff)
	{
		Map<String, Object> map = new HashMap<String, Object>();
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
	 * 修改员工职位调动
	 * @param token
	 * @param staff
	 * @return
	 */
	@RequestMapping("/editStaffJob")
	@ResponseBody
	public Map<String, Object> editStaffJob(String token,Staff staff,Job job,Department department)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if ( ! rootService.rootBytoken(token)) 
			
		{
			map.put("msc", -1);
			map.put("text", "未登录");
			return map;
		}
		staff.setJoindate(DateTool.getNowTimeNum());
		staff.setJob(job);
		staff.setDepartment(department);
		staffService.editJob(staff);
		map.put("msc", 200);
		map.put("text", "修改成功");
		
		Logs logs = new Logs();
		logs.setLogtext("修改"+staff.getStaffid()+"员工的职位信息");
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
	
	@RequestMapping("download")
	public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		List<Staff> list = staffService.queryAll();
		String[] names = {"工号","姓名","职务","性别","年龄","学历","入职时间","部门名称"}; 
		String fileName ="员工信息表";
		XSSFWorkbook excelbook = ExcelTool.createWorkbook(fileName,names,list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			excelbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try 
		{
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	
	}
	

}
