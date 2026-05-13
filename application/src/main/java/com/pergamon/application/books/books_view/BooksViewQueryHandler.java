package com.pergamon.application.books.books_view;


import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.books.book_delete.BookDeleteCommand;
import com.pergamon.core.entites.Book;
import com.pergamon.core.interfaces.IBookRepository;

@Component
public class BooksViewQueryHandler {

	private IBookRepository _boRepository;
	
	public BooksViewQueryHandler(IBookRepository boRepository)
	{
		_boRepository = boRepository;
	}
	
	@QueryHandler
	public List<Book> handle(BooksViewQuery request)
	{
		return _boRepository.GetBooks(request.organizationPerId(),request.pageNumber());
	}
}
