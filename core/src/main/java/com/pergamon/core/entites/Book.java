package com.pergamon.core.entites;

import java.time.LocalDate;
import java.util.Random;

import com.pergamon.core.enums.Availability;
import com.pergamon.core.enums.BookType;


public class Book {
	public String id; // {PL}+{_______}7int
	public String name;
	public String author;
	public BookType bookType;
	public Availability availability;
	private int pageNumber;
	
	
	public Book()
	{
		Random rnd = new Random();
		id ="PL"  + rnd.nextInt(8999999) + 1000000;
		
		
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
	
	
}
