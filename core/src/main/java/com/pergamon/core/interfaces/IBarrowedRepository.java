package com.pergamon.core.interfaces;

import java.util.List;
import java.util.UUID;

import com.pergamon.core.entites.Barrowed;

public interface IBarrowedRepository {
	
	List<Barrowed> GetVisitorBarrowedsByVisitorId(UUID visitorId);
	
	Barrowed GetBarrowedByBarrowedId(UUID barrowedId);
	
	
}
