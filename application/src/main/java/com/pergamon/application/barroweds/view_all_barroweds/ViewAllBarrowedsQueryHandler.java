package com.pergamon.application.barroweds.view_all_barroweds;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.responses.BarrowedsResponse;
import com.pergamon.core.interfaces.IBarrowedRepository;

@Component
public class ViewAllBarrowedsQueryHandler {

	 private IBarrowedRepository _baRepository;
	 public ViewAllBarrowedsQueryHandler(IBarrowedRepository baRepository)
	 {
		  _baRepository = baRepository;
	 }
	 
	 @QueryHandler
	 public BarrowedsResponse handle(ViewAllBarrowedsQuery request)
	 {
		 return new BarrowedsResponse(_baRepository.GetBarroweds(request.organizationPerId()));
	 }
}
