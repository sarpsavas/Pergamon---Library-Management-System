package com.pergamon.core.entites;

import java.util.Random;
import java.util.UUID;

public class Organization {
	private UUID organizationId;
	private String organizationPerId; //{PL}{000000}
	private String organizationName;
	public UUID organizationMasterAdminId;
	
	public Organization()
	{
		organizationId = UUID.randomUUID();
		
		Random rnd = new Random();
		organizationPerId = "L" + rnd.nextInt(899999) + 100000; 
	}
	
	public void setOrganizationName(String organizationName)
	{
		if(organizationName.length() > 50)
		{
			throw new IllegalArgumentException("organization name length exception");
		}
		this.organizationName = organizationName;
	}
	
	public String getOrganizationName()
	{
		return organizationName;
	}
	public String getOrganizationId()
	{
		return organizationName;
	}
	public String getOrganizationPerId()
	{
		return organizationName;
	}
}
