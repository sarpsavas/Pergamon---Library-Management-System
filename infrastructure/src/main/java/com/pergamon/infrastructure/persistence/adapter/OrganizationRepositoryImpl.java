package com.pergamon.infrastructure.persistence.adapter;

import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Organization;
import com.pergamon.core.interfaces.IOrganizationRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IOrganizationsDA;

public class OrganizationRepositoryImpl implements IOrganizationRepository,
IRepository<Organization>
{
	
	private final Jdbi _jdbi;
	
	public OrganizationRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public Organization getOrganizationByOrganizationId(UUID organizationId)
	{
		return _jdbi.withExtension(IOrganizationsDA.class, da -> da.getOrganizationByOrganizationId(organizationId));
	}
	
	public Organization getOrganizationByOrganizationPerId(String organizationPerId)
	{
		return _jdbi.withExtension(IOrganizationsDA.class, da -> da.getOrganizationByOrganizationPerId(organizationPerId));
	}
	
	
	public void add(Organization organization)
	{
		_jdbi.useExtension(IOrganizationsDA.class, da -> da.add(organization));
	}
	
	
	public void update(Organization organization)
	{
		_jdbi.useExtension(IOrganizationsDA.class, da -> da.update(organization));
	}
	
	
	public void delete(UUID id)
	{
		_jdbi.useExtension(IOrganizationsDA.class, da -> da.delete(id));
	}
}
