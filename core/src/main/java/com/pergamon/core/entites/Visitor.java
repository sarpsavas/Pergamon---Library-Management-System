package com.pergamon.core.entites;


import java.util.Random;
import java.util.UUID;

import com.pergamon.core.enums.AccountProfile;
import com.pergamon.core.enums.VisitorStatus;



public class Visitor extends User{
	public VisitorStatus status;
	public AccountProfile profil;
	
	
	public Visitor( AccountProfile profil)
	{
		Random rnd = new Random();
		if(profil == AccountProfile.TEACHER)
		{
			accountId = "PT" + rnd.nextInt(8999999) + 1000000;
		}
		else
		{
			accountId = "PS" + rnd.nextInt(8999999) + 1000000;
		}
		this.status = VisitorStatus.ACTIVE;
		id = UUID.randomUUID();
		
		
	}
	
	public void setName(String name)
	{
		if(name.length() >30 || name.length() <= 0)
		{
			throw new IllegalArgumentException("visitor name length error");
		}
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setLastname(String lastname)
	{
		if(lastname.length() >30 || lastname.length() <= 0)
		{
			throw new IllegalArgumentException("visitor name length error");
		}
		this.lastname = lastname;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
}
