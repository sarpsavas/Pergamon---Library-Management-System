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
	public List<Barrowed> GetVisitorBarrowedsByVisitorId(UUID visitorId)
	{
		return _jdbi.withExtension(IBarrowedDA.class, da -> da.GetBarrowedsByVisitorId(visitorId));
	}
	
	public Barrowed GetBarrowedByBarrowedId(UUID barrowedId)
	{
		return _jdbi.withExtension(IBarrowedDA.class, da -> da.GetBarrowedByBarrowedId(barrowedId));
	}
	
	public void add(Barrowed barrowed)
	{
		_jdbi.useExtension(IBarrowedDA.class, da -> da.add(barrowed));
	}
	
	public void update(Barrowed barrowed)
	{
		_jdbi.useExtension(IBarrowedDA.class, da -> da.update(barrowed));
	}
	
	public void delete(UUID barrowedId)
	{
		_jdbi.useExtension(IBarrowedDA.class, da -> da.delete(barrowedId));
	}
}
