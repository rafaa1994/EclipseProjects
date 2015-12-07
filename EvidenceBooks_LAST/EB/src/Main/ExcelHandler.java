package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ExcelHandler {

	ExcelConnector excel = new ExcelConnector();
	
	
	public void clearArrayListBook(){
		
		excel.getArrayListBook().clear();
		
	}
	
	public ArrayList<Book> getArrayListBook(){
		
		return excel.getArrayListBook();
		
	}
	
	public String confirmSell(String path, String path2)throws IOException{
		excel.prepareRaport(path2);
		String message = excel.updateExcel(path);
		excel.getArrayListBook().clear();
		return message;
		
	}
	
	public String arrayInString(String ISBN1, String path) throws IOException {
		String str="";
		str = excel.arrayStringValue(excel.searchBook(ISBN1, path));
		return str;
	}
	
	public void addBook(String path) throws IOException{
		
		for (int i = 0; i < excel.getArrayListBook().size(); i++)
			excel.addBookToExcel(excel.getArrayListBook().get(i),path);	
	}
	
	public String fillInfo(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8,String path) throws IOException{
		
		String message = excel.fillInfo(str1,str2,str3,str4,str5, str6,str7, str8,path);
		return message;
	}
	
	public String printReceipt(String ISBN, String path) throws IOException{
		
	String receipt ="";
	receipt = excel.printReceipt(excel.getArrayListBook().get(excel.getArrayListBook().size() - 1));
	return receipt;
	
	}
	
	public boolean getChanges(){
		return excel.getChanges();
	}
	
	public void searchBookOnlyByISBN(String ISBN, String path) throws IOException{
		
		boolean decision = false;
		
		try{
			Double d = Double.parseDouble(ISBN);
			decision = true;
		}
		catch (NumberFormatException nfe){
			decision = false;
			excel.setChanges(false);
		}
		
		if(decision) excel.searchBook(ISBN, path);
		
	}
	
public void searchBook(String ISBN, String path) throws IOException{
		
		excel.searchBook(ISBN, path);
		
	}
	
	
}