import java.util.Scanner;

public class CompareWords {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String firstWord, secondWord;
		
		System.out.println("First Word: ");
		
		firstWord = input.nextLine();
		
		System.out.println("Second Word: ");
		secondWord = input.nextLine();
		
//		char firstLetter = firstWord.charAt(0);
//		char secondLetter = secondWord.charAt(0);
		
		if (firstWord.compareTo(secondWord) > 0)
		{
			System.out.println(secondWord + " comes before " + firstWord);
		}
		
		else if (firstWord.compareTo(secondWord) == 0)
		{
			System.out.println(secondWord + " is the same as " + firstWord);
		}
		else
		{
			System.out.println(firstWord + " comes before " + secondWord);
		}
		System.out.println(Integer.toBinaryString(50098))
;		input.close();
	}
}
