package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11729 {
    public static StringBuilder moves = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        moves.append((int) (Math.pow(2, n) - 1)).append("\n");

        move(n, 1, 2, 3);

        System.out.println(moves);
    }

    private static void move(int n, int from, int tmp, int to) {
        if (n == 1) {
            moves.append(from).append(" ").append(to).append("\n");
            return;
        }

        move(n - 1, from, to, tmp);

        moves.append(from).append(" ").append(to).append("\n");

        move(n - 1, tmp, from, to);
    }
}
