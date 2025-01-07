package programmers.high_score_kit.dp;

public class PRG1843 {
    public int solution(String arr[]) {
        int len = arr.length;

        int[] nums = new int[len / 2 + 1];
        int[] signs = new int[len / 2];

        int numsIdx = 0;
        int signsIdx = 0;

        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                nums[numsIdx++] = Integer.parseInt(arr[i]);
            } else {
                signs[signsIdx++] = arr[i].equals("+") ? 1 : -1;
            }
        }

        int n = nums.length;

        int[][] maxDP = new int[n][n];
        int[][] minDP = new int[n][n];

        for (int i = 0; i < n; i++) {
            maxDP[i][i] = nums[i];
            minDP[i][i] = nums[i];
        }

        for (int size = 2; size <= n; size++) {
            for (int start = 0; start < n - size + 1; start++) {
                int end = start + size - 1;

                maxDP[start][end] = Integer.MIN_VALUE;
                minDP[start][end] = Integer.MAX_VALUE;

                for (int mid = start; mid < end; mid++) {
                    int a = maxDP[start][mid] + signs[mid] * maxDP[mid + 1][end];
                    int b = maxDP[start][mid] + signs[mid] * minDP[mid + 1][end];
                    int c = minDP[start][mid] + signs[mid] * maxDP[mid + 1][end];
                    int d = minDP[start][mid] + signs[mid] * minDP[mid + 1][end];

                    maxDP[start][end] = Math.max(maxDP[start][end], Math.max(a, Math.max(b, Math.max(c, d))));
                    minDP[start][end] = Math.min(minDP[start][end], Math.min(a, Math.min(b, Math.min(c, d))));
                }
            }
        }

        return maxDP[0][n - 1];
    }
}
