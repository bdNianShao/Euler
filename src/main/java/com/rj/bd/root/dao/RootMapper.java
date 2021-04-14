package com.rj.bd.root.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.root.entity.Root;

/**
* @desc 
* 
* @author TianShuo
* 
* @version 2021年4月12日 下午7:09:39
*/
@Service("rootMapper")
public interface RootMapper extends BaseMapper<Root>
{
	@Delete("delete from root where rootid=#{rootid}")
	public void delete(int rootid);
	
	@Select("select temp from root  where rootid=#{rootid}")
	Root queryOneById();
}
