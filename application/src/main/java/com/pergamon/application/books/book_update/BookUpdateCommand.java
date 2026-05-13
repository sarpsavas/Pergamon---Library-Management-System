package com.pergamon.application.books.book_update;

import java.util.UUID;

import com.pergamon.core.enums.Availability;
import com.pergamon.core.enums.BookType;

public record BookUpdateCommand(
		String id, // {PB}+{0000000}
		String name,
		String author,
		BookType bookType,
		Availability availability,
		int pageNumber,
		String organizationPerId,
		UUID defaultAdminId
		) {

}
