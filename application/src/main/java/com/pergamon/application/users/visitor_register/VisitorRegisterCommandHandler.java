package com.pergamon.application.users.visitor_register;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.enums.VisitorStatus;
import com.pergamon.core.interfaces.IRepository;

@Component
public class VisitorRegisterCommandHandler {

		private final IRepository<Transaction> _repositoryTr;
		private final IRepository<Visitor> _repositoryVi;
		
		public VisitorRegisterCommandHandler(IRepository<Transaction> repositoryTr,
				IRepository<Visitor> repositoryVi)
		{
			_repositoryTr = repositoryTr;
			_repositoryVi =repositoryVi;
		}
		
		@CommandHandler
		public void handle(VisitorRegisterCommand request)
		{
			Transaction transaction = new Transaction();
			transaction.organizationPerId = request.organizationPerId();
			transaction.type = TransactionType.CREATE_VISITOR;
			transaction.setDescription("-");
			transaction.userId = request.adminId();
			try {
				Visitor visitor = new Visitor(request.accountProfile(),VisitorStatus.ACTIVE);
				visitor.setName(request.name());
				visitor.setLastname(request.lastname());
				visitor.SetEMail(request.eMail());
				visitor.organizationPerId = request.organizationPerId();
				
				_repositoryVi.add(visitor);
				transaction.succes = Succes.SUCCESSFUL;
				_repositoryTr.add(transaction);
			} catch (Exception e) {
				transaction.succes = Succes.UNSUCCESSFUL;
				_repositoryTr.add(transaction);
				throw new IllegalArgumentException("VisitorRegisterCommand  Exception");
			}
		}
}
