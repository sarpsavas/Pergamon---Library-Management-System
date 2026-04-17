package interfaces;

import entities.*;
import java.util.List;

public interface IBookRepository {
	List<Book> GetBooksByLetters(String letters);
	
	
	
	
}
