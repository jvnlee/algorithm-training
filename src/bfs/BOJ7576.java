package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ7576 {

    public static int m, n;
    public static int[][] box;
    public static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우 1칸 이동
    public static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우 1칸 이동
    public static Queue<Tomato> queue;

    public static class Tomato {
        int x;
        int y;

        public Tomato(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs() {
        // 토마토 익히기 작업
        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();
            int x = tomato.x;
            int y = tomato.y;

            // 익은 토마토의 상하좌우 순회
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 상하좌우 중에서 선택된 방향이 인덱스 제한을 넘어가지 않는 유효한 박스칸이라면
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    // 해당 칸에 있는 토마토가 안 익은 토마토라면
                    if(box[nx][ny] == 0) {
                        // 익은 토마토가 가졌던 숫자 + 1로 대체 (익히느라 하루 소요된 것)
                        box[nx][ny] = box[x][y] + 1;
                        // 이제 이 토마토도 익은 것이므로 큐에 넣어 또 전파시키게 함
                        queue.offer(new Tomato(nx, ny));
                    }
                }
            }
        }

        int answer = 0;

        // 점검을 위해 박스 전체 순회
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) return -1; // 안 익은 토마토가 하나라도 발견되면 -1 반환
                answer = Math.max(answer, box[i][j]); // 익은 토마토라면 익은 토마토가 가진 날짜 수와 현재 날짜 수 중에서 큰 수 선택
            }
        }

        if (answer == 1) return 0; // 처음부터 다 익어있었다면 익은 토마토들이 가진 1 값이 answer에 찍히므로 0 반환
        else return (answer - 1); // 점점 익혀갔다면 answer에서 최초 큐에 넣은 토마토가 가졌던 1 값을 빼고 반환
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt(); // 가로줄 개수
        n = scanner.nextInt(); // 세로줄 개수
        scanner.nextLine(); // 버퍼 비우기

        box = new int[n][m];
        queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                box[i][j] = Integer.parseInt(input[j]);
                // 해당 칸의 토마토가 익은 토마토라면 큐에 넣음 (주변 토마토를 익힐 수 있는 토마토이므로)
                if (box[i][j] == 1) queue.offer(new Tomato(i, j));
            }
        }

        System.out.println(bfs());
    }
}
