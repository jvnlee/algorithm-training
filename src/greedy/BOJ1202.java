package greedy;

import java.util.*;

public class BOJ1202 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Gem[] gems = new Gem[n];
        int[] bags = new int[k];

        for (int i = 0; i < n; i++) {
            gems[i] = new Gem(scanner.nextInt(), scanner.nextInt());
        }

        for (int i = 0; i < k; i++) {
            bags[i] = scanner.nextInt();
        }

        Arrays.sort(gems);
        Arrays.sort(bags);

        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        long answer = 0;
        int gemIndex = 0;

        for (int i = 0; i < k; i++) {
            while (gemIndex < n && gems[gemIndex].weight <= bags[i]) {
                queue.add(gems[gemIndex++].price);
            }

            if (!queue.isEmpty()) {
                answer += queue.poll();
            }
        }

        System.out.println(answer);
    }

    public static class Gem implements Comparable<Gem> {
        int weight;
        int price;

        public Gem(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Gem g) {
            return this.weight - g.weight;
        }
    }
}
