package com.pergmaon.application.users.visitor_delete;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.interfaces.IRepository;

@Component
public class VisitorDeleteCommandHandler {
	
	private IRepository<Visitor> _repositoryUs;
	private IRepository<Transaction> _repositoryTr;
	
	public VisitorDeleteCommandHandler(IRepository<Visitor> repositoryUs,
			IRepository<Transaction> repositoryTr)
	{
		_repositoryTr = repositoryTr;
		_repositoryUs = repositoryUs;
	}
	@CommandHandler
	public void Handle(VisitorDeleteCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.DELETE_VISITOR;
		try {
			_repositoryUs.delete(request.visitorId(),request.organizationPerId());
			transaction.succes = Succes.SUCCESSFUL;
			
		} catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositoryTr.add(transaction);
			
		}
		
	}
}
