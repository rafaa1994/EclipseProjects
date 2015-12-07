package Main;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class ExcelConnector {

	private ArrayList<Book> books = new ArrayList<Book>();
	private Book book = new Book();
	private boolean changes;

	public ArrayList<Book> getArrayListBook() {
		return books;
	}

	public boolean getChanges() {
		return changes;
	}
	
	public void setChanges(boolean decision) {
		 changes = decision;
	}

	public Book getBook() {

		return book;
	}

	public ArrayList<Book> searchBook(String param, String path)
			throws IOException {

		changes = false;
		FileInputStream input = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		XSSFCell cell2 = null;

		DataFormatter formatter = new DataFormatter();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheet = workbook.getSheetAt(i);
			for (int j = sheet.getFirstRowNum() + 1; j < sheet
					.getPhysicalNumberOfRows(); j++) {
				String temp1 = "";
				String temp2 = "";
				row = sheet.getRow(j);
				cell = row.getCell(0);
				cell2 = row.getCell(6);
				if (cell.getStringCellValue().length() >= param.length()
						&& param != "") {
					temp1 = StringUtils.stripAccents(cell.getStringCellValue()
							.toLowerCase().substring(0, param.length()));
					temp2 = StringUtils.stripAccents(param.toLowerCase());
				} else if (cell.getStringCellValue().length() < param.length()
						&& param != "") {
					temp1 = StringUtils.stripAccents(cell.getStringCellValue()
							.toLowerCase());
					temp2 = StringUtils.stripAccents(param.toLowerCase()
							.substring(0, cell.getStringCellValue().length()));

				}
				if (temp1.equals(temp2) && !temp1.equals("")
						&& !temp2.equals("")
						|| param.equals(formatter.formatCellValue(cell2))) {
					book = new Book();
					for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
						cell = row.getCell(k);
						book.setSchool(workbook.getSheetName(i));
						switch (k) {
						case 0:
							book.setTitle(cell.getStringCellValue());
							break;
						case 1:
							book.setLevel(cell.getStringCellValue());
							break;
						case 2:
							book.setAuthors(cell.getStringCellValue());
							break;
						case 3:
							book.setPublisher(cell.getStringCellValue());
							break;
						case 4:
							book.setAmountOfBooks((int) cell
									.getNumericCellValue());
							break;
						case 5:
							book.setPrice(cell.getNumericCellValue());
							break;
						case 6:
							book.setISBN(formatter.formatCellValue(cell));
						default:
							break;
						}
					}
					if (book.checkItHasValue()) {
						books.add(book);
						changes = true;
					}
				}
			}
		}
		workbook.close();
		input.close();
		System.out.println();
		for(int i=0;i<books.size();i++)System.out.println(books.get(i).getTitle());
		
		return books;
	}

	public void addBookToExcel(Book temp, String path) throws IOException {

		FileInputStream input = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		DataFormatter formatter = new DataFormatter();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheet = workbook.getSheetAt(i);
			for (int j = sheet.getFirstRowNum() + 1; j < sheet
					.getPhysicalNumberOfRows(); j++) {
				row = sheet.getRow(j);
				cell = row.getCell(6);
				if (formatter.formatCellValue(cell).equals(temp.getISBN())) {
					cell = row.getCell(4);
					int am = (int) cell.getNumericCellValue();
					cell.setCellValue(am + 1);
					XSSFCellStyle st = changeCellStyle(workbook, am + 1);
					for(int z = 0;z < row.getPhysicalNumberOfCells(); z++){
						cell = row.getCell(z);
						cell.setCellStyle(st);
					}
				}
			}
		}
		FileOutputStream output = new FileOutputStream(path);
		workbook.write(output);
		workbook.close();
		input.close();
		output.close();
	}

	public String deleteBookFromExcel(Book temp, String path) throws IOException {

		FileInputStream input = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		DataFormatter formatter = new DataFormatter();
		String message = "";
		sheet = workbook.getSheet(temp.getSchool());
		for (int i = sheet.getFirstRowNum() + 1; i < sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			cell = row.getCell(6);

			if (formatter.formatCellValue(cell).equals(temp.getISBN())) {
				cell = row.getCell(4);
				int am = (int) cell.getNumericCellValue();
				if (am > 0){
					cell.setCellValue(am - 1);
					cell = row.getCell(0);
					String title = formatter.formatCellValue(cell);
				message = "Wyewidencjonowano z powodzeniem\n" + "Tytu�: "+  title + "\n\n";
				XSSFCellStyle st = changeCellStyle(workbook, am - 1);
				for(int j = 0;j < row.getPhysicalNumberOfCells(); j++){
					cell = row.getCell(j);
					cell.setCellStyle(st);
				
					}
				}
				else {
						cell = row.getCell(0);
						String title = formatter.formatCellValue(cell);
						cell = row.getCell(4);
						int amo = (int)cell.getNumericCellValue();
						message = "Wyewidencjonowanie nieudane z powodu braku ksi��ki w bazie:\n" +"Tytu�: "+  title + " Ilo��: " + amo + "\n"
						+"Dana pozycja nie zosta�a wcze�niej dodana do pliku ewidencji.\n\n";
					}
				}
		}

		FileOutputStream output = new FileOutputStream(path);
		workbook.write(output);
		workbook.close();
		input.close();
		output.close();
		return message;
	}

	public String arrayStringValue(ArrayList<Book> books2) {

		String book_string = "";
		for (int i = 0; i < books2.size(); i++) {
			book_string += books2.get(i).toStringParsing();
		}
		if (book_string.equals("")) {
			book_string = "Brak przedmiot�w o wybranym kryterium\n";
		}
		return book_string;
	}

	public String updateExcel(String path) {

		String message = "";
		try {
			for (int i = 0; i < books.size(); i++) 
				message += deleteBookFromExcel(books.get(i), path);
			
			return message;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	public String printReceipt(Book book){//, int price) {

		String receipt = "Tytu�: " + book.getTitle() + 
				"\n" + "Szko�a: " + book.getSchool() +
				"\n" + "Autorzy: "+ book.getAuthors()+ 
				"\n" + "Wydawnictwo: " + book.getPublisher() + "\n";//\t\t";

		return receipt;
	}

	@SuppressWarnings("null")
	public String fillInfo(String title, String level, String authors,
			String publisher, String amount, String price, String ISBN,
			String school, String path) throws IOException {

		String message = new String();
		book = new Book();
		book.setTitle(title);
		book.setLevel(level);
		book.setAuthors(authors);
		book.setPublisher(publisher);
		book.setAmountOfBooks(Integer.parseInt(amount));
		book.setPrice(Double.parseDouble(price));
		book.setISBN(ISBN);

		if (!book.checkItHasValue())
			return message = "Brakuje danych";

		FileInputStream input = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		DataFormatter formatter = new DataFormatter();
		boolean found = false;

		int foundNumberSheet = checkSheetExist(workbook, school);

		if (foundNumberSheet == -1) {
			sheet = workbook.createSheet(school);
			System.out.println("by� i zobaczy�");

			row = sheet.createRow(0);
			for (int j = 0; j < 7; j++) {
				cell = row.createCell(j);
				switch (j) {

				case 0:
					cell.setCellValue("Tytu�");
					sheet.autoSizeColumn(0);
					break;
				case 1:
					cell.setCellValue("Poziom");
					sheet.autoSizeColumn(1);
					break;
				case 2:
					cell.setCellValue("Autorzy");
					sheet.autoSizeColumn(2);
					break;
				case 3:
					cell.setCellValue("Wydawnictwo");
					sheet.autoSizeColumn(3);
					break;
				case 4:
					cell.setCellValue("Ilo��");
					sheet.autoSizeColumn(4);
					break;
				case 5:
					cell.setCellValue("Cena");
					sheet.autoSizeColumn(5);
					break;
				case 6:
					cell.setCellValue("ISBN");
					sheet.autoSizeColumn(6);
					break;
				default:
					break;
				}

			}

		} else sheet = workbook.getSheetAt(foundNumberSheet);

		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			cell = row.getCell(6);
			
			System.out.println("ISBN:" + formatter.formatCellValue(cell));

			if (book.getISBN().equals(formatter.formatCellValue(cell))) {
				cell = row.getCell(4);
				System.out.println("Jest ISBN \n"+ formatter.formatCellValue(cell));
				int am = (int) cell.getNumericCellValue() + book.getAmountOfBooks();
				cell.setCellValue(am);
				XSSFCellStyle st = changeCellStyle(workbook, am);
					for(int j = 0;j<row.getPhysicalNumberOfCells();j++){
						cell = row.getCell(j);
						cell.setCellStyle(st);
				}
				
				System.out.println(formatter.formatCellValue(cell));
				found = true;
			}
		}
		if (!found) {
			row = sheet.createRow(sheet.getPhysicalNumberOfRows());
			XSSFCellStyle cellStyle = changeCellStyle(workbook, book.getAmountOfBooks());
			for (int j = 0; j < 7; j++) {
				cell = row.createCell(j);
				cell.setCellStyle(cellStyle);
				switch (j) {

				case 0:
					cell.setCellValue(book.getTitle());
					sheet.autoSizeColumn(0);
					break;

				case 1:
					cell.setCellValue(book.getLevel());
					sheet.autoSizeColumn(1);
					break;
				case 2:
					cell.setCellValue(book.getAuthors());
					sheet.autoSizeColumn(2);
					break;
				case 3:
					cell.setCellValue(book.getPublisher());
					sheet.autoSizeColumn(3);
					break;
				case 4:
					cell.setCellValue(book.getAmountOfBooks());
					sheet.autoSizeColumn(4);
					break;
				case 5:
					cell.setCellValue(book.getPrice());
					sheet.autoSizeColumn(5);
					break;
				case 6:
					cell.setCellValue(book.getISBN());
					sheet.autoSizeColumn(6);
					break;
				default:
					break;
				}

			}

		}

		FileOutputStream output = new FileOutputStream(path);
		workbook.write(output);
		workbook.close();
		input.close();
		output.close();
		return message = "Wprowadzono poprawnie";
	}

	public int checkSheetExist(XSSFWorkbook wb, String school) {

		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			System.out.println(wb.getSheetName(i));
			if (wb.getSheetName(i).toLowerCase().equals(school.toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * public void deleteRow(String path) throws IOException { FileInputStream
	 * input = new FileInputStream(path); XSSFWorkbook workbook = new
	 * XSSFWorkbook(input); XSSFSheet sheet = null; XSSFRow row = null; XSSFCell
	 * cell = null; DataFormatter formatter = new DataFormatter(); boolean found
	 * = false;
	 * 
	 * sheet = workbook.getSheetAt(0); row = sheet.getRow(2);
	 * System.out.println(sheet.getPhysicalNumberOfRows());
	 * sheet.removeRow(row);
	 * 
	 * System.out.println(sheet.getPhysicalNumberOfRows()); FileOutputStream
	 * output = new FileOutputStream(path); workbook.write(output);
	 * workbook.close(); input.close(); output.close(); }
	 */

	public void prepareRaport(//ArrayList<Integer> prices,
			String path2) throws IOException {
		//for (int i = 0; i < books.size(); i++) {
		//	System.out.println("Tytul: "+ books.get(i).getTitle() + " :" + prices.get(i).intValue());
		//}
		FileInputStream input = new FileInputStream(path2);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		DateTime date = new DateTime();
		String RaportDate = String.valueOf(date.getDayOfMonth()) + "."
				+ String.valueOf(date.getMonthOfYear()) + "."
				+ String.valueOf(date.getYear());
		int foundNumberSheet = checkSheetExist(workbook, "Raport " + RaportDate);

		if (foundNumberSheet == -1) {

			sheet = workbook.createSheet(WorkbookUtil
					.createSafeSheetName("Raport " + RaportDate));
			row = sheet.createRow(0);
			for (int j = 0; j < 6; j++) {
				cell = row.createCell(j);
				switch (j) {

				case 0:
					cell.setCellValue("Tytu�");
					sheet.autoSizeColumn(0);
					break;
				case 1:
					cell.setCellValue("Poziom");
					sheet.autoSizeColumn(1);
					break;
				case 2:
					cell.setCellValue("Autorzy");
					sheet.autoSizeColumn(2);
					break;
				case 3:
					cell.setCellValue("Wydawnictwo");
					sheet.autoSizeColumn(3);
					break;
			/*	case 4:
					cell.setCellValue("Cena sprzeda�y");
					sheet.autoSizeColumn(4);
					break;*/
				case 4:
					cell.setCellValue("ISBN");
					sheet.autoSizeColumn(4);
					break;
				case 5:
					cell.setCellValue("Godzina");
					sheet.autoSizeColumn(5);
					break;
				default:
					break;
				}

			}

		} else
			sheet = workbook.getSheetAt(foundNumberSheet);
		
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getAmountOfBooks() == 0) continue;
			row = sheet.createRow(sheet.getPhysicalNumberOfRows());
			for (int j = 0; j < 6; j++) {
				cell = row.createCell(j);
				switch (j) {

				case 0:
					cell.setCellValue(books.get(i).getTitle());
					sheet.autoSizeColumn(0);
					break;
				case 1:
					cell.setCellValue(books.get(i).getLevel());
					sheet.autoSizeColumn(1);
					break;
				case 2:
					cell.setCellValue(books.get(i).getAuthors());
					sheet.autoSizeColumn(2);
					break;
				case 3:
					cell.setCellValue(books.get(i).getPublisher());
					sheet.autoSizeColumn(3);
					break;/*
				case 4:
					cell.setCellValue(prices.get(i).intValue());
					sheet.autoSizeColumn(4);
					break;*/
				case 4:
					cell.setCellValue(books.get(i).getISBN());
					sheet.autoSizeColumn(4);
					break;
				case 5:
					cell.setCellValue(date.toDate().toString()
							.substring(11, 16));
					sheet.autoSizeColumn(5);
					break;
				default:
					break;
				}

			}
		}

		FileOutputStream output = new FileOutputStream(path2);
		workbook.write(output);
		workbook.close();
		input.close();
		output.close();

	}

	public XSSFCellStyle changeCellStyle(XSSFWorkbook workbook,int num){
		
		XSSFCellStyle newCellStyle = workbook.createCellStyle();
		if(num < 6){
			XSSFColor color = new XSSFColor(new Color(255,51,51));		//light red
			newCellStyle.setFillForegroundColor(color);
			newCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		else if(num >= 6 && num < 11){
			XSSFColor color = new XSSFColor(new Color(255,204,51));		//yellow
			newCellStyle.setFillForegroundColor(color);
			newCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		else if(num >= 11){
			XSSFColor color = new XSSFColor(new Color(153,204,0));		//light green
			newCellStyle.setFillForegroundColor(color);
			newCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		
		return newCellStyle;
	}
	
}
