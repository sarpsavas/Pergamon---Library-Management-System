package ValueObjects;

import java.util.Random;

public class BookId {
	String BookId;
	
	public BookId() {
		Random rnd = new Random();
		BookId ="PL"  + rnd.nextInt(8999999) + 1000000;
	}
	
	public String GetBookId()
	{
		return BookId;
	}
}
