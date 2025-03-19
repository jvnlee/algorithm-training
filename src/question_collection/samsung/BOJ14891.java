package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] wheels = new String[4];

        for (int i = 0; i < 4; i++) {
            wheels[i] = br.readLine();
        }

        int K = Integer.parseInt(br.readLine());
        int[][] rotations = new int[K][2];

        StringTokenizer st;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            rotations[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotations[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int[] r : rotations) {
            int wheelNum = r[0];
            int direction = r[1];

            int[] rotateDirs = new int[4];
            rotateDirs[wheelNum] = direction;

            for (int i = wheelNum; i < 3; i++) {
                if (wheels[i].charAt(2) != wheels[i + 1].charAt(6)) {
                    rotateDirs[i + 1] = rotateDirs[i] * -1;
                } else {
                    break;
                }
            }

            for (int i = wheelNum; i > 0; i--) {
                if (wheels[i].charAt(6) != wheels[i - 1].charAt(2)) {
                    rotateDirs[i - 1] = rotateDirs[i] * -1;
                } else {
                    break;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (rotateDirs[i] != 0) {
                    wheels[i] = rotate(wheels[i], rotateDirs[i]);
                }
            }
        }
        int answer = 0;

        for (int i = 0; i < 4; i++) {
            if (wheels[i].charAt(0) == '1') {
                answer += (int) Math.pow(2, i);
            }
        }

        System.out.println(answer);
    }

    private static String rotate(String wheel, int direction) {
        return direction == 1
                ? wheel.charAt(7) + wheel.substring(0, 7)
                : wheel.substring(1) + wheel.charAt(0);
    }
}
