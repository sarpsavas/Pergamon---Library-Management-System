package com.pergamon.application.users.teacher_register;

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
public class TeacherRegisterCommandHandler {
	
	private IRepository<Visitor> _repositoryVi;
	private IRepository<Transaction> _repositoryTr;
	private IOrganizationRepository _orgRepository;
	
	public TeacherRegisterCommandHandler(IRepository<Visitor> repostoryVi,
			IRepository<Transaction> repostoryTr,
			IOrganizationRepository orgRepository)
	{
		_repositoryVi = repostoryVi;
		_repositoryTr = repostoryTr;
		_orgRepository = orgRepository;
	}
	
	@CommandHandler
	public void handle(TeacherRegisterCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.CREATE_VISITOR;
		try 
		{
			var organization = _orgRepository.getOrganizationByOrganizationPerId(request.organizationperid());
			if(organization == null) {throw new IllegalArgumentException("organization error");}	
		} 
		catch (Exception e) 
		{
			throw new IllegalArgumentException("organization error" + e.getMessage());
		}
		
		try {
			transaction.organizationPerId = request.organizationperid();
			
			Visitor visitor = new Visitor(AccountProfile.TEACHER, VisitorStatus.ACTIVE);
			transaction.userId = visitor.id;
			visitor.setName(request.name()); 
			visitor.setLastname(request.lastname());
			
			HashHelper hasher = new HashHelper();
			visitor.passwordHash = hasher.hashConverter(request.password());
			
			visitor.SetEMail(request.email());
			visitor.organizationPerId = request.organizationperid();
			
			_repositoryVi.add(visitor);
			transaction.succes = Succes.SUCCESSFUL;
		} catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositoryTr.add(transaction);
			throw new IllegalArgumentException("TeacherRegisterCommand error"+e.getMessage());
		}
	}
}
