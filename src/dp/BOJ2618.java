package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2618 {
    private static int N, W;
    private static int[][] dp, events;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        // dp[i][j]: 1번 경찰차가 i번째 사건을 해결, 2번 경찰차가 j번째 사건을 해결했을 때 지금까지 두 경찰차가 이동한 총 거리
        dp = new int[W + 1][W + 1];
        // events[i]: i번째 사건. 위치: (events[i][0], events[i][1])
        events = new int[W + 1][2];

        StringTokenizer st;

        // 첫번째 사건부터 W번째 사건까지 events에 위치 기록
        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());

            events[i][0] = Integer.parseInt(st.nextToken());
            events[i][1] = Integer.parseInt(st.nextToken());
        }

        bw.write(getMinDistance(1, 0, 0) + "\n");

        int p1sEventIdx = 0;
        int p2sEventIdx = 0;

        for (int i = 1; i <= W; i++) {
            int distance = getDistance(1, p1sEventIdx, i);

            if (dp[p1sEventIdx][p2sEventIdx] - distance == dp[i][p2sEventIdx]) {
                p1sEventIdx = i;
                bw.write("1\n");
            } else {
                p2sEventIdx = i;
                bw.write("2\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static int getMinDistance(int curEventIdx, int p1sEventIdx, int p2sEventIdx) {
        if (curEventIdx > W) {
            return 0; // 마지막 사건까지 도달했다면 더 이동할 필요 없음
        }

        if (dp[p1sEventIdx][p2sEventIdx] != 0) {
            return dp[p1sEventIdx][p2sEventIdx]; // 이미 계산된 경우이므로 중복 계산하지 않고 즉시 값을 반환
        }

        int moveP1 = getMinDistance(curEventIdx + 1, curEventIdx, p2sEventIdx) + getDistance(1, p1sEventIdx, curEventIdx);
        int moveP2 = getMinDistance(curEventIdx + 1, p1sEventIdx, curEventIdx) + getDistance(2, p2sEventIdx, curEventIdx);

        return dp[p1sEventIdx][p2sEventIdx] = Math.min(moveP1, moveP2);

    }

    private static int getDistance(int policeNum, int start, int end) {
        // start 사건의 발생 위치부터 end 사건의 발생 위치까지 이동 거리 계산
        int[] startPos = events[start];
        int[] endPos = events[end];

        if (start == 0) { // 경찰차 시작 위치 설정 (0번째 사건)
            if (policeNum == 1) {
                startPos = new int[]{1, 1}; // 1번 경찰차는 (1, 1)에서 시작
            } else {
                startPos = new int[]{N, N}; // 2번 경찰차는 (N, N)에서 시작
            }
        }

        return Math.abs(startPos[0] - endPos[0]) + Math.abs(startPos[1] - endPos[1]);
    }
}
