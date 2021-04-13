package com.rj.bd.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @desc 时间日期工具类
* 
* @author TianShuo
* 
* @version 2021年4月13日 上午9:34:29
*/
public class DateTool {
	/**
	 * @desc 获取当前时间戳
	 * @return
	 */
	public static String getNowTimeNum()
	
	{
		return Long.toString(System.currentTimeMillis());
	}
	/**
	 * @desc 时间戳转换成日期格式
	 * @param timestamp
	 * @return
	 */
	public static 	String  convertTimestamp2Date(String  timestampStr)
	
	{
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        long   timestamp = Long.parseLong(timestampStr);
        return simpleDateFormat.format(new Date(timestamp));
	}
	

}
