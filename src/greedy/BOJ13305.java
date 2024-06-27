package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] distances = new long[n - 1];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        long[] prices = new long[n - 1];

        for (int i = 0; i < prices.length; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        long total = distances[0] * prices[0];
        long currentPrice = prices[0];

        for (int i = 1; i < n - 1; i++) {
            if (prices[i] < currentPrice) {
                currentPrice = prices[i];
            }
            total += distances[i] * currentPrice;
        }

        System.out.println(total);
    }
}
