package programmers.high_score_kit.dp;

public class PRG42897 {
    public int solution(int[] money) {
        int answer = 0;
        int len = money.length;

        int[] dp = new int[len]; // 첫번째 집을 포함하는 경우
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);

        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }

        answer = Math.max(answer, dp[len - 2]);

        dp = new int[len]; // 첫번째 집을 포함하지 않는 경우
        dp[1] = money[1];

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }

        answer = Math.max(answer, dp[len - 1]);

        return answer;
    }
}