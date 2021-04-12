package com.rj.bd.root.controller;



import java.util.List;

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
public class RootController {
	 @Autowired
	    private IRootService rootService;
	    
	    @RequestMapping("/query")
	    @ResponseBody
		public List<Root> queryRoot()
	    {
	    	List<Root> roots = rootService.queryAll();
	    	for (Root root : roots) 
	    	{
				System.out.println(root);
			}
			return roots;
			
		}

}
