package com.pergamon.application.command.book;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Book;
import com.pergamon.core.interfaces.IRepository;

@Component
public class CreateBookCommandHandler {
	
	private IRepository<Book> _repository;
	
	
	public CreateBookCommandHandler(IRepository<Book> repository)
	{
		_repository = repository;
	}
	
	@CommandHandler
	public void Handle(CreateBookCommand request)
	{
		Book book = new Book();
		book.Name = request.name();
		
		_repository.Add(book);
	}
}
