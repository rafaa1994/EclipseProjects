package Main;

public class Book {



	private String title = new String();
	//private int class1 = 0;
	private String school = new String();
	private String level = new String();
	private String authors = new String();
	private String publisher = new String();
	private String isbn_code = new String();
	private String permission_nr = new String();
	private int amount_of_books = 0;
	private double price = 0;
	
	//Getters
	
	String getTitle(){
		return title;
	}
	/*int getClass1(){
		return class1;
	}*/
	String getSchool(){
		return school;
	}	
	String getLevel(){
		return level;
	}
	String getAuthors(){
		return authors;
	}
	String getPublisher(){
		return publisher;
	}
	String getISBN(){
		return isbn_code;
	}
	String getPersmission_NR(){
		return permission_nr;
	}
	int getAmountOfBooks(){
		return amount_of_books;
	}
	double getPrice(){
		return price;
	}

	//Setters
	
	void setTitle(String arg0){
		title = arg0;
	}
	/*
	void setClass1(int arg0){
		class1 = arg0;
	}*/
	void setSchool(String arg0){
		school = arg0;
	}
	void setLevel(String arg0){
		level = arg0;
	}
	void setAuthors(String arg0){
		authors = arg0;
	}
	void setPublisher(String arg0){
		publisher = arg0;
	}
	void setISBN(String arg0){
		isbn_code = arg0;
	}
	void setAmountOfBooks(int arg0){
		amount_of_books = arg0;
	}
	void setPrice(double arg0){
		price = arg0;
	}
	void setPersmission_NR(String arg0){
		permission_nr = arg0;
	}

public String toStringParsing(){
	String pom = "";
	
	
	pom =	"Tytu³: " + title + "\n" +
			"Poziom: " + level + "\n" +
		  //"Klasa: " + class1 + "\n" +
			"Szko³a: " + school + "\n"+
			"Autor(zy): " + authors + "\n" +
			"Wydawnictwo: " + publisher + "\n" +
			"Iloœæ: " + amount_of_books + "szt.\n" +
			"Cena: " + price + "z³\n" +
			"ISBN: " + isbn_code + "\n"+
			"Nr dopuszczenia: " + permission_nr + "\n\n"+
			"( Wycena )\n"+
			"75% - " + price*75/100 + "z³ [Stan: 5-/5 oraz 2 i 3 LO rozszerzenia]\n" +
			"80% - " + price*80/100 + "z³ [Specjalistyczne]\n" +
			"85% - " + price*85/100 + "z³ [Stan: Nowy]\n" + 
			"70% - " + price*70/100 + "z³ [Wszystkie pozosta³e]\n\n\n" ;
	
	return pom;
}
public boolean checkItHasValue(){
	if(title == "" /*|| class1 == 0 */|| school == "" || permission_nr == "" ||
			level == ""|| authors == "" || publisher == "" || 
			isbn_code == "" || amount_of_books == -1 || price == 0) return false;
	else return true;
}

}
