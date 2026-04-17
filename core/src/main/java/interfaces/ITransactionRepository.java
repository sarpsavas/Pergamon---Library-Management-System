package interfaces;

import entities.*;
import java.util.List;

public interface ITransactionRepository {
	
	List<Transaction> GetUserTransactionsByUId(String unıqUserId);
	List<Transaction> GetAllTransactions();
}
