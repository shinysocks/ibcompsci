package char_at;

import java.util.Scanner;

public class CharacterAt{
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a password that has 6 characters and a special character");
		System.out.print("Enter a password: ");
		String s = input.nextLine();
		
		int len = s.length();
		boolean isValid = false;
		for (int i = 0; i < len; i++)
		{
			if (s.charAt(i) == '@' || s.charAt(i) == '&' ||s.charAt(i) == '!')
			{
				isValid = true;
			}
		}
		
		if (isValid)
		{
			System.out.println("Valid!");
		}
		
		else
		{
			System.out.println("invalid!");
		}
	}	
}
