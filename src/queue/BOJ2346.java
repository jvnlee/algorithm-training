package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());

        if (n == 1) {
            System.out.println(1);
            return;
        }

        Deque<int[]> balloons = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            balloons.add(new int[]{i, parseInt(st.nextToken())});
        }

        int[] currentBalloon = balloons.pollFirst(); // 1번 풍선
        int move = currentBalloon[1];

        StringBuilder sb = new StringBuilder();
        sb.append(1).append(" ");

        while (!balloons.isEmpty()) {
            if (balloons.size() == 1) {
                sb.append(balloons.pollFirst()[0]);
                break;
            }

            if (move > 0) {
                while (move > 1) {
                    balloons.offerLast(balloons.pollFirst());
                    move--;
                }
                currentBalloon = balloons.pollFirst();
            } else {
                move = Math.abs(move);
                while (move > 1) {
                    balloons.offerFirst(balloons.pollLast());
                    move--;
                }
                currentBalloon = balloons.pollLast();
            }

            sb.append(currentBalloon[0]).append(" ");
            move = currentBalloon[1];
        }

        System.out.println(sb);
    }
}
