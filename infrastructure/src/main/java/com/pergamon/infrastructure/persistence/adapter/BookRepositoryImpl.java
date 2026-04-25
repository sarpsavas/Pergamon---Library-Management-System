package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.Jdbi;


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
		return null; 
	}
	
	public void Add(Book book)
	{
		_jdbi.useExtension(IBookDA.class, da -> {da.Add(null);
		});
	}
	
	public void Update(Book book)
	{
		
	}
	
	public void Delete(Book book)
	{
		
	}
	
}

