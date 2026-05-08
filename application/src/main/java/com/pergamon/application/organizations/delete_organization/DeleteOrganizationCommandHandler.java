package com.pergamon.application.organizations.delete_organization;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Organization;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.interfaces.IManagementTransactionRepository;
import com.pergamon.core.interfaces.IOrganizationMigration;
import com.pergamon.core.interfaces.IOrganizationRepository;
import com.pergamon.core.interfaces.IRepository;

@Component
public class DeleteOrganizationCommandHandler {
	
	private IRepository<Organization> _repositoryOr;
	private IOrganizationMigration _orMigration;
	private IOrganizationRepository _orRepository;
	private IManagementTransactionRepository _manTranRepository;
	
	public DeleteOrganizationCommandHandler(IRepository<Organization> repositoryOr,
			IOrganizationMigration orMigration,
			IManagementTransactionRepository manTranRepository,
			IOrganizationRepository orRepository)
	{
		_repositoryOr = repositoryOr;
		_orMigration = orMigration;
		_manTranRepository = manTranRepository;
		_orRepository = orRepository;
	}
	@CommandHandler
	public void handle(DeleteOrganizationCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.DELETE_ORGANIZATION;
		transaction.userId = request.sAdminId();
		try {
			_orMigration.deleteShema(request.organizationPerId());
			Organization organization = _orRepository.getOrganizationByOrganizationPerId(request.organizationPerId());
			_repositoryOr.delete(organization.getOrganizationId(), null);
			transaction.succes = Succes.SUCCESSFUL;
			_manTranRepository.add(transaction);
		} catch (Exception e) {
			transaction.succes = Succes.UNSUCCESSFUL;
			_manTranRepository.add(transaction);
		}
	}
}
