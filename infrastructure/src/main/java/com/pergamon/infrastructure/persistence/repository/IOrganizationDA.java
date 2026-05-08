package com.pergamon.infrastructure.persistence.repository;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Organization;

@RegisterBeanMapper(Organization.class)
public interface IOrganizationDA {
	

	@SqlUpdate("""
		    CREATE USER <schemaName>
		    IDENTIFIED BY x
		    DEFAULT TABLESPACE Organizations
		    QUOTA UNLIMITED ON Organizations
		    ACCOUNT LOCK
		    """) // TODO: must add table_space
	void addShema(@Define("id") String organizationPerId);
	
	
	
	@SqlUpdate("")
	void addBooksTable(@Define("id") String organizationPerId);
	
	@SqlUpdate("")
	void addTransactionsTable(@Define("id") String organizationPerId);
	
	@SqlUpdate("")
	void addAdminsTable(@Define("id") String organizationPerId);
	
	@SqlUpdate("")
	void addVisitorsTable(@Define("id") String organizationPerId);
	
	@SqlUpdate("")
	void addBarrowedsTable(@Define("id") String organizationPerId);
	
	@SqlUpdate("")
	void addFeedbacksTable(@Define("id") String organizationPerId);
	
	
	
	@SqlUpdate("")
	void deleteShema(@Define("id") String organizationPerId);
}
