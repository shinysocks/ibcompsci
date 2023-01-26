import java.util.Random;

public class Dice {
	private int die1;
	private int die2;
	private Random random;
	
	public Dice()
	{
		random = new Random();
	}
	
	public int roll()
	{
		die1 = random.nextInt(6);
		die2 = random.nextInt(6);
		
		System.out.println("rolling...");
		System.out.println("["+die1+"]  ["+die2+"]");
		
		return die1 + die2;
	}
}
