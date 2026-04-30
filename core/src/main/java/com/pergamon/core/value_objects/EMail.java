package com.pergamon.core.value_objects;
import jakarta.validation.constraints.Email;

public final class EMail {
	
	@Email
	private final String eMail;
	
	public EMail(String eMail)
	{
		this.eMail = eMail;
	}
	
	public String getEMail()
	{
		return eMail;
	}
}
