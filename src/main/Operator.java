package main;

import java.io.File;

public class Operator {
	ReadCSV cReader=new ReadCSV();
	WriteCSV cWriteCSV=new WriteCSV();
	public Operator(){
//		int yyyy = 1989;
		for (int yyyy = 1989; yyyy <= 2016; yyyy++) {
			task("D:\\Import\\"+"IRI"+yyyy+".xlsx",yyyy);
		}
		System.out.println("Done!");
	}
	public void task(String URL,int yyyy){
		ReadXlsx xReader=new ReadXlsx();
		WriteXlsx xWriter=new WriteXlsx();
		int charA=(int)'A';int charZ=(int)'Z';int chara=(int)'a';int charz=(int)'z';int char0=(int)'0';
		xReader.setFile(new File(URL));
		String[][] content=xReader.read();
		String[][] IRI=new String[1][content[0].length+1];
		String[][] SIRI=new String[1][content[0].length+1];
		for(int row=1;row<content.length;row++){
			int firstChar=(int)content[row][1].charAt(0);
			if(firstChar==char0||(firstChar>=charA&&firstChar<=charZ)
					||(firstChar>=chara&&firstChar<=charz)){
				SIRI=addRow(SIRI, combineFirstTwoCol(content[row]));
			}else{
				IRI=addRow(IRI, combineFirstTwoCol(content[row]));
			}
		}
		IRI[0]=writeTitle(IRI[0], content[0]);
		SIRI[0]=writeTitle(SIRI[0], content[0]);
		printOutput(SIRI);

		xWriter.setURL("D:\\Export\\ex\\IRI","IRI"+yyyy+".xlsx");
		xWriter.write(IRI);		
		xWriter.setURL("D:\\Export\\ex\\SIRI","SIRI"+yyyy+".xlsx");
		xWriter.write(SIRI);
//		for (int row = 1; row < newC.length; row++) {
//			for (int col = 1; col < newC[row].length; col++) {
//				newC[row][col]=content[row][col+1];
//			}
//		}
//		xWriter.write(newC);
	}
	public String[] writeTitle(String[] content,String[] title){
		String[] newContent=new String[content.length];
		newContent[2]="ST_ID";
		newContent[0]=title[0];
		newContent[1]=title[1];
		for (int i = 3; i < newContent.length; i++) {
			newContent[i]=title[i-1];
		}
		return newContent;
	}
	public String[] combineFirstTwoCol(String[] row){
		String[] newRow=new String[row.length+1];
		double statecode=Double.valueOf(row[0]);
		int sc=(int)statecode;
		newRow[0]=sc+"";
		newRow[1]=row[1];
		newRow[2]=sc+row[1];
		for (int i = 3; i < newRow.length; i++) {
			newRow[i]=row[i-1];
		}
		return newRow;
	}
	public String[][] addRow(String[][] content,String[] row){
		String[][] newContent=new String[content.length+1][content[0].length];
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				newContent[i][j]=content[i][j];
			}
		}
		for (int i = 0; i < newContent[newContent.length-1].length; i++) {
			newContent[newContent.length-1][i]=row[i];
		}
		return newContent;
	}
	public void printOutput(String[][] content){
		for (int row = 0; row < content.length; row++) {
			for (int col = 0; col < content[row].length; col++) {
				System.out.print(content[row][col]);
				System.out.print("|");
			}
			System.out.println("");
		}
	}
}
