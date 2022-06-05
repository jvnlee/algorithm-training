package string;

import java.util.*;

public class BOJ5052 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            scanner.nextLine();

            String[] contacts = new String[n];

            for (int j = 0; j < n; j++) {
                contacts[j] = scanner.nextLine();
            }

            Arrays.sort(contacts);

            sb.append(isConsistent(contacts)).append('\n');
        }

        System.out.println(sb);
    }

    public static String isConsistent(String[] contacts) {
        for (int i = 0; i < contacts.length - 1; i++) {
            if (contacts[i + 1].startsWith(contacts[i])) return "NO";
        }
        return "YES";
    }
}
