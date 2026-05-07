package com.pergmaon.application.users.visitor_delete;

import java.util.UUID;

public record VisitorDeleteCommand(
		UUID visitorId,
		String organizationPerId) {

}
