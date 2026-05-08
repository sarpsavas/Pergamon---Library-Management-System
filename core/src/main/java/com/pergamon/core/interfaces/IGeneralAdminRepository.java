package com.pergamon.core.interfaces;

import java.util.UUID;

import com.pergamon.core.entites.Admin;


public interface IGeneralAdminRepository {
	
	Admin GetGAdminById(UUID id);
	Admin GetIdByIdentity(String eMail, String passwordHash);
	
	void add(Admin admin);
	
	void update(Admin admin);
	
	void delete(UUID id, String organizationPerId);
}
