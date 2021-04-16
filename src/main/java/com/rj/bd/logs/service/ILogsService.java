package com.rj.bd.logs.service;

import java.util.List;

import com.rj.bd.logs.eneity.Logs;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:06:09
 */
public interface ILogsService {
	List<Logs> queryLogs();
	
	public void addLogs(Logs logs);
	
	public int getLosCount();

	List<Logs> queryPageLogs(int page, int size);
}
