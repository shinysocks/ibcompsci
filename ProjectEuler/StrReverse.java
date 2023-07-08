package ProjectEuler;
import java.util.Arrays;

public class StrReverse {
    public static void main(String[] args) {
        System.out.println(reverseString("hello hi steve a goodbye bob"));
    }

    public static String reverseString(String str) {
        String[] newStr = str.split(" ");
        String[] endStr = new String[newStr.length];
        int a = 0;
        for (int i = newStr.length - 1; i >= 0; i--) {
            System.out.println(i);
            endStr[i] = newStr[a];
            a++;
        }

        return Arrays.toString(endStr);
    }
}
