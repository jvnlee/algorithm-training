package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String code = br.readLine();
        int len = code.length();
        int MOD = 1_000_000;

        if (code.startsWith("0")) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[len + 1]; // dp[i]: 첫번재부터 i번째 글자(1번부터 셈)까지 자른 code의 가능한 해석의 수

        dp[0] = 1; // 공백 문자열 해독: 없음 (1가지)
        dp[1] = 1; // 첫 글자 해독: 첫 글자 값 자체 (1가지)

        for (int i = 2; i <= len; i++) {
            // 현재 숫자를 한자리수로 인식했을 때
            int singleDigit = code.charAt(i - 1) - '0';

            if (singleDigit != 0) {
                dp[i] += dp[i - 1];
                dp[i] %= MOD;
            }

            // 현재 숫자를 직전 숫자와 합해서 두자리수로 인식했을 때
            int doubleDigit = Integer.parseInt(code.substring(i - 2, i));

            if (doubleDigit >= 10 && doubleDigit <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= MOD;
            }
        }

        System.out.println(dp[len]);
    }
}
