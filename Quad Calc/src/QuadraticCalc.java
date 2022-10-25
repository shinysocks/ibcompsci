import java.util.Scanner;

public class QuadraticCalc {

	//variables
	Scanner scan = new Scanner(System.in);
	int rootsCount;
	static int a;
	int b;
	int c;
	int x, y;
	int yInt;
	int vertexX;
	int vertexY;
	double symmetryAxis;
	double root1;
	double root2;
	int discriminant; 
	
	public static void main(String[] args)
	{
		//inputEquation();
	}
	
	void inputEquation()
	{
		System.out.println("Enter a quadratic equation in the form ax^2 + bx + c: ");
		System.out.println("Enter a: ");
		a = scan.nextInt();
		
		System.out.println("Enter b: ");
		b = scan.nextInt();
		
		System.out.println("Enter c: ");
		c = scan.nextInt();
		
		System.out.println();
		System.out.println("Your Equation: y = " +a+ "x^2 + " +b+ "x + " +c);
		System.out.println();
	}
	
	void rootsNum()
	{
		//calculate number of roots
		discriminant = b*b - 4*a*c;
		
		if (discriminant > 0)
		{
			rootsCount = 2;
		}
		
		else if (discriminant == 0)
		{
			rootsCount = 1;
		}
		
		else
		{
			rootsCount = 0; //no solution
		}
		
		System.out.println("Number of roots: " +rootsCount);
	}
	
	void calcRoots()
	{
		if (rootsCount == 2)
		{
			root1 = (-1*b+Math.sqrt(b*b - 4*a*c))/(2*a);
			root2 = (-1*b-Math.sqrt(b*b - 4*a*c))/(2*a);
		}
		
		if (rootsCount == 1)
		{
			
		}
	}
}
