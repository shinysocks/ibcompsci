import java.util.Scanner;

public class Length {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		String a = "Joe Momma";
		
		int x = a.length();
		
		System.out.println(a + "is " + x + " big");
		
		System.out.println("Create your ID. Must be between 2 - 9 characters: ");
		String id = input.nextLine();
		
		if (id.length() < 2 || id.length() > 9)
		{
			System.out.println("read directions...");
		}
		
		else
		{
			System.out.println("ID created.");
		}
		
	}

}
