package com.pergamon.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Barrowed;

@RegisterBeanMapper(Barrowed.class)
public interface IBarrowedDA {
	
	@SqlQuery()
	List<Barrowed> GetBarrowedsByVisitorId(@Bind("Id") UUID id, @Define("organization_per_id") String organizationPerId);
	
	@SqlQuery
	Barrowed GetBarrowedByBarrowedId(@Bind("Id") UUID id, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void add(@BindBean Barrowed barrowed, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void update(@BindBean Barrowed barrowed, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void delete(@Bind("id") UUID BarrowedId, @Define("organization_per_id") String organizationPerId);
}
