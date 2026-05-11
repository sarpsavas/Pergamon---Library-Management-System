package com.pergamon.application.users.admin_update;

import java.util.UUID;

public record UpdateAdminCommand(UUID adminId,
		UUID sAdminId,
		String adminPerId, 
		String name, 
		String lastname, 
		String password, 
		String eMail, 
		String organizationPerId) {

}
