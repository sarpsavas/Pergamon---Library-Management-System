package com.pergmaon.application.users.visitor_register_approval;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.helpers.HashHelper;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.enums.AccountProfile;
import com.pergamon.core.enums.ApprovalVisitorRegister;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.enums.VisitorStatus;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.core.interfaces.IVisitorRepository;

@Component
public class VisitorRegisterApprovalCommandHandler {
	private IRepository<Visitor> _repositoryVi;
	private IRepository<Transaction> _repositoryTr;
	private IVisitorRepository _visRepository;
	
	
	public VisitorRegisterApprovalCommandHandler(IRepository<Visitor> repositoryVi,
			IRepository<Transaction> repositoryTr,
			IVisitorRepository visRepository)
	{
		_repositoryVi = repositoryVi;
		_repositoryTr = repositoryTr;
		_visRepository = visRepository;
	}
	@CommandHandler
	public void handle(VisitorRegisterApprovalCommand request)
	{
		Transaction transaction = new Transaction();
		if(request.approval() == ApprovalVisitorRegister.ACCEPT)
		{
			transaction.type = TransactionType.REGISTER_VISITOR_ACCEPT;
			try {
				Visitor visitor = _visRepository.GetVisitorById(request.id(),request.organizationperid());
				visitor.status = VisitorStatus.ACTIVE;

				_repositoryVi.update(visitor);
				
				transaction.succes = Succes.SUCCESSFUL;
				_repositoryTr.add(transaction);
			} 
			catch (Exception e) 
			{
				transaction.succes = Succes.UNSUCCESSFUL;
				_repositoryTr.add(transaction);
			}
		}
		else if(request.approval() == ApprovalVisitorRegister.REJECT)
		{
			transaction.type = TransactionType.REGISTER_VISITOR_REJECT;
			try {
				Visitor visitor = _visRepository.GetVisitorById(request.id(),request.organizationperid());
				visitor.status = VisitorStatus.ACTIVE;

				_repositoryVi.update(visitor);
				
				transaction.succes = Succes.SUCCESSFUL;
				_repositoryTr.add(transaction);
			} 
			catch (Exception e) 
			{
				transaction.succes = Succes.UNSUCCESSFUL;
				_repositoryTr.add(transaction);
			}
		}
		
		
		
	}
}
