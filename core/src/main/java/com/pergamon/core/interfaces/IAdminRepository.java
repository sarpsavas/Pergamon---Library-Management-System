package com.pergamon.core.interfaces;


import java.util.List;

import com.pergamon.core.entites.User;

public interface IAdminRepository {
	
	List<User> GetUsersByLetters(String letters);
	
}
