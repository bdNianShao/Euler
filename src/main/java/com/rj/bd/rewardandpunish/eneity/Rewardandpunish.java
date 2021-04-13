package com.rj.bd.rewardandpunish.eneity;
/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:05:36
 */

import com.rj.bd.root.entity.Root;
import com.rj.bd.staff.eneity.Staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rewardandpunish {
	public int rpid;
	public String rptext;
	public int rpkind;
	public String rptime;
	public Root root;
	public Staff staff;
}
