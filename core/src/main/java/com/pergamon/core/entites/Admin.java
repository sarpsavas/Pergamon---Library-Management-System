package com.pergamon.core.entites;


import java.util.Random;
import java.util.UUID;

import com.pergamon.core.enums.AdminProfile;



public class Admin extends User{
	public AdminProfile status;
	
	
	public Admin(String name, String lastname)
	{
		Random rnd = new Random();
		accountId = "PA" + rnd.nextInt(8999999) + 1000000;
		
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
