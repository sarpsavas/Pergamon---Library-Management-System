package com.pergamon.application.organizations.view_organizations;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Organization;
import com.pergamon.core.interfaces.IOrganizationRepository;

@Component
public class ViewOrganizationsQueryHandler {

		private IOrganizationRepository _orRepository;
		
		 public ViewOrganizationsQueryHandler(IOrganizationRepository orRepository)
		 {
			 _orRepository = orRepository;
		 }
		 
		 @QueryHandler
		 public List<Organization> handle(ViewOrganizationsQuery request)
		 {
			 return _orRepository.getOrganizations();
		 }
}
