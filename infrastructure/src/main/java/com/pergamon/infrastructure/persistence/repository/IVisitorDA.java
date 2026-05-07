package com.pergamon.infrastructure.persistence.repository;



import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Visitor;

@RegisterBeanMapper(Visitor.class)
public interface IVisitorDA {
	
	@SqlQuery("SELECT * FROM Visitors WHERE ")
	List<Visitor> GetVisitorsByLetters(@Bind("letters") String letters, @Define("organization_per_id") String organizationPerId);
	
	@SqlQuery("")
	Visitor GetVisitorById(@Bind("id")UUID id, @Define("organization_per_id") String organizationPerId);
	
	@SqlQuery("")
	Visitor GetVisitorByIdentity(@Bind("eMail") String eMail,@Bind("passwordHash") String passwordHash, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void add(@BindBean Visitor visitor, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void update(@BindBean Visitor visitor, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void delete(@Bind("id") UUID id, @Define("organization_per_id") String organizationPerId);
	
}
