package com.pergamon.core.interfaces;

import java.util.List;

import com.pergamon.core.entites.Book;

public interface IBookRepository {
	List<Book> GetBooksByLetters(String letters);
	
	
	
	
}
