package com.rj.bd.indexPage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.indexPage.service.IPageService;
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
	    	
	    List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
	    //
	    int staffnum = pageService.getStaffnum();
	    int departmentnum = pageService.getDepartmentnum();
	    int logsnum = pageService.getLogsnum();
	    int rewardnum = pageService.getRewardnum();
	    int punishnum = pageService.getPunishnum();
	    
	    
	    
	    HashMap<String, Integer> staffMap = new HashMap<String, Integer>();
	    staffMap.put("staffnum", staffnum);
	    staffMap.put("departmentnum", departmentnum);
	    staffMap.put("logsnum", logsnum);
	    staffMap.put("rewardnum", rewardnum);
	    staffMap.put("punishnum", punishnum);
	    
	    list.add(staffMap);
	    
	    return list;
			
		}

}
