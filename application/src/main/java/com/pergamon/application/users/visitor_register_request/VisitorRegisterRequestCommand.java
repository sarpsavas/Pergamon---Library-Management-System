package com.pergamon.application.users.visitor_register_request;

public record VisitorRegisterRequestCommand(String name,
		String lastname,
		String password,
		String email,
		String organizationperid) {

}
