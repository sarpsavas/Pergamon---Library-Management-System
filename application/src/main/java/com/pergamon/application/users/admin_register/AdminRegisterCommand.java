package com.pergamon.application.users.admin_register;

public record AdminRegisterCommand(String name,
		String lastname,
		String password,
		String email,
		String organizationperid) {

}
