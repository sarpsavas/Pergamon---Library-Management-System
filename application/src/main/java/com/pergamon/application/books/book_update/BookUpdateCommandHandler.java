package com.pergamon.application.books.book_update;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Book;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.enums.Availability;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.interfaces.IBookRepository;
import com.pergamon.core.interfaces.IRepository;

@Component
public class BookUpdateCommandHandler {

	private IBookRepository _boRepository;
	private IRepository<Transaction> _repositoryTr;
	
	public BookUpdateCommandHandler(IBookRepository boRepository,IRepository<Transaction> repositoryTr)
	{
		_boRepository = boRepository;
		_repositoryTr = repositoryTr;
	} 
	
	@CommandHandler
	public void handle(BookUpdateCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.UPDATE_BOOK;
		transaction.organizationPerId = request.organizationPerId();
		transaction.userId = request.defaultAdminId();
		transaction.setDescription("-");
		
		try 
		{
			Book book = new Book();
			book.id = request.id();
			book.setName(request.name());
			book.setAuthor(request.author());
			book.availability = Availability.AVAILABLE;
			book.bookType = request.bookType();
			book.setPageNumber(request.pageNumber());
			book.organizationPerId = request.organizationPerId();
			
			_boRepository.update(book);
			
			transaction.succes = Succes.SUCCESSFUL;
			_repositoryTr.add(transaction);
		} 
		catch (Exception e) 
		{
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositoryTr.add(transaction);
			throw new IllegalArgumentException("BookUpdateCommand exception");
		}
	}
}
