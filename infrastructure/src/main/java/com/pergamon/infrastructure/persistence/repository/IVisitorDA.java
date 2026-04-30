package com.pergamon.infrastructure.persistence.repository;



import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Visitor;

@RegisterBeanMapper(Visitor.class)
public interface IVisitorDA {
	
	@SqlQuery("")
	List<Visitor> GetVisitorsByLetters(@Bind("letters") String letters);
	
	@SqlQuery("")
	Visitor GetVisitorById(@Bind("id")UUID id);
	
	@SqlQuery("")
	Visitor GetVisitorByIdentity(@Bind("eMail") String eMail,@Bind("passwordHash") String passwordHash);
	
	@SqlUpdate("")
	void Add(@BindBean Visitor visitor);
	
	@SqlUpdate("")
	void Update(@BindBean Visitor visitor);
	
	@SqlUpdate("")
	void Delete(@Bind("id") UUID id);
	
}
