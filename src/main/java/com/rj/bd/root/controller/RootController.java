package com.rj.bd.root.controller;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
public Map<String, Object> addImg(@RequestParam(value = "file", required = false)MultipartFile[] files,
	String rootname,String rootpassword,String temp	) throws IllegalStateException, IOException {
	
	Map<String, Object> map = new HashMap<>();
	String rootavatar = null;
	for (MultipartFile file : files) 
	{
		//文件名称
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		//文件后缀
		String fileType = fileName.substring(fileName.indexOf("."), fileName.length());
		// 文件InputStream
		InputStream fileInputStream  = file.getInputStream();
		rootavatar = PutFile.Putimgs(fileInputStream, fileType);
	}
			
	if (rootavatar != null) 
	{
		String token =MD5Utils.stringToMD5( rootpassword + rootname);
		Root root = new Root();
		root.setRootname(rootname);
		root.setRootpassword(MD5Utils.stringToMD5(rootpassword));
		root.setToken(token);
		root.setRootavatar(rootavatar);
		root.setTemp(temp);
		rootService.save(root);
		 map.put("msc", 200);
		 map.put("text", "添加成功");
	}else {
		map.put("msc", -3);
		 map.put("text", "添加失败");
	}
		
		
	
	return map;
	
}	




}
