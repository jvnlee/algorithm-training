package programmers.high_score_kit.dp.retries;

public class PRG1843_2 {
    public int solution(String arr[]) {
        int arrLen = arr.length;

        int[] nums = new int[arrLen / 2 + 1];
        int[] signs = new int[arrLen / 2];

        int numsIdx = 0;
        int signsIdx = 0;

        for (int i = 0; i < arrLen; i++) {
            if (i % 2 == 0) {
                nums[numsIdx++] = Integer.parseInt(arr[i]);
            } else {
                signs[signsIdx++] = arr[i].equals("+") ? 1 : -1;
            }
        }

        int numsLen = nums.length;

        int[][] minDP = new int[numsLen][numsLen];
        int[][] maxDP = new int[numsLen][numsLen];

        for (int i = 0; i < numsLen; i++) {
            minDP[i][i] = nums[i];
            maxDP[i][i] = nums[i];
        }

        for (int size = 2; size <= numsLen; size++) {
            for (int start = 0; start <= numsLen - size; start++) {
                int end = start + size - 1;

                minDP[start][end] = Integer.MAX_VALUE;
                maxDP[start][end] = Integer.MIN_VALUE;

                for (int mid = start; mid < end; mid++) {
                    int r1 = minDP[start][mid] + signs[mid] * minDP[mid + 1][end];
                    int r2 = minDP[start][mid] + signs[mid] * maxDP[mid + 1][end];
                    int r3 = maxDP[start][mid] + signs[mid] * minDP[mid + 1][end];
                    int r4 = maxDP[start][mid] + signs[mid] * maxDP[mid + 1][end];

                    minDP[start][end] = Math.min(minDP[start][end], Math.min(r1, Math.min(r2, Math.min(r3, r4))));
                    maxDP[start][end] = Math.max(maxDP[start][end], Math.max(r1, Math.max(r2, Math.max(r3, r4))));
                }
            }
        }

        return maxDP[0][numsLen - 1];
    }
}
