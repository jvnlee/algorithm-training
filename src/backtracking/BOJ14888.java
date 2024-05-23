package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {
    public static int[] numbers;
    public static char[] operators, orderedOperators;
    public static boolean[] isUsed;
    public static int min = Integer.MAX_VALUE;
    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        operators = new char[n - 1];
        st = new StringTokenizer(br.readLine());

        int add = Integer.parseInt(st.nextToken());
        for (int i = 0; i < add; i++) {
            operators[i] = '+';
        }

        int subtract = Integer.parseInt(st.nextToken());
        for (int i = add; i < add + subtract; i++) {
            operators[i] = '-';
        }

        int multiply = Integer.parseInt(st.nextToken());
        for (int i = add + subtract; i < add + subtract + multiply; i++) {
            operators[i] = '*';
        }

        int divide = Integer.parseInt(st.nextToken());
        for (int i = add + subtract + multiply; i < add + subtract + multiply + divide; i++) {
            operators[i] = '/';
        }

        isUsed = new boolean[operators.length];
        orderedOperators = new char[operators.length];

        orderOperators(0);

        System.out.println(max + "\n" + min);
    }

    private static void orderOperators(int depth) {
        if (depth == operators.length) {
            int result = calculate();
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < isUsed.length; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                orderedOperators[depth] = operators[i];
                orderOperators(depth + 1);
                isUsed[i] = false;
            }
        }
    }

    private static int calculate() {
        int result = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            switch (orderedOperators[i - 1]) {
                case '+':
                    result += numbers[i];
                    break;
                case '-':
                    result -= numbers[i];
                    break;
                case '*':
                    result *= numbers[i];
                    break;
                case '/':
                    result /= numbers[i];
                    break;
            }
        }

        return result;
    }
}
