package ValueObjects;

import java.util.Random;

public class AdminId {
	String AdminId; //PA_______[7]
	
	public AdminId()
	{
		Random rnd = new Random();
		AdminId = "PA" + rnd.nextInt(8999999) + 1000000;
	}
	public String GetAdminId()
	{
		return AdminId;
	}
}
