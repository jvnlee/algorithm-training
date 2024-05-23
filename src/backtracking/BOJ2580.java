package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2580 {
    public static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 초기화
        for (int i = 0; i < 9; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solve(0, 0); // depth가 row와 col 2개
    }

    private static void solve(int row, int col) {
        // 특정 행에서 마지막 열까지 채운 경우
        if (col == 9) {
            solve(row + 1, 0); // 다음 행으로 이동
            return;
        }

        // 마지막 행까지 채운 경우 = 유효한 스도쿠 보드 완성
        if (row == 9) {
            StringBuilder solution = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                solution.append(
                    Arrays.toString(board[i]).replaceAll("[\\[\\],]", "")
                ).append("\n");
            }

            // 첫번째로 발견한 해를 출력하고 즉시 종료
            // 종료하지 않고 return문을 사용하면 추가적인 해를 찾아나서는데, 문제에서는 해를 하나만 출력하라고 했으므로 더 찾는 과정은 불필요함
            System.out.println(solution);
            System.exit(0);
        }

        // 현재 칸이 빈 칸이면 채워보기
        if (board[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (isValidFill(row, col, i)) {
                    board[row][col] = i; // 현재 칸을 유효한 숫자로 채우고
                    solve(row, col + 1); // 빈 칸을 찾아 다음 열로 이동
                    board[row][col] = 0; // 현재 칸을 채우고 뻗어나간 분기에서 해를 얻지 못했다면, 빈 칸으로 되돌려서 백트래킹이 가능하도록 함
                }
            }
            return; // 해를 찾는데에 실패했으므로 해당 분기는 종료
        }

        solve(row, col + 1); // 현재 칸이 빈 칸이 아니라면 다음 열로 이동
    }

    private static boolean isValidFill(int row, int col, int k) {
        // row 체크
        if (Arrays.toString(board[row]).contains(String.valueOf(k))) {
            return false;
        }

        // column 체크
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == k) {
                return false;
            }
        }

        // box 체크
        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
            for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
                if (board[i][j] == k) {
                    return false;
                }
            }
        }

        return true;
    }
}
