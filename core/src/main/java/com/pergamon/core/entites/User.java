package com.pergamon.core.entites;

import java.util.UUID;

import com.pergamon.core.value_objects.EMail;

public class User {
	public UUID id;
	public String accountId; //{PA}{_______}7
	public String name;
	public String lastname;
	public String passwordHash;
	public EMail eMail;
	
}

