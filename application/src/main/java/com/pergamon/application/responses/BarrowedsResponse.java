package com.pergamon.application.responses;

import java.util.List;

import com.pergamon.core.entites.Barrowed;

public class BarrowedsResponse {
	
	List<Barrowed> barroweds;
	
	public BarrowedsResponse(List<Barrowed> barroweds)
	{
		this.barroweds = barroweds;
	}
}
