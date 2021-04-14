package com.rj.bd.logs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rj.bd.department.dao.DepartMapper;
import com.rj.bd.logs.dao.LogsMapper;
import com.rj.bd.logs.eneity.Logs;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:07:10
 */
@Transactional
@Service("logsService")
public class LogsServiceImpl implements ILogsService{
	
	@Autowired
	private LogsMapper logsMapper;
	@Override
	public void addlogs(Logs logs) {
		logsMapper.add(logs);
		
	}
	@Override
	public List<Logs> queryLogs() {
		
		return logsMapper.findAll();
	}

}
