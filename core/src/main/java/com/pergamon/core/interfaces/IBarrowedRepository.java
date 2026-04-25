package com.pergamon.core.interfaces;

import java.util.List;

import com.pergamon.core.entites.Barrowed;

public interface IBarrowedRepository {
	
	List<Barrowed> GetVisitorBarrowedsByVisitorId(String visitorId);
	
	
}
