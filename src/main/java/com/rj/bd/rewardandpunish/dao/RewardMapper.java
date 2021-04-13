package com.rj.bd.rewardandpunish.dao;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:07
 */

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.rewardandpunish.eneity.Rewardandpunish;


@Service("rewardMapper")
public interface RewardMapper extends BaseMapper<Rewardandpunish>{

	@Insert("insert into rewardandpunish (rpid,rptext,rpkind,rptime,rootid,staffid) values (#{rpid},#{rptext},#{rpkind},#{rptime},#{root.rootid},#{staff.staffid})")
	public void save(Rewardandpunish r);
}
