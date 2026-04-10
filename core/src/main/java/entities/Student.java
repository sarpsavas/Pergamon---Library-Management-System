package entities;

public class Student extends Visitor
{
	public Student()
	{
		Book = 0;
	}
	public void AddBook(int bookNumber) throws Exception
	{
		if((Book + bookNumber) <= 5)
		{
			Book += bookNumber;
		}
		else
		{
			throw new Exception("MAksimum kitap sayısı");
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
