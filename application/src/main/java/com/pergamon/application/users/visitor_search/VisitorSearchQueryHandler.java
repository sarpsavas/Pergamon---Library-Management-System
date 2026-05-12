package com.pergamon.application.users.visitor_search;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Visitor;
import com.pergamon.core.interfaces.IVisitorRepository;

@Component
public class VisitorSearchQueryHandler {

	private IVisitorRepository _visRepository;
	
	public VisitorSearchQueryHandler(IVisitorRepository visRepository)
	{
		_visRepository = visRepository;
	}
	
	@QueryHandler
	public List<Visitor> handle(VisitorSearchQuery request)
	{
		
			return _visRepository.GetVisitorsByLetters(request.organizationName(), request.letters());
		
	}
}
