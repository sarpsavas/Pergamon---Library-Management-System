package entities;

import java.time.LocalDate;

import ValueObjects.BookIdObject;
import enums.BookType;
import enums.Availability;

public class Book {
	public String Id; // {PL}+{_______}7int
	public String Name;
	public String Author;
	public BookType BookType;
	public Availability Availability;
	public int PageNumber;
	
	
	public Book()
	{
		BookIdObject bookId = new BookIdObject();
		Id = bookId.GetBookId();
	}
}
