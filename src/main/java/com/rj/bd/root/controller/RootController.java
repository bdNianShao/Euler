package com.rj.bd.root.controller;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletContext;
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
import org.springframework.web.context.ContextLoader;

import com.rj.bd.logs.eneity.Logs;
import com.rj.bd.logs.service.ILogsService;
import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.tool.DateTool;
import com.rj.bd.tool.MD5Utils;
import com.rj.bd.tool.qiniu.PutFile;



/**
* @desc 前端控制器
* 
* @author TianShuo
* 
* @version 2021年4月12日 上午10:46:53
*/
@Controller
@RequestMapping("/root")
public class RootController 
{
	
 @Autowired
public IRootService rootService;
 @Autowired
 public ILogsService logsService;
 
 
@RequestMapping("/query")
@ResponseBody
public List<Root> queryRoot(String token)
{
	if ( ! rootService.rootBytoken(token)) 
		
	{
		List<Root> list = new ArrayList<Root>();
		list.add(new Root());
		return  list;
	}

	
	
	
	List<Root> roots = rootService.queryAll();
	for (Root root : roots) 
	{
		System.out.println(root);
	}
	return roots;
	
}

/**
 * @desc 登录方法
 * @param rootnum  账号
 * @param password  密码（md5加密后的的数据）
 * @return
 */
@RequestMapping("/login")
@ResponseBody
public Map<String, Object> login(String rootnum,String password)
{
	Root root = rootService.rootByRootName(rootnum);
	Map<String , Object> map = new HashMap<String, Object>();
	//判读是否为空
	if ( root==null ) 
	{
		map.put("msc", -1);
		map.put("text", "没有此账号");
	}
	else //不为再进行数据比对
	{
		boolean loginState = root.getRootpassword().equals(password);
		if (loginState) 
		{
			map.put("msc", 200);
			map.put("text", "登录成功");
			map.put("token", root.getToken());
		}
		else 
		{
			map.put("msc", -1);
			map.put("text", "密码错误");
		}
	}
	
	return map;
}
@RequestMapping("/delete")
@ResponseBody
public Map<String, Object> delete(int rootid,String token){
	
	Map<String , Object> map = new HashMap<String, Object>();
	
if ( ! rootService.rootBytoken(token)) 
		
	{
		map.put("msc", -1);
		map.put("text", "删除失败");
		return  map;
	}
	rootService.delete(rootid);
	Logs logs=new Logs();
	logs.setLogtime(DateTool.getNowTimeNum());
	String text = "删除了一个管理员："+rootid;
	logs.setLogtext(text);
	
	Root root = rootService.queryRootBytoken(token);
	logs.setRoot(root);
	logsService.addLogs(logs);
			map.put("msc", 200);
			map.put("text", "删除成功");
	return map;
}


@RequestMapping("/reset")
@ResponseBody
public Map<String, Object> reset(int rootid,String token){
	
	Map<String , Object> map = new HashMap<String, Object>();
	
if ( ! rootService.rootBytoken(token)) 
		
	{
		map.put("msc", -1);
		map.put("text", "重置失败");
		return  map;
	}
		rootService.reset(rootid);
		Logs logs=new Logs();
		logs.setLogtime(DateTool.getNowTimeNum());
		String text = "重置了一个管理员："+rootid;
		logs.setLogtext(text);
		
		Root root = rootService.queryRootBytoken(token);
		logs.setRoot(root);
		logsService.addLogs(logs);		
	
			map.put("msc", 200);
			map.put("text", "重置成功");
	return map;
	
	
}
@RequestMapping("addRoot")

@ResponseBody
public Map<String, Object> add( Root root)//,int rootid,int staffid
{
	String rootToken = root.getToken();
	Map<String , Object> map = new HashMap<String, Object>();
	if ( ! rootService.rootBytoken(rootToken)) 
	
	{
		map.put("msc", -1);
		map.put("text", "添加失败");
	return  map;
	}	
	
	root.setToken(MD5Utils.stringToMD5(root.getRootpassword()+root.getRootname()));
	System.out.println(root);
	rootService.save(root);
	Logs logs=new Logs();
	logs.setLogtime(DateTool.getNowTimeNum());
	String text = "添加了一个管理员："+root.getRootname();
	logs.setLogtext(text);
	
	 root = rootService.queryRootBytoken(rootToken);
	logs.setRoot(root);
	map.put("msc", 200);
	map.put("text", "添加成功");
	return map;
}	

@RequestMapping("addImg")
@ResponseBody
public Map<String, Object> addImg(HttpServletRequest request, HttpServletResponse response) throws Exception
{
	Map<String, Object> map = new HashMap<String, Object>();
	FileItem imageItem = null;
	String imagePrifix = "";//图片后缀
	String rootname = null;
	String rootpassword = null;
	String temp = null;
	// 将请求消息实体中每一个项目封装成单独的DiskFileItem(FileItem接口的实现)对象的任务
	// 将本次请求的request封装成DiskFileItemFactory对象
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
			
			//其它参数
			else 
			{
				if (Item.getFieldName().equals("rootname")) 
				{
					 rootname = Item.getString("utf-8");
				}
				else if (Item.getFieldName().equals("rootpassword")) 
				{
					 rootpassword = Item.getString("utf-8");
				}
				else if (Item.getFieldName().equals("temp")) 
				{
					temp = Item.getString("utf-8");
				}
				
			}
			
			
		}
	
	}
		String rootavatar = PutFile.Putimgs(imageItem.getInputStream(), imagePrifix);
		System.out.println(rootavatar);
		String token =MD5Utils.stringToMD5( rootpassword + rootname);
		
		Root root = new Root();
		root.setRootname(rootname);
		root.setRootpassword(rootpassword);
		root.setToken(token);
		root.setRootavatar(rootavatar);
		root.setTemp(temp);
		rootService.save(root);
		
	 map.put("msc", 200);
	 map.put("text", "添加成功");
	return map;
	
}	




}
