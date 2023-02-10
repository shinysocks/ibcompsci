package makingDogs;

import java.util.Scanner;

public class DogDriver {
	
	static Scanner scan = new Scanner(System.in);
	static Dog[] dogs = new Dog[3];
	
	public static void main(String[] args) {
		
		for (int i = 0; i<=dogs.length; i++)
		{
			System.out.println("My dog: ");
			String breed = scan.nextLine();
			
			System.out.println("Coat color: ");
			String coatColor = scan.nextLine();	
			
			dogs[i] = new Dog(breed, coatColor);
			System.out.println(dogs[i].toString());
			System.out.println(dogs[i].bark());

			System.out.println("...................");
		}
	}

}
