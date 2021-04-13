package com.rj.bd.root.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;


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
private IRootService rootService;

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


}
