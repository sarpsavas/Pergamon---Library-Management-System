package interfaces;

import entities.*;
import java.util.List;

public interface IBarrowedRepository {
	
	List<Barrowed> GetVisitorBarrowedsByVisitorId(String visitorId);
	
	
}
