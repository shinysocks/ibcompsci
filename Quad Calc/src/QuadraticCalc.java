import java.util.Scanner;

public class QuadraticCalc {

	//variables
	static Scanner scan = new Scanner(System.in);
	static int rootsCount;
	static int a;
	static int b;
	static int c;
	static int yInt;
	static double vertexX;
	static double vertexY;
	static double symmetryAxis;
	static double root1;
	static double root2;
	static int discriminant;
	static String sign1 = "+", sign2 = "+";
	
	public static void main(String[] args)
	{
		while (true)
		{
			inputEquation();
			System.out.println("-----------------------------------------------");
			System.out.println("1. Number and Values of Roots:");
			roots();
			System.out.println("2. Vertex and Axis of Symmetry:");
			vertex();
			System.out.println("3. Y-Intercept:");
			System.out.println("\ty-int = "+c);
			System.out.println("4. Table of Values:");
			values();
			System.out.println("-----------------------------------------------");
		}
	}
	
	static void inputEquation()
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

	static void roots()
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
		
		System.out.println("\tnumber of roots: " +rootsCount);
		
		// for two roots
		if (rootsCount == 1 || rootsCount == 2)
		{
			root1 = (-1*b+Math.sqrt(b*b - 4*a*c))/(2*a);
			root2 = (-1*b-Math.sqrt(b*b - 4*a*c))/(2*a);
			System.out.println("\troot1 x = "+root1);
			if (rootsCount == 2)
			{
				System.out.println("\troot2 x = "+root2);
			}
			
			//root 1
			if (root1 > 0)
			{
				sign1 = "-";
			}
			
			else if (root1 < 0)
			{
				root1 *= -1;
			}
			
			//root 2
			if (root2 > 0)
			{
				sign2 = "-";
			}
			
			else if (root2 < 0)
			{
				root2 *= -1;
			}
			
			System.out.print("\tFactors: (x "+sign1+" " +(int)root1+")  |");
			System.out.println("  (x "+sign2+" " +(int)root1+")");
		}
		
		else
		{
			System.out.println("No solution.");
		}
	}
	
	static void vertex()
	{
		vertexX = (double)(-1*b) / (2*a);
		vertexY = a * vertexX*vertexX + b*vertexX + c;
		
		System.out.println("\tVertex: ("+vertexX+", "+vertexY+")");
		System.out.println("\tAxis of Symmetry x = "+vertexX);
	}
	
	static void values()
	{
		System.out.println("x\ty");
		
		for (int x = -6; x <= 6; x++)
		{
			int y = a*x*x + b*x + c;
			System.out.println(x+"\t"+y);
		}
	}
	
}
