package com.pergamon.application.users.admin_search;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Admin;
import com.pergamon.core.interfaces.IAdminRepository;

@Component
public class AdminSearchQueryHandler {
	
	private final IAdminRepository _admRepository;
	
	public AdminSearchQueryHandler(IAdminRepository admRepository)
	{
		_admRepository = admRepository;
	}
	
	@QueryHandler
	public List<Admin> handle(AdminSearchQuery request)
	{
		return _admRepository.GetAdmins(request.organizationPerId());
	}
}
