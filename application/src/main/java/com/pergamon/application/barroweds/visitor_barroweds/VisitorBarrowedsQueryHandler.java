package com.pergamon.application.barroweds.visitor_barroweds;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.responses.BarrowedsResponse;
import com.pergamon.core.entites.Barrowed;
import com.pergamon.core.interfaces.IBarrowedRepository;

@Component
public class VisitorBarrowedsQueryHandler {
	
	private IBarrowedRepository _baRepository;
	
	public VisitorBarrowedsQueryHandler(IBarrowedRepository baRepository)
	{
		_baRepository =baRepository;
	}
	
	@QueryHandler
	public BarrowedsResponse handle(VisitorBarrowedsQuery request)
	{
		return new BarrowedsResponse(_baRepository.GetVisitorBarrowedsByVisitorId(request.visitorId(), request.organizationPerId()));
	}
}
