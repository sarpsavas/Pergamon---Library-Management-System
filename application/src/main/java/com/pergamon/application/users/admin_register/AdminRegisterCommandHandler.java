package com.pergamon.application.users.admin_register;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.helpers.HashHelper;
import com.pergamon.application.users.visitor_register_request.VisitorRegisterRequestCommand;
import com.pergamon.core.entites.Admin;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.enums.AccountProfile;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.enums.VisitorStatus;
import com.pergamon.core.interfaces.IOrganizationRepository;
import com.pergamon.core.interfaces.IOrganizationRepository;
import com.pergamon.core.interfaces.IRepository;

@Component
public class AdminRegisterCommandHandler {
	
	private IRepository<Admin> _repositoryAd;
	private IRepository<Transaction> _repositoryTr;
	private IOrganizationRepository _orgRepository;
	
	
	public AdminRegisterCommandHandler(IRepository<Admin> repositoryAd, IRepository<Transaction> repositoryTr,IOrganizationRepository orgRepository)
	{
		_repositoryAd = repositoryAd;
		_repositoryTr = repositoryTr;
		_orgRepository = orgRepository;
	}
	@CommandHandler
	public void handle(AdminRegisterCommand request)
	{
		
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.CREATE_ADMIN;
		try {
			var organization = _orgRepository.getOrganizationByOrganizationPerId(request.organizationperid());//organizasyon varmı kontrolü
			if(organization == null) {throw new IllegalArgumentException("organization error");}	
		} 
		catch (Exception e) {
			throw new IllegalArgumentException("organization error" + e.getMessage());
		}
		
		try {
			transaction.organizationPerId = request.organizationperid();
			
			Admin admin = new Admin();
			transaction.userId = admin.id;
			admin.setName(request.name());
			admin.setLastname(request.lastname());
			
			HashHelper hasher = new HashHelper();
			admin.passwordHash = hasher.hashConverter(request.password());
			
			admin.SetEMail(request.email());
			admin.organizationPerId = request.organizationperid();
			
			_repositoryAd.add(admin);
			transaction.succes = Succes.SUCCESSFUL;
			
		} catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositoryTr.add(transaction);
			throw new IllegalArgumentException("AdminRegisterRequestCommand error"+e.getMessage());
		}
		
		_repositoryTr.add(transaction);
		
		
	} 
}
