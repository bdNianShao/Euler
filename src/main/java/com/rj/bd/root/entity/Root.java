package com.rj.bd.root.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @desc root实体类
* 
* @author TianShuo
* 
* @version 2021年4月12日 下午6:59:01
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Root {
	public int rootid;
	public String rootavatar;
	public String rootname;
	public String rootpassword;
	public String token;
	public String temp;
}
