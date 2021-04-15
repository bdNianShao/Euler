package com.rj.bd.tool;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rj.bd.logs.eneity.Logs;

/**
* @desc 
* 
* @author TianShuo
* 
* @version 2021年4月15日 下午4:02:55
*/
public class ExcelToolLogs {
public static XSSFWorkbook  createWorkbook(String bookName,String [] array,List<Logs> list){

	XSSFWorkbook excelbook = new XSSFWorkbook(); //创建workBook
	XSSFSheet excelSheet = excelbook.createSheet();//创建sheet表
	XSSFRow excelRow = excelSheet.createRow(0);//创建行
	XSSFCellStyle headerStyle = excelbook.createCellStyle();//设置 居中
	//接下来是创建 列标题 ,cell的起始值是 0,可创建n个列标题  
	XSSFCell cell = excelRow.createCell(0);
	cell.setCellStyle(headerStyle);//居中
	cell.setCellValue(bookName);
	//接下来遍历List,并写入EXCEL中
	
	XSSFRow excelRow2 = excelSheet.createRow(1);
		for(int i = 0; i < array.length; i++){
			XSSFCell cell2 = excelRow2.createCell(i);
			cell2.setCellValue(array[i]);
		}
		
		for (int i = 0; i < list.size(); i++) {
			XSSFRow excelRow3 = excelSheet.createRow(i+2);
			excelRow3.createCell(0).setCellValue(DateTool.convertTimestamp2Date(list.get(i).getLogtime()));
			excelRow3.createCell(1).setCellValue(list.get(i).getLogtext());
			excelRow3.createCell(2).setCellValue(list.get(i).getRoot().getTemp());
			
			
			
			
			/*excelRow3.createCell(0).setCellValue(list.get(i).getStaffnum());
			excelRow3.createCell(1).setCellValue(list.get(i).getName());
			excelRow3.createCell(2).setCellValue(list.get(i).getJob().getJobname());
			excelRow3.createCell(3).setCellValue(list.get(i).getAge());
			excelRow3.createCell(4).setCellValue(list.get(i).getSex());
			excelRow3.createCell(5).setCellValue(list.get(i).getEdu());
			excelRow3.createCell(6).setCellValue(DateTool.convertTimestamp2Date(list.get(i).getJoindate()));
			excelRow3.createCell(7).setCellValue(list.get(i).getDepartment().getDepartname());*/
		}
		
		
		
		return excelbook;
}
}
