package actual_test;

public class Naver250322_Q1 {
    public int[] solution(int[] cylinder, int a) {
        int len = cylinder.length;
        int n = 0; // 분모 (첫 발이 0인 경우)
        int m = 0; // 분자 (첫 발 이후 a번 연속 격발했는데도 총에 맞지 않는 경우)

        for (int i = 0; i < len; i++) {
            if (cylinder[i] == 0) {
                n++;

                boolean isShot = false;

                for (int j = 1; j <= a; j++) {
                    if (cylinder[(i + j) % len] == 1) {
                        isShot = true;
                        break;
                    }
                }

                if (!isShot) {
                    m++;
                }
            }
        }

        if (m == 0) {
            return new int[]{0, 1};
        }

        if (m == n) {
            return new int[]{1, 1};
        }

        int gcd = gcd(m, n);

        return new int[]{m / gcd, n / gcd};
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}

/*
문제 구현 자체는 괜찮았는데 뜬금없게 기약 분수 만드는 로직이 떠오르지 않았음
다소 비효율적이더라도 결국 구현하긴 했지만, 유클리드 호제법을 이용하면 빠르다는 것을 끝나고서야 알게 됨
 */