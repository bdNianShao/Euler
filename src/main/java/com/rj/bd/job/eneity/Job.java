package com.rj.bd.job.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc: Job实体类
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
	public int jobid;
	public String jobname;
	public String jobsalary;
	public String jobtext;
	
}
