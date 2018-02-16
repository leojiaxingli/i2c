package main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class ReadCSV {
	File ifile;
	public String[][] readCSV(File file){
		byte[] data;
		int rs;
		String str="";
		String rowcol[][]=null;
		FileInputStream fis;
		try {
			fis=new FileInputStream(file);
			BufferedInputStream bis=new BufferedInputStream(fis);
			data=new byte[bis.available()];
			rs=0;
			while((rs=bis.read(data))>0){
				str += new String(data,0,rs);
			}
			int rown=str.split("\n").length;
			int coln=str.split("\n")[0].split(",").length;
			rowcol=new String[rown][coln];
			for(int i=0;i<rown;i++){
				for(int j=0;j<coln;j++){
					rowcol[i][j]=str.split("\n")[i].split(",")[j];
				}
			}
//			for (int i = 0; i < rowcol.length; i++) {
//				for (int j = 0; j < rowcol[i].length; j++) {
//					System.out.print(rowcol[i][j]);
//				}
//			}
			bis.close();
			fis.close();
		}
		catch(Exception e){}
		return rowcol;
	}
	public String[][] read(){
		return readCSV(this.ifile);
	}
	public void setFile(File file) {
		this.ifile = file;
	}
}
