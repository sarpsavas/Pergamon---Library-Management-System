package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import com.pergamon.core.entites.Admin;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.interfaces.IAdminRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IAdminDA;

@Repository
public class AdminRepositoryImpl implements IAdminRepository, IRepository<Admin>{
	
private final Jdbi _jdbi;
	
	public AdminRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Admin> GetAdminsByLetters(String letters, String organizationPerId)
	{
		return _jdbi.withExtension(IAdminDA.class, da -> da.GetAdminsByLetters(letters,organizationPerId));
	}
	
	public Admin GetAdminById(UUID id, String organizationPerId)
	{
		return _jdbi.withExtension(IAdminDA.class, da -> da.GetAdminById(id,organizationPerId));
	}
	
	public Admin GetAdminByIdentity(String eMail, String passwordHash, String organizationPerId)
	{
		return _jdbi.withExtension(IAdminDA.class, da -> da.GetAdminByIdentity(eMail, passwordHash,organizationPerId));
	}
	
	public void add(Admin admin)
	{
		_jdbi.useExtension(IAdminDA.class, da -> {da.add(admin,admin.organizationPerId);});
	}
	
	public void update(Admin admin)
	{
		_jdbi.useExtension(IAdminDA.class, da -> {da.update(admin, admin.organizationPerId);});
	}
	
	public void delete(UUID id, String organizationPerId)
	{
		_jdbi.useExtension(IAdminDA.class, da -> {da.delete(id, organizationPerId);});
	}
}
