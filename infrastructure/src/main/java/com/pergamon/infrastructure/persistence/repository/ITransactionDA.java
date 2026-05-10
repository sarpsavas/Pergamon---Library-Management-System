package com.pergamon.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;

@RegisterBeanMapper(Transaction.class)
public interface ITransactionDA {

	@SqlQuery("")
	List<Transaction> GetUserTransactionsByUId(@Bind("id") String unıqUserId, @Define("organization_per_id") String organizationPerId);
	
	@SqlQuery("")
	List<Transaction> GetAllTransactions(@Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("INSERT INTO Transactions (TransactionId, UserId, Author, Category, Availability,Pages) "
			+ "VALUES ( :id, :name, :author, :bookType, :availability, :pageNumber);")
	void add(@BindBean Transaction transaction, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void update(@BindBean Transaction transaction, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void delete(@Bind("id") UUID id, @Define("organization_per_id") String organizationPerId);
}
