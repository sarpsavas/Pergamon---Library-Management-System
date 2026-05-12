package com.pergamon.application.users.master_admin_register;

import java.util.UUID;

public record MasterAdminRegisterCommand(String name,
		String lastname,
		String password,
		String email,
		String organizationperid,
		UUID generalAdmin) {

}
