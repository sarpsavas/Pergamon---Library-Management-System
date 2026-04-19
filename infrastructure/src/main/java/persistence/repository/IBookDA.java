package persistence.repository;

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
	@SqlUpdate("")
	void Add(@BindBean Book book);
	
	//TODO: Update
	@SqlUpdate("")
	void Update(@BindBean Book book);
	
	//TODO: Delete
	@SqlUpdate("")
	void Delete(@Bind("Id") String Id );
	

}
