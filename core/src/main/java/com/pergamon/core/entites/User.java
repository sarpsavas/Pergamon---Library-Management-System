package com.pergamon.core.entites;

import java.util.UUID;

import com.pergamon.core.value_objects.EMail;

public class User {
	public UUID id;
	public String accountId; //{PA}{_______}7
	protected String name;
	protected String lastname;
	public String passwordHash;
	protected EMail eMail;
	public String organizationPerId;
	
	public void SetEMail(String eMail)
	{
		this.eMail = new EMail(eMail);
	}
	
	
}

