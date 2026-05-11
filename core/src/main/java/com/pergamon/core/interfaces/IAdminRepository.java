package com.pergamon.core.interfaces;

import java.util.List;
import java.util.UUID;

import com.pergamon.core.entites.Admin;
import com.pergamon.core.entites.Visitor;

public interface IAdminRepository {
	List<Admin> GetAdmins(String organizationPerId);
	
	Admin GetAdminById(UUID id, String organizationPerId);
	
	Admin GetAdminByIdentity( String eMail, String passwordHash, String organizationPerId);
}
