package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteXlsx {
	String dirURL;
	String fileName;
	public WriteXlsx(){
		
	}
	public void write(File file,String[][] value){
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sheet1");
		
		for (int row = 0; row < value.length; row++){
			XSSFRow rows = sheet.createRow(row);
			for (int col = 0; col < value[row].length; col++){
				if(value[row][col]==null||value[row][col]==""){
					rows.createCell(col).setCellValue("");
				}else{
					if(row>0){
						if(col>2){
							if(col==value[row].length-1){
								double d=Double.valueOf(value[row][col]);
								rows.createCell(col).setCellValue(excelToDate((int)d));
							}
							else if(col==value[row].length-3){
								rows.createCell(col).setCellValue(value[row][col]);
							}
							else{
								rows.createCell(col).setCellValue(Double.valueOf(value[row][col]));
							}
						}else if(col==0){
							double d=Double.valueOf(value[row][col]);
							rows.createCell(col).setCellValue((int)d);
						}else{
							rows.createCell(col).setCellValue(value[row][col]);
						}
					}else{
						rows.createCell(col).setCellValue(value[row][col]);
					}
				}
				
			}
		}
		
		FileOutputStream xlsxStream = null;
		
		try {
		xlsxStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		try {
		workbook.write(xlsxStream);
		} catch (IOException e) {
		e.printStackTrace();
		}
	}
	public String excelToDate(int num){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date(1899,12,31);
		Calendar ca=Calendar.getInstance();
		ca.set(date.getYear(),date.getMonth(),date.getDay());
		ca.add(Calendar.DATE, num);
		String rd = format.format(ca.getTime());
		Date fd=null;
		try {
			fd = format.parse(rd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rd;
	}
	public void write(String[][] value,String dirURL, String fileName){
		File f = new File(dirURL);
		if(!f.exists()){
			f.mkdirs();
		} 
		File file = new File(f,fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		write(file,value);
	}
	public void write(String content[][]){
		write(content,this.dirURL,this.fileName);
	}
	public void setdirURL(String URL) {
		dirURL = URL;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setURL(String URL, String fileName){
		this.setdirURL(URL);
		this.setFileName(fileName);
	}
}
