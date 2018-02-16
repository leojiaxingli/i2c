package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;


public class WriteCSV {
	String dirURL;
	String fileName;
	public void writeCSV(String str, File file) throws IOException{
		FileWriter writer = new FileWriter(file,true);
		writer.write(str);
		writer.close();
	}
	public void writeCSV(String str,String dirURL, String fileName) throws IOException{
		File f = new File(dirURL);
		if(!f.exists()){
			f.mkdirs();
		} 
		File file = new File(f,fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		writeCSV(str, file);
	}
	public void writeCSV(String str[][],String dirURL, String fileName) throws IOException{
		for(int i=0;i<str.length;i++){
			for(int j=0;j<str[i].length;j++){
				writeCSV(str[i][j], dirURL,fileName);
				if(j<str[i].length-1){
					writeCSV(",", dirURL,fileName);
				}
			}
		}
	}
	public void writeCSV(String str[],String dirURL, String fileName) throws IOException{
		for(int i=0;i<str.length;i++){
			writeCSV(str[i], dirURL, fileName);
			if(i<str.length-1){
				writeCSV(",", dirURL,fileName);
			}
		}
	}
	
	public void write(String str[][]){
		try {
			writeCSV(str,this.dirURL,this.fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void write(String str[]){
		try {
			writeCSV(str,this.dirURL,this.fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
