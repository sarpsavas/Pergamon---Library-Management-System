package com.pergamon.core.entites;


import java.util.Random;

import com.pergamon.core.enums.Availability;
import com.pergamon.core.enums.BookType;


public class Book {
	
	public String id; // {PB}+{0000000}
	private String name;
	private String author;
	public BookType bookType;
	public Availability availability;
	private int pageNumber;
	public String organizationPerId;
	public String imageUrl;
	
	
	public Book()
	{
		Random rnd = new Random();
		id = "PB" + rnd.nextInt(8999999) + 1000000;
		
	}
	public void setPageNumber(int pageNumber)
	{
		if (pageNumber <= 0)
		{
			throw new IllegalArgumentException("book author string error");
		}
		this.pageNumber = pageNumber; 
	}
	
	public int getPageNumber()
	{
		return pageNumber; 
	}
	
	public void setName(String name)
	{
		if (name.length() >80 || name == null)
		{
			throw new IllegalArgumentException("book name string error");
		}
		this.name = name; 
	}
	
	public String getName()
	{
		return name; 
	}
	
	public void setAuthor(String author)
	{
		if (author.length() >80 || author == null)
		{
			throw new IllegalArgumentException("book author string error");
		}
		this.author = author; 
	}
	
	public String getAuthor()
	{
		return author; 
	}
	
	
	public String getId() {
	    return id;
	}

	
	public BookType getBookType() {
	    return bookType;
	}


	public Availability getAvailability() {
	    return availability;
	}
	
	public String getOrganizationPerId()
	{
		return organizationPerId;
	}
	public String getImageUrl()
	{
		return imageUrl;
	}
	
	
}
