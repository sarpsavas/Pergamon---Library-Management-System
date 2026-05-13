package com.pergamon.application.books.book_delete;

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
public class BookDeleteCommandHandler {

	private IBookRepository _repositoryBo;
	private IRepository<Transaction> _repositoryTr;
	
	public BookDeleteCommandHandler(IBookRepository repositoryBo,IRepository<Transaction> repositoryTr)
	{
		_repositoryBo = repositoryBo;
		_repositoryTr = repositoryTr;
	} 
	
	@CommandHandler
	public void handle(BookDeleteCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.DELETE_BOOK;
		transaction.organizationPerId = request.organizationPerId();
		transaction.userId = request.defaultAdminId();
		transaction.setDescription("-");
		
		try 
		{
			
			_repositoryBo.delete(request.bookPerId(),request.organizationPerId());
			
			transaction.succes = Succes.SUCCESSFUL;
			_repositoryTr.add(transaction);
		} 
		catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositoryTr.add(transaction);
			throw new IllegalArgumentException("BookDeleteCommand exception");
		}
	}
}
