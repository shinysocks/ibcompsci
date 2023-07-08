package ProjectEuler;

public class Triangle {
    public static void main(String[] args) {
        printTriangleType(2, 1, 2);
    }

    public static void printTriangleType(int s1, int s2, int s3) {
        if (s1 == s2 && s2 == s3 && s3 == s1) {
            System.out.println("Equilateral.");
        } else if (s1 == s2 || s2 == s3 || s3 == s1) {
            System.out.println("Isoceles.");
        } else {
            System.out.println("Scalene.");
        }
    }
}
