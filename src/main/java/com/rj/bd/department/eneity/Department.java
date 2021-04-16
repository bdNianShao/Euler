package com.rj.bd.department.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc: department的实体类
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	public int departid;
	public String departname;
	public String departtext;
	
}
