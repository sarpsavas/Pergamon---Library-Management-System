package com.pergamon.core.interfaces;

import java.util.List;

import com.pergamon.core.entites.Organization;

public interface IOrganizationRepository {
	
	List<Organization> getOrganizations();
	Organization getOrganizationByOrganizationPerId(String organizationPerId);
	
}
