package entities;

import ValueObjects.AdminId;
import enums.*;

public class Admin extends User{
	public AdminStatus Status;
	
	public Admin()
	{
		AdminId adminId = new AdminId();
		AccountId = adminId.GetAdminId();
	}
}
