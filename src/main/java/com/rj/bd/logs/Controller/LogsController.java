package com.rj.bd.logs.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.logs.eneity.Logs;
import com.rj.bd.logs.service.ILogsService;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.tool.DateTool;
import com.rj.bd.tool.ExcelToolLogs;

/**
 * @desc: 
 * @author: ShiJie
 * @date: 2021年4月13日 上午8:01:54
 */

@Controller
@RequestMapping("/logs")
public class LogsController {
	@Autowired
	public ILogsService logsService;
	
	@Autowired
	public IRootService rootService;
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Logs> queryRoot(String token){
		if ( ! rootService.rootBytoken(token)) 
			
		{
			List<Logs> list = new ArrayList<Logs>();
			list.add(new Logs());
			return  list;
		}
		
		List<Logs> logs = logsService.queryLogs();
		

		for (Logs log : logs) 
		{
			log.setLogtime(DateTool.convertTimestamp2Date(log.getLogtime()));
		}
		
		return logs;
	}
	@RequestMapping("download")
	public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		List<Logs> list = logsService.queryLogs();
		String[] names = {"时间","日志内容","管理员"}; 
		String fileName ="日志信息表";
		XSSFWorkbook excelbook = ExcelToolLogs.createWorkbook(fileName,names,list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			excelbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try 
		{
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	
	}
	
}
