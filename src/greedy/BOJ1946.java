package greedy;

import java.util.*;

public class BOJ1946 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();

            int[] grade = new int[n + 1];

            for (int j = 0; j < n; j++) {
                int paperGrade = scanner.nextInt();
                int interviewGrade = scanner.nextInt();

                grade[paperGrade] = interviewGrade;
            }

            int count = 1;
            int lastQualifierGrade = grade[1];

            for (int j = 2; j <= n; j++) {
                if (grade[j] < lastQualifierGrade) {
                    count++;
                    lastQualifierGrade = grade[j];
                }
            }

            sb.append(count).append('\n');
        }

        System.out.println(sb);
    }
}
