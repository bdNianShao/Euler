package com.rj.bd.staff.eneity;


import com.rj.bd.department.eneity.Department;
import com.rj.bd.job.eneity.Job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc: Staff实体类
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Staff {
	public int staffid;
	public String staffnum;
	public String name;
	public String sex;
	public String edu;
	public String age;
	public String joindate;
	public Job job;
	public Department department;
}
