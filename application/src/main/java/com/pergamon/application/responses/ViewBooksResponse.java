package com.pergamon.application.responses;

import java.util.List;

import com.pergamon.core.entites.Book;

public class ViewBooksResponse {
 
	public List<Book> books;
	
	public ViewBooksResponse(List<Book> books)
	{
		this.books = books;
	}
}
