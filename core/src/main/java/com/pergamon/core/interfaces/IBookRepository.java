package com.pergamon.core.interfaces;

import java.util.List;

import com.pergamon.core.entites.Book;

public interface IBookRepository {
	List<Book> GetBooksByLetters(String letters, String organizationPerId);
	
	Book GetBookByBookId(String bookId, String organizationPerId);
	
	public void add(Book book);
	
	public void update(Book book);
	
	public void delete(String id, String organizationPerId);
	
}
