package com.pergamon.core.interfaces;

import java.util.List;



import com.pergamon.core.entites.Book;
import com.pergamon.core.enums.BookType;

public interface IBookRepository {
	List<Book> GetBooksByLetters(String letters, BookType type, int page, String organizationPerId);
	
	List<Book> GetBooks(String organizationPerId, int pageNumber);
	
	Book GetBookByBookId(String bookId, String organizationPerId);
	
	public void add(Book book);
	
	void addImage(String bookId, String organizationPerId, String url);
	
	public void update(Book book);
	
	public void delete(String id, String organizationPerId);
	
}
