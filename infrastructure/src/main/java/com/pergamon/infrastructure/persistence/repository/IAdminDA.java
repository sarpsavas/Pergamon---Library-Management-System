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
import com.pergamon.core.entites.Visitor;

@RegisterBeanMapper(Admin.class)
public interface IAdminDA {
	
	@SqlQuery("")
	List<Admin> GetAdminsByLetters(@Bind("letters") String letters, @Define("organization_per_id") String organizationPerId);
	
	@SqlQuery("")
	Admin GetAdminById(@Bind("letters") UUID id, @Define("organization_per_id") String organizationPerId);
	
	@SqlQuery("")
	Admin GetAdminByIdentity(@Bind("eMail") String eMail, @Bind("passwordHash") String passwordHash, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("INSERT INTO Admins (UserId, Name, Author, Category, Availability,Pages) "
			+ "VALUES ( :id, :name, :author, :bookType, :availability, :pageNumber);")
	void add(@BindBean Admin admin, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void update(@BindBean Admin admin, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void delete(@Bind("id") UUID id, @Define("organization_per_id") String organizationPerId);
}
