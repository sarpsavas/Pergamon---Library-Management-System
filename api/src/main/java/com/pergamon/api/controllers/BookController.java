package com.pergamon.api.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import DTOs.Requests.*;
import DTOs.Responses.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
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
	public ResponseEntity<String> BookAdd(@RequestBody BookRequest request) 
	{
		try {
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
		} catch (Exception e) {
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
