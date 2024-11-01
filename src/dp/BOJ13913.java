package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ13913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            bw.write(0 + "\n" + N);
        } else {
            int[] time = new int[100_001]; // 각 위치에 도달하는 데 걸리는 최소 시간 저장
            time[N] = 1;

            int[] prev = new int[100_001]; // 어디서 왔는지 직전 위치 정보 저장

            Queue<Integer> q = new LinkedList<>();
            q.offer(N);

            int minTime = 0;

            while (!q.isEmpty()) {
                int curPos = q.poll();

                if (curPos == K) {
                    minTime = time[curPos] - 1;
                    break;
                }

                if (curPos - 1 >= 0 && time[curPos - 1] == 0) {
                    time[curPos - 1] = time[curPos] + 1;
                    prev[curPos - 1] = curPos;
                    q.offer(curPos - 1);
                }

                if (curPos + 1 <= 100_000 && time[curPos + 1] == 0) {
                    time[curPos + 1] = time[curPos] + 1;
                    prev[curPos + 1] = curPos;
                    q.offer(curPos + 1);
                }

                if (curPos * 2 <= 100_000 && time[curPos * 2] == 0) {
                    time[curPos * 2] = time[curPos] + 1;
                    prev[curPos * 2] = curPos;
                    q.offer(curPos * 2);
                }
            }

            bw.write(minTime + "\n");

            Stack<Integer> step = new Stack<>();
            step.push(K);

            int pos = K;

            while (pos != N) {
                step.push(prev[pos]);
                pos = prev[pos];
            }

            while (!step.isEmpty()) {
                bw.write(step.pop() + " ");
            }
        }

        bw.flush();
        bw.close();
    }
}
