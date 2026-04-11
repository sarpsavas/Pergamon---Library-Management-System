package entities;


import enums.*;

public class Visitor extends User{
	public VisitorStatus Status;
	public AccountProfil Profil;
	protected int Book;
	
	public Visitor()
	{
		Book = 0;
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
