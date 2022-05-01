package bruteforce;

import java.util.*;

public class BOJ1436 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<Integer> titles = new ArrayList<>();
        int i = 0;
        int answer = 0;

        while (true) {
            i++;

            if (String.valueOf(i).contains("666")) titles.add(i);

            if (titles.size() == n) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
