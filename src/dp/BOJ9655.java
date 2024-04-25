package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        /*
        1개일 때는 SK
        2개일 때는 CY
        3개일 때는 SK
        ...
        케이스 1부터 순서대로 생각해보면
        결국 현재 케이스(n)는 직전 케이스(n - 1)의 승자가 이길 수 없다는 것을 알 수 있음
        직전 케이스에서 돌 1개가 추가된 것이므로 마지막 돌을 무조건 직전 승자가 아닌 반대 사람이 가져갈 수 있게 됨
        따라서 결국 n이 홀수냐 짝수냐에 따라 승자가 정해지는 것을 알 수 있음
         */

        if (n % 2 == 0) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}
