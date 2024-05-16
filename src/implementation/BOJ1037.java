package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < a; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num > max) {
                max = num;
            }

            if (num < min) {
                min = num;
            }
        }

        System.out.println(max * min);
    }
}