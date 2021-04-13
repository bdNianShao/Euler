package com.rj.bd.indexPage.service;


/**
* @desc root模块的接口
* 
* @author TianShuo
* 
* @version 2021年4月12日 下午7:04:18
*/
public interface IPageService {
	public int getStaffnum ();
	
	public int getDepartmentnum ();
	
	public int getLogsnum();
	
	public int getRewardnum();
	
	public int getPunishnum();
}
/*[
 {"staffnum":1},
 
 {"departmentnum":1},
 
 {"logsnum":1},
 
 {"rewardnum ":1},
 
 {"punishnum":1}
]
*/