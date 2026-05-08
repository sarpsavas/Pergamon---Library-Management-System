package com.pergamon.infrastructure.persistence.adapter;

import java.util.UUID;

import org.jdbi.v3.core.Jdbi;

import com.pergamon.core.entites.Admin;
import com.pergamon.core.interfaces.IGeneralAdminRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IAdminDA;
import com.pergamon.infrastructure.persistence.repository.IGeneralAdminDA;

public class GeneralAdminRepositoryImpl implements IGeneralAdminRepository, IRepository<Admin> {
	
	private Jdbi _jdbi;
	
	public GeneralAdminRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public Admin GetGAdminById(UUID id)
	{
		return _jdbi.withExtension(IGeneralAdminDA.class, da -> da.GetAdminById(id));
	}
	
	public Admin GetIdByIdentity(String eMail, String passwordHash)
	{
		return _jdbi.withExtension(IGeneralAdminDA.class, da -> da.GetIdByIdentity(eMail, passwordHash));
	}
	
	public void add(Admin admin)
	{
		_jdbi.useExtension(IGeneralAdminDA.class, da -> {da.add(admin,admin.organizationPerId);});
	}
	
	public void update(Admin admin)
	{
		_jdbi.useExtension(IGeneralAdminDA.class, da -> {da.update(admin, admin.organizationPerId);});
	}
	
	public void delete(UUID id, String organizationPerId)
	{
		_jdbi.useExtension(IGeneralAdminDA.class, da -> {da.delete(id, organizationPerId);});
	}
}
