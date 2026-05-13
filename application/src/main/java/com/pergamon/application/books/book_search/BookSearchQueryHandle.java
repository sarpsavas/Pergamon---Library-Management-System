package com.pergamon.application.books.book_search;

import org.springframework.stereotype.Component;

import com.pergamon.application.responses.BookSearchResponse;
import com.pergamon.core.interfaces.IBookRepository;

@Component
public class BookSearchQueryHandle {

	 private IBookRepository _bookRepository;
	 
	 public BookSearchQueryHandle(IBookRepository bookRepository)
	 {
		 _bookRepository = bookRepository;
	 }
	 
	 public BookSearchResponse handle(BookSearchQuery request)
	 {
		 BookSearchResponse response = new BookSearchResponse();
		 response.books = _bookRepository.GetBooksByLetters(request.letters(),request.type(), request.page(), request.organizationPerId());
		 return response;
	 }
}
