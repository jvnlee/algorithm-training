package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 추의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> possibleWeights = new HashSet<>();
        possibleWeights.add(0);

        for (int weight : weights) {
            Set<Integer> newPossibleWeights = new HashSet<>(possibleWeights);

            for (int pw : possibleWeights) {
                newPossibleWeights.add(pw + weight);
                newPossibleWeights.add(Math.abs(pw - weight));
            }

            possibleWeights = newPossibleWeights;
        }

        int k = Integer.parseInt(br.readLine()); // 구슬의 개수

        st = new StringTokenizer(br.readLine());

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < k; i++) {
            int marble = Integer.parseInt(st.nextToken());
            if (possibleWeights.contains(marble)) {
                answer.append("Y").append(" ");
            } else {
                answer.append("N").append(" ");
            }
        }

        System.out.println(answer);
    }
}
