package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.pergamon.core.entites.Organization;
import com.pergamon.core.interfaces.IOrganizationRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IOrganizationDA;
import com.pergamon.infrastructure.persistence.repository.IOrganizationRepDA;

@Repository
public class OrganizationRepositoryImpl implements IOrganizationRepository, IRepository<Organization> {

		private final Jdbi _jdbi;
		
		public OrganizationRepositoryImpl(Jdbi jdbi)
		{
			_jdbi = jdbi;
		}
		
		public List<Organization> getOrganizations()
		{
			return _jdbi.withExtension(IOrganizationRepDA.class, da -> da.getOrganizations());
		}
		
		public Organization getOrganizationByOrganizationPerId(String organizationPerId)
		{
			return _jdbi.withExtension(IOrganizationRepDA.class, da -> da.getOrganizationByOrganizationPerId(organizationPerId));
		}
		
		public void add(Organization admin)
		{
			_jdbi.useExtension(IOrganizationRepDA.class, da -> da.add(admin));
		}
		
		public void update(Organization admin)
		{
			_jdbi.useExtension(IOrganizationRepDA.class, da -> da.update(admin));
		}
		
		public void delete(UUID id, String nullVeriable)
		{
			_jdbi.useExtension(IOrganizationRepDA.class, da -> da.delete(id));
		}
}
