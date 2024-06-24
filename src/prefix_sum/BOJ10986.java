package prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] modSums = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            modSums[i] = (modSums[i - 1] + Integer.parseInt(st.nextToken())) % m;
        }

        int[] remainderCounts = new int[m];

        for (int i = 1; i < modSums.length; i++) {
            remainderCounts[modSums[i]]++;
        }

        // 나머지가 0인 누적합은 정답 개수에 미리 포함시킴.
        // (a1 + ... + ai) mod m = 0 인 조합 (1, i)는 조건 만족
        long answer = remainderCounts[0];

        // 나머지가 같은 누적합 2개를 짝짓는 경우의 수를 구함 (그래야 둘의 차를 구해서 얻은 부분 누적합의 나머지가 0이 됨)
        // i <= j 일때, (a1 + ... + ai-1) mod m = k, (a1 + ... + aj) mod m = k 라면 (ai + ... + aj) mod m = 0 이므로 조합 (i, j)는 조건 만족
        for (int remainderCount : remainderCounts) {
            if (remainderCount > 1) {
                answer += (long) remainderCount * (remainderCount - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}
