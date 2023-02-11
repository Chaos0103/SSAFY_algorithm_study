import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.next();
        String[] minus = line.split("-");

        int result = 0;
        for (int i = 0; i < minus.length; i++) {
            String[] plus = minus[i].split("\\+");
            int sum = 0;
            for (String num : plus) {
                sum += Integer.parseInt(num);
            }
            if (i == 0) {
                result = sum;
            } else {
                result -= sum;
            }
        }

        System.out.println(result);
    }
}
