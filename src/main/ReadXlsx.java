package main;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadXlsx {
	File iFile;
	public ReadXlsx(){
		
	}
	public String[][] read(File file){
		String [][]rowcol=null;
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int sheetCount = workbook.getNumberOfSheets();
		
		for (int i = 0; i < sheetCount; i++){
			Sheet sheet = workbook.getSheetAt(i);
			int rows = sheet.getLastRowNum() + 1;
			Row tmp = sheet.getRow(0);
			if (tmp == null){
			   continue;
			}
			int cols = tmp.getPhysicalNumberOfCells();
			rowcol=new String[rows][cols];
			for (int row = 0; row < rows; row++){
				Row r = sheet.getRow(row);
				for (int col = 0; col < cols; col++){
					Cell cell=r.getCell(col);
					String cellValue="";
					if(cell!=null){
						if(row>0&&col==5){
							switch (cell.getCellType()){  
				            case Cell.CELL_TYPE_NUMERIC: //Êý×Ö  
				            	double d=cell.getNumericCellValue();
				            	int id=(int)d;
				                cellValue = String.valueOf(id);  
				                break;  
				            case Cell.CELL_TYPE_STRING: //×Ö·û´®  
				                cellValue = String.valueOf(cell.getStringCellValue());  
				                break;  
				            default:  
					            cellValue = "Unknown";  
					            break;  
							}
						}else{
				        switch (cell.getCellType()){  
			            case Cell.CELL_TYPE_NUMERIC: //Êý×Ö  
			                cellValue = String.valueOf(cell.getNumericCellValue());  
			                break;  
			            case Cell.CELL_TYPE_STRING: //×Ö·û´®  
			                cellValue = String.valueOf(cell.getStringCellValue());  
			                break;  
			            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
			                cellValue = String.valueOf(cell.getBooleanCellValue());  
			                break;  
			            case Cell.CELL_TYPE_FORMULA: //¹«Ê½  
			                cellValue = String.valueOf(cell.getCellFormula());  
			                break;  
			            case Cell.CELL_TYPE_BLANK: //¿ÕÖµ   
			                cellValue = "";  
			                break;  
			            case Cell.CELL_TYPE_ERROR: //¹ÊÕÏ  
			                cellValue = "Error";  
			                break;  
			            default:  
			                cellValue = "Unknown";  
			                break;  
						}
						}
			        }
			        rowcol[row][col]=cellValue;
				}
			}
		}
		return rowcol;
	}
	public String[][] read(){
		return read(iFile);
	}
	public void setFile(File file) {
		this.iFile = file;
	}
}
