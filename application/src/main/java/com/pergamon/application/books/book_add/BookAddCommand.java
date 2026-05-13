package com.pergamon.application.books.book_add;

import java.util.UUID;

import com.pergamon.core.enums.Availability;
import com.pergamon.core.enums.BookType;

public record BookAddCommand(
		UUID defaultAdminId,
		String id, // {PB}+{0000000}
		String name,
		String author,
		BookType bookType,
		int pageNumber,
		String organizationPerId) {

}
