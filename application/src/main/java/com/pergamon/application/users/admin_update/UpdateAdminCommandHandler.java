package com.pergamon.application.users.admin_update;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.helpers.HashHelper;
import com.pergamon.core.entites.Admin;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.enums.AdminProfile;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.interfaces.IRepository;

@Component
public class UpdateAdminCommandHandler {
	
	 private IRepository<Transaction> _repositoryTr;
	 private IRepository<Admin> _repositoryAd;
	 
	 public UpdateAdminCommandHandler(IRepository<Transaction> repositoryTr,
			 IRepository<Admin> repositoryAd)
	 {
		 _repositoryTr = repositoryTr;
		 _repositoryAd = repositoryAd;
	 }
	 @CommandHandler
	 public void handle(UpdateAdminCommand request)
	 {
		 Transaction transaction = new Transaction();
		 transaction.organizationPerId = request.organizationPerId();
		 transaction.type = TransactionType.UPDATE_ADMIN;
		 transaction.userId = request.sAdminId();
		 
		 try {
			 Admin admin = new Admin();
			 admin.id = request.adminId();
			 admin.accountId = request.adminPerId();
			 admin.organizationPerId = request.organizationPerId();
			 admin.setName(request.name());
			 admin.setLastname(request.lastname());
			 admin.SetEMail(request.eMail());
			 
			 HashHelper hasher = new HashHelper();
			 admin.passwordHash = hasher.hashConverter(request.password());
			 admin.status = AdminProfile.DEFAULT_ADMIN;
			 _repositoryAd.update(admin);
			 transaction.succes = Succes.SUCCESSFUL;
			 _repositoryTr.add(transaction);
		} catch (Exception e) {
			 transaction.succes = Succes.UNSUCCESSFUL;
			 _repositoryTr.add(transaction);
			 throw new IllegalArgumentException("UpdateAdminCommandHandler Exception");
		}
	 }
}
