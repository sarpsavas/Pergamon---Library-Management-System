package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;

import com.pergamon.core.entites.Admin;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.interfaces.IAdminRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IAdminDA;


public class AdminRepositoryImpl implements IAdminRepository, IRepository<Admin>{
	
private final Jdbi _jdbi;
	
	public AdminRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Admin> GetAdminsByLetters(String letters)
	{
		return _jdbi.withExtension(IAdminDA.class, da -> da.GetAdminsByLetters(letters));
	}
	
	public Admin GetAdminById(UUID id)
	{
		return _jdbi.withExtension(IAdminDA.class, da -> da.GetAdminById(id));
	}
	
	public Admin GetAdminByIdentity(String eMail, String passwordHash)
	{
		return _jdbi.withExtension(IAdminDA.class, da -> da.GetAdminByIdentity(eMail, passwordHash));
	}
	
	public void add(Admin admin)
	{
		_jdbi.useExtension(IAdminDA.class, da -> {da.add(admin);});
	}
	
	public void update(Admin admin)
	{
		_jdbi.useExtension(IAdminDA.class, da -> {da.update(admin);});
	}
	
	public void delete(UUID id)
	{
		_jdbi.useExtension(IAdminDA.class, da -> {da.delete(id);});
	}
}
