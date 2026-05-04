package com.pergamon.infrastructure.persistence.repository;

import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Organization;
import com.pergamon.core.entites.Visitor;

@RegisterBeanMapper(Organization.class)
public interface IOrganizationsDA {
	
	@SqlQuery("")
	Organization getOrganizationByOrganizationId(@Bind("id")  UUID organizationId);
	
	@SqlQuery("")
	Organization getOrganizationByOrganizationPerId(@Bind("id")  String organizationperId);
	
	@SqlUpdate("")
	void Add(@BindBean Organization visitor);
	
	@SqlUpdate("")
	void Update(@BindBean Organization visitor);
	
	@SqlUpdate("")
	void Delete(@Bind("id") UUID id);
}
