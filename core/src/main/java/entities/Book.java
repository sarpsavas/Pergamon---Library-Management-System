package entities;

import java.time.LocalDate;

import ValueObjects.BookId;
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
		BookId bookId = new BookId();
		Id = bookId.GetBookId();
	}
}
