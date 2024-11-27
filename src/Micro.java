import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Micro {
    public static void main(String[] args) {
        System.out.println(calculate(solution()));
    }

    private static int calculate(String S) {
        int length = S.length();

        if (length > 100) {
            return 0;
        }

        int[] count = new int[100];
        for (int i = 0; i < length - 1; i++) {
            int num = Integer.parseInt(S.substring(i, i + 2));
            count[num] = 1;
        }

        return Arrays.stream(count).sum();
    }

    /*public static String solution() {
        // Implement your solution here

        StringBuilder sb = new StringBuilder();
        int i=0, j=1;
        while (sb.length() < 99) {
            sb.append(i++ * j);
            if (i == 10) j++;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }*/

    public static String solution() {

        StringBuilder sb = new StringBuilder();
        Set<String> pairExist = new HashSet<>();
        sb.append("00");
        int i=1, j=1, num=0, last=0;
        while (sb.length() < 99) {
            num = (i/10) * j;
            String pair = num + "" + last;
            pair = pair.substring(pair.length()-2, pair.length());
            if (sb.length() > 1 && pairExist.add(pair)) {
                sb.append(pair);
            }
            if (i >= 10) {
                j++;
                i = i%10;
            }
            last = num;
            i++;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
