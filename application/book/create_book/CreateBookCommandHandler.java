package com.pergamon.application.book.create_book;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.interfaces.IRepository;

@Component
public class CreateBookCommandHandler {
	
	private IRepository<CreateBookCommand> _repository;
	
	
	public CreateBookCommandHandler(IRepository<CreateBookCommand> repository)
	{
		_repository = repository;
	}
	
	@CommandHandler
	public void Handle(CreateBookCommand request)
	{
		_repository.Add(request);
	}
}
