package com.pergamon.application.organizations.add_organization;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Transaction;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.entites.Organization;
import com.pergamon.core.interfaces.IRepository;

@Component
public class AddOrganizationCommandHandler {
	
	private IRepository<Transaction> _repositoryTr;
	private IRepository<Organization> _repositoryOr;
	
	public AddOrganizationCommandHandler(IRepository<Transaction> repositoryTr,
			IRepository<Organization> repositoryOr)
	{
		_repositoryTr = repositoryTr;
		_repositoryOr = repositoryOr;
	}
	
	@CommandHandler
	public void handle(AddOrganizationCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.ADD_ORGANIZATION;
		//TODO: organization migration / general administrator repository / Management  transactions repository
	}
}
