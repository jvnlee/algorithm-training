package programmers;

public class PRG92342 {
    public int maxArrow;
    public int[] apeachInfo;
    public int[] maxRyanInfo;
    public int maxScoreGap;

    public int[] solution(int n, int[] info) {
        maxArrow = n;
        apeachInfo = info;
        dfs(0, new int[11], -1);
        return maxScoreGap > 0 ? maxRyanInfo : new int[]{-1};
    }

    public void dfs(int arrow, int[] ryanInfo, int idx) {
        if (arrow == maxArrow) {
            int ryanScore = 0;
            int apeachScore = 0;

            for (int i = 0; i < 11; i++) {
                if (ryanInfo[i] > apeachInfo[i]) ryanScore += 10 - i;
                else if (apeachInfo[i] != 0) apeachScore += 10 - i;
            }

            int scoreGap = ryanScore - apeachScore;

            if (scoreGap > maxScoreGap) {
                maxScoreGap = scoreGap;
                maxRyanInfo = ryanInfo;
            } else if (maxScoreGap > 0 && scoreGap == maxScoreGap) {
                for (int i = 10; i >= 0; i--) {
                    if (ryanInfo[i] > maxRyanInfo[i]) {
                        maxRyanInfo = ryanInfo;
                    } else if (maxRyanInfo[i] > ryanInfo[i]) {
                        return;
                    }
                }
            }

            return;
        }

        for (int i = idx + 1; i < 11; i++) {
            int[] nextRyanInfo = new int[11];
            System.arraycopy(ryanInfo, 0, nextRyanInfo, 0, 11);

            if (i == 10) {
                nextRyanInfo[i] += maxArrow - arrow;
                dfs(maxArrow, nextRyanInfo, i);
            } else if (maxArrow - arrow > apeachInfo[i]) {
                nextRyanInfo[i] += apeachInfo[i] + 1;
                dfs(arrow + apeachInfo[i] + 1, nextRyanInfo, i);
            }
        }
    }
}
