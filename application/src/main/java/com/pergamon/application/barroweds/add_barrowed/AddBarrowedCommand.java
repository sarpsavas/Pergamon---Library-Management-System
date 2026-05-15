package com.pergamon.application.barroweds.add_barrowed;

import java.util.UUID;

public record AddBarrowedCommand(
		UUID defaultAdminId,
		String bookId,
		UUID visitorId,
		String organizationPerId) {

}
