public class Player {
	
	private String name;
	public int cash;
	
	public Player(String n)
	{
		name = n;
		cash = 100; // starting balance
	}
	
	public String getName()
	{
		if (name.equalsIgnoreCase("loser"))
		{
			return "loser! yes! (great name) best of luck!";
		}
		
		else
		{
			return "loser (" + name + ")";
		}
	}
	
	public void lose()
	{
		System.out.println("bye..."+getName()+", you ended with "+cash+" bucks...rookie numbers, really...");
	}
}
