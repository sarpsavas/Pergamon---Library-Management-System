package com.pergmaon.application.users.visitor_sign_up;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.helpers.HashHelper;
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
	
	
	public VisitorSignUpCommandHandler(IRepository<Visitor> repositoryVi,
			IRepository<Transaction> repositoryTr)
	{
		_repositoryVi = repositoryVi;
		_repositoryTr = repositoryTr;
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
			HashHelper hasher = new HashHelper();
			visitor.passwordHash = hasher.hashConverter(request.Password());
			
			_repositoryVi.add(visitor);
			
			transaction.succes = Succes.SUCCESSFUL;
			_repositoryTr.add(transaction);
		} 
		catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
		}
		
	}
}
