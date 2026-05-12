package com.pergamon.application.users.master_admin_register;

import java.net.Authenticator.RequestorType;

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
public class MasterAdminRegisterCommandHandler {

		private IRepository<Transaction> _repositoryTr;
		private IRepository<Admin> _repositoryAd;
		
		public MasterAdminRegisterCommandHandler(IRepository<Transaction> repositoryTr, IRepository<Admin> repositoryAd)
		{
			_repositoryAd = repositoryAd;
			_repositoryTr = repositoryTr;
		}
		
		@CommandHandler
		public void handle(MasterAdminRegisterCommand request)
		{
			Transaction transaction = new Transaction();
			transaction.type = TransactionType.REGISTER_MASTER_ADMIN;
			try 
			{
				transaction.organizationPerId = request.organizationperid();
				transaction.userId = request.generalAdmin();
				transaction.setDescription("-");
				
				Admin admin = new Admin();
				admin.organizationPerId = request.organizationperid();
				admin.setName(request.name());
				admin.setLastname(request.lastname());
				admin.SetEMail(request.email());
				admin.status = AdminProfile.MASTER_ADMIN;
				
				HashHelper hasher = new HashHelper();
				admin.passwordHash = hasher.hashConverter(request.password());
				
				_repositoryAd.add(admin);
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
