package com.pergamon.infrastructure.persistence.repository;

import com.pergamon.core.entites.*;
import com.pergamon.core.enums.BookType;

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
	List<Book> GetBooks(@Define("organization_per_id") String organizationPerId, @Bind("page") int pageNumber);
	
	@SqlQuery("")
	List<Book> GetBookByLetters(@Bind("letters") String letters, @Bind("type") BookType type, @Bind("page") int page, @Bind("organization_table") String organizationTable);
	
	@SqlQuery("SELECT * FROM Books WHERE :Id = Id;")
	Book GetBookByBookId(@Bind("Id") String Id, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("INSERT INTO Books (BookId, Name, Author, Category, Availability,Pages) "
			+ "VALUES ( :id, :name, :author, :bookType, :availability, :pageNumber);")
	void add(@BindBean Book book, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void addImage(@Bind("id") String bookId, @Define("organization") String organizationPerId, @Bind("id")String url);
	
	@SqlUpdate("")
	void update(@BindBean Book book, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void delete(@Bind("Id") String Id, @Define("organization_per_id") String organizationPerId );
	

}
