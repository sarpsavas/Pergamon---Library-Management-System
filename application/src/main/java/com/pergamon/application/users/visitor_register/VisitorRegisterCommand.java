package com.pergamon.application.users.visitor_register;

import java.util.UUID;

import com.pergamon.core.enums.AccountProfile;

public record VisitorRegisterCommand(UUID adminId, 
		String name, 
		String lastname, 
		String password, 
		String eMail, 
		String organizationPerId,
		AccountProfile accountProfile) {

}
