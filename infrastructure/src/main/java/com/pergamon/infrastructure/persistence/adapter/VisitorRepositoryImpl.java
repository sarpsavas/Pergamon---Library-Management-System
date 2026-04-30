package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;

import com.pergamon.core.entites.Visitor;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.core.interfaces.IVisitorRepository;
import com.pergamon.infrastructure.persistence.repository.IVisitorDA;

public class VisitorRepositoryImpl implements IVisitorRepository, IRepository<Visitor> {
	
	private final Jdbi _jdbi;
	
	public VisitorRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Visitor> GetVisitorsByLetters(String letters)
	{
		return _jdbi.withExtension(IVisitorDA.class, da -> da.GetVisitorsByLetters(letters));
	}
	
	public Visitor GetVisitorById(UUID id)
	{
		return _jdbi.withExtension(IVisitorDA.class, da -> da.GetVisitorById(id));
	}
	
	public Visitor GetVisitorByIdentity(String eMail, String passwordHash)
	{
		return _jdbi.withExtension(IVisitorDA.class, da -> da.GetVisitorByIdentity(eMail, passwordHash));
	}
	
	public void Add(Visitor visitor)
	{
		_jdbi.useExtension(IVisitorDA.class, da -> {da.Add(visitor);});
	}
	
	public void Update(Visitor visitor)
	{
		_jdbi.useExtension(IVisitorDA.class, da -> {da.Update(visitor);});
	}
	
	public void Delete(UUID id)
	{
		_jdbi.useExtension(IVisitorDA.class, da -> {da.Delete(id);});
	}
}
