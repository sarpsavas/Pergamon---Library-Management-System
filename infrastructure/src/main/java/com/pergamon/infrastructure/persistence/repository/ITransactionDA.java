package com.pergamon.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;

@RegisterBeanMapper(Transaction.class)
public interface ITransactionDA {

	@SqlQuery("")
	List<Transaction> GetUserTransactionsByUId(@Bind("id") String unıqUserId);
	
	@SqlQuery("")
	List<Transaction> GetAllTransactions();
	
	@SqlUpdate("")
	void add(@BindBean Transaction transaction);
	
	@SqlUpdate("")
	void update(@BindBean Transaction transaction);
	
	@SqlUpdate("")
	void delete(@Bind("id") UUID id);
}
