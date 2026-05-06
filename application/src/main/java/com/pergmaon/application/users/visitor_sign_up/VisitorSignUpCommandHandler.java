package com.pergmaon.application.users.visitor_sign_up;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.enums.AccountProfile;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.core.interfaces.IVisitorRepository;

@Component
public class VisitorSignUpCommandHandler {
	private IRepository<Visitor> _repositoryVi;
	private IRepository<Transaction> _repositoryTr;
	private IVisitorRepository _visRepository;
	
	public VisitorSignUpCommandHandler(IRepository<Visitor> repositoryVi,
			IRepository<Transaction> repositoryTr,
			IVisitorRepository visRepository)
	{
		_repositoryVi = repositoryVi;
		_repositoryTr = repositoryTr;
		_visRepository = visRepository;
	}
	@CommandHandler
	public void  handle(VisitorSignUpCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.CREATE_VISITOR;
		try {
			Visitor visitor = new Visitor(AccountProfile.STUDENT);
			visitor.SetEMail(request.eMail());
			visitor.name = request.name();
			visitor.lastname = request.lastName();
			visitor.passwordHash 
			
			transaction.succes = Succes.SUCCESSFUL;
			
		} 
		catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
		}
		
	}
}
