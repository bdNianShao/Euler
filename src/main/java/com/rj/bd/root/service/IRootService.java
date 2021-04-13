package com.rj.bd.root.service;

import java.util.List;

import com.rj.bd.root.entity.Root;

/**
* @desc root模块的接口
* 
* @author TianShuo
* 
* @version 2021年4月12日 下午7:04:18
*/
public interface IRootService {

	List<Root> queryAll();

	 Root rootByRootName(String rootname);
	 
	 public boolean rootBytoken(String token);
	 
	 public Root queryRootBytoken(String token);
}
