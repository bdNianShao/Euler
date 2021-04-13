package com.rj.bd.logs.eneity;

import com.rj.bd.root.entity.Root;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc: Logs实体类
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logs {
	public int logid;
	public String logtime;
	public String logtext;
	public Root root;
	
}
