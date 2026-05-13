package com.pergamon.application.books.book_search;

import com.pergamon.core.enums.BookType;

public record BookSearchQuery(String letters,
		BookType type,
		int page,
		String organizationPerId) {

}
