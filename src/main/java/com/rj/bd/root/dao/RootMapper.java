package com.rj.bd.root.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.root.entity.Root;

/**
* @desc 管理员模块的持久层
* 
* @author TianShuo
* 
* @version 2021年4月12日 下午7:09:39
*/
public interface RootMapper extends BaseMapper<Root>
{
	@Delete("delete from root where rootid=#{rootid}")
	public void delete(int rootid);												//删除
	
	@Select("select temp from root  where rootid=#{rootid}")
	Root queryOneById();														//查询单条
	
	@Select("select * from root  where rootid=#{rootid}")
	Root queryOneById2();
}
