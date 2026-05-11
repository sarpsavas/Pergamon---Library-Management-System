package com.pergamon.application.users.admin_delete;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Admin;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.interfaces.IOrganizationRepository;
import com.pergamon.core.interfaces.IRepository;
@Component
public class DeleteAdminCommandHandler {
	
	private final IRepository<Transaction> _repositortTr;
	private final IRepository<Admin> _repositortAd;
	private IOrganizationRepository _orgRepository;
	
	public DeleteAdminCommandHandler(IRepository<Transaction> repositortTr,
			IRepository<Admin> repositortAd,
			IOrganizationRepository orgRepository)
	
	{
		_repositortTr = repositortTr;
		_repositortAd = repositortAd;
		_orgRepository = orgRepository;
	}
	@CommandHandler
	public void handle(DeleteAdminCommand request)
	{
		Transaction transaction = new Transaction();
		try {
			var organization = _orgRepository.getOrganizationByOrganizationPerId(request.organizationPerId());//organizasyon varmı kontrolü
			if(organization == null) {throw new IllegalArgumentException("organization error");}	
		} 
		catch (Exception e) {
			throw new IllegalArgumentException("organization error" + e.getMessage());
		}
		transaction.organizationPerId = request.organizationPerId();
		transaction.type = TransactionType.DELETE_ADMIN;
		
		try {
			_repositortAd.delete(request.adminId(), request.organizationPerId());
			transaction.succes = Succes.SUCCESSFUL;
			transaction.userId = request.sAdminId();
			transaction.setDescription("-");
			_repositortTr.add(transaction);
		} catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositortTr.add(transaction);
		}
	}
}
