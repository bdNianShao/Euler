package com.rj.bd.logs.dao;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.logs.eneity.Logs;
@Service("logsMapper")
public interface LogsMapper extends BaseMapper<Logs>{

	@Insert("INSERT INTO `personnel`.`logs`(`logid`, `logtime`, `logtext`, `rootid`) VALUES (0, #{logtime},#{logtext},#{root.rootid});")
	public void add(Logs logs);

	@Select("SELECT * FROM logs")
	@Results({
		@Result(column="logid",property="logid"),
		@Result(column="logtime",property="logtime"),
		@Result(column="logtext",property="logtext"),
		@Result(column="joindate",property="joindate"),
		@Result(column="rootid",property="root",one=@One(select="com.rj.bd.root.dao.RootMapper.queryOneById")),
	})
	public List<Logs> findAll();
	
	
	@Insert("insert into logs (logid,logtime,logtext,rootid) values (0,#{logtime},#{logtext},#{root.rootid})")
	public void addLogs(Logs logs);
}
