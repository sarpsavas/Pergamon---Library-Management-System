package entities;


import ValueObjects.AdminIdObject;
import enums.*;

public class Admin extends User{
	public AdminStatus Status;
	public String AdminId;
	
	public Admin()
	{
		AdminIdObject adminId = new AdminIdObject();
		AdminId = adminId.GetAdminId();
	}
}
