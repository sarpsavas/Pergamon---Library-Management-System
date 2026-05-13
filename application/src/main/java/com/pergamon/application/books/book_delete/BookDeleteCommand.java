package com.pergamon.application.books.book_delete;

import java.util.UUID;

public record BookDeleteCommand(String bookPerId,
		UUID defaultAdminId,
		String organizationPerId) {

}
