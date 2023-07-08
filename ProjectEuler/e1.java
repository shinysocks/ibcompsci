package ProjectEuler;

public class e1 {
    static long num;

    public static void main(String[] args) {
      System.out.println(getMultiples(10));
    }

    static long getMultiples(long max) {
        max -= 1;
        return (((((max / 3)) * (1 + (max / 3)) * 3) + (((max / 5) ) * (1 + (max / 5)) * 5)) - (((max / 15) ) * (1 + (max / 15)) * 15))/2;
    }
}
