package makingDogs;

import java.util.Random;

public class Dog
{
	private String breed;
	private String coatColor;
	Random random = new Random();
	
	public Dog(String b, String c)
	{
		breed = b;
		coatColor = c;
	}
	
	public String bark() // returns a random bark
	{
		String [] barks = new String[]{"WOOF!", "RUFF!", "BOW WOW!", "GRRRR..."};
		return barks[random.nextInt(3)];	
	}
	
	public String toString()
	{
		return breed +" "+ coatColor;
	}

//	You may override the toString() method to print your objects on the screen (see screen shot below).
//	In total make 3 dog objects. See the last 2 slides in notes 3.
}
