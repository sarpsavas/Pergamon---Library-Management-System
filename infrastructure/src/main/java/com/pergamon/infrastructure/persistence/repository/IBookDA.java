package com.pergamon.infrastructure.persistence.repository;

import java.awt.print.Book;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.*;

@RegisterBeanMapper(Book.class)
public interface IBookDA {
	
	//TODO: search all
	@SqlQuery("")
	List<Book> GetAll();
	
	//TODO: Add
	@SqlUpdate("INSERT INTO Books (Id, Name, Author, BookType, Availability,Pages) \r\n"
			+ "VALUES ('PL9284668', 'Kendime Düşünceler', 'Marcus Aurelius', 3, 1, 245);")
	void Add(@BindBean Book book);
	
	//TODO: Update
	@SqlUpdate("")
	void Update(@BindBean Book book);
	
	//TODO: Delete
	@SqlUpdate("")
	void Delete(@Bind("Id") String Id );
	

}
