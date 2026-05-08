package com.pergmaon.application.users.log_in;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.queryhandling.QueryHandler;

import com.pergamon.application.jwt.JwtUtil;
import com.pergamon.core.enums.LogInOption;
import com.pergamon.core.interfaces.IAdminRepository;
import com.pergamon.core.interfaces.IOrganizationRepository;
import com.pergamon.core.interfaces.IVisitorRepository;
import com.pergamon.core.responses.LogInResponse;


public class LogInQueryHandler {
	
	private IVisitorRepository _viRepository;
	private IAdminRepository _adRepository;
	private IOrganizationRepository _orRepository;
	
	public LogInQueryHandler(IVisitorRepository viRepository,
			IAdminRepository adRepository,
			IOrganizationRepository orRepository)
	{
		_viRepository = viRepository;
		_adRepository = adRepository;
		_orRepository = orRepository;
	}
	
	@QueryHandler
	public LogInResponse Handle(LogInQuery request)
	{
		LogInResponse response = new LogInResponse();
		
		JwtUtil jwt = new JwtUtil();
		
		if(request.log_in_option() == LogInOption.VISITOR)
		{
			var visitor = _viRepository.GetVisitorByIdentity(request.email(), request.toString(),request.organization_pergamon_id());
			if(visitor == null) {throw new IllegalArgumentException();}
			response.organizationPerId = request.organization_pergamon_id();
			response.id = visitor.id;
			response.token = jwt.generateToken(request.email(),visitor.profil.toString());
		} 
	else if (request.log_in_option() == LogInOption.ADMIN) 
		{
			var admin = _adRepository.GetAdminByIdentity(request.email(), request.toString(), request.organization_pergamon_id());
			if(admin == null) {throw new IllegalArgumentException();}
			response.id = admin.id;
			response.token = jwt.generateToken(request.email(),admin.status.toString());
		}
		
		return response;
	}
}
