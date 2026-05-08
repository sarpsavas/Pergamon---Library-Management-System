package com.pergamon.infrastructure.persistence.adapter;


import org.jdbi.v3.core.Jdbi;
import com.pergamon.core.interfaces.IOrganizationMigration;
import com.pergamon.infrastructure.persistence.repository.IOrganizationDA;

public class OrganizationMigrationImpl implements IOrganizationMigration
{
	
	private final Jdbi _jdbi;
	
	public OrganizationMigrationImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	
	
	
	public void addShema(String organizationPerId)
	{
		_jdbi.useExtension(IOrganizationDA.class, da -> da.addShema(organizationPerId));
	}
	
	
	public void addBooksTable(String organizationPerId)
	{
		_jdbi.useExtension(IOrganizationDA.class, da -> da.addBooksTable(organizationPerId));
	}
	public void addTransactionsTable(String organizationPerId)
	{
		_jdbi.useExtension(IOrganizationDA.class, da -> da.addTransactionsTable(organizationPerId));
	}
	public void addAdminsTable(String organizationPerId)
	{
		_jdbi.useExtension(IOrganizationDA.class, da -> da.addAdminsTable(organizationPerId));
	}
	public void addVisitorsTable(String organizationPerId)
	{
		_jdbi.useExtension(IOrganizationDA.class, da -> da.addVisitorsTable(organizationPerId));
	}
	public void addBarrowedsTable(String organizationPerId)
	{
		_jdbi.useExtension(IOrganizationDA.class, da -> da.addBarrowedsTable(organizationPerId));
	}
	public void addFeedbacksTable(String organizationPerId)
	{
		_jdbi.useExtension(IOrganizationDA.class, da -> da.addFeedbacksTable(organizationPerId));
	}
	
	
	public void deleteShema(String organizationPerId)
	{
		_jdbi.useExtension(IOrganizationDA.class, da -> da.deleteShema(organizationPerId)); //TODO: delete not see
	}
}
