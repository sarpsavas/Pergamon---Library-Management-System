package com.pergamon.core.entites;


import com.pergamon.core.enums.AdminStatus;
import com.pergamon.core.value_objects.AdminIdObject;


public class Admin extends User{
	public AdminStatus Status;
	public String AdminId;
	
	public Admin()
	{
		AdminIdObject adminId = new AdminIdObject();
		AdminId = adminId.GetAdminId();
	}
}
