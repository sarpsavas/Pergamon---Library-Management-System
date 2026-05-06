package com.pergmaon.application.users.log_in;

public record LogInQuery(
		String email,
		String password,
		String organization_pergamon_id
		) {
	
}
