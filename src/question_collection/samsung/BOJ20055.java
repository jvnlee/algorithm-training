package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055 {
    private static int N, K;
    private static int[] durability;
    private static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        durability = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        while (true) {
            answer++;

            // 1. 벨트 회전
            rotate();

            // 2. 로봇 이동
            moveRobot();

            // 3. 로봇 올리기
            addRobot();

            // 4. 내구도 0인 칸 개수 세기
            if (checkDurability()) {
                break;
            }
        }

        System.out.println(answer);
    }

    private static void rotate() {
        // 내구도 회전
        int last = durability[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            durability[i] = durability[i - 1];
        }
        durability[0] = last;

        // 로봇 회전
        for (int i = N - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false;

        // 내리는 위치에 로봇 있으면 제거
        robot[N - 1] = false;
    }

    private static void moveRobot() {
        for (int i = N - 2; i >= 0; i--) {
            if (robot[i] && !robot[i + 1] && durability[i + 1] > 0) {
                robot[i] = false;
                robot[i + 1] = true;
                durability[i + 1]--;
            }
        }

        // 내리는 위치에 로봇 있으면 제거
        robot[N - 1] = false;
    }

    private static void addRobot() {
        if (durability[0] > 0 && !robot[0]) {
            robot[0] = true;
            durability[0]--;
        }
    }

    private static boolean checkDurability() {
        int count = 0;

        for (int i = 0; i < 2 * N; i++) {
            if (durability[i] == 0) {
                count++;
            }
        }

        return count >= K;
    }
}
