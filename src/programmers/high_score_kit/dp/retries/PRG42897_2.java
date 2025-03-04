package programmers.high_score_kit.dp.retries;

public class PRG42897_2 {
    public int solution(int[] money) {
        int answer = 0;
        int len = money.length;

        int[] dp = new int[len];

        // 첫번째 집을 터는 경우
        dp[0] = money[0]; // 집1 O
        dp[1] = money[0]; // 집2 X

        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 1], money[i] + dp[i - 2]);
        }

        answer = dp[len - 2];

        dp = new int[len];

        // 첫번째 집을 털지 않는 경우
        dp[0] = 0;        // 집1 X
        dp[1] = money[1]; // 집2 O

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], money[i] + dp[i - 2]);
        }

        answer = Math.max(answer, dp[len - 1]);

        return answer;
    }
}