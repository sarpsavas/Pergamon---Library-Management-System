package com.pergmaon.application.users.log_in;

import com.pergamon.core.enums.LogInOption;

public record LogInQuery(
		String email,
		String password,
		String organization_pergamon_id,
		LogInOption log_in_option
		) {
	
}
