package com.rj.bd.logs.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.job.service.IJobService;
import com.rj.bd.logs.eneity.Logs;
import com.rj.bd.logs.service.ILogsService;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.staff.eneity.Staff;
import com.rj.bd.tool.DateTool;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:01:54
 */

@Controller
@RequestMapping("/logs")
public class LogsController {
	@Autowired
	public ILogsService logsService;
	
	@Autowired
	public IRootService rootService;
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Logs> queryRoot(String token){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			List<Logs> list = new ArrayList<Logs>();
			list.add(new Logs());
			return  list;
		}
		
		List<Logs> logs = logsService.queryLogs();
		

		for (Logs log : logs) 
		{
			log.setLogtime(DateTool.convertTimestamp2Date(log.getLogtime()));
		}
		
		return logs;
	}
}
