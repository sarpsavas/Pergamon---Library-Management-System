package com.pergamon.infrastructure.persistence.repository;

import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Admin;

@RegisterBeanMapper(Admin.class)
public interface IGeneralAdminDA {
	
	@SqlQuery("")
	Admin GetAdminById(@Bind("id")UUID id);
	
	@SqlQuery("")
	Admin GetIdByıdentity(@Bind("email")String email, @Bind("passwordhash")String passwordHash);
	
	@SqlUpdate("")//define null
	void add(@BindBean Admin admin, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")//define null
	void update(@BindBean Admin admin, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")//define null
	void delete(@Bind("id") UUID id, @Define("organization_per_id") String organizationPerId);
}
