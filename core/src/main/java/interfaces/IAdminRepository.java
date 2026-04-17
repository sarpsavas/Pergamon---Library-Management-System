package interfaces;

import entities.*;
import java.util.List;

public interface IAdminRepository {
	
	List<User> GetUsersByLetters(String letters);
	
}
