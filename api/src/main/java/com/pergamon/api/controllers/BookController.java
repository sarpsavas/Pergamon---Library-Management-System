package com.pergamon.api.controllers;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.application.books.book_search.BookSearchQuery;
import com.pergamon.application.command.book.CreateBookCommand;
import com.pergamon.application.responses.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import DTOs.Requests.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;





@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	
	public BookController(CommandGateway cGateway, QueryGateway qGateway)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
	}
	
	@GetMapping("/book-search") 
	public CompletableFuture<BookSearchResponse> BookSearchAsync(@RequestBody BookSearchQuery request ) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(BookSearchResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	
	
	@PostMapping("/role-converter")
	public ResponseEntity<String> RoleConverter(@RequestHeader("Authorization") String authHeader)
	{
		String token = authHeader.substring(7); 

	    Claims claims = Jwts.parserBuilder()
	            .setSigningKey(Keys.hmacShaKeyFor(
	        	        "@bojevtsi@sliven@".getBytes()
	            	    ))
	            .build()
	            .parseClaimsJws(token)
	            .getBody();

	    String role = claims.get("role", String.class);

	    return ResponseEntity.ok("Role: " + role);
	}
	
}
