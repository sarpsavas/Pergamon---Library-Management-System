package com.pergamon.core.value_objects;

import java.util.Random;

import com.pergamon.core.enums.AccountProfil;

public class VisitorIdObject {
	String VisitorId;
	
	public VisitorIdObject(AccountProfil profil)
	{
		Random rnd = new Random();
		if(profil == AccountProfil.Teacher)
		{
			VisitorId = "PT" + rnd.nextInt(8999999) + 1000000;
		}
		else
		{
			VisitorId = "PS" + rnd.nextInt(8999999) + 1000000;
		}
	}
	
	public String GetVisitorId() {
		return VisitorId;
	}
}
