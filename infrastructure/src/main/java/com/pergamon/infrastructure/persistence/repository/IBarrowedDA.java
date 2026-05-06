package com.pergamon.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Barrowed;

@RegisterBeanMapper(Barrowed.class)
public interface IBarrowedDA {
	
	@SqlQuery()
	List<Barrowed> GetBarrowedsByVisitorId(@Bind("Id") UUID id);
	
	@SqlQuery
	Barrowed GetBarrowedByBarrowedId(@Bind("Id") UUID id);
	
	@SqlUpdate("")
	void add(@BindBean Barrowed barrowed);
	
	@SqlUpdate("")
	void update(@BindBean Barrowed barrowed);
	
	@SqlUpdate("")
	void delete(@Bind("id") UUID BarrowedId);
}
