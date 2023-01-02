package 이코테;

import java.util.*;

public class P118 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int a = sc.nextInt();
        int b = sc.nextInt();
        int d = sc.nextInt();

        int[][] map = new int[n][m]; // 주어진 지도 (육지 0, 바다 1)
        boolean[][] mark = new boolean[n][m]; // 가본 칸을 체크하기 위함
        mark[a][b] = true; // 현재 좌표 방문 처리

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 캐릭터 움직임 (북, 동, 남, 서)
        int[] da = {-1, 0, 1, 0};
        int[] db = {0, 1, 0, -1};

        int cnt = 0;
        int rotationCnt = 0;

        while (true) {
            // (d + 3) % 4 방향으로 이동시켜보고 (반시계 90도 방향)
            d = (d + 3) % 4;
            int tmpA = a + da[d];
            int tmpB = b + db[d];

            // 안 가본 칸이고, 육지라면 그 칸으로 이동
            if (map[tmpA][tmpB] == 0 && !mark[tmpA][tmpB]) {
                a = tmpA;
                b = tmpB;
                mark[a][b] = true; // 가본 칸으로 마킹
                cnt++;
                rotationCnt = 0;
                continue;
            }

            rotationCnt++;

            // 4가지 방향 모두 바다 혹은 가본 칸이라면
            if (rotationCnt == 4) {
                // 반대 방향으로 1칸 이동시켜보고
                tmpA = a + da[(d + 2) % 4];
                tmpB = b + da[(d + 2) % 4];

                // 육지라면 이동, 바다라면 break
                if (map[tmpA][tmpB] == 0) {
                    a = tmpA;
                    b = tmpB;
                    cnt++;
                } else {
                    break;
                }

                rotationCnt = 0;
            }
        }

        System.out.println(cnt);
    }
}

/*
변수들을 입력 받을 때, 현재 좌표를 방문처리하는 것을 깜빡해서 오답이 나왔었다. 잊지 말고 꼼꼼하게 체크하자

3번 조건인 "4방향이 모두 이미 가본 칸이거나 바다로 되어있는 칸인 경우"를 간단하게 조건화 할 수 있는 것이 rotationCnt.
처음에 이 부분을 조건식으로 표현하려고 하다가 막혔다. 결국 해당 조건대로라면 아무데도 이동하지 못하고 4번 넘게 회전을 시행했을 것이므로
회전을 시행한 횟수를 측정해서 4를 초과했는지 체크하면 된다.
 */
