package com.pergamon.core.interfaces;


import java.util.List;
import java.util.UUID;

import com.pergamon.core.entites.User;
import com.pergamon.core.entites.Visitor;

public interface IVisitorRepository {
	
	List<Visitor> GetVisitorsByLetters(String letters, String organizationPerId);
	
	Visitor GetVisitorById(UUID id, String organizationPerId);
	
	Visitor GetVisitorByIdentity( String eMail, String passwordHash, String organizationPerId);
	
	
}
