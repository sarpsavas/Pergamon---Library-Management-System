package com.pergamon.api.controllers;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.application.books.book_search.BookSearchQuery;
import com.pergamon.application.responses.BookSearchResponse;
import com.pergamon.core.interfaces.IUploadBookImageService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// DOĞRU IMPORTLAR BUNLAR OLMALI
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	private final IUploadBookImageService _uploadService;
	
	@Value("${storage.location}")
    private String uploadDir;
	
	public BookController(CommandGateway cGateway, 
			QueryGateway qGateway,
			IUploadBookImageService uploadService)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
		_uploadService = uploadService;
	}
	
	@GetMapping("/book-search") 
	public CompletableFuture<BookSearchResponse> BookSearchAsync(@RequestBody BookSearchQuery request) 
	{
		try 
		{ 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(BookSearchResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping("/upload-book-image") 
	public CompletableFuture<String> uploadImage(
			@RequestParam("file") MultipartFile file,
			@RequestParam("book-id") String bookId,
			@RequestParam("organization-id") String organizationId) 
	{
		if (file.isEmpty()) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "file is empty");
        }
		try 
		{
			Path directoryPath = Paths.get(uploadDir);
			
			String originalFileName = file.getOriginalFilename();
            String extension = "";
            
            if (originalFileName != null && originalFileName.contains(".")) 
            {
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            String newFileName = UUID.randomUUID().toString() + extension;
            
            Path filePath = directoryPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath);
            
            _uploadService.addBookImage(bookId, filePath.toString(), organizationId);
            
            return CompletableFuture.completedFuture(filePath.toString());
		} 
		catch (Exception e) 
		{
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
