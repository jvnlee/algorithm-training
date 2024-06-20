package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();

        int[][] dp = new int[s2.length + 1][s1.length + 1];

        for (int i = 1; i <= s2.length; i++) {
            for (int j = 1; j <= s1.length; j++) {
                if (s2[i - 1] == s1[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[s2.length][s1.length]);
    }

    /*
    - A C A Y K P
    C 0 1 1 1 1 1
    A 1 1 2 2 2 2
    P 1 1 2 2 2 3
    C 1 2 2 2 2 3
    A 1 2 3 3 3 3
    K 1 2 3 3 4 4
    */
}
