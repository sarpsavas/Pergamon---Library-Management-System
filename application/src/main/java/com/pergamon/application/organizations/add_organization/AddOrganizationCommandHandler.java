package com.pergamon.application.organizations.add_organization;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Transaction;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.entites.Organization;
import com.pergamon.core.interfaces.IManagementTransactionRepository;
import com.pergamon.core.interfaces.IOrganizationMigration;
import com.pergamon.core.interfaces.IRepository;

@Component
public class AddOrganizationCommandHandler {
	
	private IManagementTransactionRepository _ManTraRepository;
	private IOrganizationMigration _migration;
	private IRepository<Organization> _repositoryOr;
	
	public AddOrganizationCommandHandler(IManagementTransactionRepository ManTraRepository,
			IRepository<Organization> repositoryOr,
			IOrganizationMigration migration)
	{
		_ManTraRepository = ManTraRepository;
		_repositoryOr = repositoryOr;
		_migration = migration;
	}
	
	@CommandHandler
	public void handle(AddOrganizationCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.ADD_ORGANIZATION;
		transaction.userId = request.gAdminId();
		Organization organization = new Organization();
		organization.setOrganizationName(request.organizationName());
		
		
		try 
		{
			
			_migration.addBooksTable(organization.getOrganizationPerId());
			_migration.addTransactionsTable(organization.getOrganizationPerId());
			_migration.addAdminsTable(organization.getOrganizationPerId());
			_migration.addVisitorsTable(organization.getOrganizationPerId());
			_migration.addBarrowedsTable(organization.getOrganizationPerId());
			_migration.addFeedbacksTable(organization.getOrganizationPerId());
		} 
		catch (Exception ex)
		{
			transaction.succes = Succes.UNSUCCESSFUL;
			_ManTraRepository.add(transaction);
			throw new IllegalArgumentException("addShema exception");		
			
		}
		
		try 
		{
			_repositoryOr.add(organization);
		} 
		catch (Exception ex) 
		{
			transaction.succes = Succes.UNSUCCESSFUL;
			_ManTraRepository.add(transaction);
			throw new IllegalArgumentException("management addShema exception");	
		}
		transaction.succes = Succes.SUCCESSFUL;
		_ManTraRepository.add(transaction);
		
	}
}
