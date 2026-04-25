package ValueObjects;

import java.util.Random;

public class BookIdObject {
	String BookId;
	
	public BookIdObject() {
		Random rnd = new Random();
		BookId ="PL"  + rnd.nextInt(8999999) + 1000000;
	}
	
	public String GetBookId()
	{
		return BookId;
	}
}
