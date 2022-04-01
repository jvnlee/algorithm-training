package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697 {

    public static int n, k;
    public static int second = 0; // 동생을 찾는 최소 시간
    public static int[] road = new int[100_001];

    public static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        road[x] = 1; // 0초지만 현 위치에 1로 표시 (나중에 최종 결과값에서 1 빼면 됨)
        while (!queue.isEmpty()) {
            int curPos = queue.poll(); // 현 위치

            // 현 위치가 동생 위치와 같은 경우
            if (curPos == k) {
                second = road[curPos] - 1; // 현 위치에 마킹된 시간 - 1 을 저장
                break;
            }

            // 뒤로 1칸 걸어서 도착할 수 있는 곳이 방문하지 않은 곳인 경우
            if (curPos - 1 >= 0 && road[curPos - 1] == 0) {
                road[curPos - 1] = road[curPos] + 1; // 원래 위치가 가진 숫자 + 1 저장
                queue.offer(curPos - 1); // 새로 온 위치를 큐에 넣음
            }

            // 앞으로 1칸 걸어서 도착할 수 있는 곳이 방문하지 않은 곳인 경우
            if (curPos + 1 <= 100_000 && road[curPos + 1] == 0) {
                road[curPos + 1] = road[curPos] + 1;
                queue.offer(curPos + 1);
            }

            // 현 위치 x2로 순간이동해서 도착할 수 있는 곳이 방문하지 않은 곳인 경우
            if (curPos * 2 <= 100_000 && road[curPos * 2] == 0) {
                road[curPos * 2] = road[curPos] + 1;
                queue.offer(curPos * 2);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 수빈의 위치
        k = scanner.nextInt(); // 동생의 위치

        bfs(n);
        System.out.println(second);
    }
}
