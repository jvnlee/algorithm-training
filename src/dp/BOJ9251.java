package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();

        int[][] dp = new int[s1.length + 1][s2.length + 1];

        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[s1.length][s2.length]);
    }

    /*
    ACAYKP
    CAPCAK

    두 문자열을 앞에서부터 한글자씩 길이를 늘려가면,
    LCS 길이: {0, 1, 2, 2, 3, 4}

    만약 두 문자열의 길이가 다르면...?

    A, C -> 0
    A, CA -> 1
    A, CAP -> 1
    A, CAPC -> 1
    A, CAPCA -> 1
    A, CAPACAK -> 1

    AC, C -> 1
    AC, CA -> 1
    AC, CAP -> 1
    AC, CAPC -> 2
    AC, CAPCA -> 2
    AC, CAPCAK -> 2

    ACA, C -> 1
    ACA, CA -> 2
    ACA, CAP -> 2
    ACA, CAPC -> 2
    ACA, CAPCA -> 3
    ACA, CAPCAK -> 3

    ...

    이중 배열의 원소 = 해당 인덱스까지의 문자를 가진 두 누적 문자열의 LCS 길이

    - A C A Y K P
    C 0 1 1 1 1 1
    A 1 1 2 2 2 2
    P 1 1 2 2 2 3
    C 1 2 2 2 2 3
    A 1 2 3 3 3 3
    K 1 2 3 3 4 4
     */
}
