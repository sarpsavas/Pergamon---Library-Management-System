package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;

import com.pergamon.core.entites.Barrowed;
import com.pergamon.core.interfaces.IBarrowedRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IBarrowedDA;

public class BarrowedRepositoryImpl implements IBarrowedRepository, IRepository<Barrowed>{

	private final Jdbi _jdbi;
	
	public BarrowedRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	public List<Barrowed> GetVisitorBarrowedsByVisitorId(UUID visitorId, String organizationPerId)
	{
		return _jdbi.withExtension(IBarrowedDA.class, da -> da.GetBarrowedsByVisitorId(visitorId, organizationPerId));
	}
	
	public Barrowed GetBarrowedByBarrowedId(UUID barrowedId, String organizationPerId)
	{
		return _jdbi.withExtension(IBarrowedDA.class, da -> da.GetBarrowedByBarrowedId(barrowedId,organizationPerId));
	}
	
	public void add(Barrowed barrowed)
	{
		_jdbi.useExtension(IBarrowedDA.class, da -> da.add(barrowed, barrowed.organizationPerId));
	}
	
	public void update(Barrowed barrowed)
	{
		_jdbi.useExtension(IBarrowedDA.class, da -> da.update(barrowed, barrowed.organizationPerId));
	}
	
	public void delete(UUID barrowedId, String organizationPerId)
	{
		_jdbi.useExtension(IBarrowedDA.class, da -> da.delete(barrowedId, organizationPerId));
	}
}
