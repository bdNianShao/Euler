package com.rj.bd.rewardandpunish.dao;
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
import com.rj.bd.rewardandpunish.eneity.Rewardandpunish;


@Service("rewardMapper")
public interface RewardMapper extends BaseMapper<Rewardandpunish>{

	@Insert("insert into rewardandpunish (rpid,rptext,rpkind,rptime,rootid,staffid) values (#{rpid},#{rptext},#{rpkind},#{rptime},#{root.rootid},#{staff.staffid})")
	public void save(Rewardandpunish r);
	
	@Select("select * from rewardandpunish where rpkind ='0'")
	@Results({
		@Result(column="rpid",property="rpid"),
		@Result(column="rptext",property="rptext"),
		@Result(column="rpkind",property="rpkind"),
		@Result(column="rptime",property="rptime"),
		@Result(column="rootid",property="root",one=@One(select="com.rj.bd.root.dao.RootMapper.queryOneById2")),
		@Result(column="staffid",property="staff",one=@One(select="com.rj.bd.staff.dao.StaffMapper.queryOneById"))
	})
	public List<Rewardandpunish> queryReward();
	
	@Select("select * from rewardandpunish where rpkind ='1'")
	@Results({
		@Result(column="rpid",property="rpid"),
		@Result(column="rptext",property="rptext"),
		@Result(column="rpkind",property="rpkind"),
		@Result(column="rptime",property="rptime"),
		@Result(column="rootid",property="root",one=@One(select="com.rj.bd.root.dao.RootMapper.queryOneById2")),
		@Result(column="staffid",property="staff",one=@One(select="com.rj.bd.staff.dao.StaffMapper.queryOneById"))
	})
	public List<Rewardandpunish> queryPunish();
}
