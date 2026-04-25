package com.pergamon.core.entites;

import java.time.LocalDate;

import com.pergamon.core.enums.Availability;
import com.pergamon.core.enums.BookType;
import com.pergamon.core.value_objects.BookIdObject;

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
