package com.pergamon.core.entites;


import com.pergamon.core.enums.AccountProfil;
import com.pergamon.core.enums.VisitorStatus;
import com.pergamon.core.value_objects.VisitorIdObject;


public class Visitor extends User{
	public VisitorStatus Status;
	public AccountProfil Profil;
	protected int Book;
	
	public Visitor(VisitorStatus status,  AccountProfil profil)
	{
		Status = status;
		Profil = profil;
		Book = 0;
		VisitorIdObject visitorId = new VisitorIdObject(Profil);
		AccountId = visitorId.GetVisitorId();
	}
	public void AddBook(int bookNumber) throws Exception
	{
		if(Profil == AccountProfil.Student)
		{
			if((Book + bookNumber) <= 5)
			{
				Book += bookNumber;
			}
			else
			{
				throw new Exception("Maksimum kitap sayısı");
			}
		}
		else if(Profil == AccountProfil.Teacher)
		{
			if((Book + bookNumber) <= 5)
			{
				Book += bookNumber;
			}
			else
			{
				throw new Exception("Maksimum kitap sayısı");
			}
		}
		
		
	}
	public void ExtractBook(int bookNumber) throws Exception
	{
		if((Book - bookNumber) >= 0)
		{
			Book -= bookNumber;
		}
		else
		{
			throw new Exception("Çıkarılacak kitap kalmadı");
		}
		
	}
	public int GetBook()
	{
		return Book;
	}
}
