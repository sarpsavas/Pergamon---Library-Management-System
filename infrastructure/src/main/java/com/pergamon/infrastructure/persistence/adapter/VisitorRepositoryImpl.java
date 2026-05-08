package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import com.pergamon.core.entites.Visitor;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.core.interfaces.IVisitorRepository;
import com.pergamon.infrastructure.persistence.repository.IVisitorDA;

@Repository
public class VisitorRepositoryImpl implements IVisitorRepository, IRepository<Visitor> {
	
	private final Jdbi _jdbi;
	
	public VisitorRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Visitor> GetVisitorsByLetters(String letters, String organizationPerId)
	{
		return _jdbi.withExtension(IVisitorDA.class, da -> da.GetVisitorsByLetters(letters, organizationPerId));
	}
	
	public Visitor GetVisitorById(UUID id, String organizationPerId)
	{
		return _jdbi.withExtension(IVisitorDA.class, da -> da.GetVisitorById(id, organizationPerId));
	}
	
	public Visitor GetVisitorByIdentity(String eMail, String passwordHash, String organizationPerId)
	{
		return _jdbi.withExtension(IVisitorDA.class, da -> da.GetVisitorByIdentity(eMail, passwordHash, organizationPerId));
	}
	
	public void add(Visitor visitor)
	{
		_jdbi.useExtension(IVisitorDA.class, da -> {da.add(visitor, visitor.organizationPerId);});
	}
	
	public void update(Visitor visitor)
	{
		_jdbi.useExtension(IVisitorDA.class, da -> {da.update(visitor, visitor.organizationPerId);});
	}
	
	public void delete(UUID id, String organizationPerId)
	{
		_jdbi.useExtension(IVisitorDA.class, da -> da.delete(id,organizationPerId));
	}
}
