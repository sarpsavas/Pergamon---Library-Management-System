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
	
	public List<Book> GetBooksByLetters(String letters, String organizationPerId)
	{
		return _jdbi.withExtension(IBookDA.class, da -> da.GetBookByLetters(letters, organizationPerId));
	}
	
	public Book GetBookByBookId(String bookId, String organizationPerId)
	{
		return _jdbi.withExtension(IBookDA.class, da -> da.GetBookByBookId(bookId, organizationPerId));
	}
	
	public void add(Book book)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.add(book,book.organizationPerId);
		});
	}
	
	public void update(Book book)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.update(book,book.organizationPerId);
		});
	}
	
	public void delete(UUID id, String organizationPerId)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.delete(id,organizationPerId);
		});
	}
	
}

