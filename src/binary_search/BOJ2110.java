package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] houses = new int[n];

        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int min = 1;
        int max = houses[n - 1] - houses[0];

        while (min <= max) {
            int mid = (min + max) / 2;
            int count = 1;
            int lastPlaced = houses[0];

            for (int i = 1; i < n; i++) {
                if (houses[i] - lastPlaced >= mid) {
                    count++;
                    lastPlaced = houses[i];
                }
            }

            if (count < c) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(max);
    }
}
