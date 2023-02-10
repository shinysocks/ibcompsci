import java.util.Scanner;

public class MyCharacterExample {

	public static void main(String[] args) {
		char myChar = 'T';
		System.out.println((int)myChar);
		
		int myChar2 = (int)myChar - 5;
		System.out.println((char)myChar2);
		
		Scanner in = new Scanner(System.in);
		
		while (true)
		{
			System.out.print("Enter a number to convert to a character: ");
			int a = in.nextInt();
			System.out.println((char)a);	
		}
	}
}
