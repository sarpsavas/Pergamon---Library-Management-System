package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.springframework.stereotype.Repository;

import com.pergamon.core.entites.Book;
import com.pergamon.core.enums.BookType;
import com.pergamon.core.interfaces.IBookRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IBookDA;

@Repository
public class BookRepositoryImpl implements IBookRepository{
	
	private final Jdbi _jdbi;
	
	public BookRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Book> GetBooks(String organizationPerId, int pageNumber)
	{
		return _jdbi.withExtension(IBookDA.class, da -> da.GetBooks(organizationPerId,pageNumber * 10));
	}
	
	public List<Book> GetBooksByLetters(String letters,BookType type, int page, String organizationPerId)
	{
		
		return _jdbi.withExtension(IBookDA.class, da -> da.GetBookByLetters(letters, type, page * 10, organizationPerId));
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
	public void addImage(String bookId, String organizationPerId, String url)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.addImage(bookId, organizationPerId, url);
		});
	}
	
	public void update(Book book)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.update(book,book.organizationPerId);
		});
	}
	
	public void delete(String id, String organizationPerId)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.delete(id,organizationPerId);
		});
	}
	
}

