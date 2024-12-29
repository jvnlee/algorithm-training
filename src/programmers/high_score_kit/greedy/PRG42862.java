package programmers.high_score_kit.greedy;

public class PRG42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] status = new int[n + 2];

        for (int l : lost) {
            status[l]--;
        }

        for (int r : reserve) {
            status[r]++;
        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (status[i] == -1) {
                if (status[i - 1] == 1) {
                    status[i]++;
                    status[i - 1]--;
                } else if (status[i + 1] == 1) {
                    status[i]++;
                    status[i + 1]--;
                } else {
                    count++;
                }
            }
        }

        return n - count;
    }
}
