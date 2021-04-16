package com.rj.bd.indexPage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rj.bd.indexPage.service.IPageService;
import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.tool.Configuration;

/**
* @desc 前端控制器
* 
* @author TianShuo
* 
* @version 2021年4月12日 上午10:46:53
*/
@Controller
@RequestMapping("/page")
public class PageController {
	
	
	
	 @Autowired
	    private IPageService pageService;
	 
	 
	 @Autowired
	 private IRootService rootService;
	    @RequestMapping("/query")
	    @ResponseBody
		public List<?> queryRoot(String token)
		
	    {
	    	
	    	if ( ! rootService.rootBytoken(token)) 
	    		
	    	{
	    		List<?> list = new ArrayList<Object>();
	    		return  list;
	    	}
	    	
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    //
	    int staffnum = pageService.getStaffnum();
	    int departmentnum = pageService.getDepartmentnum();
	    int logsnum = pageService.getLogsnum();
	    int rewardnum = pageService.getRewardnum();
	    int punishnum = pageService.getPunishnum();
	    
	    
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("staffnum", staffnum);
	    map.put("departmentnum", departmentnum);
	    map.put("logsnum", logsnum);
	    map.put("rewardnum", rewardnum);
	    map.put("punishnum", punishnum);
	    map.put("message", Configuration.getConfiguration());
	    list.add(map);
	    return list;
			
		}
	    

	@RequestMapping("/fk")
	public ModelAndView getFk(){
		
		
		ModelAndView mView = new ModelAndView();
		
		mView.addObject("name", "欢迎使用欧拉人事管理系统");
		
		mView.setViewName("test");
		
		return mView;
	}
	}
