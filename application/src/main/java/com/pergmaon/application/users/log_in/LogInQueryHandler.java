package com.pergmaon.application.users.log_in;

import java.util.UUID;

import com.pergamon.core.interfaces.IAdminRepository;
import com.pergamon.core.interfaces.IVisitorRepository;
import com.pergamon.core.responses.LogInResponse;


public class LogInQueryHandler {
	
	private IVisitorRepository _viRepository;
	private IAdminRepository _adRepository;
	
	public LogInQueryHandler(IVisitorRepository viRepository,
			IAdminRepository adRepository)
	{
		_viRepository = viRepository;
		_adRepository = adRepository;
	}
	
	public LogInResponse Handle(LogInQuery request)
	{
		LogInResponse response = new LogInResponse();
		try 
		{
			var visitor = _viRepository.GetVisitorByIdentity(request.email(), request.toString());
			if(visitor == null) {throw new IllegalArgumentException();}
			response.id = visitor.id;
		} 
		catch (Exception e) 
		{
			var admin = _adRepository.GetAdminByIdentity(request.email(), request.toString());
			if(admin == null) {throw new IllegalArgumentException();}
			response.id = admin.id;
		}
		
		return response;
	}
}
