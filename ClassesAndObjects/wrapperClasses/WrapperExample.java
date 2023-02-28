package wrapperClasses;

import java.util.ArrayList;
import java.util.Scanner;

public class WrapperExample {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("enter a number: ");
        String num = in.nextLine();
        
        int total = Integer.parseInt(num) + 5;
        Integer a = 9;

        System.out.println(num + total + a);

        ArrayList<Integer> ages = new ArrayList<Integer>();

        ages.add(12);
        System.out.println(ages);

        System.out.println(ages.get(0));
        in.close();
        
    }
}