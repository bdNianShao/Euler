package com.rj.bd.logs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.logs.service.ILogsService;

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
}
