package com.rj.bd.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rj.bd.root.dao.RootMapper;
import com.rj.bd.root.entity.Root;

/**
* @desc 
* 
* @author TianShuo
* 
* @version 2021年4月12日 下午7:11:16
*/

@Transactional
@Service("rootService")
public class RootServiceImpl implements IRootService
{
	@Autowired
	private RootMapper rootMapper;

	public List<Root> queryAll() 
	{
		return rootMapper.selectList(null);
	}
}
