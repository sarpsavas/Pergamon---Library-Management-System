package com.pergamon.infrastructure.persistence.repository;

import com.pergamon.core.entites.*;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.*;

@RegisterBeanMapper(Book.class)
public interface IBookDA {
	
	//TODO: search all
	@SqlQuery("")
	List<Book> GetAll();
	
	@SqlQuery("SELECT * FROM Books WHERE :Id = Id;")
	Book GetBookByBookId(@Bind("Id") String Id);
	//TODO: Add
	@SqlUpdate("INSERT INTO Books (Id, Name, Author, BookType, Availability,Pages) \r\n"
			+ "VALUES (:Id, :Name, :Author, 1, 1, :PageNumber);")
	void Add(@BindBean Book book);
	
	//TODO: Update
	@SqlUpdate("")
	void Update(@BindBean Book book);
	
	//TODO: Delete
	@SqlUpdate("")
	void Delete(@Bind("Id") String Id );
	

}
