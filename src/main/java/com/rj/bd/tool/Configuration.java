package com.rj.bd.tool;


import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import com.sun.management.OperatingSystemMXBean;

/**
* @desc 
* 
* @author TianShuo
* 
* @version 2021年4月15日 上午9:17:22
*/
public class Configuration {
	public static Map<String, Object> getConfiguration(){
		/**
		 * =======================【服务器数据】=======================
		 */
		Map<String, Object> data = new HashMap<String, Object>();
		// 服务器运行时间
		long dateTime = System.currentTimeMillis() - getServerStqrtTime();

		// 服务器内存
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		// 总的物理内存
		long totalvirtualMemory = osmxb.getTotalSwapSpaceSize(); // 单位是字节数，除以1024是K
		// 剩余的内存
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

		// 使用的内存
		long compare = totalvirtualMemory - freePhysicalMemorySize;

		data.put("serverruntime", getDate(dateTime) + "小时");
		data.put("servermemory", (compare  /1024/1024/1024)  + "GB/" + ( totalvirtualMemory /1024/1024/1024 ) + "GB");
		// cpu核数
		data.put("servercores", Runtime.getRuntime().availableProcessors());
		// cpu线程数

		// 获得线程总数
		ThreadGroup parentThread;
		for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
				.getParent() != null; parentThread = parentThread.getParent()) {
		}

		int totalThread = parentThread.activeCount();
		data.put("serverthreads", totalThread);

		// 服务器操作系统
		data.put("serveros", System.getProperty("os.name"));
		// 获取java的版本
		data.put("serverjavaversion", System.getProperty("java.version"));

		
		return data;
		
		
		
		
		
		
		
	}
	public static long getServerStqrtTime() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return time;
	}
	
	public static long getDate(long time) {
		long days = time / (1000 * 60 * 60);
		return days;
	}
}
