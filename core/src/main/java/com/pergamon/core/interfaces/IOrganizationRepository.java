package com.pergamon.core.interfaces;

import java.util.UUID;


import com.pergamon.core.entites.Organization;

public interface IOrganizationRepository {
	
	
	Organization getOrganizationByOrganizationId(  UUID organizationId);
	
	
	Organization getOrganizationByOrganizationPerId(  String organizationperId);
	
	
	
}
