package com.rj.bd.logs.dao;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.logs.eneity.Logs;
@Service("logsMapper")
public interface LogsMapper extends BaseMapper<Logs>{
	
}
