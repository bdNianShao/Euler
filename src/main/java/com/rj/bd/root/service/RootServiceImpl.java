package com.rj.bd.root.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.rj.bd.root.dao.RootMapper;
import com.rj.bd.root.entity.Root;

/**
* @desc root模块接口的实现类
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
	
	public Root rootByRootName(String rootname){
		LambdaQueryWrapper<Root> lambdaQueryWrapper=new LambdaQueryWrapper<Root>();
		lambdaQueryWrapper.eq(true, Root::getRootname, rootname);
		return rootMapper.selectOne(lambdaQueryWrapper);
	}
	public boolean rootBytoken(String token){
		LambdaQueryWrapper<Root> lambdaQueryWrapper=new LambdaQueryWrapper<Root>();
		lambdaQueryWrapper.eq(true, Root::getToken, token);
		if ( rootMapper.selectOne(lambdaQueryWrapper) != null) 
		{
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Root queryRootBytoken(String token) {
		LambdaQueryWrapper<Root> lambdaQueryWrapper=new LambdaQueryWrapper<Root>();
		lambdaQueryWrapper.eq(true, Root::getToken, token);
		return  rootMapper.selectOne(lambdaQueryWrapper);
	}

	@Override
	public void delete(int rootid) {
		rootMapper.delete(rootid);
		
	}

	@Override
	public void reset(int rootid) {
		LambdaUpdateWrapper<Root> lambdaUpdateWrapper = new LambdaUpdateWrapper<Root>();
		lambdaUpdateWrapper.eq(true, Root::getRootid, rootid);
		Root entity = new Root();
		entity.setRootpassword("e10adc3949ba59abbe56e057f20f883e");
		entity.setToken("e10adc3949ba59abbe56e057f20f883e"+entity.getRootname());
		rootMapper.update(entity, lambdaUpdateWrapper);
	}

	@Override
	public void save(Root root) {
		rootMapper.insert(root);
	}
	
}
