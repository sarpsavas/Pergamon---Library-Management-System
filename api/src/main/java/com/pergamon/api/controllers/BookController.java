package com.pergamon.api.controllers;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import DTOs.Requests.*;
import DTOs.Responses.*;
import com.pergamon.application.book.create_book;;



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
	
	@GetMapping("/book-search")  //visitor + admin
	public ResponseEntity<List<BookResponse>> BookSearch(@PathVariable String letters) 
	{
		try {
			
			return ResponseEntity.status(200).build();
		}
		catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}
	
	
	
	@GetMapping("/mybooks")  //visitor
	public ResponseEntity<List<BookResponse>> MyBooks(@PathVariable String visitorId)
	{
		try {
			return ResponseEntity.status(200).build();
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}
	
	
	@PostMapping("/book-add") //admin
	public ResponseEntity<String> BookAdd(@RequestBody CreateBookCommand request) 
	{
		try {
			_cGateway.sendAndWait(request);
			return ResponseEntity.ok("tamamlandı");
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}
	
	
	
	//TODO: book-update //admin
	@PostMapping("/book-update") 
	public ResponseEntity<String> BookUpdate(@RequestBody BookRequest request)
	{
		try {
			return ResponseEntity.ok("tamamlandı");
		} 
		catch (Exception e) {
			String a = e.getMessage();
			return ResponseEntity.status(400).build();
		}
	}
	
	
	//TODO: book-delete //admin
	public ResponseEntity<String> BookDelete(@PathVariable String BookId)
	{
		try {
			return ResponseEntity.ok("tamamlandı");
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}
	
	
}
