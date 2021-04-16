package com.rj.bd.department.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import com.rj.bd.tool.MD5Utils;
import com.rj.bd.tool.qiniu.PutFile;

/**
 * @desc: department的C层
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
	@Autowired
	
	/**
	 * 查询
	 * @param token
	 * @return
	 */
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
	
	/**
	 * 删除部门
	 * @param token
	 * @param departid
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String token,Integer departid)
	{
		Map<String , Object> map = new HashMap<String, Object>();
		if ( ! rootService.rootBytoken(token)) 
		
	{
			map.put("msc", -1);
			map.put("text", "未登录，删除失败");
		return  map;
	}
		System.out.println("delete---->"+departid);
		departService.delete(departid);
		Logs logs = new Logs();
		logs.setLogtime(DateTool.getNowTimeNum());//开始添加日志
		String text = "删除了一个部门";
		logs.setLogtext(text);
		Root root = rootService.queryRootBytoken(token);
		logs.setRoot(root);
		logsService.addLogs(logs);
		map.put("msc", 200);
		map.put("text", "删除成功");
		return map;
	}
	
	/**
	 * 添加部门
	 * @param token
	 * @param logs
	 * @param department
	 * @return
	 */
	@RequestMapping("addDepartment")
	
	@ResponseBody
	public Map<String, Object> add( String token,Logs logs,Department department)//,int rootid,int staffid
	{
		Map<String , Object> map = new HashMap<String, Object>();
		if ( ! rootService.rootBytoken(token)) 
		
	{
			map.put("msc", -1);
			map.put("text", "未登录，添加失败");
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
	
	
	@RequestMapping("addDepartimg")
	@ResponseBody
	
	public Map<String, Object> addImg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> map = new HashMap<String,Object>();
		FileItem imageItem = null;
		String imagePrifix = "";
		String departname=null;
		String departtext=null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 使用ServletFileUpload解析器上传数据，解析结果返回一个List<FileItem>集合，每一个FileItem对应一个Form表单
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设定中文处理
		upload.setHeaderEncoding("utf-8");
		List<FileItem> formItemList = null;
		try 
		{
			formItemList = upload.parseRequest(request);
		}
		catch (FileUploadException e) 
		{
			
		}

		if ((formItemList != null) || (formItemList.size() > 0)) 
		{
			for (FileItem Item : formItemList) 
			{
				if (!Item.isFormField()) {
					// 如果不是表单（筛选出文件）
					// 获取文件名字
					String fileName = Item.getName();
					System.out.println("上传文件的名字:" + fileName);
					// 获取后缀
					String prifix = fileName.substring(fileName.lastIndexOf(".") + 1);
					// 后缀全部转小写 防止后缀大小写不统一
					prifix = prifix.toLowerCase();
					//System.out.println("上传文件的后缀:" + prifix);
					if (prifix.equals("png") || prifix.equals("jpg") || prifix.equals("bmp") || prifix.equals("tif")
							|| prifix.equals("gif") || prifix.equals("jpeg")) 
					{
						// 仅支持这几种格式的数据
						imageItem =Item;
						imagePrifix = prifix;
						
					}
					else
					{
						map.put("msc", -1);
						map.put("text", "添加失败");
						return map;
					}
				}
		}
		
			String departimg = PutFile.Putimgs(imageItem.getInputStream(), imagePrifix);
			System.out.println(departimg);

			
			Department department = new Department();
			department.setDepartname(departname);
			department.setDeparttext(departtext);
			department.setDepartimg(departimg);
			departService.save(department);
		 map.put("msc", 200);
		 map.put("text", "添加成功");
		return map;
		}
		return map;
		
		
		
		
	}
	
	
	
	/**
	 * 查询单条
	 * @param token
	 * @param departid
	 * @return
	 */
	@RequestMapping("queryById")
	
	@ResponseBody
	public List<Department> queryById(String token,Integer departid){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			List<Department> list = new ArrayList<Department>();
			list.add(new Department());
			return  list;
		}
		
		List<Department> departments = departService.queryById(departid);
		return departments;
		
	}
	
	/**
	 * 修改部门
	 * @param token
	 * @param logs
	 * @param department
	 * @return
	 */
@RequestMapping("update")
	
	@ResponseBody
	public Map<String, Object> updateDepart( String token,Logs logs,Department department)//,int rootid,int staffid
	{
		Map<String , Object> map = new HashMap<String, Object>();
		if ( ! rootService.rootBytoken(token)) 
		
	{
			map.put("msc", -1);
			map.put("text", "未登录，修改失败");
		return  map;
	}
		departService.update(department);
		
		logs.setLogtime(DateTool.getNowTimeNum());//开始添加日志
		String text = "修改"+department.getDepartid()+"部门";
		logs.setLogtext(text);
		Root root = rootService.queryRootBytoken(token);
		logs.setRoot(root);
		logsService.addLogs(logs);
		map.put("msc", 200);
		map.put("text", "修改成功");
		return map;
	}	
/**
 * 查询部门模块的数量
 * @param token
 * @return
 */
@RequestMapping("queryNum")
@ResponseBody
public List<Map<String, Object>> queryNum(String token){
	List<Map<String, Object>> list = new ArrayList<>();
	if ( ! rootService.rootBytoken(token)) 
	{
		Map<String, Object> map = new HashMap<>();
		map.put("msc", -1);
		map.put("text", "未登录");
		list.add(map);
		return  list;
	}
	list = departService.queryNum();
	System.out.println(list);
	return list;
	
}


}
