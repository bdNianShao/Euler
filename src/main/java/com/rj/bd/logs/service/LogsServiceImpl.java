package com.rj.bd.logs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rj.bd.logs.dao.LogsMapper;
import com.rj.bd.logs.eneity.Logs;

/**
 * @desc: logs接口的实现类
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:07:10
 */
@Transactional
@Service("logsService")
public class LogsServiceImpl implements ILogsService{
	
	@Autowired
	private LogsMapper logsMapper;

	@Override
	public List<Logs> queryLogs() {
		
		return logsMapper.findAll();
	}

	@Override
	public void addLogs(Logs logs) {
		logsMapper.addLogs(logs);
	}

}
