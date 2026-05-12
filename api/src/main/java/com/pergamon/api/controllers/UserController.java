package com.pergamon.api.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.jdbi.v3.core.statement.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.application.command.book.CreateBookCommand;
import com.pergamon.application.users.admin_delete.DeleteAdminCommand;
import com.pergamon.application.users.admin_register.AdminRegisterCommand;
import com.pergamon.application.users.admin_search.AdminSearchQuery;
import com.pergamon.application.users.admin_update.UpdateAdminCommand;
import com.pergamon.application.users.master_admin_register.MasterAdminRegisterCommand;
import com.pergamon.application.users.teacher_register.TeacherRegisterCommand;
import com.pergamon.application.users.visitor_register.VisitorRegisterCommand;
import com.pergamon.application.users.visitor_register_request.VisitorRegisterRequestCommand;
import com.pergamon.application.users.visitor_search.VisitorSearchQuery;
import com.pergamon.core.responses.LogInResponse;
import com.pergmaon.application.users.log_in.LogInQuery;
import com.pergmaon.application.users.visitor_delete.VisitorDeleteCommand;
import com.pergmaon.application.users.visitor_register_approval.VisitorRegisterApprovalCommand;

import DTOs.Responses.AdminSearchResponse;
import DTOs.Responses.BookResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	
	public UserController(CommandGateway cGateway, QueryGateway qGateway)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
	}
	
	
	
	@DeleteMapping("/admin-delete") 
	public CompletableFuture<String> DeleteAdmin(@RequestBody DeleteAdminCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping("/admin-register") 
	public CompletableFuture<String> AddAdmin(@RequestBody AdminRegisterCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/admin-search")  
	public CompletableFuture<AdminSearchResponse> AdminSearch(@RequestBody AdminSearchQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(AdminSearchResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PatchMapping("/admin-update") 
	public CompletableFuture<String> UpdateAdmin(@RequestBody UpdateAdminCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping("/teacher-register") 
	public CompletableFuture<String> TeacherRegister(@RequestBody TeacherRegisterCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	@PostMapping("/visitor-register") 
	public CompletableFuture<String> VisitorRegister(@RequestBody VisitorRegisterCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	@PostMapping("/visitor-register-request") 
	public CompletableFuture<String> VisitorRegisterRequest(@RequestBody VisitorRegisterRequestCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/visitor-search")  
	public CompletableFuture<LogInResponse> VisitorSearch(@RequestBody VisitorSearchQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(LogInResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/log-in")  
	public CompletableFuture<LogInResponse> LogIn(@RequestBody LogInQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(LogInResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("/visitor-delete") 
	public CompletableFuture<String> VisitorDelete(@RequestBody VisitorDeleteCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	@PatchMapping("/visitor-register-approval") 
	public CompletableFuture<String> VisitorRegisterApproval(@RequestBody VisitorRegisterApprovalCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping("/master-admin-register") 
	public CompletableFuture<String> masterAdminRegister(@RequestBody MasterAdminRegisterCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	
	
	
}
