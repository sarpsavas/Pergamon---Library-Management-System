package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;



import org.springframework.stereotype.Repository;

import com.pergamon.core.entites.Book;
import com.pergamon.core.interfaces.IBookRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IBookDA;

@Repository
public class BookRepositoryImpl implements IBookRepository, IRepository<Book>{
	
	private final Jdbi _jdbi;
	
	public BookRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Book> GetBooksByLetters(String letters)
	{
		return _jdbi.withExtension(IBookDA.class, da -> da.GetBookByLetters(letters));
	}
	
	public Book GetBookByBookId(String bookId)
	{
		return _jdbi.withExtension(IBookDA.class, da -> da.GetBookByBookId(bookId));
	}
	
	public void Add(Book book)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.Add(book);
		});
	}
	
	public void Update(Book book)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.Update(book);
		});
	}
	
	public void Delete(UUID id)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.Delete(id);
		});
	}
	
}

