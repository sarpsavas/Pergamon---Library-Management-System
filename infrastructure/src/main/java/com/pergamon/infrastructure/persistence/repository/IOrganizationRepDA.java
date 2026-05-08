package com.pergamon.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Admin;
import com.pergamon.core.entites.Organization;

@RegisterBeanMapper(Organization.class)
public interface IOrganizationRepDA {

		@SqlQuery("")
		List<Organization> getOrganizations();
		
		@SqlQuery("")
		Organization getOrganizationByOrganizationPerId(@Bind("id") String organizationPerId);
		
		@SqlUpdate("")
		void add(@BindBean Organization admin);
		
		@SqlUpdate("")
		void update(@BindBean Organization admin);
		
		@SqlUpdate("")
		void delete(@Bind("id") UUID id);
		
}
