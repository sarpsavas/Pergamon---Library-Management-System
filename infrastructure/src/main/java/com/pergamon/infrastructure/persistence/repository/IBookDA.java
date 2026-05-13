package com.pergamon.infrastructure.persistence.repository;

import com.pergamon.core.entites.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.*;

@RegisterBeanMapper(Book.class)
public interface IBookDA {
	
	//TODO: search all
	@SqlQuery("")
	List<Book> GetAll();
	
	@SqlQuery("")
	List<Book> GetBookByLetters(@Bind("letters") String letters, @Bind("organization_table") String organizationTable);
	
	@SqlQuery("SELECT * FROM Books WHERE :Id = Id;")
	Book GetBookByBookId(@Bind("Id") String Id, @Define("organization_per_id") String organizationPerId);
	//TODO: Add
	@SqlUpdate("INSERT INTO Books (BookId, Name, Author, Category, Availability,Pages) "
			+ "VALUES ( :id, :name, :author, :bookType, :availability, :pageNumber);")
	void add(@BindBean Book book, @Define("organization_per_id") String organizationPerId);
	
	//TODO: Update
	@SqlUpdate("")
	void update(@BindBean Book book, @Define("organization_per_id") String organizationPerId);
	
	//TODO: Delete
	@SqlUpdate("")
	void delete(@Bind("Id") String Id, @Define("organization_per_id") String organizationPerId );
	

}
