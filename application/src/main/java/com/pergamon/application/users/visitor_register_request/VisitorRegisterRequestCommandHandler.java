package com.pergamon.application.users.visitor_register_request;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.helpers.HashHelper;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.enums.AccountProfile;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.enums.VisitorStatus;
import com.pergamon.core.interfaces.IOrganizationRepository;
import com.pergamon.core.interfaces.IRepository;

@Component
public class VisitorRegisterRequestCommandHandler {
	
	private IRepository<Visitor> _repositoryVi;
	private IRepository<Transaction> _repositoryTr;
	private IOrganizationRepository _orgRepository;
	
	public VisitorRegisterRequestCommandHandler(IRepository<Visitor> repositoryVi, IRepository<Transaction> repositoryTr,IOrganizationRepository orgRepository)
	{
		_repositoryVi = repositoryVi;
		_repositoryTr = repositoryTr;
		_orgRepository = orgRepository;
	}
	@CommandHandler
	public void handle(VisitorRegisterRequestCommand request)
	{
		
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.REGISTER_VISITOR_REQUEST;
		try {
			var organization = _orgRepository.getOrganizationByOrganizationPerId(request.organizationperid());//organizasyon varmı kontrolü
			if(organization == null) {throw new IllegalArgumentException("organization error");}	
		} 
		catch (Exception e) {
			throw new IllegalArgumentException("organization error" + e.getMessage());
		}
		
		try {
			transaction.organizationPerId = request.organizationperid();
			
			Visitor visitor = new Visitor(AccountProfile.STUDENT, VisitorStatus.BLOCKED);
			transaction.userId = visitor.id;
			visitor.name = request.name();
			visitor.lastname = request.lastname();
			
			HashHelper hasher = new HashHelper();
			visitor.passwordHash = hasher.hashConverter(request.password());
			
			visitor.SetEMail(request.email());
			visitor.organizationPerId = request.organizationperid();
			
			_repositoryVi.add(visitor);
			transaction.succes = Succes.SUCCESSFUL;
		} catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositoryTr.add(transaction);
			throw new IllegalArgumentException("VisitorRegisterRequestCommand error"+e.getMessage());
		}
		
		_repositoryTr.add(transaction);
		
		
	}
}
