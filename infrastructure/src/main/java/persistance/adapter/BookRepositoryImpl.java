package persistance.adapter;

import entities.Book;
import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.Jdbi;

import interfaces.*;
import persistence.repository.IBookDA;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

public class BookRepositoryImpl implements IBookRepository, IRepository<Book>{
	
	private final Jdbi _jdbi;
	
	public BookRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Book> GetBooksByLetters(String letters)
	{
		return _jdbi.withExtension(IBookDA.class, da -> da.GetAll().stream().map(this::toDomain).collect(Collectors.toList()));
	}
	
	public void Add(Book book)
	{
		
	}
	
	public void Update(Book book)
	{
		
	}
	
	public void Delete(Book book)
	{
		
	}
	
}

