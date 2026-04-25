package com.pergamon.core.value_objects;

import java.util.Random;

public class AdminIdObject {
	String AdminId; //PA_______[7]
	
	public AdminIdObject()
	{
		Random rnd = new Random();
		AdminId = "PA" + rnd.nextInt(8999999) + 1000000;
	}
	public String GetAdminId()
	{
		return AdminId;
	}
}
