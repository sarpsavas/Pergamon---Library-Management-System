package com.pergmaon.application.users.log_in;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;

import com.pergamon.application.jwt.JwtUtil;
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
	
	@CommandHandler
	public LogInResponse Handle(LogInQuery request)
	{
		LogInResponse response = new LogInResponse();
		
		JwtUtil jwt = new JwtUtil();
		
		try 
		{
			var visitor = _viRepository.GetVisitorByIdentity(request.email(), request.toString());
			if(visitor == null) {throw new IllegalArgumentException();}
			response.id = visitor.id;
			response.token = jwt.generateToken(request.email(),visitor.profil.toString());
		} 
		catch (Exception e) 
		{
			var admin = _adRepository.GetAdminByIdentity(request.email(), request.toString());
			if(admin == null) {throw new IllegalArgumentException();}
			response.id = admin.id;
			response.token = jwt.generateToken(request.email(),admin.status.toString());
		}
		
		return response;
	}
}
