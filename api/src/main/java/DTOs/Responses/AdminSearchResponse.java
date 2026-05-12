package DTOs.Responses;

import java.util.UUID;

import com.pergamon.core.enums.AdminProfile;
import com.pergamon.core.value_objects.EMail;

public class AdminSearchResponse {
	public UUID id;
	public String accountId; //{PA}{_______}7
	protected String name;
	protected String lastname;
	public String passwordHash;
	protected EMail eMail;
	public String organizationPerId;
	public AdminProfile status;

}
