import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String name;
		int bet;
		boolean run = true;
		int startPoint;
		
		System.out.println("[c-r-a-p-s (loser)]");
		System.out.println("~~~~~~~~~~~~~~~~~~~");		
		
		System.out.println("what's your name...loser? ");
		name = input.nextLine();
		
		Player player = new Player(name);
		System.out.println("what is up "+player.getName()+".");
		System.out.println("you've got 100 bucks...rookie numbers, really...");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		Dice dice = new Dice();
		
		while (true)
		{
			if (player.cash == 0 || run == false)
			{
				player.lose();
				break;
			}
			
			System.out.println("place your bet: ");
			bet = input.nextInt(); input.nextLine();
			
			while (bet > player.cash || bet <= 0) // check bet
			{
				System.out.println("nope...you really...want to lose before you begin?");
				System.out.println("place your (correct) bet: ");
				bet = input.nextInt(); input.nextLine();
			}
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("cash: "+ player.cash);
			System.out.println("press enter to roll...");
			input.nextLine();
			
			startPoint = dice.roll();
			
			if (startPoint == 2 || startPoint == 3 || startPoint == 12)
			{
				player.cash -= bet;
			}
			
			else if (startPoint == 7 || startPoint == 11)
			{
				System.out.println("very good...very good, really");
				player.cash += bet;
			}
			
			
			
		}	
		input.close();
	}
}